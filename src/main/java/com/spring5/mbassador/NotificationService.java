/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import com.google.common.eventbus.DeadEvent;
import com.spring5.EventBusConfig;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class NotificationService {

    // Configure async bus
    @Autowired
    private @Qualifier(EventBusConfig.MB_EVENT_BUS_HIGH_PERF) MBassador<Object> asyncBus;

// Handler will execute in a separate thread
    @Handler(delivery = Invoke.Asynchronously)
    public void asyncHandler(OrderCreatedEvent event) {
        // Time-consuming operation
    }

    @Handler(delivery = Invoke.Synchronously)
    //@Filter(condition = "event.amount > 100")
    public void handleLargePayment(PaymentProcessedEvent event) {
        System.out.println("Large payment detected: $" + event.getAmount());
    }
    
    @Handler(delivery = Invoke.Synchronously)
    //@Filter(condition = "event.amount > 1000")
    public void handleVeryLargePayment(PaymentProcessedEvent event) {
        System.out.println("Large payment detected: $" + event.getAmount());
    }

// Higher priority executes first (default = 0)
    @Handler(priority = 10)
    public void highPriorityHandler(OrderCreatedEvent event) {
        System.out.println("High priority handler");
    }

    @Handler(priority = 5)
    public void mediumPriorityHandler(OrderCreatedEvent event) {
        System.out.println("Medium priority handler");
    }

// Handle events with no subscribers
    /**
     *
     * @param deadEvent
     */
    @Handler
    public void handleDeadEvent(DeadEvent deadEvent) {
        System.out.println("No subscribers for: " + deadEvent);
    }

    @Handler
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.println("Notification: Order created - " + event.getOrderId());
    }

    // Register handler for PaymentProcessedEvent
    @Handler
    public void handlePayment(PaymentProcessedEvent event) {
        System.out.println("Notification: Payment processed - $" + event.getAmount());
    }
}
