/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import com.spring5.utils.MapToJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author javaugi
 */
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuditEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    private String eventId;
    private Instant timestamp;
    private String sourceSystem;   
    private String entityId;
    private EntityType entityType; // TRADE, FILE, USER
    private AuditAction action; // CREATE, UPDATE, DELETE
    private String userId;
    
    //You can change columnDefinition = "TEXT" to JSON or JSONB if you're using PostgreSQL.
    //@Convert(converter = MapToJsonConverter.class)
    //@Column(columnDefinition = "JSON")
    //private Map<String, Object> metadata;
    // See MapToJsonConverter on how to resove the issue
    
     public AuditEntry(String entityId,
            EntityType entityType,
            AuditAction action,
            String userId,
            Instant timestamp,
            Map<String, Object> metadata) {
        this.entityId = entityId;
        this.entityType =  entityType;
        this.action = action;
        this.userId = userId;
        this.timestamp = timestamp;
        //this.metadata =  metadata;
    }
     
}
