/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.outbox;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
public class OutboxOrderService {
    @Autowired
    private OutboxOrderRepository orderRepository;
    @Autowired
    private OutboxRepository outboxRepository;

    @Transactional
    public void createOrder(OutboxOrder order) {
        orderRepository.save(order);
        Outbox event = new Outbox();
        event.setAggregateId(order.getAggregateId());
        event.setEventType("OrderCreated");
        event.setPayload("{ \"orderId\": \"" + order.getId() + "\"}");
        outboxRepository.save(event);
    }
}