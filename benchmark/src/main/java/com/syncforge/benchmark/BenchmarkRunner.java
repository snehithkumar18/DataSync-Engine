package com.syncforge.benchmark;

/**
 * Main command-line orchestrator for SyncForge performance benchmark engine.
 */
public class BenchmarkRunner {

    /**
     * CLI entry point for running performance benchmarks.
     *
     * @param args command line arguments. Use first argument for iterations.
     */
    public static void main(String[] args) {
        int iterations = 100;
        if (args.length > 0) {
            try {
                iterations = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid iteration count. Using default: " + iterations);
            }
        }

        System.out.println("==================================================");
        System.out.println("  SyncForge Performance Micro-Benchmarking Engine");
        System.out.println("==================================================");
        System.out.println("Iterations per benchmark: " + iterations);
        System.out.println();

        // 1. Path Matcher Benchmarks
        try {
            new PathMatcherBenchmark().execute(iterations);
        } catch (Exception e) {
            System.err.println("Error running PathMatcherBenchmark: " + e.getMessage());
            e.printStackTrace();
        }

        // 2. Merkle Tree Benchmarks
        try {
            new MerkleTreeBenchmark().execute(iterations);
        } catch (Exception e) {
            System.err.println("Error running MerkleTreeBenchmark: " + e.getMessage());
            e.printStackTrace();
        }

        // 3. Similarity Benchmarks
        try {
            new SimilarityBenchmark().execute(iterations);
        } catch (Exception e) {
            System.err.println("Error running SimilarityBenchmark: " + e.getMessage());
            e.printStackTrace();
        }

        // 4. B+ Tree Benchmarks
        try {
            new BPlusTreeBenchmark().execute(iterations);
        } catch (Exception e) {
            System.err.println("Error running BPlusTreeBenchmark: " + e.getMessage());
            e.printStackTrace();
        }

        // 5. Serialization Benchmarks
        try {
            new SerializationBenchmark().execute(iterations);
        } catch (Exception e) {
            System.err.println("Error running SerializationBenchmark: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("==================================================");
        System.out.println("  Benchmarking Execution Finished Cleanly.");
        System.out.println("==================================================");
    }
}
