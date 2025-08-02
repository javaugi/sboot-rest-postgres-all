/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 *
 * @author javaugi
 */
public class TwoDArraysDemo {

    public static void main(String[] args) {
        //scannerJ8HourGlassSum();
        int[][] arr = {{1, 1, 1, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0,}, {0, 0, 2, 4, 4, 0}, {0, 0, 0, 2, 0, 0}, {0, 0, 1, 2, 4, 0}};

        System.out.println("1. j8HourGlassSum(arr) ");
        System.out.println("1 Results from int[][] maxSum=" + j8HourGlassSum(arr));
        
        List<List<Integer>> list = Arrays.stream(arr)
            .map(row -> IntStream.of(row)
                .boxed()
                .collect(Collectors.toList()))
            .collect(Collectors.toList());
        System.out.println("2. j8HourGlassSum(List<List<Integer>>) ");
        System.out.println("2 Results from j8HourGlassSum(List<List<Integer>>) maxSum=" + j8HourGlassSum(list));
    }
    
    private static void arrayPopulationMethod() {
        try{
            //Scanner
            Scanner scanner = new Scanner(System.in);
            int[][] arr1 = new int[6][6];  // Using 'var' from Java 10
            // Read input
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    arr1[i][j] = scanner.nextInt();
                }
            }
            
            List<List<Integer>> listList1 = new ArrayList<>();
            // Read input into List<List<Integer>>
            for (int i = 0; i < 6; i++) {
                List<Integer> row = new ArrayList<>();
                for (int j = 0; j < 6; j++) {
                    row.add(scanner.nextInt());
                }
                listList1.add(row);
            }         
            
            //BufferedReader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            List<List<Integer>> listListInt = new ArrayList<>();
            IntStream.range(0, 6).forEach(i -> {
                try {
                    listListInt.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            bufferedReader.close();       
            
            //Java 15
            IntStream.range(0, 6).forEach(i -> {
                var row = new ArrayList<Integer>();
                IntStream.range(0, 6).forEach(j -> row.add(scanner.nextInt()));
                listListInt.add(row);
            });
            
            //Java 8
            IntStream.range(0, 6).forEach(i -> {
                List<Integer> row = new ArrayList<Integer>();
                IntStream.range(0, 6).forEach(j -> row.add(scanner.nextInt()));
                listListInt.add(row);
            });
            
            //Java 8
            IntStream.range(0, 6).forEach(i -> {
                List<Integer> row = new ArrayList<Integer>();
                IntStream.range(0, 6).forEach(j -> row.add(arr1[i][j]));
                listListInt.add(row);
            });
        } catch(Exception e) {
            
        }
    }

    private static void scannerJ15HourGlassSum() {
        Scanner scanner = new Scanner(System.in);
        var arr = new int[6][6];  // Using 'var' from Java 10

        // Read input
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int maxSum = j8HourGlassSum(arr);

        System.out.println(maxSum);
        scanner.close();
    }

    private static void scannerJ8HourGlassSum() {
        Scanner scanner = new Scanner(System.in);
        int[][] arr = new int[6][6];

        // Read input
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                arr[i][j] = scanner.nextInt();
            }
        }

        int maxSum = j8HourGlassSum(arr);

        System.out.println(maxSum);
        scanner.close();
    }

    private static void readerJ15ArrayPopulation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            List<List<Integer>> arr = new ArrayList<>();
            IntStream.range(0, 6).forEach(i -> {
                try {
                    arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void scannerArrayPopulation() {
        Scanner scanner = new Scanner(System.in);
        List<List<Integer>> arr = new ArrayList<>();

        // Read input into List<List<Integer>>
        for (int i = 0; i < 6; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < 6; j++) {
                row.add(scanner.nextInt());
            }
            arr.add(row);
        }        
        
        IntStream.range(0, 6).forEach(i -> {
            var row = new ArrayList<Integer>();
            IntStream.range(0, 6).forEach(j -> row.add(scanner.nextInt()));
            arr.add(row);
        });
    }

    private static void readerJ8ArrayPopulation() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            List<List<Integer>> arr = new ArrayList<>();
            IntStream.range(0, 6).forEach(i -> {
                try {
                    arr.add(Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                    .map(Integer::parseInt)
                                    .collect(toList())
                    );
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    Key Differences:
    Variable Declaration:
        Java 8: Explicit type declaration (int[][] arr)
        Java 15: Can use var for local variables (var arr = new int[6][6])
    Modern Features:
        Java 15 can use text blocks (though not utilized in this solution)
        Both versions use the same algorithm for hourglass calculation
    Algorithm:
        Iterates through all possible 3x3 hourglass patterns in the 6x6 array
        Calculates the sum of each hourglass pattern
        Tracks the maximum sum found
    How It Works:
    Input Reading:
        Reads 6 lines of 6 space-separated integers
        Stores them in a 6x6 2D array
    Hourglass Pattern:
        a b c
          d
        e f g
        Sum = a + b + c + d + e + f + g
    Calculation:
        Nested loops iterate through all possible top-left positions of hourglasses
        For each position (i,j), calculates the sum of the 7 elements forming the hourglass
        Keeps track of the maximum sum encountered
    Output:
        Prints the maximum hourglass sum found
    Both solutions will produce the correct output of 19 for the given sample input. The Java 15 version uses some modern language features but implements the same core algorithm.    
     */
    private static int j8HourGlassSum(int[][] arr) {
        int maxSum = Integer.MIN_VALUE;
        // Calculate hourglass sums
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int sum = arr[i][j] + arr[i][j + 1] + arr[i][j + 2]
                        + arr[i + 1][j + 1]
                        + arr[i + 2][j] + arr[i + 2][j + 1] + arr[i + 2][j + 2];

                //if (sum > maxSum) {
                //    maxSum = sum;
                //}
                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
    /* the hour glass sum format
        1 1 1     1 1 0     1 0 0
          1         0         0
        1 1 1     1 1 0     1 0 0    
    */

    private static int j8HourGlassSum(List<List<Integer>> arr) {
        int maxSum = Integer.MIN_VALUE;
        // Calculate hourglass sums
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int sum = arr.get(i).get(j) + arr.get(i).get(j+1) + arr.get(i).get(j+2)
                        + arr.get(i+1).get(j+1)
                        + arr.get(i+2).get(j) + arr.get(i+2).get(j+1) + arr.get(i+2).get(j+2);

                maxSum = Math.max(maxSum, sum);
            }
        }
        return maxSum;
    }
}
/*
You are given a  2D array. An hourglass in an array is a portion shaped like this:

a b c
  d
e f g
For example, if we create an hourglass using the number 1 within an array full of zeros, it may look like this:

1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
0 0 0 0 0 0
Actually, there are many hourglasses in the array above. The three leftmost hourglasses are the following:

1 1 1     1 1 0     1 0 0
  1         0         0
1 1 1     1 1 0     1 0 0
The sum of an hourglass is the sum of all the numbers within it. The sum for the hourglasses above are 7, 4, and 2, respectively.

In this problem you have to print the largest sum among all the hourglasses in the array.

Input Format

There will be exactly  lines, each containing  integers seperated by spaces. Each integer will be between  and  inclusive.

Output Format

Print the answer to this problem on a single line.

Sample Input

1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0
Sample Output

19
Explanation

The hourglass which has the largest sum is:

2 4 4
  2
1 2 4
 */
