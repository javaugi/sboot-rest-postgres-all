/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author javaugi
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class CodeSignalTest {
        
    @Test
    public void testStringList() {
        ArrayList<Integer> myList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        String myString = "Hello";

        // Slicing: subList for ArrayList and substring for Strings
        ArrayList<Integer> sliceList = new ArrayList<>(myList.subList(2, 4)); // [3, 4]
        String sliceString = myString.substring(1, 3); // "el"

        // Concatenation: addAll for lists and + operator for strings
        ArrayList<Integer> concatenateList = new ArrayList<>(myList);
        concatenateList.addAll(Arrays.asList(6, 7, 8)); // [1, 2, 3, 4, 5, 6, 7, 8]
        String concatenateString = myString + ", world!"; // "Hello, world!"
        
        // Finding the index of an element in a list or a string
        // indexOf returns the first occurrence index of the element
        // returns -1 if the list or the string doesn't contain the element
        int indexList = myList.indexOf(3); // 2 - Index of element '3'
        int indexString = myString.indexOf('e'); // 1 - Index of character 'e'

        // Sorting items in ArrayList in non-increasing order
        ArrayList<Integer> sortedList = new ArrayList<>(myList);
        sortedList.sort(Collections.reverseOrder()); // [5, 4, 3, 2, 1]  
        assertTrue(indexList == 2);
    }
    
}
