package com.syncforge.benchmark;

import java.util.Random;

/**
 * Benchmarks sliding-window SimHash content similarity calculations.
 */
public class SimilarityBenchmark {

    private final MetricsCollector shingleK5 = new MetricsCollector();
    private final MetricsCollector shingleK8 = new MetricsCollector();
    private final MetricsCollector shingleK12 = new MetricsCollector();

    private byte[] data1;
    private byte[] data2;

    public SimilarityBenchmark() {
        setupData(100_000); // 100KB test files
    }

    private void setupData(int size) {
        data1 = new byte[size];
        new Random(42).nextBytes(data1);

        data2 = new byte[size];
        System.arraycopy(data1, 0, data2, 0, size);
        
        // Mutate 5% of data2 to simulate slight modifications
        Random mutator = new Random(84);
        for (int i = 0; i < size / 20; i++) {
            int idx = mutator.nextInt(size);
            data2[idx] = (byte) mutator.nextInt(256);
        }
    }

    /**
     * Executes the similarity calculation benchmarks.
     */
    public void execute(int iterations) {
        System.out.println("--- SimHash Content Similarity Benchmark (100KB Payload) ---");

        // Warmup
        for (int i = 0; i < 20; i++) {
            calculate(data1, data2, 5);
            calculate(data1, data2, 8);
        }

        // Measure K=5
        shingleK5.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            calculate(data1, data2, 5);
            shingleK5.record(System.nanoTime() - start);
        }

        // Measure K=8
        shingleK8.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            calculate(data1, data2, 8);
            shingleK8.record(System.nanoTime() - start);
        }

        // Measure K=12
        shingleK12.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            calculate(data1, data2, 12);
            shingleK12.record(System.nanoTime() - start);
        }

        shingleK5.rejectOutliers();
        shingleK8.rejectOutliers();
        shingleK12.rejectOutliers();

        printStatistics("SimHash Similarity (K=5 shingles)", shingleK5);
        printStatistics("SimHash Similarity (K=8 shingles)", shingleK8);
        printStatistics("SimHash Similarity (K=12 shingles)", shingleK12);
    }

    private void calculate(byte[] file1, byte[] file2, int k) {
        getShingleSimilarity(file1, file2, k);
    }

    private double getShingleSimilarity(byte[] a, byte[] b, int k) {
        if (a.length == 0 && b.length == 0) return 1.0;
        if (a.length == 0 || b.length == 0) return 0.0;

        java.util.Set<Long> shinglesA = getShingles(a, k);
        java.util.Set<Long> shinglesB = getShingles(b, k);

        if (shinglesA.isEmpty() && shinglesB.isEmpty()) return 1.0;
        if (shinglesA.isEmpty() || shinglesB.isEmpty()) return 0.0;

        int intersection = 0;
        for (long s : shinglesA) {
            if (shinglesB.contains(s)) {
                intersection++;
            }
        }
        int union = shinglesA.size() + shinglesB.size() - intersection;
        return (double) intersection / union;
    }

    private java.util.Set<Long> getShingles(byte[] bytes, int k) {
        java.util.Set<Long> shingles = new java.util.HashSet<>();
        if (bytes.length < k) {
            long hash = 0;
            for (byte b : bytes) {
                hash = (hash * 31) + (b & 0xFF);
            }
            shingles.add(hash);
            return shingles;
        }

        for (int i = 0; i <= bytes.length - k; i++) {
            long windowHash = 0;
            for (int j = 0; j < k; j++) {
                windowHash = (windowHash * 31) + (bytes[i + j] & 0xFF);
            }
            shingles.add(windowHash);
        }
        return shingles;
    }

    private void printStatistics(String label, MetricsCollector collector) {
        System.out.printf("[%s] Count: %d\n", label, collector.getCount());
        System.out.printf("  Mean:    %,.2f ns (%,.2f us, %,.2f ms)\n", 
            collector.getMean(), collector.getMean() / 1000.0, collector.getMean() / 1_000_000.0);
        System.out.printf("  Median:  %,.2f ns (%,.2f us, %,.2f ms)\n", 
            collector.getMedian(), collector.getMedian() / 1000.0, collector.getMedian() / 1_000_000.0);
        System.out.printf("  p95:     %,.2f ns\n", collector.getPercentile(95.0));
        System.out.printf("  Skewness: %.3f\n", collector.getSkewness());
        System.out.println();
    }
}
