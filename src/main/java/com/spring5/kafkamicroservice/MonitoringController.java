/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

//import io.lettuce.core.tracing.Tracer;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Tracer;
import java.util.Map;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    private final KafkaAdminClient adminClient;
    private final MeterRegistry meterRegistry;
    private final Tracer tracer;
    
    public MonitoringController(KafkaAdminClient adminClient, MeterRegistry meterRegistry, Tracer tracer) {
        this.adminClient = adminClient;
        this.meterRegistry = meterRegistry;
        this.tracer = tracer;
        //this.tracer = GlobalOpenTelemetry.getTracer("trading-consumer");
    }    

    @GetMapping("/kafka-health")
    public ResponseEntity<Map<String, Object>> getKafkaHealth() {
        /* TODO
        Span span = tracer.spanBuilder("checkKafkaHealth").startSpan();
        try (Scope scope = span.makeCurrent()) {
            Map<String, Object> healthInfo = new HashMap<>();
            
            // 1. Cluster health
            healthInfo.put("cluster", adminClient.describeCluster().nodes().get()
                .stream()
                .map(node -> Map.of(
                    "id", node.idString(),
                    "host", node.host(),
                    "port", node.port(),
                    "rack", node.rack()
                ))
                .collect(Collectors.toList()));
            
            // 2. Consumer lag
            healthInfo.put("consumerLag", meterRegistry.find("kafka.consumer.lag")
                .tags("group", "file-storage-group")
                .gauge().value());
            
            // 3. Producer metrics
            healthInfo.put("producerMetrics", Map.of(
                "sendRate", meterRegistry.find("kafka.producer.record.send.rate").gauge().value(),
                "errorRate", meterRegistry.find("kafka.producer.record.error.rate").gauge().value()
            ));
            
            return ResponseEntity.ok(healthInfo);
        } catch (Exception e) {
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
        // */
        
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/trace/{tradeId}")
    public ResponseEntity<Map<String, Object>> getTradeTrace(@PathVariable String tradeId) {
        // Implementation would query your tracing backend
        return ResponseEntity.ok(Map.of(
            "tradeId", tradeId,
            "spans", getSpansForTrade(tradeId)
        ));
    }
    
    public String getSpansForTrade(String tradeId) {
        return null;
    }
}