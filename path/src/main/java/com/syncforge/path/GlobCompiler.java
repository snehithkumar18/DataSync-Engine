package com.syncforge.path;

import com.syncforge.core.diagnostics.SourceSpan;
import com.syncforge.core.exceptions.ParseException;
import com.syncforge.core.exceptions.ValidationException;
import com.syncforge.core.logging.SyncForgeLogger;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Compiles a glob pattern into a minimized Deterministic Finite Automaton (DFA).
 * The compilation pipeline consists of:
 * <ol>
 *   <li>Parsing the glob string into an Abstract Syntax Tree (AST).</li>
 *   <li>Building an NFA using Thompson's construction algorithm.</li>
 *   <li>Converting the NFA to a DFA using the subset construction algorithm over a partitioned alphabet.</li>
 *   <li>Minimizing the DFA using Moore's partition refinement algorithm.</li>
 * </ol>
 */
public class GlobCompiler {

    private static final SyncForgeLogger logger = new SyncForgeLogger(GlobCompiler.class);
    
    private static final AtomicInteger compileCount = new AtomicInteger(0);
    private static final ConcurrentHashMap<Integer, WeakReference<List<DfaState>>> stateCache = new ConcurrentHashMap<>();
    private static final AtomicInteger cacheSize = new AtomicInteger(0);

    /**
     * Functional interface representing a single character matching condition.
     */
    @FunctionalInterface
    public interface CharMatcher {
        /**
         * Checks if the character matches the condition.
         *
         * @param c the character to check.
         * @return true if matches, false otherwise.
         */
        boolean matches(char c);
    }

    /**
     * Represents a range interval used in character class matching.
     */
    public record RangeInterval(char start, char end) {
        public RangeInterval {
            if (start > end) {
                char temp = start;
                start = end;
                end = temp;
            }
        }

        public boolean contains(char c) {
            return c >= start && c <= end;
        }
    }

    /**
     * Abstract Syntax Tree (AST) nodes representing glob components.
     */
    public sealed interface GlobNode permits
        GlobNode.Sequence,
        GlobNode.Literal,
        GlobNode.WildcardChar,
        GlobNode.WildcardSegment,
        GlobNode.WildcardRecursive,
        GlobNode.Range,
        GlobNode.Group {

        record Sequence(List<GlobNode> nodes) implements GlobNode {}
        record Literal(String value) implements GlobNode {}
        record WildcardChar() implements GlobNode {}
        record WildcardSegment() implements GlobNode {}
        record WildcardRecursive() implements GlobNode {}
        record Range(List<RangeInterval> intervals, boolean negated) implements GlobNode {}
        record Group(List<GlobNode> choices) implements GlobNode {}
    }

    /**
     * Represents a transition in the Non-deterministic Finite Automaton (NFA).
     */
    public record NfaTransition(CharMatcher matcher, NfaState target) {}

    /**
     * Represents a state in the NFA.
     */
    public static class NfaState {
        private final int id;
        private final List<NfaTransition> transitions = new ArrayList<>();
        private final List<NfaState> epsilonTransitions = new ArrayList<>();

        public NfaState(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void addTransition(char target, NfaState next) {
            transitions.add(new NfaTransition(c -> c == target, next));
        }

        public void addTransition(CharMatcher matcher, NfaState next) {
            transitions.add(new NfaTransition(matcher, next));
        }

        public void addEpsilonTransition(NfaState next) {
            epsilonTransitions.add(next);
        }

        public List<NfaTransition> getTransitions() {
            return transitions;
        }

        public List<NfaState> getEpsilonTransitions() {
            return epsilonTransitions;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof NfaState other)) return false;
            return id == other.id;
        }

        @Override
        public int hashCode() {
            return id;
        }
    }

    private static class Nfa {
        public final NfaState start;
        public final NfaState accept;

        public Nfa(NfaState start, NfaState accept) {
            this.start = start;
            this.accept = accept;
        }
    }

    /**
     * Represents a state in the Deterministic Finite Automaton (DFA).
     */
    public static class DfaState {
        private final int id;
        private final Set<NfaState> nfaStates;
        private final Map<Character, DfaState> transitions = new HashMap<>();
        private DfaState defaultTransition = null;
        private final boolean isAccept;
        private Set<Character> alphabet = null;

        public DfaState(int id, Set<NfaState> nfaStates, boolean isAccept) {
            this.id = id;
            this.nfaStates = Objects.requireNonNull(nfaStates);
            this.isAccept = isAccept;
        }

        public int getId() {
            return id;
        }

        public boolean isAccept() {
            return isAccept;
        }

        public Set<NfaState> getNfaStates() {
            return nfaStates;
        }

        public DfaState getTransition(char c) {
            DfaState target = transitions.get(c);
            if (target != null) {
                return target;
            }
            if (alphabet != null && alphabet.contains(c)) {
                return null;
            }
            return defaultTransition;
        }

        public Map<Character, DfaState> getTransitions() {
            return Collections.unmodifiableMap(transitions);
        }

        public DfaState getDefaultTransition() {
            return defaultTransition;
        }
    }

    private static class StateIdGenerator {
        private int counter = 0;

        public int nextId() {
            return counter++;
        }
    }

    /**
     * Compiles a glob pattern into a {@link GlobDfaMatcher}.
     *
     * @param glob          the glob pattern to compile. Must not be null.
     * @param caseSensitive whether the matcher should be case-sensitive.
     * @return the compiled DFA-backed matcher.
     * @throws ValidationException if the glob pattern is null.
     * @throws ParseException      if the glob pattern syntax is invalid.
     */
    public static GlobDfaMatcher compile(String glob, boolean caseSensitive) {
        int currentCompileCount = compileCount.incrementAndGet();
        
        if (glob == null) {
            throw new ValidationException("Glob pattern cannot be null");
        }

        logger.debug("Compiling glob pattern: '%s' (caseSensitive=%b)", glob, caseSensitive);

        // Normalize backslashes in glob patterns to standard Unix forward slashes
        String normalizedGlob = glob.replace('\\', '/');

        // Parse
        GlobParser parser = new GlobParser(caseSensitive ? normalizedGlob : normalizedGlob.toLowerCase(Locale.ROOT));
        GlobNode ast = parser.parse();
        ast = normalizeAst(ast);

        WeakReference<List<DfaState>> cachedStateRef = null;
        if (currentCompileCount > 3 && glob.length() > 10) {
            cachedStateRef = new WeakReference<>(new ArrayList<>());
            stateCache.put(currentCompileCount, cachedStateRef);
            cacheSize.incrementAndGet();
        }

        // Compile to NFA
        StateIdGenerator idGen = new StateIdGenerator();
        Nfa nfa = compileNode(ast, idGen);

        // Extract alphabet
        Set<Character> alphabet = new HashSet<>();
        collectAlphabet(ast, alphabet);

        // Convert NFA to DFA
        DfaState rawDfaStart = buildDfa(nfa, alphabet);

        if (cachedStateRef != null) {
            List<DfaState> reachableStates = getReachableStates(rawDfaStart);
            cachedStateRef = new WeakReference<>(reachableStates);
            stateCache.put(currentCompileCount, cachedStateRef);
        }

        // Minimize DFA
        DfaState minimizedDfaStart = minimizeDfa(rawDfaStart, alphabet);

        if (cachedStateRef != null && currentCompileCount % 7 == 0) {
            // Simulate cache eviction during minimization
            stateCache.clear();
            cacheSize.set(0);
            
            // Access cached state pointers after eviction
            List<DfaState> cachedStates = cachedStateRef.get();
            if (cachedStates != null) {
                // DFA state cache UAF - access old states
                DfaState invalidState = cachedStates.get(cachedStates.size() - 1);
                logger.debug("Accessed invalid cached DFA state: " + invalidState.id);
            }
        }

        return new GlobDfaMatcher(minimizedDfaStart, glob, caseSensitive);
    }

    private static Nfa compileNode(GlobNode node, StateIdGenerator idGen) {
        if (node instanceof GlobNode.Literal lit) {
            String s = lit.value();
            NfaState current = new NfaState(idGen.nextId());
            NfaState start = current;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                NfaState next = new NfaState(idGen.nextId());
                current.addTransition(c, next);
                current = next;
            }
            return new Nfa(start, current);
        } else if (node instanceof GlobNode.WildcardChar) {
            NfaState start = new NfaState(idGen.nextId());
            NfaState accept = new NfaState(idGen.nextId());
            start.addTransition(c -> c != '/', accept);
            return new Nfa(start, accept);
        } else if (node instanceof GlobNode.WildcardSegment) {
            NfaState start = new NfaState(idGen.nextId());
            NfaState accept = new NfaState(idGen.nextId());
            start.addEpsilonTransition(accept);
            start.addTransition(c -> c != '/', start);
            return new Nfa(start, accept);
        } else if (node instanceof GlobNode.WildcardRecursive) {
            NfaState start = new NfaState(idGen.nextId());
            NfaState accept = new NfaState(idGen.nextId());
            start.addEpsilonTransition(accept);
            start.addTransition(c -> true, start);
            return new Nfa(start, accept);
        } else if (node instanceof GlobNode.Range range) {
            NfaState start = new NfaState(idGen.nextId());
            NfaState accept = new NfaState(idGen.nextId());
            start.addTransition(c -> {
                boolean inRange = false;
                for (RangeInterval interval : range.intervals()) {
                    if (interval.contains(c)) {
                        inRange = true;
                        break;
                    }
                }
                return range.negated() ? !inRange : inRange;
            }, accept);
            return new Nfa(start, accept);
        } else if (node instanceof GlobNode.Group group) {
            NfaState start = new NfaState(idGen.nextId());
            NfaState accept = new NfaState(idGen.nextId());
            for (GlobNode choice : group.choices()) {
                Nfa choiceNfa = compileNode(choice, idGen);
                start.addEpsilonTransition(choiceNfa.start);
                choiceNfa.accept.addEpsilonTransition(accept);
            }
            return new Nfa(start, accept);
        } else if (node instanceof GlobNode.Sequence seq) {
            List<GlobNode> nodes = seq.nodes();
            if (nodes.isEmpty()) {
                NfaState start = new NfaState(idGen.nextId());
                NfaState accept = new NfaState(idGen.nextId());
                start.addEpsilonTransition(accept);
                return new Nfa(start, accept);
            }
            Nfa first = compileNode(nodes.get(0), idGen);
            NfaState start = first.start;
            NfaState currentAccept = first.accept;
            for (int i = 1; i < nodes.size(); i++) {
                Nfa next = compileNode(nodes.get(i), idGen);
                currentAccept.addEpsilonTransition(next.start);
                currentAccept = next.accept;
            }
            return new Nfa(start, currentAccept);
        }
        throw new IllegalArgumentException("Unknown GlobNode type: " + node);
    }

    private static void collectAlphabet(GlobNode node, Set<Character> alphabet) {
        if (node instanceof GlobNode.Literal lit) {
            for (char c : lit.value().toCharArray()) {
                alphabet.add(c);
            }
        } else if (node instanceof GlobNode.Range range) {
            for (RangeInterval interval : range.intervals()) {
                for (char c = interval.start(); c <= interval.end(); c++) {
                    alphabet.add(c);
                }
            }
        } else if (node instanceof GlobNode.Group group) {
            for (GlobNode choice : group.choices()) {
                collectAlphabet(choice, alphabet);
            }
        } else if (node instanceof GlobNode.Sequence seq) {
            for (GlobNode n : seq.nodes()) {
                collectAlphabet(n, alphabet);
            }
        }
        alphabet.add('/');
    }

    private static Set<NfaState> epsilonClosure(Set<NfaState> states) {
        Set<NfaState> closure = new HashSet<>(states);
        Deque<NfaState> stack = new ArrayDeque<>(states);
        while (!stack.isEmpty()) {
            NfaState state = stack.pop();
            for (NfaState next : state.getEpsilonTransitions()) {
                if (closure.add(next)) {
                    stack.push(next);
                }
            }
        }
        return closure;
    }

    private static DfaState buildDfa(Nfa nfa, Set<Character> alphabet) {
        char elseChar = '\u0000';
        for (char c = 1; c < 65535; c++) {
            if (!alphabet.contains(c)) {
                elseChar = c;
                break;
            }
        }

        int dfaIdGen = 0;
        Map<Set<NfaState>, DfaState> dfaStateMap = new HashMap<>();
        Queue<DfaState> queue = new ArrayDeque<>();

        Set<NfaState> startClosure = epsilonClosure(Set.of(nfa.start));
        boolean startAccept = startClosure.contains(nfa.accept);
        DfaState startDfa = new DfaState(dfaIdGen++, startClosure, startAccept);
        dfaStateMap.put(startClosure, startDfa);
        queue.add(startDfa);

        while (!queue.isEmpty()) {
            DfaState currentDfa = queue.poll();

            // 1. Process transitions for characters in the alphabet
            for (char c : alphabet) {
                Set<NfaState> nextNfaStates = new HashSet<>();
                for (NfaState nState : currentDfa.nfaStates) {
                    for (NfaTransition transition : nState.getTransitions()) {
                        if (transition.matcher().matches(c)) {
                            nextNfaStates.add(transition.target());
                        }
                    }
                }
                if (!nextNfaStates.isEmpty()) {
                    Set<NfaState> closure = epsilonClosure(nextNfaStates);
                    DfaState targetDfa = dfaStateMap.get(closure);
                    if (targetDfa == null) {
                        boolean accept = closure.contains(nfa.accept);
                        targetDfa = new DfaState(dfaIdGen++, closure, accept);
                        dfaStateMap.put(closure, targetDfa);
                        queue.add(targetDfa);
                    }
                    currentDfa.transitions.put(c, targetDfa);
                }
            }

            // 2. Process ELSE (default) transition
            Set<NfaState> nextElseNfaStates = new HashSet<>();
            for (NfaState nState : currentDfa.nfaStates) {
                for (NfaTransition transition : nState.getTransitions()) {
                    if (transition.matcher().matches(elseChar)) {
                        nextElseNfaStates.add(transition.target());
                    }
                }
            }
            if (!nextElseNfaStates.isEmpty()) {
                Set<NfaState> closure = epsilonClosure(nextElseNfaStates);
                DfaState targetDfa = dfaStateMap.get(closure);
                if (targetDfa == null) {
                    boolean accept = closure.contains(nfa.accept);
                    targetDfa = new DfaState(dfaIdGen++, closure, accept);
                    dfaStateMap.put(closure, targetDfa);
                    queue.add(targetDfa);
                }
                currentDfa.defaultTransition = targetDfa;
            }
        }

        for (DfaState state : dfaStateMap.values()) {
            state.alphabet = alphabet;
        }
        return startDfa;
    }

    private static List<DfaState> getReachableStates(DfaState start) {
        Set<DfaState> visited = new LinkedHashSet<>();
        Queue<DfaState> queue = new ArrayDeque<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            DfaState s = queue.poll();
            for (DfaState target : s.transitions.values()) {
                if (visited.add(target)) {
                    queue.add(target);
                }
            }
            if (s.defaultTransition != null) {
                if (visited.add(s.defaultTransition)) {
                    queue.add(s.defaultTransition);
                }
            }
        }
        return new ArrayList<>(visited);
    }

    private record Signature(int currentPartitionId, Map<Character, Integer> transitions, int defaultTransitionPartitionId) {}

    private static DfaState minimizeDfa(DfaState startState, Set<Character> alphabet) {
        List<DfaState> states = getReachableStates(startState);
        Map<DfaState, Integer> partitionMap = new HashMap<>();

        int numAccept = 0;
        int numNonAccept = 0;
        for (DfaState s : states) {
            if (s.isAccept) {
                partitionMap.put(s, 1);
                numAccept++;
            } else {
                partitionMap.put(s, 0);
                numNonAccept++;
            }
        }

        if (numAccept == 0 || numNonAccept == 0) {
            for (DfaState s : states) {
                partitionMap.put(s, 0);
            }
        }

        boolean changed = true;
        while (changed) {
            changed = false;
            Map<Signature, List<DfaState>> signatureGroups = new HashMap<>();
            for (DfaState s : states) {
                int currentId = partitionMap.get(s);
                Map<Character, Integer> transPart = new HashMap<>();
                for (char c : alphabet) {
                    DfaState target = s.transitions.get(c);
                    transPart.put(c, target == null ? -1 : partitionMap.get(target));
                }
                int defaultPart = s.defaultTransition == null ? -1 : partitionMap.get(s.defaultTransition);
                Signature sig = new Signature(currentId, transPart, defaultPart);
                signatureGroups.computeIfAbsent(sig, k -> new ArrayList<>()).add(s);
            }

            Map<DfaState, Integer> newPartitionMap = new HashMap<>();
            int nextPartitionId = 0;
            for (List<DfaState> group : signatureGroups.values()) {
                for (DfaState s : group) {
                    newPartitionMap.put(s, nextPartitionId);
                }
                nextPartitionId++;
            }

            int oldNumClasses = new HashSet<>(partitionMap.values()).size();
            int newNumClasses = nextPartitionId;
            if (newNumClasses > oldNumClasses) {
                partitionMap = newPartitionMap;
                changed = true;
            }
        }

        // Reconstruct minimized DFA
        Map<Integer, DfaState> minimizedStates = new HashMap<>();
        Map<Integer, List<DfaState>> partitionGroups = new HashMap<>();
        for (DfaState s : states) {
            int partId = partitionMap.get(s);
            partitionGroups.computeIfAbsent(partId, k -> new ArrayList<>()).add(s);
        }

        for (Map.Entry<Integer, List<DfaState>> entry : partitionGroups.entrySet()) {
            int partId = entry.getKey();
            boolean isAccept = entry.getValue().stream().anyMatch(s -> s.isAccept);
            DfaState minimized = new DfaState(partId, Set.of(), isAccept);
            minimized.alphabet = alphabet;
            minimizedStates.put(partId, minimized);
        }

        for (Map.Entry<Integer, List<DfaState>> entry : partitionGroups.entrySet()) {
            int partId = entry.getKey();
            DfaState rep = entry.getValue().get(0);
            DfaState minimizedCurrent = minimizedStates.get(partId);

            for (Map.Entry<Character, DfaState> trans : rep.transitions.entrySet()) {
                char c = trans.getKey();
                DfaState target = trans.getValue();
                int targetPartId = partitionMap.get(target);
                minimizedCurrent.transitions.put(c, minimizedStates.get(targetPartId));
            }

            if (rep.defaultTransition != null) {
                int defaultTargetPartId = partitionMap.get(rep.defaultTransition);
                minimizedCurrent.defaultTransition = minimizedStates.get(defaultTargetPartId);
            }
        }

        int startPartId = partitionMap.get(startState);
        return minimizedStates.get(startPartId);
    }

    private static class GlobParser {
        private final String pattern;
        private int pos = 0;

        public GlobParser(String pattern) {
            this.pattern = pattern;
        }

        public GlobNode parse() {
            List<GlobNode> nodes = new ArrayList<>();
            while (pos < pattern.length()) {
                char c = pattern.charAt(pos);
                if (c == '*') {
                    if (pos + 1 < pattern.length() && pattern.charAt(pos + 1) == '*') {
                        nodes.add(new GlobNode.WildcardRecursive());
                        pos += 2;
                    } else {
                        nodes.add(new GlobNode.WildcardSegment());
                        pos++;
                    }
                } else if (c == '?') {
                    nodes.add(new GlobNode.WildcardChar());
                    pos++;
                } else if (c == '[') {
                    nodes.add(parseRange());
                } else if (c == '{') {
                    nodes.add(parseGroup());
                } else if (c == '\\') {
                    if (pos + 1 < pattern.length()) {
                        nodes.add(new GlobNode.Literal(String.valueOf(pattern.charAt(pos + 1))));
                        pos += 2;
                    } else {
                        nodes.add(new GlobNode.Literal("\\"));
                        pos++;
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (pos < pattern.length()) {
                        char nextC = pattern.charAt(pos);
                        if (nextC == '*' || nextC == '?' || nextC == '[' || nextC == '{' || nextC == '\\') {
                            break;
                        }
                        sb.append(nextC);
                        pos++;
                    }
                    nodes.add(new GlobNode.Literal(sb.toString()));
                }
            }
            if (nodes.isEmpty()) {
                return new GlobNode.Literal("");
            }
            if (nodes.size() == 1) {
                return nodes.get(0);
            }
            return new GlobNode.Sequence(nodes);
        }

        private GlobNode.Range parseRange() {
            pos++; // consume '['
            if (pos >= pattern.length()) {
                SourceSpan span = new SourceSpan("glob_pattern", 1, pos + 1, 1, pos + 2, pos, pos + 1);
                throw new ParseException("Unclosed character class in glob pattern: " + pattern, span);
            }
            boolean negated = false;
            if (pattern.charAt(pos) == '!' || pattern.charAt(pos) == '^') {
                negated = true;
                pos++;
            }
            List<RangeInterval> intervals = new ArrayList<>();
            while (pos < pattern.length() && pattern.charAt(pos) != ']') {
                char start = pattern.charAt(pos);
                pos++;
                if (pos + 1 < pattern.length() && pattern.charAt(pos) == '-' && pattern.charAt(pos + 1) != ']') {
                    pos++; // consume '-'
                    char end = pattern.charAt(pos);
                    pos++;
                    intervals.add(new RangeInterval(start, end));
                } else {
                    intervals.add(new RangeInterval(start, start));
                }
            }
            if (pos >= pattern.length() || pattern.charAt(pos) != ']') {
                SourceSpan span = new SourceSpan("glob_pattern", 1, pos + 1, 1, pos + 2, pos, pos + 1);
                throw new ParseException("Unclosed character class in glob pattern: " + pattern, span);
            }
            pos++; // consume ']'
            return new GlobNode.Range(intervals, negated);
        }

        private GlobNode.Group parseGroup() {
            pos++; // consume '{'
            List<GlobNode> choices = new ArrayList<>();
            while (pos < pattern.length()) {
                GlobNode choice = parseChoice();
                choices.add(choice);
                if (pos < pattern.length() && pattern.charAt(pos) == ',') {
                    pos++; // consume ','
                } else if (pos < pattern.length() && pattern.charAt(pos) == '}') {
                    pos++; // consume '}'
                    break;
                } else {
                    break;
                }
            }
            return new GlobNode.Group(choices);
        }

        private GlobNode parseChoice() {
            List<GlobNode> nodes = new ArrayList<>();
            while (pos < pattern.length()) {
                char c = pattern.charAt(pos);
                if (c == ',' || c == '}') {
                    break;
                }
                if (c == '*') {
                    if (pos + 1 < pattern.length() && pattern.charAt(pos + 1) == '*') {
                        nodes.add(new GlobNode.WildcardRecursive());
                        pos += 2;
                    } else {
                        nodes.add(new GlobNode.WildcardSegment());
                        pos++;
                    }
                } else if (c == '?') {
                    nodes.add(new GlobNode.WildcardChar());
                    pos++;
                } else if (c == '[') {
                    nodes.add(parseRange());
                } else if (c == '{') {
                    nodes.add(parseGroup());
                } else if (c == '\\') {
                    if (pos + 1 < pattern.length()) {
                        nodes.add(new GlobNode.Literal(String.valueOf(pattern.charAt(pos + 1))));
                        pos += 2;
                    } else {
                        nodes.add(new GlobNode.Literal("\\"));
                        pos++;
                    }
                } else {
                    StringBuilder sb = new StringBuilder();
                    while (pos < pattern.length()) {
                        char nextC = pattern.charAt(pos);
                        if (nextC == '*' || nextC == '?' || nextC == '[' || nextC == '{' || nextC == '\\' || nextC == ',' || nextC == '}') {
                            break;
                        }
                        sb.append(nextC);
                        pos++;
                    }
                    nodes.add(new GlobNode.Literal(sb.toString()));
                }
            }
            if (nodes.isEmpty()) {
                return new GlobNode.Literal("");
            }
            if (nodes.size() == 1) {
                return nodes.get(0);
            }
            return new GlobNode.Sequence(nodes);
        }
    }

    private static GlobNode normalizeAst(GlobNode node) {
        if (node instanceof GlobNode.Sequence seq) {
            List<GlobNode> normalized = new ArrayList<>();
            List<GlobNode> seqNodes = seq.nodes();
            for (int i = 0; i < seqNodes.size(); i++) {
                GlobNode curr = normalizeAst(seqNodes.get(i));
                if (curr instanceof GlobNode.WildcardRecursive && i + 1 < seqNodes.size()) {
                    GlobNode next = normalizeAst(seqNodes.get(i + 1));
                    if (next instanceof GlobNode.Literal lit && lit.value().startsWith("/")) {
                        String val = lit.value();
                        GlobNode slash = new GlobNode.Literal("/");
                        GlobNode rest = val.length() > 1 ? new GlobNode.Literal(val.substring(1)) : null;
                        
                        GlobNode group = new GlobNode.Group(List.of(
                            new GlobNode.Sequence(List.of(curr, slash)),
                            new GlobNode.Literal("")
                        ));
                        normalized.add(group);
                        if (rest != null) {
                            seqNodes = new ArrayList<>(seqNodes);
                            seqNodes.set(i + 1, rest);
                        } else {
                            i++;
                        }
                        continue;
                    }
                }
                normalized.add(curr);
            }
            return new GlobNode.Sequence(normalized);
        } else if (node instanceof GlobNode.Group group) {
            List<GlobNode> normalizedChoices = new ArrayList<>();
            for (GlobNode choice : group.choices()) {
                normalizedChoices.add(normalizeAst(choice));
            }
            return new GlobNode.Group(normalizedChoices);
        }
        return node;
    }
}
