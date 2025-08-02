/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author javaugi
 */
public interface DocumentRepository extends JpaRepository<KafkaDocument, Long> {
    
}
