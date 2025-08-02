/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
public class DiagnosisTask implements Callable<String> {
    private final DiagnosisRequest request;
    private final List<Rule> rules;

    public DiagnosisTask(DiagnosisRequest request, List<Rule> rules) {
        this.request = request;
        this.rules = rules;
    }

    @Override
    public String call() throws Exception {
        int requestId = request.getRequestId();
        Set<Fact> facts = request.getSymptoms();
        List<String> conclusions = new ArrayList<>();

        System.out.println(Thread.currentThread().getName() + ": Processing request " + requestId + " with symptoms: " + facts);

        for (Rule rule : rules) {
            if (rule.evaluate(facts)) {
                conclusions.add(rule.getConclusion());
            }
        }

        if (conclusions.isEmpty()) {
            return "Request " + requestId + ": No diagnosis found.";
        } else {
            return "Request " + requestId + ": Possible diagnoses - " + conclusions.stream().collect(Collectors.joining(", ")) + ".";
        }
    }
}
