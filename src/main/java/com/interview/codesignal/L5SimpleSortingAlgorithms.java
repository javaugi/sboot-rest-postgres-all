/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

/**
 *
 * @author javaugi
 */
/*
Simple Sorting Algorithms with Java
Lesson Overview
Welcome to the practice-based lesson dedicated to Simple Sorting Algorithms. Sorting is one of the most investigated classes of algorithms 
in computer science. Understanding different methods of sorting becomes more crucial as data size increases. In this lesson, we are going
to revisit basic sorting algorithms: Bubble, Selection, and Insertion sorts. These are not only excellent exercises for those new to 
coding, but they also lay the groundwork for more complex sorting algorithms like QuickSort.

Quick Look at QuickSort
Before we dive into the basics, let's peek into more complex territory by examining QuickSort, a popular divide-and-conquer sorting algorithm. 
The idea behind it is to pick a pivot element from the array and partition the other elements into two arrays according to whether they are 
less than or greater than the pivot. The pivot is then placed in its correct sorted position between the two subarrays. This process is 
recursively applied to each of the two arrays around the pivot, leading to a sorted array.

Here's how you can implement QuickSort in Java:

What's Next: Back to Basics!
On top of QuickSort, we will reverse gears and return to the simple sorting algorithms — Bubble Sort, Selection Sort, and Insertion Sort.
These foundational algorithms will not only reinforce your understanding of the sorting principle but are often used as a stepping stone 
to understanding more complex algorithms. Happy learning, and let's sort it out!
 */
public class L5SimpleSortingAlgorithms {

    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        
        quickSort(arr, 0, arr.length - 1);
    }
    
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotElement = partition(arr, low, high);
            // Recursively sort elements before partition and after partition
            quickSort(arr, low, pivotElement - 1);
            quickSort(arr, pivotElement + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        int highValue = arr[high];
        int i = (low - 1); // Index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= highValue) {
                i++;

                // Swap arr[i] and arr[j]
                swap(arr, i, j);
            }
        }

        // Swap arr[i+1] and arr[high] (or pivot)
        int rtnValue = i + 1;
        swap(arr, rtnValue, high);

        return rtnValue;
    }
    
    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }    

    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("\nOriginal array: ");
        printArray(arr);
        
        System.out.println("\n Sorting array: high=" + (arr.length - 1));
        quickSort(arr);
        //quickSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted array: ");
        printArray(arr);
    }
    
    private static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
