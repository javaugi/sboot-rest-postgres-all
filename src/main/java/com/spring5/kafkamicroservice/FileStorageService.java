/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import static com.spring5.kafkamicroservice.FileOperation.DELETE;
import static com.spring5.kafkamicroservice.FileOperation.UPDATE;
import static com.spring5.kafkamicroservice.FileOperation.UPLOAD;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @KafkaListener(topics = "${app.topics.file-events}", groupId = "file-storage-group")
    public void handleFileEvent(ConsumerRecord<String, FileStorageEvent> record, Acknowledgment ack) {
        try {
            FileStorageEvent event = record.value();
            logger.info("Received file event: {}", event);

            switch (event.getOperation()) {
                case UPLOAD:
                    // Process file upload (would typically move from temp to permanent storage)
                    processFileUpload(event);
                    break;
                case DELETE:
                    deleteFile(event.getFilePath());
                    break;
                case UPDATE:
                    updateFileMetadata(event.getFileId(), event.getFilePath());
                    break;
            }

            ack.acknowledge();
        } catch (Exception e) {
            logger.error("Error processing file event: {}", e.getMessage());
            // Implement retry or dead-letter queue logic here
        }
    }
    
    @KafkaListener(topics = "${app.topics.file-events}", groupId = "file-storage-group")
    public void handleFileEventWithRuleSwitch(ConsumerRecord<String, FileStorageEvent> record, Acknowledgment ack) {
        try {
            FileStorageEvent event = record.value();
            logger.info("Received file event: {}", event);

            switch (event.getOperation()) {
                case UPLOAD -> // Process file upload (would typically move from temp to permanent storage)
                    processFileUpload(event);
                case DELETE -> deleteFile(event.getFilePath());
                case UPDATE -> updateFileMetadata(event.getFileId(), event.getFilePath());
            }

            ack.acknowledge();
        } catch (Exception e) {
            logger.error("Error processing file event: {}", e.getMessage());
            // Implement retry or dead-letter queue logic here
        }
    }

    private void processFileUpload(FileStorageEvent event) {
        // Implementation would:
        // 1. Validate file
        // 2. Store in appropriate location (S3, filesystem, etc.)
        // 3. Update metadata database
        logger.info("Processing file upload for trade {}: {}", event.getTradeId(), event.getFilePath());
    }

    // Other file operations...
    private void deleteFile(String filePath) {
        logger.info("deleteFile {}", filePath);
    } 
    
    private void updateFileMetadata(String fileId, String filePath) {
        logger.info("updateFileMetadata {}: {}", fileId, filePath);
    }
    
    public String store(MultipartFile confirmationFile){
        return confirmationFile.getName();
    }
}
