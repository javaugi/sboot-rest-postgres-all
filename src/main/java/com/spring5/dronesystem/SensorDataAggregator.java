/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/*
Defense-Specific Knowledge
    Integrated Sensor Architecture (ISA): Research basic concepts of sensor fusion and data integration
    MIL-STD-498: Understand the documentation and development standards
    Security Practices:
    Secure coding standards (CERT, OWASP)
    Data encryption in transit and at rest
    Authentication/authorization mechanisms
*/

/*

3. Behavioral Preparation

STAR Method Responses
Prepare examples demonstrating:

Complex Problem Solving:
    "Describe a time you debugged a complex multithreading issue"
    Highlight your systematic approach and tools used (jstack, VisualVM)

Team Collaboration:
    "Give an example of working with geographically dispersed teams"
    Mention tools (Slack, Zoom, Jira) and strategies (timezone management)

Defense-Specific Scenarios:
    "Describe how you've handled sensitive data or systems"
    Emphasize security protocols and compliance

Sample Response Structure:
    Situation:  Working on a sensor data processing system with strict latency requirements
    Task:       Needed to reduce processing time by 50% to meet military spec
    Action:     Profiled application, identified bottleneck in serialization, implemented custom binary protocol
    Result:     Achieved 60% reduction, system met all operational requirements

*/

public class SensorDataAggregator {

    private final ConcurrentMap<String, SensorData> latestData = new ConcurrentHashMap<>();
    private final BlockingQueue<SensorData> dataQueue = new LinkedBlockingQueue<>();
    private final AtomicLong processedCount = new AtomicLong(0);
    private final ExecutorService processingPool;
    private volatile boolean running = true;
    
    private final Map<String, AtomicDouble> sensorReadings = new ConcurrentHashMap<>();
    
    public void updateReading(String sensorId, double value) {
        sensorReadings.computeIfAbsent(sensorId, k -> new AtomicDouble()).set(value);
    }
    
    public double getAverageReading() {
        return sensorReadings.values().stream()
            .mapToDouble(AtomicDouble::get)
            .average()
            .orElse(0.0);
    }    

    public SensorDataAggregator(int poolSize) {
        this.processingPool = Executors.newFixedThreadPool(poolSize);
        startProcessing();
    }

    public void addData(SensorData data) {
        dataQueue.offer(data);
    }

    private void startProcessing() {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            processingPool.submit(() -> {
                while (running) {
                    try {
                        SensorData data = dataQueue.poll(100, TimeUnit.MILLISECONDS);
                        if (data != null) {
                            processData(data);
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
            });
        }
    }

    private void processData(SensorData data) {
        latestData.put(data.getDroneId(), data);
        processedCount.incrementAndGet();
        // Additional processing logic here
    }

    public SensorData getLatestData(String droneId) {
        return latestData.get(droneId);
    }

    public Map<String, SensorData> getAllLatestData() {
        return new HashMap<>(latestData);
    }

    public long getProcessedCount() {
        return processedCount.get();
    }

    public void shutdown() {
        running = false;
        processingPool.shutdown();
        try {
            if (!processingPool.awaitTermination(5, TimeUnit.SECONDS)) {
                processingPool.shutdownNow();
            }
        } catch (InterruptedException e) {
            processingPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
