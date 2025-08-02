/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
public class ConvertToIntegerArray {

    public static Integer[] inputlineToIntegerArray(String inputLine) {
        return Arrays.stream(inputLine.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                .toArray(Integer[]::new);
    }

    public static Integer[] integerListToIntegerArray(List<Integer> list) {
        return list.toArray(Integer[]::new);
    }    

    public static Integer[] integerListToIntegerArray(int[] intArr) {
        return Arrays.stream(intArr)
                .boxed()
                .collect(Collectors.toList())
                .toArray(Integer[]::new);
    }    
}
