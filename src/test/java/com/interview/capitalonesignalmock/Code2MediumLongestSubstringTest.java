/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author javaugi
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Code2MediumLongestSubstringTest {
    Code2MediumStringProcessing main = new Code2MediumStringProcessing();
    
    @Test
    public void testLongestSubstring_TwoDistinctChars() {
        assertEquals(5, main.longestSubstringWithAtLeastTwoDistinctChars("ccaabbb"));
    }

    @Test
    public void testLongestSubstring_SingleChar() {
        assertEquals(3, main.longestSubstringWithAtLeastTwoDistinctChars("aaa"));
    }

    @Test
    public void testLongestSubstring_EmptyString() {
        assertEquals(0, main.longestSubstringWithAtLeastTwoDistinctChars(""));
    }

    @Test
    public void testLongestSubstring_AllDistinct() {
        assertEquals(2, main.longestSubstringWithAtLeastTwoDistinctChars("abc"));
    }    
}
