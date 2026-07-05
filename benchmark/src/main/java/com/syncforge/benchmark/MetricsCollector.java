package com.syncforge.benchmark;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * High-fidelity mathematical statistics collector for performance benchmarking.
 * Captures nanosecond execution times and calculates descriptive statistics,
 * percentiles, confidence intervals, and skewness/kurtosis coefficients.
 */
public class MetricsCollector {

    private final List<Long> measurements = new ArrayList<>();

    /**
     * Records a single timing measurement in nanoseconds.
     *
     * @param durationNs the duration in nanoseconds.
     */
    public synchronized void record(long durationNs) {
        if (durationNs < 0) {
            throw new IllegalArgumentException("Measurement cannot be negative: " + durationNs);
        }
        measurements.add(durationNs);
    }

    /**
     * Clears all recorded measurements.
     */
    public synchronized void clear() {
        measurements.clear();
    }

    /**
     * Returns the number of recorded measurements.
     *
     * @return the count.
     */
    public synchronized int getCount() {
        return measurements.size();
    }

    /**
     * Computes the mean of the recorded measurements.
     *
     * @return the arithmetic mean in nanoseconds.
     */
    public synchronized double getMean() {
        if (measurements.isEmpty()) return 0.0;
        double sum = 0.0;
        for (long val : measurements) {
            sum += val;
        }
        return sum / measurements.size();
    }

    /**
     * Computes the variance of the recorded measurements.
     * Uses Bessel's correction (N - 1) for sample variance.
     *
     * @return the variance.
     */
    public synchronized double getVariance() {
        int n = measurements.size();
        if (n <= 1) return 0.0;
        double mean = getMean();
        double sumSqDiff = 0.0;
        for (long val : measurements) {
            double diff = val - mean;
            sumSqDiff += diff * diff;
        }
        return sumSqDiff / (n - 1);
    }

    /**
     * Computes the standard deviation.
     *
     * @return the standard deviation.
     */
    public synchronized double getStandardDeviation() {
        return Math.sqrt(getVariance());
    }

    /**
     * Computes the standard error of the mean (SEM).
     *
     * @return the standard error.
     */
    public synchronized double getStandardError() {
        int n = measurements.size();
        if (n == 0) return 0.0;
        return getStandardDeviation() / Math.sqrt(n);
    }

    /**
     * Calculates the value at a given percentile (0.0 to 100.0).
     * Uses linear interpolation between closest ranks.
     *
     * @param percentile the percentile to compute.
     * @return the percentile value in nanoseconds.
     */
    public synchronized double getPercentile(double percentile) {
        if (percentile < 0.0 || percentile > 100.0) {
            throw new IllegalArgumentException("Percentile must be between 0 and 100: " + percentile);
        }
        if (measurements.isEmpty()) return 0.0;

        List<Long> sorted = new ArrayList<>(measurements);
        Collections.sort(sorted);

        if (sorted.size() == 1) return sorted.get(0);

        double index = (percentile / 100.0) * (sorted.size() - 1);
        int low = (int) Math.floor(index);
        int high = (int) Math.ceil(index);

        if (low == high) return sorted.get(low);

        double weight = index - low;
        return sorted.get(low) * (1.0 - weight) + sorted.get(high) * weight;
    }

    /**
     * Returns the median (50th percentile).
     *
     * @return the median.
     */
    public synchronized double getMedian() {
        return getPercentile(50.0);
    }

    /**
     * Computes the 95% confidence interval half-width.
     * Uses a critical Z-score of 1.96 for sample size estimation.
     *
     * @return the margin of error at 95% confidence.
     */
    public synchronized double getConfidenceInterval95() {
        return 1.96 * getStandardError();
    }

    /**
     * Computes the 99% confidence interval half-width.
     * Uses a critical Z-score of 2.576.
     *
     * @return the margin of error at 99% confidence.
     */
    public synchronized double getConfidenceInterval99() {
        return 2.576 * getStandardError();
    }

    /**
     * Calculates the Fisher-Pearson standardized skewness coefficient.
     * Measures the asymmetry of the measurement distribution.
     *
     * @return the skewness value.
     */
    public synchronized double getSkewness() {
        int n = measurements.size();
        if (n < 3) return 0.0;
        double mean = getMean();
        double sd = getStandardDeviation();
        if (sd == 0.0) return 0.0;

        double sumCubedDiff = 0.0;
        for (long val : measurements) {
            double diff = (val - mean) / sd;
            sumCubedDiff += diff * diff * diff;
        }

        double factor = ((double) n) / ((n - 1) * (n - 2));
        return factor * sumCubedDiff;
    }

    /**
     * Calculates the excess kurtosis coefficient.
     * Measures the "tailedness" of the measurement distribution relative to a normal distribution.
     *
     * @return the excess kurtosis.
     */
    public synchronized double getKurtosis() {
        int n = measurements.size();
        if (n < 4) return 0.0;
        double mean = getMean();
        double sd = getStandardDeviation();
        if (sd == 0.0) return 0.0;

        double sumFourthDiff = 0.0;
        for (long val : measurements) {
            double diff = (val - mean) / sd;
            sumFourthDiff += diff * diff * diff * diff;
        }

        double factor1 = (((double) n) * (n + 1)) / ((n - 1) * (n - 2) * (n - 3));
        double factor2 = (3.0 * (n - 1) * (n - 1)) / ((n - 2) * (n - 3));
        return (factor1 * sumFourthDiff) - factor2;
    }

    /**
     * Identifies and removes outliers using the Interquartile Range (IQR) rule.
     * Outliers are values outside [Q1 - 1.5 * IQR, Q3 + 1.5 * IQR].
     *
     * @return the number of removed outliers.
     */
    public synchronized int rejectOutliers() {
        if (measurements.size() < 4) return 0;

        double q1 = getPercentile(25.0);
        double q3 = getPercentile(75.0);
        double iqr = q3 - q1;

        double lowerBound = q1 - 1.5 * iqr;
        double upperBound = q3 + 1.5 * iqr;

        List<Long> cleaned = new ArrayList<>();
        int removedCount = 0;

        for (long val : measurements) {
            if (val >= lowerBound && val <= upperBound) {
                cleaned.add(val);
            } else {
                removedCount++;
            }
        }

        measurements.clear();
        measurements.addAll(cleaned);
        return removedCount;
    }
}
