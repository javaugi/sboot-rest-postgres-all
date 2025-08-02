/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaMonitoringService {

    private final MeterRegistry meterRegistry;
    private final KafkaTemplate<?, ?> kafkaTemplate;

    public KafkaMonitoringService(MeterRegistry meterRegistry, @Qualifier("objectKafkaTemplate") KafkaTemplate<?, ?> kafkaTemplate) {
        this.meterRegistry = meterRegistry;
        this.kafkaTemplate = kafkaTemplate;
        //monitorKafkaMetrics();
    }

    /* TODO
    private void monitorKafkaMetrics() {
        // Track message production rates
        meterRegistry.gauge("kafka.producer.messages.sent", 
            kafkaTemplate.metrics().values().stream()
                .filter(m -> m.metricName().name().equals("record-send-rate"))
                .mapToDouble(Metric::value)
                .sum());

        // Track error rates
        meterRegistry.gauge("kafka.producer.errors", 
            kafkaTemplate.metrics().values().stream()
                .filter(m -> m.metricName().name().equals("record-error-rate"))
                .mapToDouble(Metric::value)
                .sum());
    }

    @Scheduled(fixedRate = 30000)
    public void logKafkaStatus() {
        Map<String, Map<MetricName, ? extends Metric>> metrics = kafkaTemplate.metrics();
        metrics.forEach((clientId, clientMetrics) -> {
            clientMetrics.forEach((name, metric) -> {
                if (name.name().endsWith("-rate") || name.name().endsWith("-total")) {
                    LoggerFactory.getLogger(KafkaMonitoringService.class)
                        .info("Kafka Metric {}: {}", name, metric.value());
                }
            });
        });
    }
    // */
}
