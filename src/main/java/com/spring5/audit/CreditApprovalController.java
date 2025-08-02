/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/credit")
public class CreditApprovalController {

    private final MeterRegistry meterRegistry;

    @Autowired
    public CreditApprovalController(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @PostMapping("/approve")
    public String approveCredit(@RequestParam double amount) {
        long start = System.nanoTime();

        // Simulate some logic
        try {
            Thread.sleep((long) (Math.random() * 500)); // simulate latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long end = System.nanoTime();
        long latencyInMillis = TimeUnit.NANOSECONDS.toMillis(end - start);

        // Record latency to Prometheus
        meterRegistry.timer("credit_approval_latency").record(end - start, TimeUnit.NANOSECONDS);

        return "Approved $" + amount + " in " + latencyInMillis + " ms";
    }
}