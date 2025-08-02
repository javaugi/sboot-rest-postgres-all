/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.top10algo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
// Top 10 Algorithms for Coding Interview
//       https://www.programcreek.com/2012/11/top-10-algorithms-for-coding-interview/
//       https://www.programcreek.com/2013/08/leetcode-problem-classification/
public class Top10Algorithms {

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 3};
        System.out.println(Arrays.toString(arr));
        arr = removeDuplicates(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr2 = {1, 2, 2, 3, 3};
        arr2 = removeDups(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    public static int[] removeDups(int[] arr) {
        Set<Integer> set = IntStream.of(arr).boxed()
                .collect(Collectors.toCollection(HashSet::new));
        int[] rv = new int[set.size()];
        int count = 0;
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            rv[count++] = iter.next();
        }

        return rv;
    }

    public static int[] removeDuplicates(int[] A) {
        if (A.length < 2) {
            return A;
        }

        int j = 0;
        int i = 1;

        while (i < A.length) {
            if (A[i] == A[j]) {
                i++;
            } else {
                j++;
                A[j] = A[i];
                i++;
            }
        }

        int[] B = Arrays.copyOf(A, j + 1);

        return B;
    }
}
