package com.sisllc.mathformulas.ci.ch01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestUniqueSubstring {

    //Approach 1: Brute Force
    //Time complexity : O(n3)O(n^3)O(n3)
    //Space complexity : O(min(n,m))O(min(n, m))O(min(n,m))
    public static int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j)) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        return ans;
    }

    public static String longestSubstring1(String s) {
        String returnValue = "";
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (allUnique(s, i, j) && returnValue.length() < j - i) {
                    returnValue = s.substring(i, j);
                }
            }
        }
        return returnValue;
    }

    public static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }

    //Approach 2: Sliding Window
    //Time complexity : O(2n)=O(n)O(2n) = O(n)O(2n)=O(n). In the worst case each character
    //    will be visited twice by iii and jjj.
    //Space complexity : O(min(m,n))O(min(m, n))O(min(m,n)). Same as the previous approach.
    //We need O(k)O(k)O(k) space for the sliding window, where kkk is the size of the Set.
    //  The size of the Set is upper bounded by the size of the string nnn and the size of
    //  the charset/alphabet mmm.
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }

    public static String longestSubstring2(String s) {
        String returnValue = "";
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                if (returnValue.length() < j - i) {
                    returnValue = s.substring(i, j);
                }
            } else {
                set.remove(s.charAt(i++));
            }
        }
        return returnValue;
    }

    //Approach 3: Sliding Window Optimized
    //Time complexity : O(n)O(n)O(n). Index jjj will iterate nnn times.
    //Space complexity (HashMap) : O(min(m,n))O(min(m, n))O(min(m,n)). Same as the previous approach.
    //Space complexity (Table): O(m)O(m)O(m). mmm is the size of the charset.
    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }

    public static String longestSubstring3(String s) {
        String returnValue = "";
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
                if (returnValue.length() < j - i + 1) {
                    returnValue = s.substring(i - 1, j);
                }
            } else {
                returnValue = s.substring(i, j);
            }
            map.put(s.charAt(j), j + 1);
        }
        return returnValue;
    }

    public static int lengthOfLongestSubstring4(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        String input = "abcabcabab";
        System.out.println("length " + lengthOfLongestSubstring1(input) + " of " + longestSubstring1(input));
        System.out.println("length " + lengthOfLongestSubstring2(input) + " of " + longestSubstring2(input));
        System.out.println("length " + lengthOfLongestSubstring3(input) + " of " + longestSubstring3(input));
        System.out.println("length " + lengthOfLongestSubstring4(input));
    }
}
