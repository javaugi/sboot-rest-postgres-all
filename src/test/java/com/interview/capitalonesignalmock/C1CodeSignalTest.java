/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author javaugi
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class C1CodeSignalTest {
    
    @Test
    public void testWarmUpArrayManipulation() {
        Code1WarmUpArrayManipulation main = new Code1WarmUpArrayManipulation();
        int[] intArr = {1, 2, 3, 4, 5};
        int result = main.secondLargest(intArr);
        System.out.println("testArrayManipulation Test 1 The result is: " + result + " from input: " + intArr);
        assertTrue((result == 4), "The result should be 4 from {1, 2, 3, 4, 5}");
        
        int[] intArr2 = {5, 5, 4, 2, 3};
        result = main.secondLargest(intArr2);
        System.out.println("testArrayManipulation Test 2 The result is: " + result + " from input: " + intArr2);
        assertTrue((result == 4), "The result should be 4 from {5, 5, 4, 2, 3}");
        
        int[] intArr3 = {1, 1, 1};
        result = main.secondLargest(intArr3);
        System.out.println("testArrayManipulation Test 3 The result is: " + result + " from input: " + intArr3);        
        assertTrue((result == -1), "The result should be -1 from {1, 1, 1}");
    }
    
    @Test
    public void testMediumStringManipulation() {
        Code2MediumStringProcessing main = new Code2MediumStringProcessing();
        String input = "ccaabbb";
        int result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("testStringManipulation Test 1 The result is: " + result + " from input: " + input);
        assertTrue((result == 5), "The result should be 5 from ccaabbb");

        input = "dkfkflfhf";
        result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("testStringManipulation Test 2 The result is: " + result + " from input: " + input);
        assertTrue((result == 4), "The result should be 4 from dkfkflfhf");

        input = "cfdfehgf";
        result = main.longestSubstringWithAtLeastTwoDistinctChars(input);
        System.out.println("testStringManipulation Test 3 The result is: " + result + " from input: " + input);
        assertTrue((result == 3), "The result should be 3 from cfdfehgf");
    }    
    
    @Test
    public void testHardGraphTreeTraversalMaxLength() {
        Code3HardGraphTreeTraversal main = new Code3HardGraphTreeTraversal();
        Code3HardGraphTreeTraversal.TreeNode root = main.new  TreeNode(-10);
        root.left = main.new  TreeNode(9);
        root.right = main.new  TreeNode(20);
        root.right.left = main.new  TreeNode(15);
        root.right.right = main.new  TreeNode(7);
        System.out.println("testMaxPathSum_NegativeTree ...");
        assertEquals(42, main.maxPathSum(root)); // Path: 15 → 20 → 7
    }

    
    
    /*
    Key Points:
    Tests request limit enforcement and token refill.
    Uses TimeUnit.SECONDS.sleep(1) to test the refill mechanism.
    Validates per-user limits with multiple users.    
    */
    @Test
    public void testExpertSysDesignConcurrency() throws InterruptedException {
        Code4ExpertSysDesignConcurrency main = new Code4ExpertSysDesignConcurrency();
        
        Code4ExpertSysDesignConcurrency.RateLimiter limiter = main.new RateLimiter();
        String userId = "user1";
        
        // First 100 requests should pass
        for (int i = 0; i < 100; i++) {
            assertTrue(limiter.allowRequest(userId));
        }
        
        // 101st request should fail
        assertFalse(limiter.allowRequest(userId));

        // Wait for refill (1 second)
        TimeUnit.SECONDS.sleep(2);
        
        // Next request after refill should pass
        assertTrue(limiter.allowRequest(userId));
    }
    
    
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

    
    /*
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
    
    
        Tools to Enhance Testing
        Mockito: For mocking dependencies (e.g., database calls in RateLimiter).

        JaCoCo: To ensure code coverage (aim for 80%+).

        Test Containers: For integration tests with real databases (PostgreSQL/Redis).    
    */
}
