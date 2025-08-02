/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.spring5.EventBusConfig;
import java.util.LinkedList;
import java.util.Queue;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class TransactionalEventPublisher {

    private final MBassador<Object> eventBus;
    private final ThreadLocal<Queue<Object>> eventQueue = ThreadLocal.withInitial(LinkedList::new);

    public TransactionalEventPublisher(@Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus) {
        this.eventBus = eventBus;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleTransactionCompletion(TransactionCompletedEvent event) {
        Queue<Object> events = eventQueue.get();
        while (!events.isEmpty()) {
            eventBus.publish(events.poll());
        }
        eventQueue.remove();
    }

    public void publishAfterCommit(Object event) {
        eventQueue.get().add(event);
    }
}
