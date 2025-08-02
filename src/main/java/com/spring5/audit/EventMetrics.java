/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import com.spring5.EventBusConfig;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import net.engio.mbassy.bus.MBassador;
import org.apache.kafka.clients.consumer.ConsumerPartitionAssignor.Subscription;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class EventMetrics {

    private final MeterRegistry meterRegistry;
    private final @Qualifier(EventBusConfig.MB_EVENT_BUS) MBassador<Object> eventBus;
    
    @PostConstruct
    public void setupMetrics() {
        /*
        // Count all published events
        eventBus.subscribe((Object event) -> {
            meterRegistry.counter("events.published", 
                "type", event.getClass().getSimpleName())
                .increment();
        });

        // Measure handling time
        eventBus.addHandlerInterceptor(new HandlerInterceptor() {
            @Override
            public void onBeforeHandling(Object message, Subscription subscription, Object listener) {
                Timer.Sample sample = Timer.start(meterRegistry);
                subscription.getMetadata().setProperty("timer.sample", sample);
            }

            @Override
            public void onAfterHandling(Object message, Subscription subscription, Object listener) {
                Timer.Sample sample = (Timer.Sample) subscription.getMetadata().getProperty("timer.sample");
                if (sample != null) {
                    sample.stop(meterRegistry.timer("events.handling.time",
                        "type", message.getClass().getSimpleName(),
                        "handler", listener.getClass().getSimpleName()));
                }
            }
        });
        // */
    }
}