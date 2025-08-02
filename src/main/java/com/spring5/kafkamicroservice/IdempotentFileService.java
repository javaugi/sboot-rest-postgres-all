/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import java.time.Instant;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.KafkaException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

/**
 *
 * @author javaugi
 */
@Service
//@RequiredArgsConstructor
public class IdempotentFileService {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final FileProcessedRepository processedRepo;
    
    public IdempotentFileService(@Qualifier("stringKafkaTemplate") KafkaTemplate<String, String> kafkaTemplate, FileProcessedRepository processedRepo) {
        this.kafkaTemplate = kafkaTemplate;
        this.processedRepo = processedRepo;
    }
    
    private boolean fileExistByEventId(String eventId) {
        //return false;
        return processedRepo.existsByEventId(eventId);
    }

    @KafkaListener(topics = "${app.topics.file-events}", groupId = "file-storage-group")
    public void handleFileEvent(ConsumerRecord<String, FileStorageEvent> record, Acknowledgment ack) {
        // Check if we've already processed this event
        if (fileExistByEventId(record.value().getEventId())) {
            ack.acknowledge();
            return;
        }        

        try {
            processFile(record.value());
            
            // Record processing
            processedRepo.save(new FileProcessedRecord(
                record.value().getEventId(),
                Instant.now()
            ));
            
            ack.acknowledge();
        } catch (Exception e) {
            throw new KafkaException("Processing failed", e);
        }
    }
    
    private void processFile(FileStorageEvent fileStorageEvent) {
        System.out.println("processFile " + fileStorageEvent);
    }
}