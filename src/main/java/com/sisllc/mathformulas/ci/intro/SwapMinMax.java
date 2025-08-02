package com.sisllc.mathformulas.ci.intro;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;

public class SwapMinMax {

    public static int getMinIndex(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }
        return minIndex;
    }

    public static int getMaxIndex(int[] array) {
        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void swap(int[] array, int m, int n) {
        int temp = array[m];
        array[m] = array[n];
        array[n] = temp;
    }

    public static void swapMinMaxBetter(int[] array) {
        int minIndex = getMinIndex(array);
        int maxIndex = getMaxIndex(array);
        swap(array, minIndex, maxIndex);
    }

    public static void swapMinMax(int[] array) {
        int minIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[minIndex]) {
                minIndex = i;
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < array.length; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }

        int temp = array[minIndex];
        array[minIndex] = array[maxIndex];
        array[maxIndex] = temp;
    }

    public static void main(String[] args) {
        int[] array = AssortedMethods.randomArray(10, -10, 10);
        System.out.println("        " + AssortedMethods.arrayToString(array));
        int[] array2 = array;
        int[] array3 = array;
        long time1 = System.currentTimeMillis();
        swapMinMax(array2);
        System.out.println("array2  " + AssortedMethods.arrayToString(array2));
        long time2 = System.currentTimeMillis();
        System.out.println("1. it takes " + (time2 - time1));
        swapMinMaxBetter(array);
        System.out.println("array   " + AssortedMethods.arrayToString(array));
        time1 = System.currentTimeMillis();
        System.out.print("2. it takes " + (time1 - time2));
    }

}
