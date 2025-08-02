package com.interview.sort;

import com.sisllc.mathformulas.bigo.*;
import java.util.Arrays;

/**
 * Quicksort is a divide and conquer algorithm. It first divides a large list
 * into two smaller sub-lists and then recursively sort the two sub-lists. If we
 * want to sort an array without any extra space, quicksort is a good option. On
 * average, time complexity is O(n log(n)). The basic step of sorting an array
 * are as follows:
 *
 * Select a pivot, normally the middle one From both ends, swap elements and
 * make left elements < pivot and all right > pivot Recursively sort left part
 * and right part
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class QuickSortExample {

    public static void main(String[] args) {
        int[] x = {9, 2, 4, 7, 3, 7, 10};
        System.out.println(Arrays.toString(x));

        int low = 0;
        int high = x.length - 1;

        quickSort(x, low, high);
        System.out.println(Arrays.toString(x));
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (arr == null || arr.length == 0) {
            return;
        }

        if (low >= high) {
            return;
        }

        // pick the pivot
        int middle = low + (high - low) / 2;
        int pivot = arr[middle];

        // make left < pivot and right > pivot
        int i = low, j = high;
        while (i <= j) {
            // Find element on left that should be on right
            while (arr[i] < pivot) {
                i++;
            }

            // Find element on right that should be on left
            while (arr[j] > pivot) {
                j--;
            }

            // Swap elements, and move left and right indices
            if (i <= j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        // recursively sort two sub parts
        if (low < j) {
            quickSort(arr, low, j);
        }

        if (high > i) {
            quickSort(arr, i, high);
        }
    }

}
