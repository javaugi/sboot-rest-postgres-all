/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.misc;

import com.spring5.EventBusConfig;
import com.spring5.mbassador.NotificationHandler;
import com.spring5.mbassador.OrderCreatedEvent;
import net.engio.mbassy.bus.MBassador;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EventHandlingTest {
    
    @Test
    public void testOrderCreatedEvent() {
        // Setup
        MBassador<Object> eventBus = new EventBusConfig().eventBus();
        NotificationHandler handler = mock(NotificationHandler.class);
        eventBus.subscribe(handler);
        
        // Test
        eventBus.publish(new OrderCreatedEvent("TEST-123", "test@example.com"));
        
        // Verify
        verify(handler, times(1))
            .handleOrderCreated(any(OrderCreatedEvent.class));
    }
}
