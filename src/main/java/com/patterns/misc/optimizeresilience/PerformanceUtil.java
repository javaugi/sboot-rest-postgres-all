/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patterns.misc.optimizeresilience;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 * @author javaugi
 */
public class PerformanceUtil {
    private PerformanceUtil() {}
    
    
    public static void main(String[] args) {
        PerformanceUtil.measureTimeByRunnable(() -> {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < 1_000_000; i++) {
                list.add("value" + i);
            }
        }, "Runnable List population");
        
        
        // Example 1: Measuring the time to create a String
        String message = measureTimeBySupplier(() -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 100000; i++) {
                sb.append("a");
            }
            return sb.toString();
        }, "Supplier String creation");
        System.out.println("Supplier Created message: " + message.substring(0, 10) + "..."); // Print a part of the string

        // Example 2: Measuring the time to calculate the sum of numbers
        Integer sum = measureTimeBySupplier(() -> {
            int total = 0;
            for (int i = 1; i <= 1000000; i++) {
                total += i;
            }
            return total;
        }, "Supplier Sum calculation");
        System.out.println("Supplier Sum: " + sum);

        // Example 3: Measuring the time to perform a simple arithmetic operation
        Double product = measureTimeBySupplier(() -> 123.45 * 67.89, "Supplier Multiplication");
        System.out.println("Supplier Product: " + product);

        // Example 4: Using a predefined method as the Supplier
        java.util.Random random = new java.util.Random();
        Integer randomNumber = measureTimeBySupplier(random::nextInt, "Supplier Generating random integer");
        System.out.println("Supplier Random number: " + randomNumber);
    }
    

    public static <T> T measureTimeBySupplier(Supplier<T> supplier, String taskName) {
        long start = System.nanoTime();
        T result = supplier.get();
        long end = System.nanoTime();
        System.out.println(taskName + " took " + (end - start)/1_000_000 + " ms");
        return result;
    }

    public static void measureTimeByRunnable(Runnable runnable, String taskName) {
        long start = System.nanoTime();
        runnable.run();
        long end = System.nanoTime();
        System.out.println(taskName + " took " + (end - start)/1_000_000 + " ms");
    }    
}
