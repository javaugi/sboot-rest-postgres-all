/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author javaugi
 */
/*
Advanced ArrayList Manipulation in Java
Lesson Overview
In this practice-oriented lesson, we'll be tackling Advanced ArrayList Manipulation, a crucial topic in any technical interview. 
Java's ArrayList is part of the java.util package and provides a versatile and powerful data structure used in almost every 
aspect of programming. Mastering advanced manipulation techniques can streamline your code, optimize time complexity, 
and solve complex problems efficiently.

Quick Example
Let's assume we have the following problem — given two ArrayLists sorted in ascending order, we need to merge them into a single sorted list.

The expected algorithm for this task uses two pointers, one for each list, and compares the elements pointed to by these pointers, 
appending the smaller one to the result list. If one of the lists is exhausted, it simply appends the remaining elements from the other list. 
This is a classic example of the Two Pointer Technique, frequently employed in list manipulation problems.

Here's how you can implement the solution in Java:

Coming Up Next: Exercise Time!
Grasping this lesson's subject matter is key to becoming proficient in Java and acing your technical interviews. Following a comprehensive
understanding of the basics, take time to dive into the exercises. Remember, the goal isn't just to memorize these algorithms but to learn 
how to dissect and tackle real-world problems using these tools. Let's proceed to practice!
 */
public class L4ArrayListManipulation {

    public static ArrayList<Integer> mergeSortedLists(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        ArrayList<Integer> mergedList = new ArrayList<>();
        int i = 0, j = 0;

        while (i < list1.size() && j < list2.size()) {
            if (list1.get(i) <= list2.get(j)) {
                mergedList.add(list1.get(i));
                i++;
            } else {
                mergedList.add(list2.get(j));
                j++;
            }
        }

        // Append remaining elements of list1, if any
        while (i < list1.size()) {
            mergedList.add(list1.get(i));
            i++;
        }

        // Append remaining elements of list2, if any
        while (j < list2.size()) {
            mergedList.add(list2.get(j));
            j++;
        }

        return mergedList;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(5);

        list2.add(2);
        list2.add(4);
        list2.add(6);

        ArrayList<Integer> mergedList = mergeSortedLists(list1, list2);
        System.out.println("method 1 \n list1=" + list1 + "\n list2=" + list2 + "\n Merged List: " + mergedList);

        mergedList = mergeSortedLists2(list1, list2);
        System.out.println("method 2\n list1=" + list1 + "\n list2=" + list2 + "\n Merged List: " + mergedList);
    }
    
    public static ArrayList<Integer> mergeSortedLists2(ArrayList<Integer> list1, ArrayList<Integer> list2) {
        list1.addAll(list2);
        Collections.sort(list1);
        return list1;
    }
}
