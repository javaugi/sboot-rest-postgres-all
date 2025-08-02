package com.sisllc.mathformulas.ci.ch01;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q11UniqueChars {

    private static final Logger log = LoggerFactory.getLogger(Q11UniqueChars.class);

    public static boolean isUniqueChars(String str) {
        if (str.length() > 128) {
            return false;
        }
        int checker = 0;
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0) {
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    public static boolean isUniqueChars2(String str) {
        if (str.length() > 128) {
            return false;
        }
        boolean[] char_set = new boolean[128];
        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i);
            if (char_set[val]) {
                return false;
            }
            char_set[val] = true;
        }
        return true;
    }

    public static boolean isUniqueChars4(String str) {
        if (str.length() > 128) {
            return false;
        }

        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == chars[i + 1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean isUniqueChars3(String str) {
        if (str.length() > 128) {
            return false;
        }

        Set<Character> uniqueSet = new HashSet();
        for (char c : str.toCharArray()) {
            uniqueSet.add(c);
        }

        return str.length() == uniqueSet.size();
    }

    public static boolean containsUniqueWords(String sentence) {
        sentence = sentence.replaceAll("[^a-zA-Z]", " ").toLowerCase();
        String[] words = sentence.split("\\s+");
        Set wordSet = new HashSet(Arrays.asList(words));

        //TreeSet and sort are not needed and they are here to sort for display and compare purpose
        wordSet = new TreeSet(Arrays.asList(words));
        Arrays.sort(words);
        System.out.println("sorted words=\n " + Arrays.toString(words) + "\n wordSet= \n" + wordSet);
        System.out.println("words length=" + words.length + "-set size=" + wordSet.size());

        return words.length == wordSet.size();
    }

    public static void main(String[] args) {
        String[] words = {"abcde", "hello", "apple", "kite", "padle"};
        for (String word : words) {
            System.out.println(word + ": " + isUniqueChars(word) + " " + isUniqueChars2(word));
        }
    }

    boolean uniqueCharacters(String str) {
        // If at any time we encounter 2 same characters, return false
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return false;
                }
            }
        }

        // If no duplicate characters encountered,
        // return true
        return true;
    }
}
