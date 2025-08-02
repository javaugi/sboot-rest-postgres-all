/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author javaugi
 */
public class ConvertToIntArray {

    //String text = "apple, banana orange  grape,kiwi";
    //String[] items = text.split("[,\\s]+");
    public static int[] inputlineToIntArray(String inputLine) {
        return Arrays.stream(inputLine.split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int[] strArrToIntArray(String[] tokens) {
        return Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int[] integerListToIntArray(List<Integer> list) {
        int[] intArr = list.stream()
                .mapToInt(Integer::intValue) // Convert Integer to int
                .toArray();
        return intArr;
    }

}
