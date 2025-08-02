package com.sisllc.mathformulas.ci.ch17.Question17_8;

public class ArrayIntegersContinuousSequenceLargetsSum {

    public static int getMaxSum(int[] a) {
        int maxSum = 0;
        int runningSum = 0;
        for (int i = 0; i < a.length; i++) {
            System.out.println("runing sum=" + runningSum + "-maxSum=" + maxSum);
            runningSum += a[i];
            if (maxSum < runningSum) {
                maxSum = runningSum;
            } else if (runningSum < 0) {
                runningSum = 0;
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = {2, -8, 3, -2, 4, -10};
        System.out.println(getMaxSum(a));
        int[] b = {2, -8, 5, -2, 4, -10};
        System.out.println(getMaxSum(b));
    }
}
