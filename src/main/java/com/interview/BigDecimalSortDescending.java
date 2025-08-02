/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author javaugi
 */
public class BigDecimalSortDescending {

    public static void main(String[] args) {
        // Input data
        String[] input = {"9",
            "-100", "50", "0", "56.6", "90",
            "0.12", ".12", "02.34", "000.000"
        };
        System.out.println("Original=" + Arrays.toString(input));
        
        String[] input0 = Arrays.copyOfRange(input, 0, input.length);
        System.out.println("Original copied=" + Arrays.toString(input0));

        String first = input[0];
        String[] input2 = Arrays.copyOfRange(input, 1, input.length);
        System.out.println("Original copied 1 to n=" + Arrays.toString(input2));

        System.out.println("Solution 1 \n");
        sortPrint1(first, input2);
        System.out.println("Solution 2 \n");
        sortPrint2(first, input2);

        System.out.println("Solution 3 \n");
        int n = 9;
        String[] input3 = {
            "-100", "50", "0", "56.6", "90",
            "0.12", ".12", "02.34", "000.000"
        };
        sortPrint3(n, input3);
    }

    /*
Sample Input

9
-100
50
0
56.6
90
0.12
.12
02.34
000.000
Sample Output

90
56.6
50
02.34
0.12
.12
0
000.000
-100    
    */
    private static void run0() {        
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n + 2];
        for (int i = 0; i < n; i++) {
            s[i] = sc.next();
        }
        sc.close();

        //Write your code here
        List<String> originalNumbers = new ArrayList<>();
        for (String v : s) {
            if (v != null && !v.isEmpty()) {
                originalNumbers.add(v);
            }
        }

        List<String> sortedNumbers = new ArrayList<>(originalNumbers);
        // Custom comparator to sort based on BigDecimal value
        sortedNumbers
                .sort((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)));

        s = new String[sortedNumbers.size()];
        int ndx = 0;
        for (String v : sortedNumbers) {
            s[ndx] = v;
            ndx++;
        }
        //System.out.println("Original Sorted array=" + Arrays.toString(s));
        n = s.length;
        //Output
        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }
    }

    private static void sortPrint3(int n, String[] s) {
        System.out.println("1 sortPrint3 n=" + 9 + "-array=" + Arrays.toString(s));
        List<BigDecimal> decimals = new ArrayList<>();
        for (String num : s) {
            if (num != null && !num.isEmpty()) {
                decimals.add(new BigDecimal(num));
            }
        }
        System.out.println("2 sortPrint3 decimals=" + decimals);

        // Sort in descending order
        Collections.sort(decimals, Collections.reverseOrder());
        System.out.println("3 Done sorting  sortPrint3 decimals=" + decimals);

        s = new String[1 + decimals.size()];
        s[0] = "" + n;
        int ndx = 1;
        for (BigDecimal v : decimals) {
            s[ndx] = v.toString();
            ndx++;
        }
        //System.out.println("Original Sorted array=" + Arrays.toString(s));
        System.out.println("4 FINAL s=" + Arrays.toString(s));
        n = s.length;
        //Output
        for (int i = 0; i < n; i++) {
            System.out.println(s[i]);
        }
    }

    private static void sortPrint2(String first, String[] input) {
        System.out.println(first);
        Arrays.stream(input)
                .sorted((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)))
                .forEach(System.out::println);
    }

    private static void sortPrint1(String first, String[] input) {
        // Convert to BigDecimal and sort in descending order
        /*
        List<BigDecimal> decimals = new ArrayList<>();
        for (String num : input) {
            decimals.add(new BigDecimal(num));
        }

        // Sort in descending order
        Collections.sort(decimals, Collections.reverseOrder());
        // */

        // Print the results (preserving original string representation)
        // To match exact input format, we need to track original strings
        List<String> originalNumbers = Arrays.asList(input);
        List<String> sortedNumbers = new ArrayList<>(originalNumbers);

        // Custom comparator to sort based on BigDecimal value
        sortedNumbers.sort((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)));

        // Print the sorted numbers
        System.out.println(first);
        for (String num : sortedNumbers) {
            System.out.println(num);
        }
    }
}

/*
Explanation
Convert Strings to BigDecimal

Ensures correct numerical sorting (avoids lexicographic issues with String sorting).

Sort in Descending Order

Collections.sort(decimals, Collections.reverseOrder()) sorts BigDecimal values in descending order.

Preserve Original String Format

Since BigDecimal parsing removes leading zeros (e.g., 02.34 → 2.34), we instead:

Sort the original strings based on their BigDecimal values.

Use a custom comparator:

java
sortedNumbers.sort((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)));
Print the Result

Maintains the exact input format (e.g., 02.34 instead of 2.34).

Alternative (Using Java Streams)
java
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.*;

public class BigDecimalSortDescending {
    public static void main(String[] args) {
        String[] input = { "-100", "50", "0", "56.6", "90", "0.12", ".12", "02.34", "000.000" };
        
        Arrays.stream(input)
              .sorted((a, b) -> new BigDecimal(b).compareTo(new BigDecimal(a)))
              .forEach(System.out::println);
    }
}
Key Takeaways
BigDecimal ensures correct numerical sorting (handles decimals, negatives, and leading zeros).

Custom comparator sorts strings while preserving formatting.

Descending order is achieved using Collections.reverseOrder() or (b.compareTo(a)).

This approach guarantees accurate numerical sorting while keeping the original string representations intact.
 */
