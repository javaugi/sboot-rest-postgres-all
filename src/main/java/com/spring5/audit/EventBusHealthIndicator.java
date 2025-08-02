/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.spring5.EventBusConfig;
import net.engio.mbassy.bus.MBassador;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class EventBusHealthIndicator implements HealthIndicator {

    private final MBassador<Object> eventBus;

    public EventBusHealthIndicator(@Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public Health health() {
        try {
            // Simple ping test
            eventBus.publish(new PingEvent());
            return Health.up().build();
        } catch (Exception e) {
            return Health.down()
                .withException(e)
                .withDetail("message", "Event bus not functioning")
                .build();
        }
    }

    private static class PingEvent {}
}
