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
public class PossibleCommonColdRule implements Rule {
    @Override
    public boolean evaluate(Set<Fact> facts) {
        boolean hasSoreThroat = false;
        boolean hasRunnyNose = false;

        for (Fact fact : facts) {
            if (fact.getName().equals("soreThroat") && (Boolean) fact.getValue()) {
                hasSoreThroat = true;
            }
            if (fact.getName().equals("runnyNose") && (Boolean) fact.getValue()) {
                hasRunnyNose = true;
            }
        }
        return hasSoreThroat && hasRunnyNose;
    }

    @Override
    public String getConclusion() {
        return "Possible Common Cold";
    }
}
