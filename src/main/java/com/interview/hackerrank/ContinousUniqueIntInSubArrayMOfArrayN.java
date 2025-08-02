    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author javaugi
 */
public class ContinousUniqueIntInSubArrayMOfArrayN {
    
    public static void main(String[] args) {
        //scanner();
        
        
        int[] intArr0 = {3, 5, 4, 3};
        int[][] intArr = {{5, 3, 5, 2, 3, 2}, {5, 3, 5, 7, 8, 9, 5, 3, 5}, {5, 3, 5, 2, 3, 2, 5, 3, 5}, {1, 1, 1, 1, 1, 1, 1, 1, 1}};

        int ndx = 0;
        for (int[] row: intArr) {        
            System.out.println("Solution 1=" + maxUniqueNumbers(row, intArr0[ndx]));
            System.out.println("Solution 2=" + maxUniqueNumbers2(row, intArr0[ndx]));
            ndx++;
        }
    }

    private static void scanner() {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int n = in.nextInt();
        int m = in.nextInt();
        int maxUnique = 0;
        
        for (int i = 0; i < n; i++) {
            int num = in.nextInt();
            
            // Add the new number to the deque and update frequency map
            deque.add(num);
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            
            // When the window size reaches m
            if (deque.size() == m) {
                // Update maxUnique if current window has more unique numbers
                maxUnique = Math.max(maxUnique, frequencyMap.size());
                
                // Remove the leftmost number from the window
                int removed = deque.remove();
                if (frequencyMap.get(removed) == 1) {
                    frequencyMap.remove(removed);
                } else {
                    frequencyMap.put(removed, frequencyMap.get(removed) - 1);
                }
            }
        }
        
        System.out.println(maxUnique);
    }
    
    /*
Key Components Added:
Frequency Map (HashMap<Integer, Integer>):
    Tracks how many times each number appears in the current window
    Allows O(1) access to count of each number
Sliding Window Logic:
    For each new number:
        Add to deque and update its count in frequency map
        When window size reaches M:
            Compare current unique count (frequencyMap.size()) with max
            Remove oldest number from deque and update frequency map
Efficiency Considerations:
    O(N) time complexity - Each element is processed exactly twice
    O(M) space complexity - The deque and map store at most M elements
    Handles the constraints efficiently (n ≤ 10⁵ with 1s time limit)
How It Works:
    As we read each number, we add it to our deque (window)
    We maintain counts of each number in the current window
    When the window reaches size M:
        We check how many unique numbers we have (frequencyMap.size())
        We remove the oldest number from the window
        If that number's count drops to 0, we remove it from the frequency map
    We keep track of the maximum number of unique elements seen in any window    
    */
    
    //Alternative Implementation (Using HashSet):
    /*
        Note: This naive approach has O(N*M) time complexity and would fail for large inputs due to time constraints. The first solution 
        using deque and frequency map is optimized for the problem constraints.
    */
    public static int maxUniqueNumbers2(int[] arr, int m) {
        int max = 0;
        for (int i = 0; i <= arr.length - m; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = i; j < i + m; j++) {
                set.add(arr[j]);
            }
            max = Math.max(max, set.size());
        }
        return max;
    }    
    
     // Solution to find the maximum number of unique integers in all contiguous subarrays of size M using a Deque and HashSet for efficient computation:
     public static int maxUniqueNumbers(int[] arr, int m) {
        if (m == 0 || arr.length == 0) return 0;
        
        Deque<Integer> deque = new ArrayDeque<>();
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        int maxUnique = 0;
        
        for (int i = 0; i < arr.length; i++) {
            // Add current element to deque and update frequency
            deque.addLast(arr[i]);
            frequencyMap.put(arr[i], frequencyMap.getOrDefault(arr[i], 0) + 1);
            
            // When window size reaches m
            if (deque.size() == m) {
                // Update the Max against the map size
                maxUnique = Math.max(maxUnique, frequencyMap.size());
                // Remove the leftmost element from window and update frequency
                int removed = deque.remove();
                frequencyMap.put(removed, frequencyMap.get(removed) - 1);


                /* Update the Max against the map size  - No diff for this call before or after the deque.remove
                maxUnique = Math.max(maxUnique, frequencyMap.size());
                // */
                /*
                int removed = deque.removeFirst();
                if (frequencyMap.get(removed) == 1) {
                    frequencyMap.remove(removed);
                } else {
                    frequencyMap.put(removed, frequencyMap.get(removed) - 1);
                }
                // */
            }
        }
        
        return maxUnique;
    }
     
     /*
    Explanation:
    Data Structures:
        Deque: Used to maintain the current window of elements (size M)
        HashMap: Tracks the frequency of each number in the current window
    Sliding Window Approach:
        Iterate through the array while maintaining a window of size M
        For each new element:
            Add it to the deque and update its frequency in the map
            When window size reaches M:
                Update maxUnique if current window has more unique elements
                Remove the leftmost element from the deque and update frequency map

    Efficiency:
        Time Complexity: O(N) - Each element is processed exactly twice (added and removed once)
        Space Complexity: O(M) - The deque and map store at most M elements

    Example:
    Input:
        6 3
        5 3 5 2 3 2
    Processing:
        Window [5,3,5]: 2 unique numbers (5,3)
        Window [3,5,2]: 3 unique numbers (3,5,2) ← New max
        Window [5,2,3]: 3 unique numbers
        Window [2,3,2]: 2 unique numbers (2,3)
    Output:
        3     
     */
}
