/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

/**
 *
 * @author javaugi
 */
public class ClassInheritance {

    public static void main(String[] args) {
        // Create an Adder object
        ClassInheritance.Adder adder = new ClassInheritance().new Adder();

        // Print the superclass name
        System.out.println("My superclass is: " + adder.getClass().getSuperclass().getName());

        // Test the add method with multiple parameters
        System.out.print(adder.add(10, 32) + " ");
        System.out.print(adder.add(10, 3) + " ");
        System.out.print(adder.add(10, 10));
    }

    /*
        But the first solution matches the exact problem description which mentions "takes 2 integers as parameters". The sample output 
        showing three numbers (42, 13, 20) appears to be from multiple calls to the add method rather than a single call with three parameters.
    */
    class ArithmeticMultiple {
        // Method to add variable number of integers

        public int add(int... numbers) {
            int sum = 0;
            for (int num : numbers) {
                sum += num;
            }
            return sum;
        }
    }

    class Arithmetic {
        // Method to add two integers

        public int add(int a, int b) {
            return a + b;
        }


    }
    class Adder extends Arithmetic {
        // This class inherits the add method from Arithmetic
        // No additional implementation needed unless we want to modify behavior
    }
}

/*
    Key Points:
    Arithmetic Class:
    Adder Class:
        Inherits from Arithmetic using extends keyword
        Automatically gets the add method from its superclass
        Doesn't need any additional implementation since we're not modifying the behavior
    Solution Class:
        Demonstrates the inheritance relationship
        Shows that Adder can use the add method from Arithmetic
    Output matches the sample requirements:
        My superclass is: Arithmetic
        42 13 20
*/
