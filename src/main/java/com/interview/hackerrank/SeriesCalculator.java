/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Scanner;

/**
 *
 * @author javaugi
 */
public class SeriesCalculator {
    public static void main(String[] args) {
        //runScanner();
        runArray();
    }

    private static void runScanner() {
        Scanner scanner = new Scanner(System.in);
        
        // Read number of queries
        System.out.print("Enter number of queries: ");
        int q = scanner.nextInt();
             
        for (int i = 0; i < q; i++) {
            // Read a, b, and n for each query
            System.out.print("Enter a, b, and n (space-separated): ");
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int n = scanner.nextInt();
            
            // Calculate and print the series
            printSeries(a, b, n);
        }
        
        scanner.close();
    }

    public static void runArray() {        
        int[][] intArr = {{0, 2, 10}, {5, 3, 5}};

        for (int[] row: intArr) {
            printSeries(row[0], row[1], row[2]);
        }
       
    }
    //a + 2⁰×b + (a + 2⁰×b + 2¹×b) + (a + 2⁰×b + 2¹×b + 2²×b
    public static void printSeries(int a, int b, int n) {
        int current = a;
        for (int i = 0; i < n; i++) {
            // Calculate each term: a + 2^0*b + 2^1*b + ... + 2^i*b
            current += Math.pow(2, i) * b;
            System.out.print(current + " ");
        }
        System.out.println(); // Move to next line after each series
    }    
}

/*
Explanation: Input Handling:

The program first reads the number of queries (q)
For each query, it reads three integers: a, b, and n
Series Calculation:

The series is calculated as:
    Term 0: a + 2⁰×b
    Term 1: a + 2⁰×b + 2¹×b
    Term 2: a + 2⁰×b + 2¹×b + 2²×b
    ...
    Term n-1: a + Σ (from i=0 to n-1) 2ⁱ×b

Output:
    Each series is printed as space-separated integers
    A new line is started after each complete series

Example Usage:
Input:
    2
    0 2 5
    5 3 4
Output:
    2 6 14 30 62 
    8 14 26 50 

Alternative Implementation (Using Bit Shift for Powers of 2): For better performance, you can replace Math.pow(2, i) with (1 << i):

    current += (1 << i) * b;
This version is more efficient as it uses bit shifting instead of floating-point exponentiation.
Would you like me to modify the solution for any specific constraints or requirements?
*/
