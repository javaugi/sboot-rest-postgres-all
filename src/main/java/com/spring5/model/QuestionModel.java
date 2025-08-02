/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.model;

import com.spring5.entity.Question;
import org.springframework.hateoas.EntityModel;

/**
 *
 * @author javaugi
 */
public class QuestionModel extends EntityModel<Question> {
    private final Question question;
    
    public QuestionModel(Question question) {
        this.question = question;
    }
    

    public Long getId() {
        return question.getId();
    }
    
    public String getText() {
        return question.getText();
    }
        
}
