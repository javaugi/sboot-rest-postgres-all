/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

/**
 *
 * @author javaugi
 */
public class CapitalCharInString {

    private static final CapitalCharInString main = new CapitalCharInString();

    public static void main(String[] args) {
        main.test1();
        main.test2();
    }

    private void test1() {
        String word = "This is the Test to Check How many Capital Chars are";

        int capitalCharCount = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                capitalCharCount++;
            }
        }
        System.out.println("There are total " + capitalCharCount + " capital characters ");

        char char1 = 'A';
        char char2 = 'a';
        char char3 = '1';
        char char4 = '$';

        // Method 1: Using Character.isUpperCase()
        System.out.println(char1 + " is uppercase: " + Character.isUpperCase(char1)); // true
        System.out.println(char2 + " is uppercase: " + Character.isUpperCase(char2)); // false
        System.out.println(char3 + " is uppercase: " + Character.isUpperCase(char3)); // false
        System.out.println(char4 + " is uppercase: " + Character.isUpperCase(char4)); // false

        // Method 2: Using comparison with ASCII values (less preferred, but shows how it works)
        // Uppercase letters in ASCII are between 'A' (65) and 'Z' (90) inclusive.
        System.out.println(char1 + " is uppercase: " + (char1 >= 'A' && char1 <= 'Z')); // true
        System.out.println(char2 + " is uppercase: " + (char2 >= 'A' && char2 <= 'Z')); // false
        System.out.println(char3 + " is uppercase: " + (char3 >= 'A' && char3 <= 'Z')); // false
        System.out.println(char4 + " is uppercase: " + (char4 >= 'A' && char4 <= 'Z')); // false

        // Example Usage in a loop
        String testString = "Hello World!";
        for (int i = 0; i < testString.length(); i++) {
            char c = testString.charAt(i);
            if (Character.isUpperCase(c)) {
                System.out.println(c + " is an uppercase letter.");
            } else {
                System.out.println(c + " is not an uppercase letter.");
            }
        }
    }

    private void test2() {
        char[] a = {'a', 'b', 'c', 'd', 'e', 'A', 'B', 'C', 'D', 'E'};

        int lowerCaseCount = 0;
        int upperCaseCount = 0;
        for (char ch : a) {
            if (Character.isUpperCase(ch)) {
                upperCaseCount++;
            } else {
                lowerCaseCount++;
            }
        }
        System.out.println("The array length has " + upperCaseCount + " upper case and " + lowerCaseCount + " lower case and the total is " + (lowerCaseCount + lowerCaseCount));
    }
}
