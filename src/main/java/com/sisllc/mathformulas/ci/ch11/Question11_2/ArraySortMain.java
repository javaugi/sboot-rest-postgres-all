package com.sisllc.mathformulas.ci.ch11.Question11_2;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;
import java.util.Arrays;

public class ArraySortMain {

    public static void main(String[] args) {
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        System.out.println(AssortedMethods.stringArrayToString(array));
        Arrays.sort(array, new AnagramComparator());
        System.out.println(AssortedMethods.stringArrayToString(array));
    }
}
