/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal.concurrency;

import java.util.Set;

/**
 *
 * @author javaugi
 */
public class PossibleFluRule implements Rule {
    @Override
    public boolean evaluate(Set<Fact> facts) {
        boolean highTemperature = false;
        boolean hasCough = false;

        for (Fact fact : facts) {
            if (fact.getName().equals("temperature") && (Double) fact.getValue() > 38.0) {
                highTemperature = true;
            }
            if (fact.getName().equals("cough") && (Boolean) fact.getValue()) {
                hasCough = true;
            }
        }
        return highTemperature && hasCough;
    }

    @Override
    public String getConclusion() {
        return "Possible Flu";
    }
}
