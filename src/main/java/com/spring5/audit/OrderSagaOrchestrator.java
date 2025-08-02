/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

// Saga Orchestrator

import com.spring5.EventBusConfig;
import lombok.RequiredArgsConstructor;
import net.engio.mbassy.bus.MBassador;
import net.engio.mbassy.listener.Handler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderSagaOrchestrator {

    private final @Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus;
    private final InventoryClient inventoryClient;
    private final PaymentClient paymentClient;

    @Handler
    public void onOrderReceived(OrderReceivedEvent event) {
        try {
            inventoryClient.reserveItems(event.getOrderId(), event.getItems())
                .thenAccept(result -> eventBus.publish(new InventoryReservedEvent(event.getOrderId())))
                .exceptionally(ex -> {
                    eventBus.publish(new OrderFailedEvent(event.getOrderId(), "Inventory reservation failed"));
                    return null;
                });
        } catch (Exception e) {
            eventBus.publish(new OrderFailedEvent(event.getOrderId(), e.getMessage()));
        }
    }

    @Handler
    public void onInventoryReserved(InventoryReservedEvent event) {
        paymentClient.processPayment(event.getOrderId())
            .thenAccept(result -> eventBus.publish(new PaymentProcessedEvent(event.getOrderId())))
            .exceptionally(ex -> {
                // Compensate inventory reservation
                inventoryClient.cancelReservation(event.getOrderId());
                eventBus.publish(new OrderFailedEvent(event.getOrderId(), "Payment processing failed"));
                return null;
            });
    }

    @Handler
    public void onPaymentProcessed(PaymentProcessedEvent event) {
        eventBus.publish(new OrderCompletedEvent(event.getOrderId()));
    }
}
