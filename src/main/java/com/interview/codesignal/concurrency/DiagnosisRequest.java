/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal.concurrency;

import java.util.Set;

/**
 *
 * 
 * // --- Concurrency for Processing Multiple Cases ---
 * // Represents a single diagnosis request
 * @author javaugi
 */
public class DiagnosisRequest {
    private int requestId;
    private Set<Fact> symptoms;

    public DiagnosisRequest(int requestId, Set<Fact> symptoms) {
        this.requestId = requestId;
        this.symptoms = symptoms;
    }

    public int getRequestId() {
        return requestId;
    }

    public Set<Fact> getSymptoms() {
        return symptoms;
    }    
}
