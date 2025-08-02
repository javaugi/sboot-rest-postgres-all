package com.interview.sort;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;

/*
 * MergeSort(arr[], l,  r)
If r > l
     1. Find the middle point to divide the array into two halves:
             middle m = (l+r)/2
     2. Call mergeSort for first half:
             Call mergeSort(arr, l, m)
     3. Call mergeSort for second half:
             Call mergeSort(arr, m+1, r)
     4. Merge the two halves sorted in step 2 and 3:
             Call merge(arr, l, m, r)
 *
 * @author javaugi
 */
public class MergeSort {

    public static void mergesort(int[] array) {
        int[] helper = new int[array.length];
        mergesort(array, helper, 0, array.length - 1);
    }

    public static void mergesort(int[] array, int[] helper, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            mergesort(array, helper, low, middle); // Sort left half
            mergesort(array, helper, middle + 1, high); // Sort right half
            merge(array, helper, low, middle, high); // Merge them
        }
    }

    public static void merge(int[] array, int[] helper, int low, int middle, int high) {
        /* Copy both halves into a helper array */
        for (int i = low; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /* Iterate through helper array. Compare the left and right half, 
         * copying back the smaller element from the two halves into the original array.
        */
        while (helperLeft <= middle && helperRight <= high) {
            if (helper[helperLeft] <= helper[helperRight]) {
                array[current] = helper[helperLeft];
                helperLeft++;
            } else { // If right element is smaller than left element
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        /* Copy the rest of the left side of the array into the target array */
        int remaining = middle - helperLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current + i] = helper[helperLeft + i];
        }
    }

    public static void main(String[] args) {
        int size = 20;
        int[] array = AssortedMethods.randomArray(size, 0, size - 1);
        int[] validate = new int[size];
        AssortedMethods.printIntArray(array);
        for (int i = 0; i < size; i++) {
            validate[array[i]]++;
        }
        mergesort(array);
        for (int i = 0; i < size; i++) {
            validate[array[i]]--;
        }
        AssortedMethods.printIntArray(array);
        for (int i = 0; i < size; i++) {
            if (validate[i] != 0 || (i < (size - 1) && array[i] > array[i + 1])) {
                System.out.println("ERROR");
            }
        }
    }

}
