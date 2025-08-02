/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import io.opentelemetry.context.propagation.TextMapGetter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.apache.http.Header;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.web.servlet.function.ServerRequest.Headers;

public class TracingConsumerInterceptor implements ConsumerInterceptor<String, Object> {

    private final Tracer tracer;

    public TracingConsumerInterceptor() {
        this.tracer = GlobalOpenTelemetry.getTracer("trading-consumer");
    }
    
    @Override
    public void configure(Map<String, ?> props) {
        /* TODO
        props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);    
        // */
    }
    
    @Override
    public void close() {
        
    }
    
    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> map) {
        
    }

    @Override
    public ConsumerRecords<String, Object> onConsume(ConsumerRecords<String, Object> records) {
        for (ConsumerRecord<String, Object> record : records) {
            Context context = null; /* TODO GlobalOpenTelemetry.getPropagators().getTextMapPropagator()
                .extract(Context.current(), record.headers(), new TextMapGetter<Headers>() {
                    @Override
                    public Iterable<String> keys(Headers carrier) {
                        return StreamSupport.stream(carrier.spliterator(), false)
                            .map(Header::key)
                            .collect(Collectors.toList());
                    }

                    @Override
                    public String get(Headers carrier, String key) {
                        Header header = null; 
                            .filter(h -> h.key().equals(key))
                            .findFirst()
                            .orElse(null); 
                        return header != null ? new String(header.getValue()) : null;
                    }
                });
            // */
            
            if (context == null) {
                continue;
            }

            try (Scope scope = context.makeCurrent()) {
                Span span = tracer.spanBuilder("kafka.consume")
                    .setParent(context)
                    .setAttribute("messaging.system", "kafka")
                    .setAttribute("messaging.destination", record.topic())
                    .setAttribute("messaging.destination_kind", "topic")
                    .setAttribute("messaging.operation", "process")
                    .startSpan();
                
                span.end();
            }
        }
        return records;
    }

    // Other required interceptor methods...
    
}
