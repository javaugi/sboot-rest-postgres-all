/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.text.diff.*;
//import com.github.difflib.*;
//import com.github.difflib.algorithm.DiffException;

/**
 *
 * @author javaugi
 */
public class StringDiff {
    //Finding and Printing the Difference Between Two Strings in Java
    // Here are several approaches to compare two strings and show their differences:

    public static void main(String[] args) {
        runArrayTest();
                
        String s1 = "hello";
        String s2 = "java";
        System.out.println("\n printTwoStrings ...");
        printTwoStrings(s1, s2);
        
        String s3 = "welcometojava";
        System.out.println("\n getSmallestAndLargest ...");
        System.out.println(getSmallestAndLargest(s3, 3));
        System.out.println("\n getSmallestAndLargestByTreeSet ...");
        System.out.println(getSmallestAndLargestByTreeSet(s3, 3));

        String s4 = "Welcome To Java";
        System.out.println("\n getSmallestAndLargest ...");
        System.out.println(getSmallestAndLargest(s4, 3));
        System.out.println("\n getSmallestAndLargestByTreeSet ...");
        System.out.println(getSmallestAndLargestByTreeSet(s4, 3));
    }
    
    private static void runArrayTest() {
        int[] intArr = {2,8,7,15,4,19,7,20};
        System.out.println("\n runArrayTest with arr length" + intArr.length + "-original=" + Arrays.toString(intArr));
        Arrays.sort(intArr);
        System.out.println("\n sorted=" + Arrays.toString(intArr));
        System.out.println("\n Arrays.binarySearch(intArr, 7)=" + Arrays.binarySearch(intArr, 7));
        System.out.println("\n CopyOf(intArr, 15)=" + Arrays.toString(Arrays.copyOf(intArr, 15)));
        System.out.println("\n copyOfRange(intArr, 0, 20)=" + Arrays.toString(Arrays.copyOfRange(intArr, 0, 20)));
    }
    
    public static String getSmallestAndLargest(String s, int k) {
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);
        
        //note i = 0 or 1 both work
        //for (int i = 1; i <= s.length() - k; i++) {
        for (int i = 0; i <= s.length() - k; i++) {
            String current = s.substring(i, i + k);
            if (current.compareTo(smallest) < 0) {
                smallest = current;
            }
            if (current.compareTo(largest) > 0) {
                largest = current;
            }
        }
        
        return smallest + "\n" + largest;
    }
    /*
    Explanation:
    Initialization:
        Start by setting both smallest and largest to the first substring of length k
        Iterate through all possible substrings:
        For each position i from 1 to s.length() - k:
        Extract the current substring of length k
        Compare it with smallest and update if smaller
        Compare it with largest and update if larger
    Return Result:
        Format the result as smallest + "\n" + largest
    Main Method:
        Reads input string s and integer k
        Calls getSmallestAndLargest and prints the result
    Key Points:
        Lexicographical Comparison: Uses String.compareTo() which compares strings based on Unicode values
        Efficiency: O(n) time complexity where n is the length of the string, since we examine each possible substring exactly once
        Edge Cases: Handles cases where k equals the string length (returns the entire string for both smallest and largest)    
    */
    public static String getSmallestAndLargestByTreeSet(String s, int k) {
        /*
        This version uses a TreeSet to automatically sort the substrings, then returns the first and last elements. While cleaner, it has 
            slightly higher memory usage (O(n) space) compared to the first solution (O(1) space).    
        */
        SortedSet<String> substrings = new TreeSet<>();
        
        for (int i = 0; i <= s.length() - k; i++) {
            substrings.add(s.substring(i, i + k));
        }
        
        return substrings.first() + "\n" + substrings.last();
    }
    
    private static void printTwoStrings(String A, String B) {
        // 1. Sum the lengths
        System.out.println(A.length() + B.length());
        
        // 2. Lexicographical comparison
        System.out.println(A.compareTo(B) > 0 ? "Yes" : "No");
        
        // 3. Capitalize first letters
        System.out.println(
            capitalizeFirstLetter(A) + " " + capitalizeFirstLetter(B)
        );        
    }

    private static String capitalizeFirstLetter(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    public static void printStringDifferences(String str1, String str2) {
        int minLength = Math.min(str1.length(), str2.length());

        System.out.println("String 1: " + str1);
        System.out.println("String 2: " + str2);

        StringBuilder sb = new StringBuilder();        
        for (int i = 0; i < minLength; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                String formatted = String.format("Position %d: '%c' vs '%c'\n", i, str1.charAt(i), str2.charAt(i));
                System.out.printf(formatted);
                sb.append(formatted);
            }
        }

        // Handle extra characters in longer string
        if (str1.length() > str2.length()) {
            String formatted = String.format("String 1 has extra characters: '%s'\n", str1.substring(minLength));
            System.out.printf(formatted);
            sb.append(formatted);
        } else if (str2.length() > str1.length()) {
            String formatted = String.format("String 2 has extra characters: '%s'\n", str2.substring(minLength));
            System.out.printf(formatted);
            sb.append(formatted);
        }
        System.out.println("printStringDifferences  Differences:" + sb.toString());
    }

    public static void printDiffApacheCommon(String str1, String str2) {
        StringsComparator comparator = new StringsComparator(str1, str2);
        EditScript<Character> script = comparator.getScript();

        script.visit(new CommandVisitor<Character>() {
            @Override
            public void visitInsertCommand(Character c) {
                System.out.println("Insert: " + c);
            }

            @Override
            public void visitDeleteCommand(Character c) {
                System.out.println("Delete: " + c);
            }

            @Override
            public void visitKeepCommand(Character c) {
                // Characters that match
            }
        });
        System.out.println("printDiffApacheCommon Differences:" + script.getModifications());
        
    }

    public static void printWordDifferences(String str1, String str2) {
        String[] words1 = str1.split("\\s+");
        String[] words2 = str2.split("\\s+");

        int maxLength = Math.max(words1.length, words2.length);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            String word1 = i < words1.length ? words1[i] : "";
            String word2 = i < words2.length ? words2[i] : "";

            if (!word1.equals(word2)) {                
                String formatted = String.format("Word %d: '%s' vs '%s'\n", i, word1, word2);
                System.out.printf(formatted);
                sb.append(formatted);
            }
        }
        System.out.println("printStringDifferences  Differences:" + sb.toString());
    }

    /*
    public static void printDiffWithLibrary(String str1, String str2) {
        try {
            List<AbstractDelta<String>> deltas = DiffUtils.diff(
                    Arrays.asList(str1.split("")),
                    Arrays.asList(str2.split(""))
            ).getDeltas();

            for (AbstractDelta<String> delta : deltas) {
                System.out.println(delta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // */
}
