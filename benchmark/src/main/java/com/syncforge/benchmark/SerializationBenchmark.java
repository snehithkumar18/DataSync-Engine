package com.syncforge.benchmark;

import com.syncforge.metadata.*;
import com.syncforge.snapshot.SnapshotModel;
import com.syncforge.snapshot.SnapshotReader;
import com.syncforge.snapshot.SnapshotWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Benchmarks custom prefix-compressed SFSN binary serialization against Java standard Object Streams.
 */
public class SerializationBenchmark {

    private final MetricsCollector sfsnWriteMetrics = new MetricsCollector();
    private final MetricsCollector sfsnReadMetrics = new MetricsCollector();
    private final MetricsCollector javaWriteMetrics = new MetricsCollector();

    private SnapshotModel model;
    private byte[] sfsnBytes;

    public SerializationBenchmark() {
        try {
            setupData(5000);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void setupData(int count) throws IOException {
        List<EntryMetadata> entries = new ArrayList<>();
        FileTimestamp ts = FileTimestamp.fromEpochMilli(1000L, 1000L, 1000L);
        FileMode mode = FileMode.builder().type(FileMode.Type.FILE).permissions(0644).build();
        FileHash hash = FileHash.sha256("e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855");

        for (int i = 0; i < count; i++) {
            entries.add(new FileEntry(
                "src/main/java/com/syncforge/core/package/subpackage/utils/HelperComponent_Node_" + i + ".java",
                ts, mode, 5000L + i, hash, Collections.emptyMap(), Collections.emptyList()
            ));
        }

        model = new SnapshotModel(entries, 1, 0, null);
        sfsnBytes = new SnapshotWriter(true).write(model);
    }

    /**
     * Executes the serialization benchmarks.
     */
    public void execute(int iterations) throws IOException {
        System.out.println("--- Serialization & Compression Efficiency Benchmark (5,000 Entries) ---");

        // Warmup
        for (int i = 0; i < 50; i++) {
            new SnapshotWriter(true).write(model);
            new SnapshotReader().read(sfsnBytes);
            serializeJava();
        }

        // Measure SFSN write (compressed)
        sfsnWriteMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            new SnapshotWriter(true).write(model);
            sfsnWriteMetrics.record(System.nanoTime() - start);
        }

        // Measure SFSN read (compressed)
        sfsnReadMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            new SnapshotReader().read(sfsnBytes);
            sfsnReadMetrics.record(System.nanoTime() - start);
        }

        // Measure Java standard write
        javaWriteMetrics.clear();
        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            serializeJava();
            javaWriteMetrics.record(System.nanoTime() - start);
        }

        sfsnWriteMetrics.rejectOutliers();
        sfsnReadMetrics.rejectOutliers();
        javaWriteMetrics.rejectOutliers();

        printStatistics("SFSN Custom Serializer (Write)", sfsnWriteMetrics);
        printStatistics("SFSN Custom Deserializer (Read)", sfsnReadMetrics);
        printStatistics("Java Native Object stream (Write)", javaWriteMetrics);

        byte[] javaBytes = serializeJava();
        System.out.printf("  SFSN Binary Size: %,d bytes\n", sfsnBytes.length);
        System.out.printf("  Java Object Size: %,d bytes (%.2fx larger)\n", 
            javaBytes.length, (double) javaBytes.length / sfsnBytes.length);
        System.out.println();
    }

    private byte[] serializeJava() {
        List<java.util.Map<String, Object>> representation = new ArrayList<>();
        for (EntryMetadata entry : model.getEntries()) {
            java.util.Map<String, Object> map = new java.util.HashMap<>();
            map.put("path", entry.getNormalizedPath());
            map.put("size", entry.getSize());
            map.put("mtime", entry.getTimestamp().mtimeMillis());
            map.put("permissions", entry.getMode().getPosixPermissions());
            map.put("type", entry.getMode().getType().name());
            if (entry instanceof FileEntry fe) {
                map.put("hash", fe.getContentHash().value());
            }
            representation.add(map);
        }

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(representation);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
