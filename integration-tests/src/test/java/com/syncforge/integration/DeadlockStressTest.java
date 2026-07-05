package com.syncforge.integration;

import com.syncforge.planner.PathLockManager;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

public class DeadlockStressTest {

    @Test
    public void testConcurrentHierarchicalLocking() throws InterruptedException, ExecutionException {
        PathLockManager lockManager = new PathLockManager();
        int threadCount = 20;
        int operationsPerThread = 50;

        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        List<Callable<Integer>> tasks = new ArrayList<>();

        String[] lockPaths = {
            "src",
            "src/main",
            "src/main/java",
            "src/main/java/com",
            "src/main/java/com/App.java",
            "src/main/resources",
            "src/test",
            "docs",
            "docs/README.md"
        };

        for (int i = 0; i < threadCount; i++) {
            final int threadId = i;
            tasks.add(() -> {
                Random rand = new Random(threadId);
                int successfulLocks = 0;

                for (int op = 0; op < operationsPerThread; op++) {
                    String path = lockPaths[rand.nextInt(lockPaths.length)];
                    boolean writeLock = rand.nextBoolean();

                    if (writeLock) {
                        boolean acquired = lockManager.acquireWriteLock(path);
                        if (acquired) {
                            successfulLocks++;
                            // Simulate small work
                            Thread.sleep(rand.nextInt(3) + 1);
                            lockManager.releaseWriteLock(path);
                        }
                    } else {
                        boolean acquired = lockManager.acquireReadLock(path);
                        if (acquired) {
                            successfulLocks++;
                            Thread.sleep(rand.nextInt(3) + 1);
                            lockManager.releaseReadLock(path);
                        }
                    }
                }
                return successfulLocks;
            });
        }

        List<Future<Integer>> futures = executor.invokeAll(tasks);
        executor.shutdown();
        boolean finished = executor.awaitTermination(15, TimeUnit.SECONDS);
        assertTrue(finished, "Executor did not finish in time - potential deadlock!");

        int totalAcquired = 0;
        for (Future<Integer> f : futures) {
            totalAcquired += f.get();
        }

        System.out.println("Concurrent lock stress test completed. Total locks successfully acquired & released: " + totalAcquired);
        assertTrue(totalAcquired > 0);
    }
}
