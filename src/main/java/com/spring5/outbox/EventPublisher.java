/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.outbox;

import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    public void publish(Outbox event) {
        // Publish the event to message broker
    }
}
