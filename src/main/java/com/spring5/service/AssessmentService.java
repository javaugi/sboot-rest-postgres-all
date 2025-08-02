/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import com.spring5.entity.Answer;
import com.spring5.entity.Assessment;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
@org.springframework.stereotype.Service
public class AssessmentService {
    private final Map<Long, Assessment> assessments = new HashMap<>();
    private long nextId = 1;

    public Assessment save(Assessment assessment) {
        Assessment newAssessment = new Assessment(nextId++, assessment.getPatientId(), assessment.getDate(), assessment.getStatus());
        assessments.put(newAssessment.getId(), newAssessment);
        return newAssessment;
    }
    
    public static Assessment createAssessment(long patientId, LocalDate date, String status) {
        return Assessment.builder()
                .patientId(patientId)
                .date(date)
                .status(status)
                .build();
    }    

    public Assessment findById(Long id) {
        return assessments.get(id);
    }

    public List<Assessment> findAll() {
        return new ArrayList<>(assessments.values());
    }

    public Assessment update(Long id, Assessment updatedAssessment) {
        if (!assessments.containsKey(id)) {
            return null;
        }
        assessments.put(id, new Assessment(id, updatedAssessment.getPatientId(), updatedAssessment.getDate(), updatedAssessment.getStatus()));
        return assessments.get(id);
    }

    public List<Answer> findAnswersByAssessmentId(Long assessmentId, AnswerService answerService) {
        return answerService.findAll().stream()
                .filter(answer -> answer.getAssessmentId().equals(assessmentId))
                .collect(Collectors.toList());
    }   
}
