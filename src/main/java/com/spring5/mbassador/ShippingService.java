/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.mbassador;

import net.engio.mbassy.listener.Handler;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
public class ShippingService {
    @Handler
    public void prepareShipping(OrderCreatedEvent event) {
        System.out.println("Preparing shipment for order " + event.getOrderId());
    }    
}
