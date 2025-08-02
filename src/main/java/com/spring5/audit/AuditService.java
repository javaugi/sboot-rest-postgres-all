/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.audit;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.ElasticsearchException;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {

    @Autowired
    private ElasticsearchClient elasticsearchClient;

    public void persistAuditEvent(AuditEvent event) {
        try {
            IndexResponse response = elasticsearchClient.index(IndexRequest.of(i -> i
                .index("audit-logs")
                .id(event.getEventId())
                .document(event)
            ));
            System.out.println("Indexed with version: " + response.version());
        } catch (IOException | ElasticsearchException e) {
            // Log or handle exception securely
            e.printStackTrace();
        }
    }
}
