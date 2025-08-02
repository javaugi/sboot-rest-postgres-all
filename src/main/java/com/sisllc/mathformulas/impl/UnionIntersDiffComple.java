package com.sisllc.mathformulas.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/*
Sets Union Intersection Difference and Complement
Union: Set of members that belong to the first set "or" the second set.

Intersection: Set of members that belong to the first set "and" the second set.

Difference: Set of members that belong to the first set "and not" the second set.

Complement: Set of members that belong to the second (universal) set "and not" the first set.

Given the following sets:

   A={2,4,6,8,10}
   B={1,2,3,4,5}

   Union:        A∪B = {1,2,3,4,5,6,8,10}
   Intersection: A∩B = {2,4}
   Difference:   A-B = {6,8,10}
   Complement:   AB = {1,3,5}
 // */

 /*
 * How to find union, intersect, difference and complement of two sets in java?
What is the difference between unions, intersects, differences and complements?
Find Union of sets.
Find Intersect of sets.
Find Complement of sets.
Find Difference of sets.
 */
public class UnionIntersDiffComple {

    public static void main(String args[]) {

        Set<Integer> A = new HashSet<Integer>();
        A.addAll(Arrays.asList(2, 4, 6, 8, 10));

        Set<Integer> B = new HashSet<Integer>();
        B.addAll(Arrays.asList(1, 2, 3, 4, 5));

        /*
         * Union: Set of members that belong to set A "or" set B.
         */
        Set<Integer> union = new HashSet<Integer>();
        union.addAll(A);
        union.addAll(B);

        /*
         * Intersection: Set of members that belong to set A "and" set B.
         */
        Set<Integer> intersection = new HashSet<Integer>();
        intersection.addAll(A);
        intersection.retainAll(B);

        /*
         * Difference: Set of members that belong set A "and not" set B.
         */
        Set<Integer> difference = new HashSet<Integer>();
        difference.addAll(A);
        difference.removeAll(B);

        /*
	 * Complement: Set of members that belong to set B "and not" set A.
         */
        Set<Integer> complement = new HashSet<Integer>();
        complement.addAll(B);
        complement.removeAll(A);

        System.out.println("A: " + A);
        System.out.println("B: " + B);
        System.out.println("union: " + union);
        System.out.println("intersection: " + intersection);
        System.out.println("difference: " + difference);
        System.out.println("complement: " + complement);

    }

    public Set<String> union(Set<String> list1, Set<String> list2) {
        Set<String> returnValue = new TreeSet();
        returnValue.addAll(list1);
        returnValue.addAll(list2);
        return returnValue;
    }

    public Set<String> intersect(Set<String> list1, Set<String> list2) {
        Set<String> returnValue = new TreeSet();
        returnValue.addAll(list1);
        returnValue.retainAll(list2);
        return returnValue;
    }

    public Set<String> diff(Set<String> list1, Set<String> list2) {
        Set<String> returnValue = new TreeSet();
        returnValue.addAll(list1);
        returnValue.removeAll(list2);
        return returnValue;
    }

    public Set<String> complement(Set<String> list1, Set<String> list2) {
        Set<String> returnValue = new TreeSet();
        returnValue.addAll(list2);
        returnValue.removeAll(list1);
        return returnValue;
    }
}
