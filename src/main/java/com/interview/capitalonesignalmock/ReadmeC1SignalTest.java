/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

/**
 *
 * @author javaugi
 */
public class ReadmeC1SignalTest {
    /*
Please give me a detailed coding example of  the General Coding Assessment offered by CodeSignal for Senior Java Software Engineer, Full Stack position
The General Coding Assessment (GCA) on CodeSignal for a Senior Java Software Engineer (Full Stack) typically includes 4 problems of increasing difficulty, testing:

Algorithmic problem-solving (arrays, graphs, DP).

Object-oriented design (Java-specific best practices).

System design principles (scalability, concurrency).

Here’s a detailed mock assessment mirroring CodeSignal’s format, with solutions in Java and explanations.

Problem 1: Warm-Up (Easy) – Array Manipulation
Task: Given an array of integers, return the second-largest distinct value. If none, return -1.

Example:
Input: [5, 5, 4, 2, 3] → Output: 4
Input: [1, 1, 1] → Output: -1

Solution:

java
Copy
public int secondLargest(int[] nums) {
    int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
    for (int num : nums) {
        if (num > first) {
            second = first;
            first = num;
        } else if (num > second && num != first) {
            second = num;
        }
    }
    return second != Integer.MIN_VALUE ? second : -1;
}
Key Points:

Single-pass O(N) time, O(1) space.

Handles duplicates and edge cases (all identical values).

Problem 2: Medium – String Processing
Task: Given a string s, find the length of the longest substring with at most two distinct characters.

Example:
Input: "ccaabbb" → Output: 5 ("aabbb")

Solution (Sliding Window):

java
Copy
public int longestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0, maxLen = 0;
    for (int right = 0; right < s.length(); right++) {
        char c = s.charAt(right);
        map.put(c, map.getOrDefault(c, 0) + 1);
        while (map.size() > 2) {
            char leftChar = s.charAt(left);
            map.put(leftChar, map.get(leftChar) - 1);
            if (map.get(leftChar) == 0) map.remove(leftChar);
            left++;
        }
        maxLen = Math.max(maxLen, right - left + 1);
    }
    return maxLen;
}
Key Points:

Sliding window + hashmap to track character counts.

O(N) time, O(1) space (since map size ≤ 3).

Problem 3: Hard – Graph/Tree Traversal
Task: Given a binary tree, find the maximum path sum, where a path is any sequence of nodes connected by edges.

Example:
Input:

Copy
    1  
   / \  
  2   3  
Output: 6 (Path: 2 → 1 → 3)

Solution (Post-Order DFS):

java
Copy
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class Solution {
    int maxSum = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }
    
    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));
        maxSum = Math.max(maxSum, left + right + node.val);
        return Math.max(left, right) + node.val;
    }
}
Key Points:

Recursive DFS with O(N) time.

Tracks local (branch) and global (path) maxima.

Problem 4: Expert – System Design + Concurrency
Task: Design a thread-safe, scalable RateLimiter class in Java that allows 100 requests per second per user.

Solution (Token Bucket Algorithm):

java
Copy
import java.util.concurrent.*;

public class RateLimiter {
    private final ConcurrentHashMap<String, Semaphore> userLimits = new ConcurrentHashMap<>();
    private final int MAX_TOKENS = 100;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public RateLimiter() {
        scheduler.scheduleAtFixedRate(this::refillTokens, 1, 1, TimeUnit.SECONDS);
    }

    public boolean allowRequest(String userId) {
        Semaphore semaphore = userLimits.computeIfAbsent(userId, k -> new Semaphore(MAX_TOKENS));
        return semaphore.tryAcquire();
    }

    private void refillTokens() {
        userLimits.replaceAll((k, v) -> new Semaphore(MAX_TOKENS));
    }
}
Key Points:

Semaphores for thread-safe token tracking.

Scheduled refills reset limits every second.

O(1) per-request time complexity.

CodeSignal Pro Tips
Speed vs. Correctness:

Solve Problem 1 quickly (5-10 mins).

Allocate more time for Problems 3-4.

Edge Cases:

Test empty inputs, duplicates, and overflow (e.g., Integer.MIN_VALUE).

Java-Specifics:

Use StringBuilder for string manipulation.

Prefer ArrayDeque over LinkedList for queues.

Practice Resources
CodeSignal Arcade: Master their question bank.

LeetCode: Focus on:

Graph problems (LC 124, 337).

Concurrency (LC 1188, 1195).

Design: Practice rate limiters, caches, and distributed systems.

Need a deeper breakdown of any problem? Ask away!

Please give me some example on how to do JUnit tests on each of those above cases
Here are JUnit 5 (Jupiter) test examples for each of the four coding problems discussed earlier. I’ll include test cases, assertions, and edge-case coverage to ensure robustness.

1. Testing secondLargest (Array Problem)
java
Copy
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecondLargestTest {
    @Test
    void testSecondLargest_DistinctValues() {
        int[] nums = {5, 3, 9, 1};
        assertEquals(5, new Solution().secondLargest(nums));
    }

    @Test
    void testSecondLargest_Duplicates() {
        int[] nums = {5, 5, 4, 2};
        assertEquals(4, new Solution().secondLargest(nums));
    }

    @Test
    void testSecondLargest_AllIdentical() {
        int[] nums = {1, 1, 1};
        assertEquals(-1, new Solution().secondLargest(nums));
    }

    @Test
    void testSecondLargest_EmptyArray() {
        int[] nums = {};
        assertEquals(-1, new Solution().secondLargest(nums));
    }
}
Key Points:

Tests for duplicates, all identical values, and empty array.

Uses assertEquals for expected results.

2. Testing longestSubstring (Sliding Window Problem)
java
Copy
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LongestSubstringTest {
    @Test
    void testLongestSubstring_TwoDistinctChars() {
        assertEquals(5, new Solution().longestSubstring("ccaabbb"));
    }

    @Test
    void testLongestSubstring_SingleChar() {
        assertEquals(3, new Solution().longestSubstring("aaa"));
    }

    @Test
    void testLongestSubstring_EmptyString() {
        assertEquals(0, new Solution().longestSubstring(""));
    }

    @Test
    void testLongestSubstring_AllDistinct() {
        assertEquals(2, new Solution().longestSubstring("abc"));
    }
}
Key Points:

Covers edge cases (empty string, all identical characters).

Validates the sliding window logic.

3. Testing maxPathSum (Binary Tree Problem)
java
Copy
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MaxPathSumTest {
    @Test
    void testMaxPathSum_PositiveTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        assertEquals(6, new Solution().maxPathSum(root));
    }

    @Test
    void testMaxPathSum_NegativeTree() {
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        assertEquals(42, new Solution().maxPathSum(root)); // Path: 15 → 20 → 7
    }

    @Test
    void testMaxPathSum_SingleNode() {
        TreeNode root = new TreeNode(1);
        assertEquals(1, new Solution().maxPathSum(root));
    }
}
Key Points:

Tests positive and negative values.

Validates single-node and multi-node trees.

4. Testing RateLimiter (Concurrency Problem)
java
Copy
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.concurrent.*;

class RateLimiterTest {
    @Test
    void testRateLimiter_AllowRequests() throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        String userId = "user1";
        
        // First 100 requests should pass
        for (int i = 0; i < 100; i++) {
            assertTrue(limiter.allowRequest(userId));
        }
        
        // 101st request should fail
        assertFalse(limiter.allowRequest(userId));

        // Wait for refill (1 second)
        TimeUnit.SECONDS.sleep(1);
        
        // Next request after refill should pass
        assertTrue(limiter.allowRequest(userId));
    }

    @Test
    void testRateLimiter_MultipleUsers() {
        RateLimiter limiter = new RateLimiter();
        assertTrue(limiter.allowRequest("user1"));
        assertTrue(limiter.allowRequest("user2")); // Different user
    }
}
Key Points:

Tests request limit enforcement and token refill.

Uses TimeUnit.SECONDS.sleep(1) to test the refill mechanism.

Validates per-user limits with multiple users.

General JUnit Best Practices
Test Naming:
Use descriptive names like test[Method]_[Scenario] (e.g., testMaxPathSum_NegativeTree).

Edge Cases:
Always test empty inputs, null values, and extreme values.

Concurrency:
For thread-safe code, use CountDownLatch or ExecutorService to simulate concurrent access.

Parameterized Tests:
Use @ParameterizedTest for data-driven testing (e.g., multiple input-output pairs).

Example of Parameterized Test (JUnit 5):

java
Copy
@ParameterizedTest
@CsvSource({
    "'ccaabbb', 5",
    "'aaa', 3",
    "'', 0"
})
void testLongestSubstring_Parameterized(String input, int expected) {
    assertEquals(expected, new Solution().longestSubstring(input));
}
Tools to Enhance Testing
Mockito: For mocking dependencies (e.g., database calls in RateLimiter).

JaCoCo: To ensure code coverage (aim for 80%+).

Test Containers: For integration tests with real databases (PostgreSQL/Redis).

Need help adapting these tests for a specific framework (e.g., Spring Boot)? Ask away!    
    */
    
}
