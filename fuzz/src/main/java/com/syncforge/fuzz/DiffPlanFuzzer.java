package com.syncforge.fuzz;

import com.syncforge.diff.DiffEngine;
import com.syncforge.snapshot.SnapshotModel;
import com.syncforge.snapshot.SnapshotReader;
import com.syncforge.planner.DAGPlanner;

public class DiffPlanFuzzer {
    public static void fuzzerTestOneInput(byte[] data) {
        if (data.length < 10) return;
        try {
            int midpoint = data.length / 2;
            byte[] firstHalf = new byte[midpoint];
            byte[] secondHalf = new byte[data.length - midpoint];
            System.arraycopy(data, 0, firstHalf, 0, midpoint);
            System.arraycopy(data, midpoint, secondHalf, 0, secondHalf.length);

            // Iterate multiple times to trigger temporal bugs
            for (int i = 0; i < 200; i++) {
                SnapshotReader reader = new SnapshotReader();
                SnapshotModel oldSnap = reader.read(firstHalf);
                SnapshotModel newSnap = reader.read(secondHalf);

                new DAGPlanner().plan(new DiffEngine().diff(oldSnap, newSnap));
            }
        } catch (Exception expected) {
            // Expected exceptions
        }
    }
}
