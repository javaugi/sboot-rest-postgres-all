/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *
 * @author javaugi
 */
public class PrimeChecker {

    public static void main(String[] args) {
        //bufferedReaderLine();
        
        int[][] intArrs = {{2,1,3,4,5}, {7,18,2,5,10}};
        
        
        // results  2    2   2 3     2 3 5 
        //          7    7   7 2     7 2 5 
        for (int[] intArr: intArrs) {
            int n1 = intArr[0];
            int n2 = intArr[1];
            int n3 = intArr[2];
            int n4 = intArr[3];
            int n5 = intArr[4];

            //always skip the 4th one
            doCheckPrime(n1, n2, n3, n4, n5);            
        }
        
       
    }
    
    

    private static void bufferedReaderLine() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n1 = Integer.parseInt(br.readLine());
            int n2 = Integer.parseInt(br.readLine());
            int n3 = Integer.parseInt(br.readLine());
            int n4 = Integer.parseInt(br.readLine());
            int n5 = Integer.parseInt(br.readLine());
            doCheckPrime(n1, n2, n3, n4, n5);
        } catch (IOException | NumberFormatException e) {
            System.out.println(e);
        }
    }

    private static void doCheckPrime(int n1, int n2, int n3, int n4, int n5) {
        Prime ob = new Prime();
        ob.checkPrime(n1);
        ob.checkPrime(n1, n2);
        ob.checkPrime(n1, n2, n3);
        ob.checkPrime(n1, n2, n3, n4, n5);
    }

    static class Prime {

        public static void checkPrime(int... numbers) {
            for (int num : numbers) {
                if (isPrime(num)) {
                    System.out.print(num + " ");
                }
            }
            System.out.println();
        }

        private static boolean isPrime(int n) {
            if (n <= 1) {
                return false;
            }
            if (n == 2) {
                return true;
            }
            if (n % 2 == 0) {
                return false;
            }
            /*
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) {
                    return false;
                }
            }
            // */
            
            for (int i = 3; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

}

/*
why there is no with 4 parameters ob.checkPrime(n1,n2,n3,n4);?
The code doesn't include a call with exactly 4 parameters (ob.checkPrime(n1,n2,n3,n4)) because the problem's test cases and expected output only require calls with 1, 2, 3, and 5 parameters. Here's why:

Problem Constraints:

The input is always exactly 5 integers (as shown in the sample input)

The output shows exactly 4 lines (as shown in the sample output)

The test cases in the problem are specifically designed to test:

Single argument check

Two arguments check

Three arguments check

Five arguments check

Sample Input/Output:

Input:
2
1
3
4
5

Output:
2 
2 
2 3 
2 3 5 
The output lines correspond to:

First line: checkPrime(n1)

Second line: checkPrime(n1,n2)

Third line: checkPrime(n1,n2,n3)

Fourth line: checkPrime(n1,n2,n3,n4,n5)

Why No 4-Parameter Call:

The problem is testing your ability to handle variable arguments (varargs), not all possible combinations

The test cases are designed to verify:

Basic single-argument case

Intermediate cases (2 and 3 arguments)

A larger case (5 arguments) to ensure the solution scales

Including a 4-parameter call would be redundant for testing purposes

Solution Design:

The checkPrime method uses int... numbers (varargs) to handle any number of arguments

This means it can handle 4 parameters if needed, but the test cases simply don't require it

The method is flexible enough to work with any number of arguments from 1 to many

If you wanted to add a 4-parameter test case, you could modify the main method like this:

java
ob.checkPrime(n1);
ob.checkPrime(n1,n2);
ob.checkPrime(n1,n2,n3);
ob.checkPrime(n1,n2,n3,n4);  // Added 4-parameter call
ob.checkPrime(n1,n2,n3,n4,n5);
But this would produce 5 lines of output instead of the required 4, so it's not included in the original solution. The problem specifically asks for just 4 output lines corresponding to the 1, 2, 3, and 5 parameter calls.
*/


/*
Please write a prime check program for this scenario You are given a class Solution and its main method in the editor. Your task is to create a class Prime. The class Prime should contain a single method checkPrime.

The locked code in the editor will call the checkPrime method with one or more integer arguments. You should write the checkPrime method in such a way that the code prints only the prime numbers.

Please read the code given in the editor carefully. Also please do not use method overloading!

Note: You may get a compile time error in this problem due to the statement below:

  BufferedReader br=new BufferedReader(new InputStreamReader(in));
This was added intentionally, and you have to figure out a way to get rid of the error.

Input Format

There are only five lines of input, each containing one integer.

Output Format

There will be only four lines of output. Each line contains only prime numbers depending upon the parameters passed to checkPrime in the main method of the class Solution. In case there is no prime number, then a blank line should be printed.

Sample Input

2
1
3
4
5
Sample Output

2 
2 
2 3 
2 3 5
Here's the solution to the prime check problem:

java
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.reflect.*;

class Prime {
    public void checkPrime(int... numbers) {
        for (int num : numbers) {
            if (isPrime(num)) {
                System.out.print(num + " ");
            }
        }
        System.out.println();
    }
    
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }
}

public class Solution {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int n1 = Integer.parseInt(br.readLine());
            int n2 = Integer.parseInt(br.readLine());
            int n3 = Integer.parseInt(br.readLine());
            int n4 = Integer.parseInt(br.readLine());
            int n5 = Integer.parseInt(br.readLine());
            Prime ob = new Prime();
            ob.checkPrime(n1);
            ob.checkPrime(n1,n2);
            ob.checkPrime(n1,n2,n3);
            ob.checkPrime(n1,n2,n3,n4,n5);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
Explanation:
Prime Class:

Contains the checkPrime method that takes variable arguments (int... numbers)

For each number, checks if it's prime using the helper method isPrime

Prints prime numbers separated by spaces

Prints a new line after processing each set of numbers

isPrime Method:

Efficiently checks if a number is prime:

Numbers ≤ 1 are not prime

2 is prime

Even numbers > 2 are not prime

Checks divisibility up to √n for odd numbers

Solution Class:

Reads 5 integers from input

Creates an instance of Prime

Calls checkPrime with different numbers of arguments:

First call with 1 number

Second call with 2 numbers

Third call with 3 numbers

Fourth call with 5 numbers

Handling BufferedReader Error:

The code properly handles the BufferedReader by wrapping it in a try-catch block

This resolves the potential IOException that might occur during input reading

Sample Execution:
For input:

2
1
3
4
5
The program will output:

2 
2 
2 3 
2 3 5 
Each line shows the prime numbers from the respective method calls, with non-prime numbers filtered out. The solution efficiently checks for primes without using method overloading, as required by the problem constraints.
*/