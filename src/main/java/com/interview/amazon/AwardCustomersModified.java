/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.amazon;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author javaugi
 */
public class AwardCustomersModified {
public static void main(String[] args) {
        int[] points = {10, 5, 20, 15, 30, 25}; // Example customer points
        System.out.println("Example customer points:" + Arrays.toString(points));
        
        printFinalAwards(points);
    }

    public static void printFinalAwards(int[] points) {
        // Create an array of Customer objects (index + total points)
        Customer[] customers = new Customer[points.length];
        for (int i = 0; i < points.length; i++) {
            // Calculate total points for each customer (assuming they could be in top 3)
            // We'll sort and pick top 3 later
            customers[i] = new Customer(i, points[i]);
        }
        System.out.println("\n Example customers: " + List.of(customers));

        // Sort customers in descending order of their original points
        Arrays.sort(customers, Comparator.comparingInt(Customer::getPoints).reversed());
        System.out.println("\n  reverse sorted customers: " + List.of(customers));

        // Assign awards to top 3 customers
        customers[0].totalPoints = customers[0].points + points.length;     // 1st place
        customers[1].totalPoints = customers[1].points + (points.length - 1); // 2nd place
        customers[2].totalPoints = customers[2].points + (points.length - 2); // 3rd place

        // Sort again based on totalPoints to confirm order (if needed)
        Arrays.sort(customers, 0, 3, Comparator.comparingInt(Customer::getTotalPoints).reversed());

        System.out.println("Final Champion Awards:");
        System.out.println("1st Place: Customer " + customers[0].index + 
                          " | Original Points: " + customers[0].points + 
                          " | Award: +" + points.length + 
                          " | Total: " + customers[0].totalPoints);

        System.out.println("2nd Place: Customer " + customers[1].index + 
                          " | Original Points: " + customers[1].points + 
                          " | Award: +" + (points.length - 1) + 
                          " | Total: " + customers[1].totalPoints);

        System.out.println("3rd Place: Customer " + customers[2].index + 
                          " | Original Points: " + customers[2].points + 
                          " | Award: +" + (points.length - 2) + 
                          " | Total: " + customers[2].totalPoints);
        
        Customer[] customersSub3 = Arrays.copyOfRange(customers, 0, 3);
        System.out.println("FROM COPY - Final Champion Awards:");
        System.out.println("1st Place: Customer " + customersSub3[0].index + 
                          " | Original Points: " + customersSub3[0].points + 
                          " | Award: +" + points.length + 
                          " | Total: " + customersSub3[0].totalPoints);

        System.out.println("2nd Place: Customer " + customersSub3[1].index + 
                          " | Original Points: " + customersSub3[1].points + 
                          " | Award: +" + (points.length - 1) + 
                          " | Total: " + customersSub3[1].totalPoints);

        System.out.println("3rd Place: Customer " + customersSub3[2].index + 
                          " | Original Points: " + customersSub3[2].points + 
                          " | Award: +" + (points.length - 2) + 
                          " | Total: " + customersSub3[2].totalPoints);
    }

    
    /*
Example customer points:[10, 5, 20, 15, 30, 25]
Top 3 Customers (Indices) who receive awards:
1st Place: Customer 4 (Award: 6 points) (Total Award: 36 points)
2nd Place: Customer 5 (Award: 5 points) (Total Award: 30 points)
3rd Place: Customer 2 (Award: 4 points) (Total Award: 24 points)    
    */
    
    // Helper class to store customer index, original points, and total points
    static class Customer {
        int index;
        int points;
        int totalPoints;

        Customer(int index, int points) {
            this.index = index;
            this.points = points;
            this.totalPoints = points; // Initialize with original points
        }

        public int getPoints() {
            return points;
        }

        public int getTotalPoints() {
            return totalPoints;
        }

        @Override
        public String toString() {
            return "\n Customer{" + "index=" + index + ", points=" + points + ", totalPoints=" + totalPoints + '}';
        }
        
    }    
}

/*
Please modify the logic as follows:  1st place → points[i] + points.length points
2nd place → points[i] + (points.length - 1) points
3rd place → points[i] + (points.length - 2) points then print the ith number for the final champion award
Modified Problem Understanding:
Given an array points[] where points[i] represents the points of customer i, we need to determine the top 3 customers who will receive awards in the second round based on the following criteria:

1st place → points[i] + points.length points

2nd place → points[i] + (points.length - 1) points

3rd place → points[i] + (points.length - 2) points

Solution Approach:
Calculate the total points for each customer by adding:

points[i] + (points.length - rank + 1) where rank is 1, 2, or 3.

Sort the customers based on their total points in descending order.

Print the top 3 customers with their original indices and final award points.

Java Program:
java
Copy
import java.util.Arrays;
import java.util.Comparator;

public class AwardCustomersModified {
    public static void main(String[] args) {
        int[] points = {10, 5, 20, 15, 30, 25}; // Example customer points
        
        printFinalAwards(points);
    }

    public static void printFinalAwards(int[] points) {
        // Create an array of Customer objects (index + total points)
        Customer[] customers = new Customer[points.length];
        for (int i = 0; i < points.length; i++) {
            // Calculate total points for each customer (assuming they could be in top 3)
            // We'll sort and pick top 3 later
            customers[i] = new Customer(i, points[i]);
        }

        // Sort customers in descending order of their original points
        Arrays.sort(customers, Comparator.comparingInt(Customer::getPoints).reversed());

        // Assign awards to top 3 customers
        customers[0].totalPoints = customers[0].points + points.length;     // 1st place
        customers[1].totalPoints = customers[1].points + (points.length - 1); // 2nd place
        customers[2].totalPoints = customers[2].points + (points.length - 2); // 3rd place

        // Sort again based on totalPoints to confirm order (if needed)
        Arrays.sort(customers, 0, 3, Comparator.comparingInt(Customer::getTotalPoints).reversed());

        System.out.println("Final Champion Awards:");
        System.out.println("1st Place: Customer " + customers[0].index + 
                          " | Original Points: " + customers[0].points + 
                          " | Award: +" + points.length + 
                          " | Total: " + customers[0].totalPoints);

        System.out.println("2nd Place: Customer " + customers[1].index + 
                          " | Original Points: " + customers[1].points + 
                          " | Award: +" + (points.length - 1) + 
                          " | Total: " + customers[1].totalPoints);

        System.out.println("3rd Place: Customer " + customers[2].index + 
                          " | Original Points: " + customers[2].points + 
                          " | Award: +" + (points.length - 2) + 
                          " | Total: " + customers[2].totalPoints);
    }

    // Helper class to store customer index, original points, and total points
    static class Customer {
        int index;
        int points;
        int totalPoints;

        Customer(int index, int points) {
            this.index = index;
            this.points = points;
            this.totalPoints = points; // Initialize with original points
        }

        public int getPoints() {
            return points;
        }

        public int getTotalPoints() {
            return totalPoints;
        }
    }
}
Output Example:
For points = {10, 5, 20, 15, 30, 25}, the output will be:

Copy
Final Champion Awards:
1st Place: Customer 4 | Original Points: 30 | Award: +6 | Total: 36
2nd Place: Customer 5 | Original Points: 25 | Award: +5 | Total: 30
3rd Place: Customer 2 | Original Points: 20 | Award: +4 | Total: 24
Explanation:
Original Points:

Customer 4: 30

Customer 5: 25

Customer 2: 20

Award Points Added:

1st place: 30 + 6 = 36

2nd place: 25 + 5 = 30

3rd place: 20 + 4 = 24

Final Ranking:

1st: Customer 4 (36 points)

2nd: Customer 5 (30 points)

3rd: Customer 2 (24 points)

Key Takeaways:
Original points determine who gets into the top 3.

Award points are added based on their ranks (points.length, points.length - 1, points.length - 2).

Final ranking is based on the total points after adding awards.
*/