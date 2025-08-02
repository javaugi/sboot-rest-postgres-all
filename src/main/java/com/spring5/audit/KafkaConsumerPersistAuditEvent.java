/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

/**
 *
 * @author javaugi
 */
public class KafkaConsumerPersistAuditEvent {
    @Autowired
    private ElasticsearchClient elasticsearchClient;
    
    @KafkaListener(topics = "audit-events")
    public void persistAuditEvent(AuditEvent event) {
        // Index in Elasticsearch
        try{
            elasticsearchClient.index(
                IndexRequest.of(idx -> idx
                    .index("audit-logs")
                    .id(event.getEventId())
                    .document(event)
                ));
        }catch(ElasticsearchException | IOException ex) {
            
        }
    }    

}
