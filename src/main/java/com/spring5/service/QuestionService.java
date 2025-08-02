/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import com.spring5.entity.Question;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author javaugi
 */
@org.springframework.stereotype.Service
public class QuestionService {
    private final Map<Long, Question> questions = new HashMap<>();
    private long nextId = 1;

    public Question save(Question question) {
        Question newQuestion = new Question(nextId++, question.getText());
        questions.put(newQuestion.getId(), newQuestion);
        return newQuestion;
    }
    
    public static Question createQuestion(String text) {
        return Question.builder()
                .text(text)
                .build();
    }       

    public Question findById(Long id) {
        return questions.get(id);
    }

    public List<Question> findAll() {
        return new ArrayList<>(questions.values());
    }    
}
