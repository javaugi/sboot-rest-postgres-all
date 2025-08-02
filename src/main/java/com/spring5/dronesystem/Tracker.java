/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class Tracker {

    private final ConcurrentMap<String, Position> currentPositions = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, List<Position>> positionHistory = new ConcurrentHashMap<>();
    private final AtomicInteger trackingCount = new AtomicInteger(0);
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    public Tracker() {
        // Schedule periodic position logging
        scheduler.scheduleAtFixedRate(this::logPositions, 1, 1, TimeUnit.MINUTES);
    }

    public void updatePosition(String droneId, double latitude, double longitude, double altitude) {
        Position newPosition = new Position(latitude, longitude, altitude, System.currentTimeMillis());
        currentPositions.put(droneId, newPosition);
        positionHistory.computeIfAbsent(droneId, k -> new CopyOnWriteArrayList<>()).add(newPosition);
        trackingCount.incrementAndGet();
    }

    public Position getCurrentPosition(String droneId) {
        return currentPositions.get(droneId);
    }

    public List<Position> getPositionHistory(String droneId) {
        return new ArrayList<>(positionHistory.getOrDefault(droneId, Collections.emptyList()));
    }

    public int getTrackingCount() {
        return trackingCount.get();
    }

    private void logPositions() {
        // Implementation for logging positions to persistent storage
    }

    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
