/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.milsys;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.time.*;

public class SensorFusionSystem {

    private final Map<String, SensorProcessor> sensorProcessors = new ConcurrentHashMap<>();
    private final PriorityBlockingQueue<FusionEvent> eventQueue
            = new PriorityBlockingQueue<>(100, Comparator.comparing(FusionEvent::getPriority));

    private final ExecutorService fusionExecutor = Executors.newWorkStealingPool();
    private volatile boolean shutdown = false;

    public static class FusionEvent {

        private final String sensorId;
        private final double[] values;
        private final Instant timestamp;
        private final int priority; // Military priority level (0-9)

        public FusionEvent(String sensorId, double[] values, Instant timestamp, int priority) {
            this.sensorId = sensorId;
            this.values = values;
            this.timestamp = timestamp;
            this.priority = priority;
        }

        // Getters omitted

        public String getSensorId() {
            return sensorId;
        }

        public double[] getValues() {
            return values;
        }

        public Instant getTimestamp() {
            return timestamp;
        }

        public int getPriority() {
            return priority;
        }
    }

    public void registerSensor(String sensorId,
            Function<double[], Double> processingAlgorithm,
            int normalPriority) {
        sensorProcessors.put(sensorId, new SensorProcessor(sensorId, processingAlgorithm, normalPriority));
    }

    public void startFusionEngine() {
        fusionExecutor.submit(() -> {
            while (!shutdown) {
                try {
                    FusionEvent event = eventQueue.poll(100, TimeUnit.MILLISECONDS);
                    if (event != null) {
                        processEvent(event);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    public void submitSensorData(String sensorId, double[] rawData) {
        SensorProcessor processor = sensorProcessors.get(sensorId);
        if (processor != null) {
            eventQueue.offer(new FusionEvent(
                    sensorId,
                    rawData,
                    Instant.now(),
                    processor.normalPriority
            ));
        }
    }

    private void processEvent(FusionEvent event) {
        SensorProcessor processor = sensorProcessors.get(event.sensorId);
        if (processor != null) {
            try {
                double result = processor.processingAlgorithm.apply(event.values);

                // Military decision threshold check
                if (result > processor.getThreshold()) {
                    triggerDefenseProtocol(event.sensorId, result);
                }
            } catch (Exception e) {
                // Secure error handling
            }
        }
    }

    private void triggerDefenseProtocol(String sensorId, double value) {
        // Implementation would interface with actual defense systems
        System.out.println("ALERT: Sensor " + sensorId + " detected critical value: " + value);
    }

    private static class SensorProcessor {

        private final String sensorId;
        private final Function<double[], Double> processingAlgorithm;
        private final int normalPriority;
        private final AtomicReference<Double> threshold = new AtomicReference<>(1.0);

        public SensorProcessor(String sensorId,
                Function<double[], Double> processingAlgorithm,
                int normalPriority) {
            this.sensorId = sensorId;
            this.processingAlgorithm = processingAlgorithm;
            this.normalPriority = normalPriority;
        }

        public double getThreshold() {
            return threshold.get();
        }

        public void setThreshold(double newThreshold) {
            threshold.set(newThreshold);
        }
    }
}
