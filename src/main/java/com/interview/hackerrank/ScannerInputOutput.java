/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Scanner;

/**
 *
 * @author javaugi
 */
public class ScannerInputOutput {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i = scan.nextInt();
        double d = scan.nextDouble(); //newline after the integer is skipped automatically
        scan.nextLine(); //  required here to skip the newlineafter the double so that the nextLine works
        String s = scan.nextLine();
        /*
        Note: If you use the nextLine() method immediately following the nextInt() method, recall that nextInt() 
            reads integer tokens; because of this, the last newline character for that line of integer input is 
            still queued in the input buffer and the next nextLine() will be reading the remainder of the integer line (which is empty).        
        */
        
        // Write your code here.
        scan.close();
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + i);
    }
}
