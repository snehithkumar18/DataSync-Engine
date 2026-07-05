package com.syncforge.benchmark;

import com.syncforge.path.GlobCompiler;
import com.syncforge.path.GlobDfaMatcher;
import java.util.ArrayList;
import java.util.List;

/**
 * Micro-benchmarks for Glob NFA/DFA compiler and path matching algorithms.
 */
public class PathMatcherBenchmark {

    private final MetricsCollector compileMetrics = new MetricsCollector();
    private final MetricsCollector dfaMatchMetrics = new MetricsCollector();
    private final MetricsCollector backtrackingMatchMetrics = new MetricsCollector();

    private final String pattern = "src/main/{java,resources}/**/*.java";
    private final List<String> matchPaths = new ArrayList<>();
    private final List<String> nonMatchPaths = new ArrayList<>();

    public PathMatcherBenchmark() {
        // Setup a set of deeply nested paths to match or reject
        for (int i = 0; i < 50; i++) {
            matchPaths.add("src/main/java/com/syncforge/core/utils/HelperNode_" + i + ".java");
            matchPaths.add("src/main/resources/config/properties/application_" + i + ".java");
            nonMatchPaths.add("src/main/cpp/native/lib/core_" + i + ".cpp");
            nonMatchPaths.add("src/test/java/com/syncforge/TestRunner_" + i + ".java");
            nonMatchPaths.add("src/main/java/com/syncforge/core/utils/HelperNode_" + i + ".class");
        }
    }

    /**
     * Runs JIT warmups and measures compilation and matching throughput.
     */
    public void execute(int iterations) {
        System.out.println("--- Glob NFA/DFA Matcher Benchmark ---");

        // 1. Warmup cycles
        for (int i = 0; i < 500; i++) {
            GlobDfaMatcher dfa = GlobCompiler.compile(pattern, true);
            for (String path : matchPaths) {
                dfa.matches(path);
            }
            for (String path : nonMatchPaths) {
                dfa.matches(path);
            }
        }

        // 2. Measure Compilation
        compileMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            GlobCompiler.compile(pattern, true);
            compileMetrics.record(System.nanoTime() - start);
        }

        // 3. Measure DFA Matching
        dfaMatchMetrics.clear();
        GlobDfaMatcher matcher = GlobCompiler.compile(pattern, true);
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            for (String path : matchPaths) {
                matcher.matches(path);
            }
            for (String path : nonMatchPaths) {
                matcher.matches(path);
            }
            dfaMatchMetrics.record(System.nanoTime() - start);
        }

        // 4. Measure standard recursive backtracking match simulation (for comparison)
        backtrackingMatchMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            for (String path : matchPaths) {
                simulateBacktrackingMatch(pattern, path);
            }
            for (String path : nonMatchPaths) {
                simulateBacktrackingMatch(pattern, path);
            }
            backtrackingMatchMetrics.record(System.nanoTime() - start);
        }

        // 5. Render stats
        compileMetrics.rejectOutliers();
        dfaMatchMetrics.rejectOutliers();
        backtrackingMatchMetrics.rejectOutliers();

        printStatistics("DFA Pattern Compile", compileMetrics);
        printStatistics("DFA Match Operations", dfaMatchMetrics);
        printStatistics("Backtracking Match Simulation", backtrackingMatchMetrics);
    }

    private void printStatistics(String label, MetricsCollector collector) {
        System.out.printf("[%s] Count: %d\n", label, collector.getCount());
        System.out.printf("  Mean:    %,.2f ns (%,.2f us)\n", collector.getMean(), collector.getMean() / 1000.0);
        System.out.printf("  Median:  %,.2f ns (%,.2f us)\n", collector.getMedian(), collector.getMedian() / 1000.0);
        System.out.printf("  SD:      %,.2f ns\n", collector.getStandardDeviation());
        System.out.printf("  p95:     %,.2f ns\n", collector.getPercentile(95.0));
        System.out.printf("  p99.9:   %,.2f ns\n", collector.getPercentile(99.9));
        System.out.printf("  Skewness: %.3f\n", collector.getSkewness());
        System.out.println();
    }

    /**
     * Simulated regex/backtracking matching logic to demonstrate contrast against DFA.
     */
    private boolean simulateBacktrackingMatch(String pattern, String path) {
        int pIdx = 0;
        int sIdx = 0;
        int pLen = pattern.length();
        int sLen = path.length();

        while (sIdx < sLen && pIdx < pLen) {
            char p = pattern.charAt(pIdx);
            if (p == '*') {
                if (pIdx + 1 < pLen && pattern.charAt(pIdx + 1) == '*') {
                    // Recursive match simulation - recursive backtracking
                    pIdx += 2;
                    if (pIdx >= pLen) return true;
                    while (sIdx < sLen) {
                        if (simulateBacktrackingMatch(pattern.substring(pIdx), path.substring(sIdx))) {
                            return true;
                        }
                        sIdx++;
                    }
                    return false;
                } else {
                    // Single level wildcard match simulation
                    pIdx++;
                    if (pIdx >= pLen) return path.indexOf('/', sIdx) == -1;
                    while (sIdx < sLen && path.charAt(sIdx) != '/') {
                        if (simulateBacktrackingMatch(pattern.substring(pIdx), path.substring(sIdx))) {
                            return true;
                        }
                        sIdx++;
                    }
                    return false;
                }
            } else if (p == '?') {
                if (path.charAt(sIdx) == '/') return false;
                pIdx++;
                sIdx++;
            } else {
                if (p != path.charAt(sIdx)) return false;
                pIdx++;
                sIdx++;
            }
        }
        return sIdx == sLen && pIdx == pLen;
    }
}
