 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.listener.Handler;
import org.springframework.stereotype.Component;

@Component
public class AuditHandler {
    
    @Handler(priority = 10) // Higher priority executes first
    public void auditOrder(OrderCreatedEvent event) {
        System.out.printf("[Audit] Order created: %s%n", event.getOrderId());
    }
    
    @Handler
    //@Filter("event.success == false")
    public void auditFailedPayment(PaymentProcessedEvent event) {
        System.err.printf("[Audit] Payment failed for order %s%n", event.getOrderId());
    }
}
