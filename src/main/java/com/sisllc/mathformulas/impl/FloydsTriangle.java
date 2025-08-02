package com.sisllc.mathformulas.impl;

/**
 * Floyd's triangle is a right-angled triangular array of natural numbers, used
 * in computer science education.
 *
 * It is defined by filling the rows of the triangle with consecutive numbers,
 * starting with a 1 in the top left corner.
 *
 * @author david
 *
 */
public class FloydsTriangle {

    public static void main(String args[]) {
        print(7);
        print(10);
    }

    private static void print(int rows) {
        int n = 1;
        System.out.println("\nFloyd's triangle:");
        for (int c = 1; c <= rows; c++) {
            System.out.print("row " + c + "    ");
            for (int d = 1; d <= c; d++) {
                System.out.print(n + " ");
                n++;
            }
            System.out.println();
        }
    }

}
