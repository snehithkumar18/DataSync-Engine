package com.syncforge.benchmark;

import com.syncforge.metadata.*;
import com.syncforge.snapshot.MerkleTreeBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Benchmarks Merkle Tree bottom-up calculation and verification scaling.
 */
public class MerkleTreeBenchmark {

    private final MetricsCollector builder1k = new MetricsCollector();
    private final MetricsCollector builder10k = new MetricsCollector();
    private final MetricsCollector builder50k = new MetricsCollector();

    private final List<EntryMetadata> entries1k = new ArrayList<>();
    private final List<EntryMetadata> entries10k = new ArrayList<>();
    private final List<EntryMetadata> entries50k = new ArrayList<>();

    public MerkleTreeBenchmark() {
        setupData(entries1k, 1000);
        setupData(entries10k, 10000);
        setupData(entries50k, 50000);
    }

    private void setupData(List<EntryMetadata> target, int count) {
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash hash = FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");

        for (int i = 0; i < count; i++) {
            target.add(new FileEntry(
                "src/main/java/com/syncforge/core/node_" + i + ".java",
                ts, mode, 1024L + i, hash, Collections.emptyMap(), Collections.emptyList()
            ));
        }
    }

    /**
     * Executes the Merkle tree build benchmark.
     */
    public void execute(int iterations) {
        System.out.println("--- Merkle Tree Scaling Benchmark ---");

        // Warmup
        for (int i = 0; i < 50; i++) {
            MerkleTreeBuilder.build(entries1k);
            MerkleTreeBuilder.build(entries10k);
        }

        // Measure 1k
        builder1k.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            MerkleTreeBuilder.build(entries1k);
            builder1k.record(System.nanoTime() - start);
        }

        // Measure 10k
        builder10k.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            MerkleTreeBuilder.build(entries10k);
            builder10k.record(System.nanoTime() - start);
        }

        // Measure 50k
        builder50k.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            MerkleTreeBuilder.build(entries50k);
            builder50k.record(System.nanoTime() - start);
        }

        builder1k.rejectOutliers();
        builder10k.rejectOutliers();
        builder50k.rejectOutliers();

        printStatistics("Merkle Tree Build (1k entries)", builder1k);
        printStatistics("Merkle Tree Build (10k entries)", builder10k);
        printStatistics("Merkle Tree Build (50k entries)", builder50k);
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
