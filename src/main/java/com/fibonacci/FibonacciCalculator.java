/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fibonacci;

import static com.fibonacci.FibonacciCalc.fib;
import static com.fibonacci.FibonacciCalc.fibonacci;
import static com.fibonacci.FibonacciCalc.max;
import java.math.BigInteger;

/**
 *
 * @author javaugi
 */
public class FibonacciCalculator {
    /*
    Explanation of the Methods:

    1. fibonacciIterative(int n):

    This is the most efficient and commonly used approach for calculating Fibonacci numbers.
    It initializes two variables, a and b, to the first two Fibonacci numbers (0 and 1).
    It then iterates from i = 2 up to n. In each iteration:
    It stores the current value of b in a temporary variable temp.
    It calculates the next Fibonacci number by adding a and b and stores it in b.
    It updates a with the previous value of b (stored in temp).
    Finally, it returns the value of b, which represents the nth Fibonacci number.
    2. fibonacciRecursive(int n):

    This method directly implements the mathematical definition of Fibonacci numbers:
    F(0) = 0
    F(1) = 1
    F(n) = F(n-1) + F(n-2) for n > 1
    While conceptually simple, this approach has a significant drawback: it recalculates the same
    Fibonacci numbers multiple times, leading to exponential time complexity. This makes it very inefficient for larger values of n.
    
    3. fibonacciIterativeWithArray(int n):

    This method uses an array to store the Fibonacci numbers as they are calculated.
    It initializes the first two elements of the array (fib[0] and fib[1]).
    It then iterates from i = 2 up to n, calculating each Fibonacci number by summing the previous two values in the array.
    Finally, it returns the value at fib[n]. This approach is also efficient.
    
    4. fibonacciMemoization(int n) and fibonacciMemoizationWrapper(int n):

    This method demonstrates memoization, a dynamic programming technique to optimize recursive solutions.
    fibonacciMemoizationWrapper creates an array memo of size n + 1 to store the calculated Fibonacci numbers.
    It initializes all elements to -1, indicating that they haven't been calculated yet.
    fibonacciMemoization is the recursive function:
    It checks if the result for n is already stored in the memo array (i.e., memo[n] is not -1). If so, it returns the stored value.
    If not, it calculates the Fibonacci number recursively, stores the result in memo[n], and then returns it.
    Memoization avoids redundant calculations by storing and reusing the results of subproblems, significantly 
    improving the efficiency of the recursive approach.
    Choosing the Right Method:

    For most practical purposes, the iterative approach (fibonacciIterative) is the best choice due to its efficiency and simplicity.
    The recursive approach (fibonacciRecursive) is good for understanding the concept but should be avoided for larger 
    values of n due to its performance issues.
    The iterative approach with an array (fibonacciIterativeWithArray) is also efficient and can be useful if you 
    need to access previous Fibonacci numbers in the sequence later.
    Memoization (fibonacciMemoization) is a powerful technique for optimizing recursive solutions in dynamic programming problems.
    The main method in the example demonstrates how to use each of these methods and prints the result for n = 10. 
    You can change the value of n to calculate Fibonacci numbers for different positions in the sequence.    
    */
    // Method 1: Iterative approach (more efficient for larger numbers)
    public static int fibonacciIterative(int n) {
        if (n <= 1) {
            return n;
        }
        int a = 0;
        int b = 1;
        for (int i = 2; i <= n; i++) {
            int temp = b;
            b = a + b;
            a = temp;
        }
        return b;
    }

    // Method 2: Recursive approach (conceptually simpler but less efficient)
    public static int fibonacciRecursive(int n) {
        if (n <= 1) {
            return n;
        }
        return fibonacciRecursive(n - 1) + fibonacciRecursive(n - 2);
    }

    // Method 3: Iterative approach with an array to store Fibonacci numbers
    public static int fibonacciIterativeWithArray(int n) {
        if (n <= 1) {
            return n;
        }
        int fib[] = new int[n + 1];
        fib[0] = 0;
        fib[1] = 1;
        for (int i = 2; i <= n; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    // Method 4: Recursive approach with Memoization (Dynamic Programming)
    private static int memo[];

    public static int fibonacciMemoization(int n) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != -1) {
            return memo[n];
        }
        memo[n] = fibonacciMemoization(n - 1) + fibonacciMemoization(n - 2);
        return memo[n];
    }

    public static int fibonacciMemoizationWrapper(int n) {
        memo = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1; // Initialize memoization array with -1 (indicating not calculated)
        }
        return fibonacciMemoization(n);
    }

    public static void main(String[] args) {
        int n = 10; // Calculate Fibonacci number up to the 10th term

        System.out.println("Fibonacci Iterative (" + n + "): " + fibonacciIterative(n));
        System.out.println("Fibonacci Recursive (" + n + "): " + fibonacciRecursive(n));
        System.out.println("Fibonacci Iterative with Array (" + n + "): " + fibonacciIterativeWithArray(n));
        System.out.println("Fibonacci Memoization (" + n + "): " + fibonacciMemoizationWrapper(n));
        System.out.println("Fibonacci Recursive (" + n + "): \n" + printFib(n));
    }    
    
    public static String printFib(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(fibonacci2(i));
            sb.append(", ");
        }

        return sb.toString().trim();
    }    
    
    public static BigInteger fibonacci2(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        if (i <= 1) {
            return BigInteger.valueOf(i);
        }
        return fibonacci2(i - 1).add(fibonacci2(i - 2));
    }   
}
