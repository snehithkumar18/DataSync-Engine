package com.syncforge.benchmark;

import com.syncforge.indexing.BPlusTree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Benchmarks B+ Tree indexing engine performance under split and range scan stresses.
 */
public class BPlusTreeBenchmark {

    private final MetricsCollector insertMetrics = new MetricsCollector();
    private final MetricsCollector searchMetrics = new MetricsCollector();
    private final MetricsCollector rangeScanMetrics = new MetricsCollector();

    private final int elementCount = 50_000;
    private final List<Long> insertKeys = new ArrayList<>();
    private final BPlusTree<Long, String> warmedTree = new BPlusTree<>(8);

    public BPlusTreeBenchmark() {
        Random r = new Random(1337);
        for (int i = 0; i < elementCount; i++) {
            long key = r.nextLong();
            insertKeys.add(key);
            warmedTree.insert(key, "Val_" + i);
        }
    }

    /**
     * Executes the B+ Tree benchmarks.
     */
    public void execute(int iterations) {
        System.out.println("--- B+ Tree Indexing Benchmark (50,000 Nodes) ---");

        // Warmup
        BPlusTree<Long, String> warmupTree = new BPlusTree<>(8);
        for (int i = 0; i < 5000; i++) {
            warmupTree.insert((long) i, "W_" + i);
            warmupTree.search((long) (i / 2));
            warmupTree.searchRange(1000L, 2000L);
        }

        // Measure insertion splits
        insertMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            BPlusTree<Long, String> tree = new BPlusTree<>(8);
            long start = System.nanoTime();
            for (long key : insertKeys) {
                tree.insert(key, "A");
            }
            insertMetrics.record(System.nanoTime() - start);
        }

        // Measure random searches on warmed tree
        searchMetrics.clear();
        Random sr = new Random(999);
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            for (int k = 0; k < 1000; k++) {
                long key = insertKeys.get(sr.nextInt(elementCount));
                warmedTree.search(key);
            }
            searchMetrics.record(System.nanoTime() - start);
        }

        // Measure sequential range scans on warmed tree
        rangeScanMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            for (int k = 0; k < 50; k++) {
                // Scan a small slice of keys
                warmedTree.searchRange(-1000000L, 1000000L);
            }
            rangeScanMetrics.record(System.nanoTime() - start);
        }

        insertMetrics.rejectOutliers();
        searchMetrics.rejectOutliers();
        rangeScanMetrics.rejectOutliers();

        printStatistics("B+ Tree 50k Bulk Insertions", insertMetrics);
        printStatistics("B+ Tree 1,000 Key Lookups", searchMetrics);
        printStatistics("B+ Tree 50 Range Scans", rangeScanMetrics);
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
