/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author javaugi
 */
public class SortMerge {

    public static void main(String[] args) {
        String[] strArr = {"33 Rumpa 3.68","85 Ashis 3.85","56 Samiha 3.75","19 Samara 3.75","22 Fahim 3.76"};
        List<Student> studentList = new ArrayList<>();
        for (String str: strArr) {
            String[] tokens = str.split("\\s+");
            int id = Integer.parseInt(tokens[0]);
            String fname = tokens[1];
            double cgpa = Double.parseDouble(tokens[2]);
            Student student = new Student(id, fname, cgpa);
            studentList.add(student);
        }
        
        sortStudents(studentList);
        for (Student st : studentList) {
            System.out.println(st.getFname());
        }

    }

    public static void scanner() {
        Scanner in = new Scanner(System.in);
        int testCases = Integer.parseInt(in.nextLine());

        List<Student> studentList = new ArrayList<>();
        while (testCases > 0) {
            int id = in.nextInt();
            String fname = in.next();
            double cgpa = in.nextDouble();

            Student st = new Student(id, fname, cgpa);
            studentList.add(st);

            testCases--;
        }

        sortStudents(studentList);

        for (Student st : studentList) {
            System.out.println(st.getFname());
        }
    }

    private static void sortStudents(List<Student> studentList) {
        Collections.sort(studentList, new Comparator<Student>() {
            @Override
            public int compare(Student a, Student b) {
                int i = Double.compare(b.getCgpa(), a.getCgpa());
                if (i != 0) {
                    return i;
                }

                i = a.getFname().compareTo(b.getFname());
                if (i != 0) {
                    return i;
                }

                return Integer.compare(a.getId(), b.getId());
            }

        });
    }

    private static void sortByLambda(List<Student> studentList) {
        Collections.sort(studentList, (Student a, Student b) -> {
            int i = Double.compare(b.getCgpa(), a.getCgpa());
            if (i != 0) {
                return i;
            }

            i = a.getFname().compareTo(b.getFname());
            if (i != 0) {
                return i;
            }
            
            return Integer.compare(a.getId(), b.getId());
        });
    }
}
