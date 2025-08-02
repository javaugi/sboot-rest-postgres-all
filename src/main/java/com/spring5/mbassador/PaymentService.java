/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import com.spring5.EventBusConfig;
import lombok.RequiredArgsConstructor;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {
    
    private final @Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus;

    public void processPayment(String orderId, double amount) {
        // Payment processing logic...
        boolean success = Math.random() > 0.2; // 80% success rate for demo
        
        System.out.printf("Payment %s for order %s%n", 
            success ? "succeeded" : "failed", orderId);
        
        // Publish event
        eventBus.publish(new PaymentProcessedEvent(orderId, amount, success));
    }
}