/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.model;

import com.spring5.entity.Assessment;
import java.time.LocalDate;
import org.springframework.hateoas.EntityModel;
/**
 *
 * @author javaugi
 */
public class AssessmentModel extends EntityModel<Assessment> {
    private final Assessment assessment;

    public AssessmentModel(Assessment assessment) {
        this.assessment = assessment;
    }
    
    public Long getId() {
        return assessment.getId();
    }

    public Long getPatientId() {
        return assessment.getPatientId();
    }

    public LocalDate getDate() {
        return assessment.getDate();
    }

    public String getStatus() {
        return assessment.getStatus();
    }
    
    
}
