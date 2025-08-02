/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author javaugi
 */
public class CaseInsensitiveAnagramChecker {

    public static void main(String[] args) {
        String a = "anagram";
        String b = "margana";
        System.out.println("Calling isAnagram a=" + a + "-b=" + b);
        System.out.println(isAnagram(a, b) ? "Anagrams" : "Not Anagrams");
        System.out.println("Calling isAnagramUsingMap");
        System.out.println(isAnagramUsingMap(a, b) ? "Anagrams" : "Not Anagrams");
    }

    /*
    Explanation:
        Length Check: First, we check if the strings have the same length. If not, they can't be anagrams.
        Case Insensitivity: Convert both strings to lowercase to make the comparison case-insensitive.
    Character Sorting:
        Convert each string to a character array
        Sort the character arrays alphabetically
    Comparison:
        Compare the sorted arrays using Arrays.equals()
        If they match, the strings are anagrams    
    */
    /*
    Short Summary

    Situation                   Are Anagrams?
    null vs null                No
    null vs "abc"               No
    "abc" vs "cba"              Yes
    "" vs "" (empty strings)	Yes
    */
    public static boolean isAnagram(String a, String b) {
        // If lengths are different, they can't be anagrams
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }

        // Convert to lowercase and sort the characters
        char[] aChars = a.toLowerCase().toCharArray();
        char[] bChars = b.toLowerCase().toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);

        // Compare the sorted arrays
        return Arrays.equals(aChars, bChars);
    }

    /*
    Key Points:
        Time Complexity:
            Sorting approach: O(n log n)
            Frequency map approach: O(n)
        Space Complexity:
            Both approaches use O(n) extra space
        Edge Cases:
            Handles different cases (e.g., "Hello" vs "hello")
            Handles different lengths efficiently
            Works with all English alphabetic characters

        Both solutions would correctly classify the sample inputs as shown in your examples. The sorting approach is more concise, while the 
            frequency map approach is more efficient for very large strings.    
    */
    public static boolean isAnagramUsingMap(String a, String b) {
        if (a == null || b == null || a.length() != b.length()) {
            return false;
        }

        Map<Character, Integer> freq = new HashMap<>();

        // Count frequencies in first string
        for (char c : a.toLowerCase().toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // Subtract frequencies from second string
        for (char c : b.toLowerCase().toCharArray()) {
            int count = freq.getOrDefault(c, 0);
            if (count == 0) {
                return false;
            }
            freq.put(c, count - 1);
        }

        return true;
    }
}
