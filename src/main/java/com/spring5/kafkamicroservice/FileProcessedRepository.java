/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.kafkamicroservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author javaugi
 */
public interface FileProcessedRepository extends JpaRepository<FileProcessedRecord, Long> {
    
    @Query("SELECT f FROM FileProcessedRecord f  WHERE f.eventId=(:eventId)")
    boolean existsByEventId(@Param("eventId") String eventId);
}
