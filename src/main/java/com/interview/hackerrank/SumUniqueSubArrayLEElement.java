package com.interview.hackerrank;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SumUniqueSubArrayLEElement {
    public static void main(String[] args) {
        int[] intArr = {2, 5, 6, 8, 4};
        printUniqueExItself(intArr);
        printUniqueExItself2(intArr);
        printUniqueExItself3(intArr);
        printUnique(intArr);
        printUnique2(intArr);
    }
  
    private static void printUniqueExItself(int[] intArr) {
        Set<String> u = new HashSet<>();
        Arrays.sort(intArr);
        System.out.println("\n printUniqueExItself Original intArr=" + Arrays.toString(intArr));
        int ej = 0;
        int[] sub = {};
        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j <= i; j++) {
                ej = intArr[j];
                if (i == j && i == intArr.length - 1) {
                    sub = Arrays.copyOfRange(intArr, j, i + 1);
                } else {
                    sub = Arrays.copyOfRange(intArr, j, i);
                }
                if (sub.length == 1 && sub[0] == ej) {
                    continue;
                }
                String str = "";
                if (sub.length > 0) {
                    str = Arrays.toString(sub);
                    str = str.replaceAll("\\[", "\\(");
                    str = str.replaceAll("]", ")");
                }
                if (str != null && !str.isBlank()) {
                    u.add(str);
                }
                //System.out.println("printUniqueExItself Size=" + u.size() + "-ej=" + ej + "-j=" + j  + "-i=" + i  + "-str=" + str + "-sub=" + Arrays.toString(sub) + "\n u set=" + u);
            }
            System.out.println("printUniqueExItself Size=" + u.size());
        }
    }
    
    private static void printUniqueExItself2(int[] intArr) {
        Set<String> u = new HashSet<>();
        Arrays.sort(intArr);
        System.out.println("\n printUniqueExItself2 Original intArr=" + Arrays.toString(intArr));
        int ej = 0;
        int[] sub = {};
        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j <= i; j++) {
                ej = intArr[j];
                sub = Arrays.copyOfRange(intArr, j, i);
                if (sub.length == 1 && sub[0] == ej) {
                    continue;
                }
                String str = "";
                if (sub.length > 0) {
                    str = Arrays.toString(sub);
                    str = str.replaceAll("\\[", "\\(");
                    str = str.replaceAll("]", ")");
                }
                if (str != null && !str.isBlank()) {
                    u.add(str);
                }
                //System.out.println("printUniqueExItself Size=" + u.size() + "-ej=" + ej + "-j=" + j  + "-i=" + i  + "-str=" + str + "-sub=" + Arrays.toString(sub) + "\n u set=" + u);
            }
            System.out.println("printUniqueExItself2 Size=" + u.size());
        }
    }
    
   private static void printUniqueExItself3(int[] intArr) {
        Set<String> u = new HashSet<>();
        Arrays.sort(intArr);
        System.out.println("\n printUniqueExItself3 Original intArr=" + Arrays.toString(intArr));
        int ej = 0;
        int[] sub = {};
        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j <= i; j++) {
                ej = intArr[j];
                sub = Arrays.copyOfRange(intArr, j, i);
                if (sub.length == 1 && sub[0] == ej) {
                    continue;
                }
                if (sub.length > 0) {
                    u.add(Arrays.toString(sub));
                }
                //System.out.println("printUniqueExItself Size=" + u.size() + "-ej=" + ej + "-j=" + j  + "-i=" + i  + "-str=" + str + "-sub=" + Arrays.toString(sub) + "\n u set=" + u);
            }
            System.out.println("printUniqueExItself3 Size=" + u.size());
        }
    }    
    private static void printUnique(int[] intArr) {
        Set<String> u = new HashSet<>();
        Arrays.sort(intArr);
        System.out.println("\n printUnique Original intArr=" + Arrays.toString(intArr));
        
        int[] sub = {};
        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j && i == intArr.length - 1) {
                    sub = Arrays.copyOfRange(intArr, j, i + 1);
                } else {
                    sub = Arrays.copyOfRange(intArr, j, i);
                }
                String str = "";
                if (sub.length > 0) {
                    str = Arrays.toString(sub);
                    str = str.replaceAll("\\[", "\\(");
                    str = str.replaceAll("]", ")");
                }
                if (str != null && !str.isBlank()) {
                    u.add(str);
                }
                //System.out.println("printUnique     Size=" + u.size() + "-ej=" + ej + "-j=" + j  + "-i=" + i  + "-str=" + str + "-sub=" + Arrays.toString(sub) + "\n u set=" + u);
            }
            System.out.println("printUnique     Size=" + u.size());
        }
    }
    
    private static void printUnique2(int[] intArr) {
        Set<String> u = new HashSet<>();
        Arrays.sort(intArr);
        System.out.println("\n printUnique2 Original intArr=" + Arrays.toString(intArr));
        int[] sub = {};
        for (int i = 0; i < intArr.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i == j && i == intArr.length - 1) {
                    sub = Arrays.copyOfRange(intArr, j, i + 1);
                    System.out.println("printUnique2  i == j && i == intArr.length - 1  sub=" + Arrays.toString(sub));
                } else {
                    sub = Arrays.copyOfRange(intArr, j, i);
                }
                if (sub.length > 0) {
                    u.add(Arrays.toString(sub));
                }
                //System.out.println("printUniqueExItself Size=" + u.size() + "-ej=" + ej + "-j=" + j  + "-i=" + i  + "-str=" + str + "-sub=" + Arrays.toString(sub) + "\n u set=" + u);
            }
            System.out.println("printUnique2    Size=" + u.size());
        }
    }    
}
