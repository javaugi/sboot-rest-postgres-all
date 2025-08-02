/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;
import org.springframework.stereotype.Component;

@Component
public class NotificationHandler {
    
    @Handler
    public void handleOrderCreated(OrderCreatedEvent event) {
        System.out.printf("[Notification] Order %s created for %s%n", 
            event.getOrderId(), event.getCustomerEmail());
    }
    
    @Handler(delivery = Invoke.Asynchronously)
    public void handleSuccessfulPayment(PaymentProcessedEvent event) {
        if (event.isSuccess()) {
            System.out.printf("[Notification] Payment processed for order %s: $%.2f%n", 
                event.getOrderId(), event.getAmount());
        }
    }
}