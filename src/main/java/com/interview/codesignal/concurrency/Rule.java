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
public interface Rule {
    boolean evaluate(Set<Fact> facts);
    String getConclusion();
}
