/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author javaugi
 */
public class Code2MediumStringProcessing {

    /*
Problem 2: Medium – String Processing
Task: Given a string s, find the length of the longest substring with at most two distinct characters.

Example:
Input: "ccaabbb" → Output: 5 ("aabbb")

Solution (Sliding Window):   
    
Key Points:

Sliding window + hashmap to track character counts.

O(N) time, O(1) space (since map size ≤ 3).    
     */
    public static void main(String[] args) {
        Code2MediumStringProcessing main = new Code2MediumStringProcessing();
        String input = "ccaabbb";
        int result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("1 The result is: " + result + " from input: " + input);

        input = "dkfkflfhf";
        result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("2 The result is: " + result + " from input: " + input);

        input = "cfdfehgf";
        result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("3 The result is: " + result + " from input: " + input);
        
        /*
        --- exec-maven-plugin:3.1.0:exec (default-cli) @ cimathformulas ---
        1 The result is: 5 from input: ccaabbb
        2 The result is: 4 from input: dkfkflfhf
        3 The result is: 3 from input: cfdfehgf
        ------------------------------------------        
        */
        
    }
    
    public int longestSubstringWithAtLeastTwoDistinctChars(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            left = processMapReducer(map, s, left);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    private int processMapReducer(Map<Character, Integer> map, String s, int left) {
        while (map.size() > 2 && left < s.length()) {
            char leftChar = s.charAt(left);
            map.put(leftChar, map.get(leftChar) - 1);
            if (map.get(leftChar) == 0) {
                map.remove(leftChar);
            }
            left++;
        }
        return left;
    }
}
