/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class OperationCenter {

    private final Map<String, DroneStatus> droneStatuses = new HashMap<>();
    private final Lock statusLock = new ReentrantLock();
    private final Condition statusUpdated = statusLock.newCondition();
    private final SensorDataAggregator aggregator;
    private final CommunicationManager commManager;
    private final Profiler profiler;
    private final Tracker tracker;

    public OperationCenter(SensorDataAggregator aggregator,
            CommunicationManager commManager,
            Profiler profiler,
            Tracker tracker) {
        this.aggregator = aggregator;
        this.commManager = commManager;
        this.profiler = profiler;
        this.tracker = tracker;
    }

    public void updateDroneStatus(String droneId, DroneStatus status) {
        statusLock.lock();
        try {
            droneStatuses.put(droneId, status);
            statusUpdated.signalAll();
        } finally {
            statusLock.unlock();
        }
    }

    public DroneStatus getDroneStatus(String droneId) {
        statusLock.lock();
        try {
            return droneStatuses.get(droneId);
        } finally {
            statusLock.unlock();
        }
    }

    public void waitForStatusUpdate(String droneId, DroneStatus expectedStatus, long timeoutMillis)
            throws InterruptedException {
        statusLock.lock();
        try {
            long endTime = System.currentTimeMillis() + timeoutMillis;
            while (!expectedStatus.equals(droneStatuses.get(droneId))) {
                long remaining = endTime - System.currentTimeMillis();
                if (remaining <= 0) {
                    break;
                }
                statusUpdated.await(remaining, TimeUnit.MILLISECONDS);
            }
        } finally {
            statusLock.unlock();
        }
    }

    public void processIncomingData(SensorData data) {
        profiler.startOperation("processIncomingData");
        try {
            aggregator.addData(data);
            tracker.updatePosition(data.getDroneId(),
                    data.getLatitude(),
                    data.getLongitude(),
                    data.getAltitude());
            // Additional processing logic
        } finally {
            profiler.endOperation("processIncomingData");
        }
    }

    public void sendCommand(String droneId, DroneCommand command) {
        profiler.startOperation("sendCommand");
        try {
            // Implementation for sending commands to drones
        } finally {
            profiler.endOperation("sendCommand");
        }
    }
}
