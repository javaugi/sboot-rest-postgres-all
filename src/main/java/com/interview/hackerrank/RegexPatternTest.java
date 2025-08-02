/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 *
 * @author javaugi
 */
public class RegexPatternTest {

    public static void main(String[] args) {
        //scanner();
        String[] strArr = {"([A-Z])(.+)", "[AZ[a-z](a-z)", "batcatpat(nat"};
        for (String token : strArr) {
            String answer = isValidRegex(token) ? "Valid" : "Invalid";
            System.out.println(answer + "       with this pattern=" + token);
        }
    }

    private static void scanner() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        int testCases = Integer.parseInt(scanner.nextLine());
        while (testCases-- > 0) {
            String pattern = scanner.nextLine();
            //Write your code
            System.out.println(isValidRegex(pattern) ? "Valid" : "Invalid");

        }
    }

    private static boolean isValidRegex(String regex) {
        try {
            Pattern.compile(regex);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /*
    Explanation:
    Reading Input:
        First reads the number of test cases
        Then reads each pattern to validate
    Validation Method:
        Uses Pattern.compile() which throws PatternSyntaxException if the pattern is invalid
        Returns true if compilation succeeds (valid pattern), false otherwise
    Output:
        Prints "Valid" for compilable patterns
        Prints "Invalid" for patterns that throw exceptions
    Key Points:
        Pattern.compile(): The core validation mechanism - Java's built-in regex compiler
        Exception Handling: Catches PatternSyntaxException to detect invalid patterns
    Edge Cases: Handles all possible invalid regex syntax including:
        Unbalanced parentheses/brackets
        Invalid character classes
        Invalid quantifiers
        Any other malformed regex syntax    
     */

    public static boolean isValidRegexCompex(String pattern) {
        // Quick checks for common issues
        if (pattern.isEmpty()) {
            return false;
        }

        // Count open/close brackets/parentheses
        long openBrackets = pattern.chars().filter(c -> c == '[').count();
        long closeBrackets = pattern.chars().filter(c -> c == ']').count();
        if (openBrackets != closeBrackets) {
            return false;
        }

        long openParens = pattern.chars().filter(c -> c == '(').count();
        long closeParens = pattern.chars().filter(c -> c == ')').count();
        if (openParens != closeParens) {
            return false;
        }

        // More checks could be added here...
        // Final check with actual compilation
        try {
            Pattern.compile(pattern);
            return true;
        } catch (PatternSyntaxException e) {
            return false;
        }
    }
}
