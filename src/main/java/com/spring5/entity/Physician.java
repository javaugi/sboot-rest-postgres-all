/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

/**
 *
 * @author javaugi
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Physician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String firstName;
    private String lastName;
    private String licenseNumber;
    private String specialization;
    
    @OneToMany(mappedBy = "supervisingPhysician", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Nurse.class)
    @BatchSize(size = 20)
    private List<Nurse> supervisedNurses;
    
    @OneToMany(mappedBy = "physician", cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Appointment.class)
    @BatchSize(size = 20)
    private List<Appointment> appointments;
}
