/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.*;
import java.util.concurrent.*;

public class Profiler {

    private final ConcurrentMap<String, OperationStats> operationStats = new ConcurrentHashMap<>();
    private final ThreadLocal<Long> operationStartTime = new ThreadLocal<>();
    private final ScheduledExecutorService reporter = Executors.newSingleThreadScheduledExecutor();

    public Profiler() {
        // Schedule periodic reporting
        reporter.scheduleAtFixedRate(this::reportStats, 1, 1, TimeUnit.MINUTES);
    }

    public void startOperation(String operationName) {
        operationStartTime.set(System.nanoTime());
    }

    public void endOperation(String operationName) {
        long duration = System.nanoTime() - operationStartTime.get();
        operationStats.compute(operationName, (k, v) -> {
            if (v == null) {
                return new OperationStats(duration);
            }
            v.recordDuration(duration);
            return v;
        });
    }

    public OperationStats getOperationStats(String operationName) {
        return operationStats.get(operationName);
    }

    public Map<String, OperationStats> getAllStats() {
        return new HashMap<>(operationStats);
    }

    private void reportStats() {
        // Implementation for reporting performance statistics
    }

    public void shutdown() {
        reporter.shutdown();
        try {
            if (!reporter.awaitTermination(5, TimeUnit.SECONDS)) {
                reporter.shutdownNow();
            }
        } catch (InterruptedException e) {
            reporter.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
