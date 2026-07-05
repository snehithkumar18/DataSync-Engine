package com.syncforge.benchmark;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BenchmarkTest {

    @Test
    public void testMetricsCollectorStatistics() {
        MetricsCollector collector = new MetricsCollector();
        collector.record(100L);
        collector.record(200L);
        collector.record(300L);
        collector.record(400L);
        collector.record(500L);

        assertEquals(5, collector.getCount());
        assertEquals(300.0, collector.getMean());
        assertEquals(300.0, collector.getMedian());
        assertEquals(25000.0, collector.getVariance());
        assertEquals(158.11388300841898, collector.getStandardDeviation(), 0.0001);
        assertEquals(70.71067811865476, collector.getStandardError(), 0.0001);
        assertEquals(300.0, collector.getPercentile(50.0));
        assertEquals(100.0, collector.getPercentile(0.0));
        assertEquals(500.0, collector.getPercentile(100.0));
    }

    @Test
    public void testBenchmarkDryRuns() {
        // Run with 1 iteration to verify code path compiles and works
        assertDoesNotThrow(() -> new PathMatcherBenchmark().execute(1));
        assertDoesNotThrow(() -> new MerkleTreeBenchmark().execute(1));
        assertDoesNotThrow(() -> new SimilarityBenchmark().execute(1));
        assertDoesNotThrow(() -> new BPlusTreeBenchmark().execute(1));
        assertDoesNotThrow(() -> new SerializationBenchmark().execute(1));
    }
}
