/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

import java.util.Arrays;

/**
 *
 * @author javaugi
 */
/*
Advanced Array Manipulation Techniques
Lesson Overview
Welcome to another pivotal lesson in your Java interview preparation. In this lesson, we will concentrate on Advanced Array
Manipulation Techniques, focusing on the representation and manipulation of arrays directly, without relying on built-in functions. 
This topic is indispensable when preparing for technical interviews, as many problems often involve performing various operations on arrays.

Quick Example
Take, for example, the Java code provided for rotating an array by k positions. The operation can appear complex initially,
but it is made simple by understanding how array indices function. In Java, arrays are fixed in size, and we need to use loops or
manual approaches to achieve the desired operations.

In this example, we will manually perform a rotation by k positions using a series of steps to break down and reassemble the array content.

The code might look like this:

Motivation
Developing proficiency in Advanced Array Manipulation Techniques is rewarding and powerful, as it not only opens up efficient ways to
solve problems that may appear convoluted at first, but also cultivates the skills necessary for handling even more complex algorithms. 
Through practice exercises, we aim to equip you with an intuitive understanding of these techniques, which will significantly aid you 
in your problem-solving abilities. So, let's get started!

The Java operator % in the formula k = k % nums.length is the modulo operator. It calculates the remainder 
of the division of the left operand (k) by the right operand (nums.length).

Let's break down what it does and why it's useful in this context, which usually involves working with 
arrays or lists and handling wrapping around indices.

Understanding the Modulo Operator (%)

When you perform a % b, the result is the remainder when a is divided by b.

Example 1: 7 % 3 results in 1 because 7 divided by 3 is 2 with a remainder of 1 (7 = 3 * 2 + 1).
Example 2: 10 % 5 results in 0 because 10 divided by 5 is 2 with a remainder of 0 (10 = 5 * 2 + 0).
Example 3: 15 % 4 results in 3 because 15 divided by 4 is 3 with a remainder of 3 (15 = 4 * 3 + 3).
Example 4: -7 % 3 results in -1 in Java (the sign of the result is usually the same as the sign of the dividend).
Example 5: 7 % -3 results in 1 in Java.
How it Works in k = k % nums.length

In the context of k = k % nums.length, where nums is likely an array or list and k is some kind of shift or
index value, the modulo operator serves the crucial purpose of wrapping around the indices.

Imagine an array nums with a length of, say, 5 (indices 0, 1, 2, 3, 4).

If k is within the bounds (0 to 4):

If k = 2, then k % 5 is 2 % 5, which equals 2. So, the index remains within the valid range.
If k = 0, then k % 5 is 0 % 5, which equals 0.
If k is greater than or equal to the length:

If k = 5, then k % 5 is 5 % 5, which equals 0. This effectively wraps back to the beginning of the array.
If k = 7, then k % 5 is 7 % 5, which equals 2. This wraps around once and then lands at index 2.
If k = 10, then k % 5 is 10 % 5, which equals 0. Wraps around twice.
If k is negative:

If k = -1, then k % 5 is -1 % 5, which equals -1 in Java. While this is the mathematical remainder, in array 
indexing contexts, you often want a positive index. You might see this adjusted to (k % nums.length + nums.length) 
% nums.length to ensure a positive result (e.g., -1 % 5 + 5) % 5 becomes (-1 + 5) % 5, which is 4 % 5, resulting in 4).
If k = -6, then k % 5 is -6 % 5, which equals -1. Adjusted: (-1 + 5) % 5 = 4.
Common Use Cases:

This formula is frequently used in algorithms that involve:

Circular Arrays or Buffers: When you reach the end of the array, you want to loop back to the beginning.
Rotations: Shifting the elements of an array or list by a certain number of positions.
Hashing: Distributing keys within a certain range of indices in a hash table.
Cyclic Operations: Performing operations that repeat in a cycle.

Circular Arrays or Buffers: When you reach the end of the array, you want to loop back to the beginning.
Rotations: Shifting the elements of an array or list by a certain number of positions.
Hashing: Distributing keys within a certain range of indices in a hash table.
Cyclic Operations: Performing operations that repeat in a cycle.

In essence, k = k % nums.length normalizes the value of k to be within the valid index range of the nums
array (from 0 to nums.length - 1), handling cases where k might be larger than the array size or even negative. 
It ensures that any index-related calculations stay within the bounds of the data structure.
 */
public class L2AdvancedArrayManipulation {

    public static void rotateArray(int[] nums, int k) {
        // Ensure k is within the bounds of the array length
        System.out.println("1. Starting rotateArray k=" + k + "-total legth=" + nums.length);
        k = k % nums.length;
        System.out.println("2. rotateArray k = k % nums.length now k=" + k);
        // Reverse the entire array
        reverse(nums, 0, nums.length - 1);
        System.out.println("3. rotateArray after Reverse the entire array: \n" + Arrays.toString(nums));
        // Reverse the first k elements
        reverse(nums, 0, k - 1);
        System.out.println("4. rotateArray after Reverse the first k elements: \n" + Arrays.toString(nums));
        // Reverse the rest of the array
        reverse(nums, k, nums.length - 1);
        System.out.println("5. rotateArray after Reverse the rest of the array: \n" + Arrays.toString(nums));
    }

    private static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    private static void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }

    /*
    In this example, when k = 3, the algorithm: 
    first   reverses the entire array, resulting in [7, 6, 5, 4, 3, 2, 1].
    Then,   it reverses the first k elements, giving us [5, 6, 7, 4, 3, 2, 1], 
    and finally, reverses the rest of the array to get [5, 6, 7, 1, 2, 3, 4], achieving the desired rotation.    
     */
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};        
        System.out.println("Job starts now with initial array as follows: \n" + Arrays.toString(nums));
        int k = 3;  //rotate k position
        rotateArray(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        // Output: 5 6 7 1 2 3 4
        
        
        System.out.println("New method ...");
        rotate(nums, k);
        for (int num : nums) {
            System.out.print(num + " ");
        }
        // Output: 5 6 7 1 2 3 4
    }
    
    public static void rotate(int[] nums, int k) {
        k %= nums.length;
        reverseDeepSeek(nums, 0, nums.length - 1);
        reverseDeepSeek(nums, 0, k - 1);
        reverseDeepSeek(nums, k, nums.length - 1);
    }

    private static void reverseDeepSeek(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    /*
Explanation
Modulo Operation: The line k %= nums.length; ensures that if k is larger than the array length, it wraps around. For example, rotating an array of length 7 by 10 positions is equivalent to rotating it by 3 positions (since 10 % 7 = 3).

Reverse Function: The reverse function takes an array and two indices, start and end, and reverses the elements in the subarray defined by these indices.

Three Reversals:

First Reverse: Reverses the entire array to bring the last k elements to the front but in reverse order.

Second Reverse: Reverses the first k elements to correct their order.

Third Reverse: Reverses the remaining elements to correct their order.

For the input array {1, 2, 3, 4, 5, 6, 7} and k = 3, the output after rotation is {5, 6, 7, 1, 2, 3, 4}. This method efficiently rotates the array in O(n) time with O(1) additional space.    
    */
}
