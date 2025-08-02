/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import com.spring5.entity.Assessment;
import com.spring5.entity.Patient;
import com.spring5.repository.PatientRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author javaugi
 */
@org.springframework.stereotype.Service
public class PatientService {
    
    
    @Autowired 
    private PatientRepository patientRepository;

    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }
    
    public Patient findById(Long id) {
        return patientRepository.findById(id).orElse(null);
    }

    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    public Patient update(Long id, Patient updatedPatient) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) {
            return null;
        }
        
        BeanUtils.copyProperties(updatedPatient, patient, "id");
        return patientRepository.save(patient);
    }

    public List<Assessment> findAssessmentsByPatientId(Long patientId, AssessmentService assessmentService) {
        return assessmentService.findAll().stream()
                .filter(assessment -> assessment.getPatientId().equals(patientId))
                .collect(Collectors.toList());
    }
}