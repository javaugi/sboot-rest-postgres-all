/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

/**
 *
 * @author javaugi
 */
public class GenericOperations {
    public static void main(String args[]) {
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        
        printArray(intArray);
        printArray(stringArray);
    }

    public static <T> void printArray(T[] array) {
        for (T element : array) {
            //System.out.println(element);
            doPrint.print(element);
        }
    }

    public static final GenericPrint doPrint = ele -> System.out.println(ele);
    //Spublic static final PerformOperation isOdd = n -> n % 2 != 0;

}
