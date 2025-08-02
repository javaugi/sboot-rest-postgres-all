/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

import com.interview.capitalonesignalmock.Code2MediumStringProcessing;
import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author javaugi
 */
public class ExampleParameterizedTest {
    
    
    //Example of Parameterized Test (JUnit 5):
    @ParameterizedTest
    @CsvSource({
        "'ccaabbb', 5",
        "'aaa', 3",
        "'', 0"
    })
    public void testLongestSubstring_Parameterized(String input, int expected) {
        System.out.println("testLongestSubstring_Parameterized - Example of Parameterized Test (JUnit 5):");
        Code2MediumStringProcessing main = new Code2MediumStringProcessing();        
        assertEquals(expected, main.longestSubstringWithAtLeastTwoDistinctChars(input));
    }
    
    
    // The method to test
    public boolean isPalindrome(String candidate) {
        if (candidate == null) {
            return false;
        }
        String reversed = new StringBuilder(candidate).reverse().toString();
        return candidate.equals(reversed);
    }

    // Parameterized test with a set of palindrome strings
    @ParameterizedTest
    @ValueSource(strings = { "radar", "level", "rotor", "civic", "madam" })
    public void testIsPalindrome_withValidPalindromes(String candidate) {
        assertTrue(isPalindrome(candidate), candidate + " should be a palindrome");
    }

    // Parameterized test with a set of non-palindrome strings
    @ParameterizedTest
    @ValueSource(strings = { "hello", "world", "java", "parameterized", "junit" })
    public void testIsPalindrome_withInvalidPalindromes(String candidate) {
        assertFalse(isPalindrome(candidate), candidate + " should not be a palindrome");
    }    
}
