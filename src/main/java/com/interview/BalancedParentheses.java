/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

import java.util.*;

/*
Explanation:
    Stack Usage: We use a stack to keep track of opening parentheses.
    Pushing: When we encounter an opening parenthesis ((, {, [), we push it onto the stack.
    Popping: When we encounter a closing parenthesis (), }, ]), we check if it matches the top of the stack:
    If the stack is empty, it's unbalanced.
    If the closing parenthesis doesn't match the top of the stack, it's unbalanced.
    Final Check: After processing all characters, if the stack is empty, the string is balanced; otherwise, it's not.

Key Points:
    Time Complexity: O(n) where n is the length of the string (each character is processed once).
    Space Complexity: O(n) in the worst case (when all characters are opening parentheses).
    Edge Cases: Handles empty strings (returns true) and strings with only closing parentheses (returns false).
    This solution efficiently checks for balanced parentheses using the LIFO principle of stacks.
*/
public class BalancedParentheses {
    public static void main(String[] args) {
        /*
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            System.out.println(isBalanced(input));
        }
        // */
        
        System.out.println("1 isBalanced ...");
        List<String> inputs = Arrays.asList( "{}()","({()})", "{}(","[])");
        for (String input: inputs) {
            String value = (isBalanced(input) ? "true": "false");
            System.out.println(value);
        }
        
        System.out.println("2 isBalanced ...");
        for (String input: inputs) {
            String value = (isBalanced2(input) ? "true": "false");
            System.out.println(value);
        }
    }
    
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                
                char top = stack.pop();
                if (!((c == '}' && top == '{') || 
                      (c == ']' && top == '[') || 
                      (c == ')' && top == '('))) {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    public static boolean isBalanced2(String s) {        
        Stack<Character> stack = new Stack<>();
        
        for (char c: s.toCharArray()) {
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char top = stack.pop();                
                if (!((c == '}' && top == '{') || (c == ']' && top == '[')
                    || (c == ')' && top == '('))) {
                    return false;
                }
            }
            
        }
        
        
        return stack.isEmpty();
    }
}

/*

please write me a program for this scenario below In computer science, a stack or LIFO (last in, first out) is an abstract data type that serves as a collection of elements, with two principal operations: push, which adds an element to the collection, and pop, which removes the last element that was added.(Wikipedia)
A string containing only parentheses is balanced if the following is true: 1. if it is an empty string 2. if A and B are correct, AB is correct, 3. if A is correct, (A) and {A} and [A] are also correct.

Examples of some correctly balanced strings are: "{}()", "[{()}]", "({()})"

Examples of some unbalanced strings are: "{}(", "({)}", "[[", "}{" etc.

Given a string, determine if it is balanced or not.

Input Format

There will be multiple lines in the input file, each having a single non-empty string. You should read input till end-of-file.

The part of the code that handles input operation is already provided in the editor.

Output Format

For each case, print 'true' if the string is balanced, 'false' otherwise.

Sample Input

{}()
({()})
{}(
[]
Sample Output

true
true
false
true
*/