/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author javaugi
 */
public class TotalValidSubarrays {
    public static void main(String[] args) {
        int[] intArr = {2, 5, 6, 8, 4};
        Arrays.sort(intArr); // Sort the array first
        //intArr = {2, 4, 5, 6, 8};
        System.out.println("1 Total valid subarrays: " + countTotalValidSubarrays(intArr));
        System.out.println("2 Total valid subarrays: " + countTotalValidSubarrays2(intArr));
    }

    public static int countTotalValidSubarrays(int[] arr) {
        int total = 0;

        for (int num : arr) {
            List<List<Integer>> validSubarrays = new ArrayList<>();
            generateSubarrays(arr, num, 0, new ArrayList<>(), validSubarrays);

            for (List<Integer> sub : validSubarrays) {
                if (sub.stream().mapToInt(Integer::intValue).sum() == num) {
                    total++;
                }
            }
        }

        return total;
    }

    private static void generateSubarrays(int[] arr, int num, int start, List<Integer> current, List<List<Integer>> result) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }

        for (int i = start; i < arr.length && arr[i] <= num; i++) {
            current.add(arr[i]);
            generateSubarrays(arr, num, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }    
    
    public static int countTotalValidSubarrays2(int[] arr) {
        int total = 0;

        for (int num : arr) {
            List<List<Integer>> validSubarrays = new ArrayList<>();
            generateSubarrays2(arr, num, 0, new ArrayList<>(), validSubarrays);

            total = total + validSubarrays.size();
        }

        return total;
    }

    private static void generateSubarrays2(int[] arr, int num, int start, List<Integer> current, List<List<Integer>> result) {
        if (!current.isEmpty()) {
            result.add(new ArrayList<>(current));
        }

        for (int i = start; i < arr.length && arr[i] <= num; i++) {
            current.add(arr[i]);
            generateSubarrays(arr, num, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }  
}
