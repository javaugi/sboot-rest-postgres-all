/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

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
public class KafkaAuditEvent extends BaseEvent {
    private String entityId;
    private EntityType entityType; // TRADE, FILE, USER
    private AuditAction action; // CREATE, UPDATE, DELETE
    private String userId;
    private Map<String, Object> metadata;

    public KafkaAuditEvent(String id,
            Instant timestamp,
            String sourceSystem,
            String entityId,
            EntityType entityType,
            AuditAction action,
            String userId,
            Map<String, Object> metadata) {
        super(id, timestamp, sourceSystem);
        
        this.entityId = entityId;
        this.entityType =  entityType;
        this.action = action;
        this.userId = userId;
        this.metadata =  metadata;
    }
}
