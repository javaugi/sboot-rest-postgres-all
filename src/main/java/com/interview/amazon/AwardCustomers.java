/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.amazon;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author javaugi
 */
public class AwardCustomers {
    
    public static void main(String[] args) {
        int[] points = {10, 5, 20, 15, 30, 25}; // Example customer points
        System.out.println("Example customer points:" + Arrays.toString(points));
        
        int[] awards = determineAwards(points);
        
        System.out.println("Top 3 Customers (Indices) who receive awards:");
        System.out.println("1st Place: Customer " + awards[0] + " (Award: " + points.length + " points)"  + " (Total Award: " + (points.length + points[awards[0]]) + " points)");
        System.out.println("2nd Place: Customer " + awards[1] + " (Award: " + (points.length - 1) + " points)"  + " (Total Award: " + (points.length - 1 + points[awards[1]]) + " points)");
        System.out.println("3rd Place: Customer " + awards[2] + " (Award: " + (points.length - 2) + " points)"  + " (Total Award: " + (points.length - 2 + points[awards[2]]) + " points)");
    }
    
    public static int[] determineAwards(int[] points) {
        // Create an array of Customer objects (index + points)
        Customer[] customers = new Customer[points.length];
        for (int i = 0; i < points.length; i++) {
            customers[i] = new Customer(i, points[i]);
        }

        // Sort customers in descending order of points
        Arrays.sort(customers, Comparator.comparingInt(Customer::getPoints).reversed());

        // Extract the top 3 customer indices
        int[] top3Indices = new int[3];
        top3Indices[0] = customers[0].index; // 1st place
        top3Indices[1] = customers[1].index; // 2nd place
        top3Indices[2] = customers[2].index; // 3rd place

        return top3Indices;
    }

    // Helper class to store customer index and points
    static class Customer {

        int index;
        int points;
        
        Customer(int index, int points) {
            this.index = index;
            this.points = points;
        }
        
        public int getPoints() {
            return points;
        }
    }
    
}
/*
I have a integer array points[] that stores customer's points that customer i has points of points[i]. The second round will award three customers with points of points[].length, points.length -1, and points.length -2 to determine the final award. Who will receive the final award? Please write a Java program to prove
Problem Understanding:
Given an array points[] where points[i] represents the points of customer i, we need to determine the top 3 customers who will receive awards in the second round based on the following criteria:

1st place → points.length points

2nd place → points.length - 1 points

3rd place → points.length - 2 points

Solution Approach:
Sort the array in descending order to identify the top 3 customers.

Assign awards:

1st place → points.length points (highest award)

2nd place → points.length - 1 points

3rd place → points.length - 2 points

Return the indices of the top 3 customers.

Java Program:
java
Copy
import java.util.Arrays;
import java.util.Comparator;

public class AwardCustomers {
    public static void main(String[] args) {
        int[] points = {10, 5, 20, 15, 30, 25}; // Example customer points
        
        int[] awards = determineAwards(points);
        
        System.out.println("Top 3 Customers (Indices) who receive awards:");
        System.out.println("1st Place: Customer " + awards[0] + " (Award: " + points.length + " points)");
        System.out.println("2nd Place: Customer " + awards[1] + " (Award: " + (points.length - 1) + " points)");
        System.out.println("3rd Place: Customer " + awards[2] + " (Award: " + (points.length - 2) + " points)");
    }

    public static int[] determineAwards(int[] points) {
        // Create an array of Customer objects (index + points)
        Customer[] customers = new Customer[points.length];
        for (int i = 0; i < points.length; i++) {
            customers[i] = new Customer(i, points[i]);
        }

        // Sort customers in descending order of points
        Arrays.sort(customers, Comparator.comparingInt(Customer::getPoints).reversed());

        // Extract the top 3 customer indices
        int[] top3Indices = new int[3];
        top3Indices[0] = customers[0].index; // 1st place
        top3Indices[1] = customers[1].index; // 2nd place
        top3Indices[2] = customers[2].index; // 3rd place

        return top3Indices;
    }

    // Helper class to store customer index and points
    static class Customer {
        int index;
        int points;

        Customer(int index, int points) {
            this.index = index;
            this.points = points;
        }

        public int getPoints() {
            return points;
        }
    }
}
Output Example:
For points = {10, 5, 20, 15, 30, 25}, the output will be:

Copy
Top 3 Customers (Indices) who receive awards:
1st Place: Customer 4 (Award: 6 points)
2nd Place: Customer 5 (Award: 5 points)
3rd Place: Customer 2 (Award: 4 points)
Explanation:
Sorting the customers by points in descending order gives:

[30, 25, 20, 15, 10, 5]

Top 3 customers (indices):

1st place: Customer 4 (30 points) → Award: 6 points

2nd place: Customer 5 (25 points) → Award: 5 points

3rd place: Customer 2 (20 points) → Award: 4 points

Key Takeaways:
Sorting helps identify the top customers.

Awards are assigned based on points.length, points.length - 1, and points.length - 2.

Indices are preserved to correctly identify customers.
 */
