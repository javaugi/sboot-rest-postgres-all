/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.controller;

import com.spring5.entity.MedicalHistory;
import com.spring5.entity.Patient;
import com.spring5.repository.MedicalHistoryRepository;
import com.spring5.repository.PatientRepository;
import com.spring5.resource.PatientResource;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author javaugi
 */
@RestController
@RequestMapping("/apisec/patients")
public class PatientApiSecController {
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private MedicalHistoryRepository medicalHistoryRepository;

    @GetMapping("/{id}/medical-history")
    @PreAuthorize("hasRole('PHYSICIAN') or hasRole('NURSE')")
    public ResponseEntity<MedicalHistory> getMedicalHistory(@PathVariable Long id) {
        return ResponseEntity.ok(medicalHistoryRepository.findById(id).orElse(null));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('NURSE')")
    public ResponseEntity<PatientResource> createPatient(@RequestBody Patient patient) {
        patient = patientRepository.save(patient);
        return ResponseEntity.ok(new PatientResource(patient));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        Optional<Patient> patientOpt = patientRepository.findById(id);

        if (patientOpt.isPresent()) {
            patientRepository.deleteById(id); // Use deleteById for deleting by ID
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content for successful deletion
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found if the product doesn't exist
        }
    }
    
}
