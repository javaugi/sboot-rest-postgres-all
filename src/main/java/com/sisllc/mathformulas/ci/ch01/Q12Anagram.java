package com.sisllc.mathformulas.ci.ch01;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q12Anagram {

    private static final Logger log = LoggerFactory.getLogger(Q12Anagram.class);

    public static String sort(String s) {
        char[] content = s.toCharArray();
        java.util.Arrays.sort(content);
        return new String(content);
    }

    public static boolean permutation(String s, String t) {
        return sort(s).equals(sort(t));
    }

    public static boolean anagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] letters = new int[128];
        int num_unique_chars = 0;
        int num_completed_t = 0;
        char[] s_array = s.toCharArray();
        for (char c : s_array) { // count number of each char in s.
            if (letters[c] == 0) {
                ++num_unique_chars;
            }
            ++letters[c];
        }
        for (int i = 0; i < t.length(); ++i) {
            int c = (int) t.charAt(i);
            if (letters[c] == 0) { // Found more of char c in t than in s.
                return false;
            }
            --letters[c];
            if (letters[c] == 0) {
                ++num_completed_t;
                if (num_completed_t == num_unique_chars) {
                    // itÕs a match if t has been processed completely
                    return true;
                    //return i == t.length() - 1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"hello", "llloh"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = permutation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
            System.out.println(anagram(word1, word2));
        }
    }

    public static boolean isAnagram(String input1, String input2) {
        //Remove all whitespace first
        String s1 = input1.replaceAll("\\s", "");
        String s2 = input2.replaceAll("\\s", "");

        boolean status = true;
        if (s1.length() != s2.length()) {
            status = false;
        } else {
            //Convert into character array
            char[] s1Array = s1.toLowerCase().toCharArray();
            char[] s2Array = s2.toLowerCase().toCharArray();

            //Sorting both character array
            Arrays.sort(s1Array);
            Arrays.sort(s2Array);

            //Check if both arrays are equal
            status = Arrays.equals(s1Array, s2Array);
        }
        System.out.println(s1 + " and " + s2 + " are anagrams ? " + status);
        return status;
    }
}
