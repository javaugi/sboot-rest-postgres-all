/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author javaugi
 */
public class LamdaExpressionDemo {

    static class MyMath {

        public PerformOperation isOdd() {
            return n -> n % 2 != 0;
        }

        public PerformOperation isEven() {
            return n -> n % 2 == 0;
        }
        //a whole number greater than 1 that cannot be exactly divided by any whole number other than itself and 1 (e.g. 2, 3, 5, 7, 11).
        public PerformOperation isPrime() {
            return n -> {
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
            };
        }

        public PerformOperation isPalindrome() {
            return n -> {
                String numStr = Integer.toString(n);
                return numStr.equals(new StringBuilder(numStr).reverse().toString());
            };
        }

        public boolean checker(PerformOperation p, int num) {
            return p.check(num);
        }
    }

    private static final int[] intArr = {5, 6};
    private static final List<String> lists = Arrays.asList("1 4", "2 5", "3 898", "1 3", "2 12");
    //results EVEN PRIME PALINDROME ODD COMPOSITE
    private static final List<String> lists2 = Arrays.asList("3 344", "3 454", "2 68", "2 67", "1 12", "1 3");
    //resutls NOT PALINDROME PALINDROME COMPOSITE PRIME EVEN ODD

    public static void main(String[] args) {
        System.out.println("Test 1 with lists=" + lists);
        runDemo1(intArr[0], lists);
        System.out.println("Test 1 Done");
        System.out.println("Test 2 with lists=" + lists2);
        runDemo1(intArr[1], lists2);
        System.out.println("Test Demo 2 with lists=" + lists2);
        runDemo2(lists2);
    }

    private static void runDemo1(int T, List<String> lists) {
        MyMath ob = new MyMath();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        int ndx = 0;
        while (T-- > 0) {
            //String s = br.readLine().trim();
            String s = lists.get(ndx);
            ndx++;

            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }

    private static void runDemo2(List<String> lists) {
        MyMath ob = new MyMath();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        for (String s : lists) {
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }

    private void scannerHankerRank() throws Exception {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T-- > 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

            }
            System.out.println(ans);
        }
    }

    private static void scanner(List<String> lists) {
        PerformOperation isOdd = n -> n % 2 != 0;

        PerformOperation isPrime = n -> {
            if (n < 2) {
                return false;
            }
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        PerformOperation isPalindrome = n -> {
            String original = Integer.toString(n);
            String reversed = new StringBuilder(original).reverse().toString();
            return original.equals(reversed);
        };

        for (String s : lists) {
            StringTokenizer st = new StringTokenizer(s);
            int operation = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            boolean result = false;
            String output = "";

            switch (operation) {
                case 1:
                    result = isOdd.check(number);
                    output = result ? "ODD" : "EVEN";
                    break;
                case 2:
                    result = isPrime.check(number);
                    output = result ? "PRIME" : "COMPOSITE";
                    break;
                case 3:
                    result = isPalindrome.check(number);
                    output = result ? "PALINDROME" : "NOT PALINDROME";
                    break;
                default:
                    output = "Invalid operation";
            }

            System.out.println(output);
        }
    }

    //DeekSeek
    private static void scanner() {
        PerformOperation isOdd = n -> n % 2 != 0;

        PerformOperation isPrime = n -> {
            if (n < 2) {
                return false;
            }
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
        };

        PerformOperation isPalindrome = n -> {
            String original = Integer.toString(n);
            String reversed = new StringBuilder(original).reverse().toString();
            return original.equals(reversed);
        };

        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        while (testCases-- > 0) {
            int operation = scanner.nextInt();
            int number = scanner.nextInt();

            boolean result = false;
            String output = "";

            switch (operation) {
                case 1:
                    result = isOdd.check(number);
                    output = result ? "ODD" : "EVEN";
                    break;
                case 2:
                    result = isPrime.check(number);
                    output = result ? "PRIME" : "COMPOSITE";
                    break;
                case 3:
                    result = isPalindrome.check(number);
                    output = result ? "PALINDROME" : "NOT PALINDROME";
                    break;
                default:
                    output = "Invalid operation";
            }

            System.out.println(output);
        }
    }
}

/*
Write the following methods that return a lambda expression performing a specified action:

PerformOperation isOdd(): The lambda expression must return  if a number is odd or  if it is even.
PerformOperation isPrime(): The lambda expression must return  if a number is prime or  if it is composite.
PerformOperation isPalindrome(): The lambda expression must return  if a number is a palindrome or  if it is not.
Input Format

Input is handled for you by the locked stub code in your editor.

Output Format

The locked stub code in your editor will print  lines of output.

Sample Input

The first line contains an integer,  (the number of test cases).

The  subsequent lines each describe a test case in the form of  space-separated integers:
The first integer specifies the condition to check for ( for Odd/Even,  for Prime, or  for Palindrome). The second integer denotes the number to be checked.

5
1 4
2 5
3 898
1 3
2 12
Sample Output

EVEN
PRIME
PALINDROME
ODD
COMPOSITE

 */

 /*
Here's a Java solution that implements the requested lambda expressions for checking odd/even, prime, and palindrome numbers:

java
import java.util.*;

interface PerformOperation {
    boolean check(int a);
}

public class Solution {
    public static void main(String[] args) {
        PerformOperation isOdd = n -> n % 2 != 0;
        
        PerformOperation isPrime = n -> {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
        
        PerformOperation isPalindrome = n -> {
            String original = Integer.toString(n);
            String reversed = new StringBuilder(original).reverse().toString();
            return original.equals(reversed);
        };
        
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        while (testCases-- > 0) {
            int operation = scanner.nextInt();
            int number = scanner.nextInt();
            
            boolean result = false;
            String output = "";
            
            switch (operation) {
                case 1:
                    result = isOdd.check(number);
                    output = result ? "ODD" : "EVEN";
                    break;
                case 2:
                    result = isPrime.check(number);
                    output = result ? "PRIME" : "COMPOSITE";
                    break;
                case 3:
                    result = isPalindrome.check(number);
                    output = result ? "PALINDROME" : "NOT PALINDROME";
                    break;
                default:
                    output = "Invalid operation";
            }
            
            System.out.println(output);
        }
    }
}
Explanation:
Interface Definition:

PerformOperation is a functional interface with a single method check(int a) that returns a boolean.

Lambda Implementations:

isOdd: Checks if a number is odd using modulo operation

isPrime: Checks for primality by testing divisibility up to √n

isPalindrome: Converts number to string and checks if it reads the same forwards and backwards

Input Handling:

Reads number of test cases

For each test case, reads the operation type and number

Uses switch-case to select the appropriate operation

Output:

Prints "ODD"/"EVEN" for operation 1

Prints "PRIME"/"COMPOSITE" for operation 2

Prints "PALINDROME"/"NOT PALINDROME" for operation 3

Alternative Implementation (Using Separate Methods):
java
import java.util.*;

interface PerformOperation {
    boolean check(int a);
}

public class Solution {
    public static PerformOperation isOdd() {
        return n -> n % 2 != 0;
    }
    
    public static PerformOperation isPrime() {
        return n -> {
            if (n < 2) return false;
            for (int i = 2; i * i <= n; i++) {
                if (n % i == 0) return false;
            }
            return true;
        };
    }
    
    public static PerformOperation isPalindrome() {
        return n -> {
            String s = Integer.toString(n);
            return s.equals(new StringBuilder(s).reverse().toString());
        };
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();
        
        while (testCases-- > 0) {
            int op = scanner.nextInt();
            int num = scanner.nextInt();
            String ans = "";
            
            if (op == 1) {
                ans = isOdd().check(num) ? "ODD" : "EVEN";
            } else if (op == 2) {
                ans = isPrime().check(num) ? "PRIME" : "COMPOSITE";
            } else if (op == 3) {
                ans = isPalindrome().check(num) ? "PALINDROME" : "NOT PALINDROME";
            }
            
            System.out.println(ans);
        }
    }
}
Both implementations correctly handle the specified operations and produce the expected output for the given sample input. The second version encapsulates each operation in its own method that returns the lambda expression.
 */

 /*
Here's the completed code with the MyMath class implementation that includes the required methods and lambda expressions:

java
import java.io.*;
import java.util.*;
import java.util.StringTokenizer;

interface PerformOperation {
    boolean check(int a);
}

class MyMath {
    public PerformOperation isOdd() {
        return n -> n % 2 != 0;
    }
    
    public PerformOperation isPrime() {
        return n -> {
            if (n <= 1) return false;
            if (n == 2) return true;
            if (n % 2 == 0) return false;
            for (int i = 3; i * i <= n; i += 2) {
                if (n % i == 0) return false;
            }
            return true;
        };
    }
    
    public PerformOperation isPalindrome() {
        return n -> {
            String numStr = Integer.toString(n);
            return numStr.equals(new StringBuilder(numStr).reverse().toString());
        };
    }
    
    public boolean checker(PerformOperation p, int num) {
        return p.check(num);
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        MyMath ob = new MyMath();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        PerformOperation op;
        boolean ret = false;
        String ans = null;
        while (T--> 0) {
            String s = br.readLine().trim();
            StringTokenizer st = new StringTokenizer(s);
            int ch = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (ch == 1) {
                op = ob.isOdd();
                ret = ob.checker(op, num);
                ans = (ret) ? "ODD" : "EVEN";
            } else if (ch == 2) {
                op = ob.isPrime();
                ret = ob.checker(op, num);
                ans = (ret) ? "PRIME" : "COMPOSITE";
            } else if (ch == 3) {
                op = ob.isPalindrome();
                ret = ob.checker(op, num);
                ans = (ret) ? "PALINDROME" : "NOT PALINDROME";
            }
            System.out.println(ans);
        }
    }
}
Explanation:
PerformOperation Interface:

Defines a functional interface with a single method check(int a) that returns a boolean.

MyMath Class:

isOdd(): Returns a lambda that checks if a number is odd using modulo operation

isPrime(): Returns a lambda that checks for primality with optimizations (skip even numbers, check up to √n)

isPalindrome(): Returns a lambda that checks if a number reads the same forwards and backwards

checker(): Executes the passed operation on the given number

Main Method:

Reads input and processes each test case

Uses StringTokenizer to parse the operation type and number

Calls the appropriate operation method based on the input

Prints "ODD"/"EVEN", "PRIME"/"COMPOSITE", or "PALINDROME"/"NOT PALINDROME" based on the result

Key Features:
Lambda Expressions: Each operation is implemented as a concise lambda expression

Efficiency: The prime checker is optimized to skip even divisors and only check up to the square root

String Handling: The palindrome checker converts the number to a string for easy reversal

Functional Programming: Uses the PerformOperation functional interface to pass operations as parameters

This solution correctly implements the required functionality while maintaining clean, modular code structure.
 */
