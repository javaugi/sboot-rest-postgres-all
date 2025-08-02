/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.sisllc.mathformulas.top10algo;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class RemoveArrayDuplicates {

    private static final Logger log = LoggerFactory.getLogger(RemoveArrayDuplicates.class);

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
                A[++j] = A[i++];
                //j++;
                //A[j] = A[i];
                //i++;
            }
        }

        System.out.println("Processed array: " + Arrays.toString(A));
        int[] B = Arrays.copyOf(A, j + 1);

        return B;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 2, 3, 3, 5, 5, 5};
        System.out.println("Original array: " + Arrays.toString(a));
        a = removeDuplicates(a);
        System.out.println("Removed array: " + Arrays.toString(a));
    }
}
