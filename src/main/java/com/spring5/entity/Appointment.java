/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.entity;

import com.spring5.validator.AllowedValues;
import com.spring5.validator.ValidAppointment;
import jakarta.persistence.Entity;
import java.time.LocalDateTime;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "APPOINTMENT")
@ValidAppointment
public class Appointment implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reason;
    
    @AllowedValues(value = {"SCHEDULED", "COMPLETED", "CANCELLED"}, 
                  message = "Status must be SCHEDULED, COMPLETED, or CANCELLED")
    private String status; // Scheduled, Completed, Cancelled
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    
    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private Nurse nurse;
    
    @ManyToOne
    @JoinColumn(name = "physician_id")
    private Physician physician;
}
