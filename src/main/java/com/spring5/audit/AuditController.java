/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

//import org.apache.coyote.BadRequestException;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.kafka.core.KafkaTemplate;

@RestController
public class AuditController {
    
    private final KafkaTemplate<String, AuditEvent> kafkaTemplate;
    
    public AuditController(@Qualifier("auditEventKafkaTemplate") KafkaTemplate<String, AuditEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/audit-events")
    public ResponseEntity<Void> logEvent(@RequestBody AuditEvent event) {
        try{
            if (event.getUserId() == null || event.getEntityId() == null) {
                throw new BadRequestException("Missing required fields");
            }

            // Send to Kafka (async)
            kafkaTemplate.send("audit-events", event.getEventId(), event);
        }catch(Exception ex) {
            
        }
        return ResponseEntity.accepted().build();
    }
}
