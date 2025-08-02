/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.service;

import com.spring5.entity.Answer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author javaugi
 */
@org.springframework.stereotype.Service
public class AnswerService {
    private final Map<Long, Answer> answers = new HashMap<>();
    private long nextId = 1;

    public Answer save(Answer answer) {
        Answer newAnswer = new Answer(nextId++, answer.getAssessmentId(), answer.getQuestionId(), answer.getAnswerText());
        answers.put(newAnswer.getId(), newAnswer);
        return newAnswer;
    }
    
    public static Answer createAnswer(long assessmentId, long questionId, String value) {
        return Answer.builder()
                .assessmentId(assessmentId)
                .questionId(questionId)
                .answerText(value)
                .build();
    }

    public Answer findById(Long id) {
        return answers.get(id);
    }

    public List<Answer> findAll() {
        return new ArrayList<>(answers.values());
    }    
}
