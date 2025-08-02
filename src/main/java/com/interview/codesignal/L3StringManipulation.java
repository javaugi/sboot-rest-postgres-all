/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

/**
 *
 * @author javaugi
 */
/*
Introduction to String Manipulation in Java
Welcome back! I'm glad to have you continuing your journey toward mastering Java with us! This lesson will shift our focus to
advanced string manipulation. String manipulation is one of the most fundamental skill sets necessary for tackling real-world
programming problems. Understanding these principles is essential as they help break down complex problems into simpler ones. 
It also improves one's adaptability in situations where the specific language syntax might not be readily available.

Exploring the Basics
In Java programming, a string is considered a sequence of characters, which allows us to manipulate and play around with text 
data easily. For instance, one can access individual characters directly using their indices, find substrings within a larger 
string, and even compare strings.

For example, consider a task to find the longest common prefix among an array of strings. Finding the longest common prefix among 
an array of strings involves iterating character by character over the strings, starting from the first character. We compare the 
characters at the same position across all strings until we find a mismatch or reach the end of one of the strings. 
The common characters encountered up to this point form the longest common prefix. This approach ensures we only retain characters 
that are common to all strings from the beginning.

The code might look like this:

I   n practice segment, we will delve deep into various string manipulation techniques using Java. Don't worry if you feel overwhelmed; 
our goal here is step-by-step comprehension, not fast-paced learning. Our example problems delve into the intricacies of string manipulation,
helping you to iteratively develop your own unique solving patterns and strategies. We aim to foster a deep understanding rather than rote memorization of algorithms. Let's dive in!
 */
public class L3StringManipulation {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        String shortest = strs[0];
        for (String str : strs) {
            if (str.length() < shortest.length()) {
                shortest = str;
            }
        }

        for (int i = 0; i < shortest.length(); i++) {
            char charToCheck = shortest.charAt(i);
            for (String str : strs) {
                if (str.charAt(i) != charToCheck) {
                    return shortest.substring(0, i);
                }
            }
        }
        return shortest;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        System.out.println(longestCommonPrefix(strs));  // Outputs: "fl"
        
        String word = "hello";
        System.out.println("Print the char array=" + word);  // Outputs: "fl"
        // 'ch' is each individual character in `word`
        for (char ch : word.toCharArray()) {
            System.out.println(ch); // print each character from 'hello'
        }

    }
}
