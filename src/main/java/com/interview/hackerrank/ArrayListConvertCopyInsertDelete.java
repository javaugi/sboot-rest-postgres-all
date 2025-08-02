/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author javaugi
 */
public class ArrayListConvertCopyInsertDelete {

    public static void main(String[] args) {
        //test1();
        test2();
    }

    private static void test2() {
        String line = "12 0  1 78 12";
        int[] intArr = inputlineToIntArray(line);

        System.out.println("Oroginal array=" + Arrays.toString(intArr)); //[12, 0, 1, 78, 12]
        intArr = insertIntoIntArrUsingList(intArr, 5, 23);
        System.out.println("After insert 23 at 5 array=" + Arrays.toString(intArr)); //[12, 0, 1, 78, 12, 23]
        intArr = deleteFromIntArrUsingList(intArr, 0);
        System.out.println("After delete at 0=" + Arrays.toString(intArr)); //[0, 1, 78, 12, 23]
    }

    private static void test1() {
        int[] intArr = {12, 0, 1, 78, 12};

        System.out.println("Oroginal array=" + Arrays.toString(intArr)); //[12, 0, 1, 78, 12]
        intArr = insertIntoIntArrUsingList(intArr, 5, 23);
        System.out.println("After insert 23 at 5 array=" + Arrays.toString(intArr)); //[12, 0, 1, 78, 12, 23]
        intArr = deleteFromIntArrUsingList(intArr, 0);
        System.out.println("After delete at 0=" + Arrays.toString(intArr)); //[0, 1, 78, 12, 23]
    }

    public static int[] insertIntoIntArrUsingList(int[] originalArray, int index, int newValue) {
        List<Integer> intList = intArrayToIntegerList(originalArray);

        intList.add(index, newValue);

        originalArray = integerListToIntArray(intList);
        return originalArray;
    }

    public static int[] deleteFromIntArrUsingList(int[] originalArray, int index) {
        List<Integer> intList = intArrayToIntegerList(originalArray);

        intList.remove(index);

        originalArray = integerListToIntArray(intList);
        return originalArray;
    }

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

    public static Integer[] inputlineToIntegerArray(String inputLine) {
        return Arrays.stream(inputLine.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                .toArray(Integer[]::new);
    }

    public static Integer[] integerListToIntegerArray(List<Integer> list) {
        return list.toArray(Integer[]::new);
    }

    public static List<Integer> inputlineToIntegerList(String inputLine) {
        return Arrays.stream(inputLine.split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public static List<Integer> intArrayToIntegerList(int[] originalArray) {
        List<Integer> integerList = Arrays.stream(originalArray)
                .boxed()
                .collect(Collectors.toList());
        return integerList;
    }


    public static List<Integer> integerArrayToIntegerList(Integer[] integerArr) {
        return new ArrayList<>(Arrays.asList(integerArr));
    }

    public static int[] insertElement(int[] originalArray, int newElement, int position) {
        int newArrayLength = originalArray.length + 1;
        int[] newArray = new int[newArrayLength];

        // Copy elements before the insertion point
        for (int i = 0; i < position; i++) {
            newArray[i] = originalArray[i];
        }

        // Insert the new element
        newArray[position] = newElement;

        // Copy elements after the insertion point
        for (int i = position + 1; i < newArrayLength; i++) {
            newArray[i] = originalArray[i - 1];
        }

        return newArray;
    }

    public static int[] insertElementSysCopy(int[] originalArray, int index, int newValue) {
        int[] newArray = new int[originalArray.length + 1];
        System.arraycopy(originalArray, 0, newArray, 0, index);

        newArray[index] = newValue;

        System.arraycopy(originalArray, index, newArray, index + 1, originalArray.length - index);
        return newArray;
    }

    public static void scanner() {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        scan.nextLine();
        String line = scan.nextLine();
        Integer[] L = new Integer[N];
        String[] tokens = line.split("\\s+");
        for (int i = 0; i < L.length; i++) {
            L[i] = Integer.valueOf(tokens[i]);
        }

        int Q = scan.nextInt();
        scan.nextLine();

        for (int i = 0; i < Q; i++) {
            String op = scan.nextLine();
            String values = scan.nextLine();
            if ("Insert".equals(op)) {
                String[] lvArray = values.split(" ");
                int x = Integer.parseInt(lvArray[0]);
                int y = Integer.parseInt(lvArray[1]);
                List<Integer> list = new ArrayList<>(Arrays.asList(L));
                list.add(x, y);
                L = list.toArray(new Integer[0]);
            } else if ("Delete".equals(op)) {
                int x = Integer.parseInt(values);
                List<Integer> list = new ArrayList<>(Arrays.asList(L));
                list.remove(x);
                L = list.toArray(new Integer[0]);
            }
        }
        System.out.println(Arrays.toString(L).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", ""));

    }
}
