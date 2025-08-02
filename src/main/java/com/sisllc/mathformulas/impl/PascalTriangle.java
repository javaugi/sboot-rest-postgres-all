package com.sisllc.mathformulas.impl;

/*
1
1 1
1 2 1
1 3 3 1
1 4 6 4 1
1 5 10 10 5 1
// */
/**
 * Example of the pascal triangle: This is a java example that demonstrates how
 * to build a Pascal Triangle using*loops.**@author david
 *
 */
public class PascalTriangle {

    public static void main(String[] args) {
        int N = 10; // Integer.parseInt(args[0]);
        int[][] pascal = new int[N + 1][];

        pascal[1] = new int[1 + 2];
        pascal[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            pascal[i] = new int[i + 2];
            for (int j = 1; j < pascal[i].length - 1; j++) {
                pascal[i][j] = pascal[i - 1][j - 1] + pascal[i - 1][j];
            }
        }

        // print results
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < pascal[i].length - 1; j++) {
                System.out.print(pascal[i][j] + " ");
            }
            System.out.println();
        }
    }
}
