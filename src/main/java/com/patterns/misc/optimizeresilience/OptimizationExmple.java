/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.patterns.misc.optimizeresilience;

import com.spring5.entity.User;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author javaugi
 */
public class OptimizationExmple {

    private static final OptimizationExmple main = new OptimizationExmple();

    public static void main(String[] args) {
        try{
            main.run();
        }catch(Exception ex) {
            
        }
        
    }

    private void run() throws Exception {
        boolean runTest = false; 
        
        //A. Code-Level Optimizations
        if (runTest) {
            codeLevelEfficientObjectCreation();
        }
        
        //B. JVM-Level Optimizations
        if (runTest) {
            jvmLevelOptimization();
        }

    }
    
    private void jvmLevelOptimization() {
        /*
        1. Garbage Collection Tuning
        Use Case: Low-latency apps (e.g., trading systems).
        Solution:
            G1GC (Default in JDK 9+): Balances throughput/latency.
            ZGC (JDK 15+): Sub-millisecond pauses for large heaps.

            # JVM Flags for G1GC
            -XX:+UseG1GC -XX:MaxGCPauseMillis=200
        
        2. JIT Compilation
            Problem: Cold-start performance.
            Solution: Warm up critical paths.

                // Trigger JIT compilation early
                for (int i = 0; i < 10_000; i++) {
                  hotMethod();  // JIT-compiled after ~10K invocations (default threshold)
                }
        3. Memory Layout
            Problem: Poor cache locality.
            Solution: Use contiguous memory (arrays vs. linked lists).

            // Bad: Linked data structures
            class Node { Node next; int value; }  // Pointer chasing

            // Good: Flat memory (e.g., for matrix operations)
            int[] matrix = new int[1000];  // CPU         
        */
    }
    
    public final class Config {
        public static final int TIMEOUT = 5000;
    }

    //The first part is from ChatGPT and the second part is from DeepSeek
    private void codeLevelEfficientObjectCreation() throws Exception {
        // 1. Compiler-Level Optimizations
            // Use Primitive Types Instead of Wrappers            
            // Avoid this
            Integer count = 0;
            // Prefer this
            int countInt = 0;
            
            //Use StringBuilder for String Concatenation in Loops
            // Bad
            String result = "";
            for (int i = 0; i < 1000; i++) {
                result += i;
            }

            // Good
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                sb.append(i);
            }
            
            // Use final key word - Allows the JVM to make assumptions.
            // the Config class above
        //2. Memory Optimization
            //string pooling 
                // Good: uses string pool
                String s1 = "hello";
                // Bad: creates new string object
                String s2 = new String("hello");
            //Avoid Memory Leaks with Proper Resource Management - Use try-with-resources:
                boolean runTryWithResource = false;
                if (runTryWithResource) {
                    try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
                        System.out.println(reader.readLine());
                    }
                }
        // 3. Collections and Data Structures
            //Use Right Collection for the Job
                // When order doesn’t matter
                Set<String> names = new HashSet<>();
                // When order matters
                List<String> names2 = new ArrayList<>();
                // When frequent search is needed
                Map<String, User> userMap = new HashMap<>();
            //Initialize with Capacity
                List<String> list = new ArrayList<>(100); // avoids resizing
        //4. Concurrency Optimizations
            //Use ExecutorService instead of manual threads
                ExecutorService executor = Executors.newFixedThreadPool(4);
                executor.submit(() -> {
                    System.out.println("Task running...");
                });
                executor.shutdown();
            // Use Concurrent Collections
                Map<String, Integer> map = new ConcurrentHashMap<>();
        //5. I/O Optimization
            // Use Buffered I/O
                BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            //Use NIO for High Performance
                Path path = Paths.get("file.txt");
                List<String> lines = Files.readAllLines(path);

        // 6. Algorithmic Optimization
            //Optimize Loops and Recursion
                // Bad
                for (int i = 0; i < list.size(); i++) {
                    process(list.get(i));
                }
                // Good
                for (String item : list) {
                    process(item);
                }
            //Memoization Example
                Map<Integer, Long> cache = new HashMap<>();
                /*
                long fibonacci(int n) {
                    if (n <= 1) return n;
                    if (cache.containsKey(n)) return cache.get(n);

                    long result = fibonacci(n - 1) + fibonacci(n - 2);
                    cache.put(n, result);
                    return result;
                }
                // */
        //7. Avoiding Unnecessary Object Creation
            //Use Static Factory Methods
            // Instead of using 'new' every time
                Boolean b = Boolean.valueOf("true");
        //8. Profiling and Monitoring Tools
            /*
                VisualVM
                JConsole
                Java Flight Recorder
                YourKit
                JProfiler
                // */
        //9. JVM and GC Tuning
            //Configure your JVM via command line:  java -Xmx2G -Xms512M -XX:+UseG1GC -jar app.jar
        //10. Spring-Specific Optimizations
            // Use @Lazy, @Scope("prototype"), or caching wisely
                /*
                @Bean
                @Lazy
                public ExpensiveService expensiveService() {
                    return new ExpensiveService();
                }
                // */
        //11. Database and ORM Optimizations
                /*
                Use pagination for queries
                Fetch only what you need (SELECT columns)
                Use JOIN FETCH or batch fetching to avoid N+1 queries

                @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
                User findByIdWithRoles(Long id);
                // */
        //12. JIT Optimization Tips
                /*
                Avoid excessive object creation
                Avoid reflection (when performance is critical)
                Use final/static methods for inlining
                // */
                
        /* additionals
        3. Avoid Unnecessary Synchronization
        Use ConcurrentHashMap instead of synchronizing HashMap.
        Prefer ReadWriteLock for read-heavy concurrent operations.

        4. Use Efficient Data Structures
        ArrayList vs LinkedList (use based on indexing vs insertion use case).
        HashMap vs TreeMap (unordered vs sorted).
        // */
        
        
        //The following is the second part from DeepSeek
        // 1. Efficient Object Creation
        //        Problem: Excessive object allocation in loops causes GC pressure.
        //        Solution: Reuse objects (e.g., with object pools or thread-local variables).
        // Bad: New StringBuilder per iteration
            /*
            for (int i = 0; i < 1000; i++) {
                StringBuilder sb = new StringBuilder();  // Creates 1000 objects
            }

            // Good: Reuse a single instance
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 1000; i++) {
                sb.setLength(0);  // Reset instead of reallocating
            }
            // */
        
        //2. Collections Optimization
        //    Problem: ArrayList with frequent insertions at index 0 causes O(n) shifts.
        //    Solution: Use LinkedList for frequent insertions/deletions.
        // Bad: ArrayList for FIFO queue
        List<String> queue = new ArrayList<>();  // Slow for remove(0)
        // Good: LinkedList for FIFO
        Queue<String> queueLinkedList = new LinkedList<>();  // O(1) for poll()

        
        //3. Avoid Boxed Primitives
        //    Problem: Unnecessary Integer, Long overhead.
        //    Solution: Use primitives (int, long) where possible.

        // Bad: Boxed primitive
        List<Integer> numbers = new ArrayList<>();  // Heap overhead
        // Good: Primitive collections (Eclipse Collections, FastUtil)
        //IntList numbersList = new intArrayList();   // No box        
        
    }
    
    
    private void process(String item) {
        
    }

}
