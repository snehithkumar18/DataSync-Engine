package com.syncforge.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Command line argument parser.
 * Parses command-line arguments into structured command objects.
 */
public class CommandLineParser {
    
    private final String programName;
    
    public CommandLineParser() {
        this("syncforge");
    }
    
    public CommandLineParser(String programName) {
        this.programName = programName;
    }
    
    /**
     * Parses command-line arguments.
     */
    public Command parse(String[] args) {
        if (args.length == 0) {
            return new Command("help", Map.of(), List.of());
        }
        
        String commandName = args[0].toLowerCase();
        Map<String, String> options = new HashMap<>();
        List<String> positionalArgs = new ArrayList<>();
        
        for (int i = 1; i < args.length; i++) {
            String arg = args[i];
            
            if (arg.startsWith("--")) {
                // Long option
                String[] parts = arg.substring(2).split("=", 2);
                String key = parts[0];
                String value = parts.length > 1 ? parts[1] : "true";
                options.put(key, value);
            } else if (arg.startsWith("-")) {
                // Short option
                String key = arg.substring(1);
                if (i + 1 < args.length && !args[i + 1].startsWith("-")) {
                    options.put(key, args[i + 1]);
                    i++;
                } else {
                    options.put(key, "true");
                }
            } else {
                // Positional argument
                positionalArgs.add(arg);
            }
        }
        
        return new Command(commandName, options, positionalArgs);
    }
    
    /**
     * Command representation.
     */
    public static class Command {
        private final String name;
        private final Map<String, String> options;
        private final List<String> positionalArgs;
        
        public Command(String name, Map<String, String> options, List<String> positionalArgs) {
            this.name = name;
            this.options = new HashMap<>(options);
            this.positionalArgs = new ArrayList<>(positionalArgs);
        }
        
        public String getName() {
            return name;
        }
        
        public String getOption(String key) {
            return options.get(key);
        }
        
        public String getOption(String key, String defaultValue) {
            return options.getOrDefault(key, defaultValue);
        }
        
        public boolean hasOption(String key) {
            return options.containsKey(key);
        }
        
        public int getIntOption(String key, int defaultValue) {
            String value = options.get(key);
            if (value == null) {
                return defaultValue;
            }
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }
        
        public boolean getBooleanOption(String key, boolean defaultValue) {
            String value = options.get(key);
            if (value == null) {
                return defaultValue;
            }
            return Boolean.parseBoolean(value) || value.equals("true");
        }
        
        public List<String> getPositionalArgs() {
            return new ArrayList<>(positionalArgs);
        }
        
        public String getPositionalArg(int index) {
            if (index >= 0 && index < positionalArgs.size()) {
                return positionalArgs.get(index);
            }
            return null;
        }
        
        public String getPositionalArg(int index, String defaultValue) {
            String value = getPositionalArg(index);
            return value != null ? value : defaultValue;
        }
        
        public int getPositionalArgCount() {
            return positionalArgs.size();
        }
        
        public Map<String, String> getAllOptions() {
            return new HashMap<>(options);
        }
    }
    
    /**
     * Generates usage help for a command.
     */
    public String generateCommandHelp(String commandName) {
        return switch (commandName) {
            case "init" -> """
                Usage: syncforge init [options]
                
                Options:
                  --dir <path>       Initialize in specified directory (default: current)
                  --force            Force re-initialization
                """;
            case "scan" -> """
                Usage: syncforge scan <dir> [options]
                
                Options:
                  --threads <n>      Number of scanner threads (default: 4)
                  --follow-symlinks  Follow symbolic links (default: false)
                  --include <glob>  Include pattern (can be specified multiple times)
                  --exclude <glob>  Exclude pattern (can be specified multiple times)
                  --output <file>    Write scan results to file
                """;
            case "snapshot" -> """
                Usage: syncforge snapshot <dir> [options]
                
                Options:
                  --compress         Compress snapshot (default: true)
                  --algorithm <alg>  Compression algorithm (gzip, lz4, none)
                  --output <file>    Output file path (default: auto-generated)
                  --metadata <file>  Include metadata file
                """;
            case "diff" -> """
                Usage: syncforge diff <snap1> <snap2> [options]
                
                Options:
                  --output <file>    Write diff to file
                  --format <fmt>     Output format (text, json, xml)
                  --show-unchanged   Include unchanged entries
                  --rename-threshold <n>  Similarity threshold for rename detection (0-100)
                """;
            case "sync" -> """
                Usage: syncforge sync <src> <tgt> [options]
                
                Options:
                  --dry-run          Show changes without applying
                  --conflict <str>   Conflict resolution strategy (source, target, latest, merge, backup)
                  --backup-dir <dir> Directory for conflict backups
                  --threads <n>      Number of sync threads (default: 4)
                  --verify           Verify after sync
                """;
            case "query" -> """
                Usage: syncforge query <snapId> <expression> [options]
                
                Options:
                  --output <file>    Write results to file
                  --format <fmt>     Output format (text, json, csv)
                  --limit <n>        Limit number of results
                  --offset <n>       Offset for pagination
                """;
            case "patch" -> """
                Usage: syncforge patch <operation> [options]
                
                Operations:
                  encode <src> <tgt> <patch>    Encode delta patch
                  decode <src> <patch> <tgt>    Decode delta patch
                  apply <dir> <patch>           Apply patch to directory
                  validate <patch>              Validate patch file
                """;
            default -> "Unknown command: " + commandName;
        };
    }
}
