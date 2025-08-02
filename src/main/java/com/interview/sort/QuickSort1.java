package com.interview.sort;

import java.util.Arrays;

/**
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
/*
QuickSort
Like Merge Sort, QuickSort is a Divide and Conquer algorithm. It picks an element as
    pivot and partitions the given array around the picked pivot. There are many
    different versions of quickSort that pick pivot in different ways.

Always pick first element as pivot.
Always pick last element as pivot (implemented below)
Pick a random element as pivot.
Pick median as pivot.
The key process in quickSort is partition(). Target of partitions is, given an array
    and an element x of array as pivot, put x at its correct position in sorted array
    and put all smaller elements (smaller than x) before x, and put all greater elements
    (greater than x) after x. All this should be done in linear time.

    This function takes last element as pivot, places
   the pivot element at its correct position in sorted
    array, and places all smaller (smaller than pivot)
   to left of pivot and all greater elements to right
   of pivot
partition (arr[], low, high)
{
    // pivot (Element to be placed at right position)
    pivot = arr[high];

    i = (low - 1)  // Index of smaller element

    for (j = low; j <= high- 1; j++)
    {
        // If current element is smaller than or equal to pivot
        if (arr[j] <= pivot)
        {
            i++;    // increment index of smaller element
            swap arr[i] and arr[j]
        }
    }
    swap arr[i + 1] and arr[high])
    return (i + 1)
}

 */
public class QuickSort1<T extends Comparable<T>> {

    public static void main(String[] args) {
        Integer[] x = {9, 2, 4, 7, 3, 7, 10};
        System.out.println(Arrays.toString(x));

        QuickSort1<Integer> qs = new QuickSort1<>();

        qs.sort(x);
        System.out.println(Arrays.toString(x));
        System.out.println("\n\n\n Another logic ...");
        Integer[] x2 = {9, 2, 4, 7, 3, 7, 10};
        qs.sort2(x2);
        System.out.println(Arrays.toString(x2));
    }

    /**
     * Sort.
     *
     * @param array the array
     */
    public void sort(T[] array) {
        T[] sortedArr = quicksort(array, 0, array.length - 1);
        System.out.println("sort \n original array=" + Arrays.toString(array) + "\n sorted array=" + Arrays.toString(sortedArr));
    }

    /**
     * Quicksort.
     *
     * @param array the array
     * @param lo the lo
     * @param hi the hi
     * @return the t[]
     */
    private T[] quicksort(T[] array, int lo, int hi) {
        if (hi > lo) {
            int partitionPivotIndex = (hi - lo) + lo; // (int) (Math.random() * (hi - lo) + lo);
            int newPivotIndex = partition(array, lo, hi, partitionPivotIndex);
            quicksort(array, lo, newPivotIndex - 1);
            quicksort(array, newPivotIndex + 1, hi);
        }
        return array;
    }

    /**
     * Partition.
     *
     * @param array the array
     * @param lo the lo
     * @param hi the hi
     * @param pivotIndex the pivot index
     * @return the int
     */
    private int partition(T[] array, int lo, int hi, int pivotIndex) {
        T pivotValue = array[pivotIndex];
        swap(array, pivotIndex, hi); // send to the back
        int index = lo;
        for (int i = lo; i < hi; i++) {
            if ((array[i]).compareTo(pivotValue) <= 0) {
                swap(array, i, index);
                index++;
            }
        }
        swap(array, hi, index);
        return index;
    }

    /**
     * Swap.
     *
     * @param array the array
     * @param i the i
     * @param j the j
     */
    private void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public void sort2(T[] array) {
        T[] sortedArr = quickSort(array, 0, array.length - 1);
        System.out.println("sort2 \n original array=" + Arrays.toString(array) + "\n sorted array=" + Arrays.toString(sortedArr));
    }
    
    private T[] quickSort(T[] arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is now
           at right place */
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);  // Before pi
            quickSort(arr, pi + 1, high); // After pi
        }
        return arr;
    }

    private int partition(T[] arr, int low, int high) {
        // pivot (Element to be placed at right position)
        T pivot = arr[high];
        int i = (low - 1); // Index of smaller element

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than or
            // equal to pivot
            if ((arr[j]).compareTo(pivot) <= 0) {
                i++;    // increment index of smaller element
                swap(arr, i, j);
            }
        }
        
        int returnValue = i + 1;
        swap(arr, returnValue, high);
        return returnValue;
    }
}
