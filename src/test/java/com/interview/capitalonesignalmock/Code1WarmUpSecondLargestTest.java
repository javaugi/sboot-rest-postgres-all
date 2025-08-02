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
public class Code1WarmUpSecondLargestTest {
    Code1WarmUpArrayManipulation main = new Code1WarmUpArrayManipulation();
    
    @Test
    public void testSecondLargest_DistinctValues() {
        int[] nums = {5, 3, 9, 1};
        assertEquals(5, main.secondLargest(nums));
    }

    @Test
    public void testSecondLargest_Duplicates() {
        int[] nums = {5, 5, 4, 2};
        assertEquals(4, main.secondLargest(nums));
    }

    @Test
    public void testSecondLargest_AllIdentical() {
        int[] nums = {1, 1, 1};
        assertEquals(-1, main.secondLargest(nums));
    }

    @Test
    public void testSecondLargest_EmptyArray() {
        int[] nums = {};
        assertEquals(-1, main.secondLargest(nums));
    }    
}
