/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import com.patterns.behavioral.state.Context;
import java.nio.charset.StandardCharsets;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.context.annotation.Scope;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;

/**
 *
 * @author javaugi
 */
public class TracingProducerInterceptor {
    

    private final Tracer tracer;

    public TracingProducerInterceptor() {
        this.tracer = GlobalOpenTelemetry.getTracer("trading-producer");
    }

    public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
        Span span = tracer.spanBuilder("kafka.send")
            .setAttribute("messaging.system", "kafka")
            .setAttribute("messaging.destination", record.topic())
            .setAttribute("messaging.destination_kind", "topic")
            .startSpan();
        
        /*
        try (Scope scope = span.makeCurrent()) {
            // Inject trace context into headers
            GlobalOpenTelemetry.getPropagators().getTextMapPropagator()
                .inject(Context.current(), record.headers(), (carrier, key, value) -> {
                    if (carrier != null) {
                        carrier.add(new RecordHeader(key, value.getBytes(StandardCharsets.UTF_8)));
                    }
                });
            
            return record;
        } finally {
            span.end();
        }
        // */
        
        return null;
    }

    // Other required interceptor methods...
}
