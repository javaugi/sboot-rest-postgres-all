/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.capitalonesignalmock;

import com.interview.capitalonesignalmock.Code4ExpertSysDesignConcurrency.RateLimiter;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author javaugi
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class Code4ExpertRateLimiterTest {
    Code4ExpertSysDesignConcurrency main = new Code4ExpertSysDesignConcurrency();
    
    
    @Test
    public void testRateLimiter_AllowRequests() throws InterruptedException {
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

    @Test
    public void testRateLimiter_MultipleUsers() {
        Code4ExpertSysDesignConcurrency.RateLimiter limiter = main.new RateLimiter();
        assertTrue(limiter.allowRequest("user1"));
        assertTrue(limiter.allowRequest("user2")); // Different user
    }
    
}
