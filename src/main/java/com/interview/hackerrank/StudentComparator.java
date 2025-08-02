/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Comparator;

/**
 *
 * @author javaugi
 */
public class StudentComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getCGPA() != s2.getCGPA()) {
            return Double.compare(s2.getCGPA(), s1.getCGPA()); // Higher CGPA first
        }
        if (!s1.getName().equals(s2.getName())) {
            return s1.getName().compareTo(s2.getName()); // Alphabetical order
        }
        return Integer.compare(s1.getID(), s2.getID()); // Lower ID first
    }    
}
