package com.abc.utils;


import java.util.Arrays;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author javaugi
 */
public class FormattedOutput {

    public static void main(String[] args) {
        String[] strings = {"Java", "Programming", "Hello"};
        int[] numbers = {5, 42, 123};

        System.out.println("1 starting with Strins=" + Arrays.toString(strings) + "-numbers=" + Arrays.toString(numbers));
        for (int i = 0; i < strings.length; i++) {
            System.out.printf("%-15s%03d%n", strings[i], numbers[i]);
        }

        System.out.println("2 starting with Strins=" + Arrays.toString(strings) + "-numbers=" + Arrays.toString(numbers));
        String format = "%-15s%03d%n";
        String forma2 = "%-15s%03d%n";
        for (int i = 0; i < strings.length; i++) {
            System.out.print(String.format(forma2, strings[i], numbers[i]));
        }

    }
    /*
    Output:
        Java           005
        Programming    042
        Hello          123
    Explanation of the format specifiers: "%-15s%03d%n" 
        First Column (%-15s):
            % - Start of format specifier
            - - Left-justify
            15 - Minimum width of 15 characters
            s - String format
        Second Column (%03d):
            % - Start of format specifier
            0 - Pad with zeros
            3 - Minimum width of 3 digits
            d - Decimal integer format
        %n: Platform-independent newline    
     */

    private static void scanner() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("================================");

        for (int i = 0; i < 3; i++) {
            String str = scanner.next();
            int num = scanner.nextInt();

            // Format and print the output
            System.out.printf("%-15s%03d%n", str, num);
        }

        System.out.println("================================");
        scanner.close();
    }

}
