/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author javaugi
 */
public class StringRegexTokenizer {
    
    public static void main(String[] args) {
        String s = "He is a very very good boy, isn't he?";
        String s2 = "           YES      leading spaces        are valid,    problemsetters are         evillllll";
        
        System.out.println("\n 1. doSpliting ...");
        doSpliting(s);
        System.out.println("\n 1. doTokenizing ...");
        doTokenizing(s);

        System.out.println("\n 2. doSpliting ...");
        doSpliting(s2);
        System.out.println("\n 2. doTokenizing ...");
        doTokenizing(s2);
    }
    /*
    Key Points:
        The regex [^a-zA-Z]+ splits on any non-alphabetic characters (including spaces, punctuation, etc.)
        trim() ensures we don't count empty strings from leading/trailing delimiters
        The solution handles all specified characters: !,?._'@ as delimiters
        Apostrophes in words like "isn't" are treated as delimiters, splitting into "isn" and "t"    
    */
    
    /*
    Splitting the String:
        Use String.split() with regex [^a-zA-Z]+ which means:
        [^a-zA-Z] - Match any character that is NOT an English letter (a-z or A-Z)
        + - Match one or more of these non-letter characters
    This effectively splits the string on any sequence of non-letter characters    
    */
    private static void doSpliting(String s) {
        if (s ==  null || s.isEmpty() || s.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        s = s.trim();
        // Split on any non-letter character
        String[] tokens = s.split("[^a-zA-Z]+");
        
        System.out.println(tokens.length);
        for (String token : tokens) {
            System.out.println(token);
        }        
    }
    
    private static void doTokenizing(String s) {
        if (s ==  null || s.isEmpty() || s.trim().isEmpty()) {
            System.out.println(0);
            return;
        }
        s = s.trim();
        
        StringTokenizer st = new StringTokenizer(s, " !,?._'@");
        System.out.println(st.countTokens());
        
        while (st.hasMoreTokens()) {
            System.out.println(st.nextToken());
        }        
    }
    
    private static void scanner() {
        Scanner scan = new Scanner(System.in);
        String s = scan.nextLine().trim();
        scan.close();
        
        if (s.isEmpty()) {
            System.out.println(0);
            return;
        }
        doTokenizing(s);
    }
    
}
/*
Given a string, , matching the regular expression [A-Za-z !,?._'@]+, split the string into tokens. We define a token to be one or more consecutive English alphabetic letters. Then, print the number of tokens, followed by each token on a new line.

Note: You may find the String.split method helpful in completing this challenge.

Input Format

A single string, .

Constraints

 is composed of any of the following: English alphabetic letters, blank spaces, exclamation points (!), commas (,), question marks (?), periods (.), underscores (_), apostrophes ('), and at symbols (@).
Output Format

On the first line, print an integer, , denoting the number of tokens in string  (they do not need to be unique). Next, print each of the  tokens on a new line in the same order as they appear in input string .

Sample Input

He is a very very good boy, isn't he?
Sample Output

10
He
is
a
very
very
good
boy
isn
t
he
Explanation

We consider a token to be a contiguous segment of alphabetic characters. There are a total of  such tokens in string , 
and each token is printed in the same order in which it appears in string .
*/
