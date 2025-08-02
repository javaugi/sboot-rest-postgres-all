/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.model;

import com.spring5.entity.Answer;
import org.springframework.hateoas.EntityModel;

/**
 *
 * @author javaugi
 */
public class AnswerModel extends EntityModel<Answer> {
    private final Answer answer;
    
    public AnswerModel(Answer answer) {
        this.answer = answer;
    }
    
    public Long getId() {
        return answer.getId();
    }

    public Long getAssessmentId() {
        return answer.getAssessmentId();
    }

    public Long getQuestionId() {
        return answer.getQuestionId();
    }

    public String getAnswerText() {
        return answer.getAnswerText();
    }
    
    
}
