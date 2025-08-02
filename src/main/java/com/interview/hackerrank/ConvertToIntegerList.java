/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
public class ConvertToIntegerList {
    
    public static void main(String[] args) {
        int[] intArr = {3, 2, 4, 7, 8};
        System.out.println("Orig array=" + Arrays.toString(intArr));
        System.out.println("asList array=" + Arrays.asList(intArr));
        System.out.println("List.of array=" + List.of(intArr));
        System.out.println("ArrayList array=" + new ArrayList<>(Arrays.asList(intArr)));
    }
    

    public static List<Integer> inputlineToIntegerList(String inputLine) {
        return Arrays.stream(inputLine.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> intArrayToIntegerList(int[] intArr) {
        Arrays.asList(intArr); //fixed array
        List.of(intArr);  //fixed array
        new ArrayList<>(Arrays.asList(intArr)); //resizable

        List<Integer> integerList = Arrays.stream(intArr)
                .boxed()
                .collect(Collectors.toList());
        return integerList;
    }

    public static List<Integer> intArrayToIntegerList(Integer[] integerArr) {
        Arrays.asList(integerArr); //fixed array
        List.of(integerArr);  //fixed array
        new ArrayList<>(Arrays.asList(integerArr)); //resizable
                
        List<Integer> integerList = Arrays.stream(integerArr)
                .collect(Collectors.toList());
        return integerList;
    }
}
