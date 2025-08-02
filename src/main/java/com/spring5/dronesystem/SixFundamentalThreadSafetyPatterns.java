/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 *
 * @author javaugi

 */
public class SixFundamentalThreadSafetyPatterns {

    static final SixFundamentalThreadSafetyPatterns main = new SixFundamentalThreadSafetyPatterns();

    public static void main(String[] args) {
        try {
            System.out.println("SixThreadSafetyPatterns");
            System.out.println("1. Immutability: ");
            // Immutable objects are inherently thread-safe because their state cannot be modified after creation. 
            // This eliminates the possibility of race conditions.
            /*
            1. Immutability:
            Key aspects of Immutability:
                The class is declared final (optional but recommended).
                All instance variables are declared final.
                No methods modify the state of the object after creation (no setters).
                If mutable objects are used internally (like List in this example), defensive copies are made in the constructor, 
                    and the internal reference is made unmodifiable.
            */
            main.runImmutableExample();

            System.out.println("2. Synchronization (using synchronized keyword) ");
            /*
                The synchronized keyword provides a mechanism to control access to shared resources by multiple threads. 
                    It can be used on methods or blocks of code.            
            */
            main.runSynchronizationExample();

            System.out.println("3. Locks (using java.util.concurrent.locks): ");
            /*
            The java.util.concurrent.locks package provides more flexible locking mechanisms than the synchronized keyword. 
                ReentrantLock is a common implementation.             
            */
            main.runConcurrentLockExample();

            System.out.println("4. Atomic Variables (using java.util.concurrent.atomic): ");
            /*
            Atomic variables provide lock-free, thread-safe operations on single variables. 
                 They use underlying hardware primitives for efficiency.            
            */
            main.runAtomicExample();

            System.out.println("5. ThreadLocal: ");
            /*
            ThreadLocal provides a way to create variables that are accessible only by the thread that created them. 
                This isolates data and avoids the need for explicit synchronization.            
            */
            main.runThreadLocalExample();

            System.out.println("6. Concurrent Collections (using java.util.concurrent): ");
            /*
            The java.util.concurrent package provides thread-safe collection implementations that are designed for 
                concurrent access without explicit external synchr            
            ConcurrentHashMap allows multiple threads to read and write to the map concurrently without the risk of data 
                corruption. Other concurrent collections include ConcurrentLinkedQueue, CopyOnWriteArrayList, etc.
            */
            main.runConcurrentMapExample();
            /*
            These examples demonstrate some of the fundamental thread safety patterns in Java. The choice of which pattern to
                use depends on the specific requirements of your application and the nature of the shared data and operations being 
                performed. Remember to carefully analyze your code for potential race conditions when dealing with multithreading.            
            */
        } catch (InterruptedException ex) {
        }
    }

    public void runConcurrentMapExample() throws InterruptedException {
        Map<String, Integer> wordCounts = new ConcurrentHashMap<>();

        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                wordCounts.compute("apple", (key, val) -> (val == null) ? 1 : val + 1);
            }
        };

        Runnable task2 = () -> {
            for (int i = 0; i < 1500; i++) {
                wordCounts.compute("banana", (key, val) -> (val == null) ? 1 : val + 1);
            }
        };

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Word counts: " + wordCounts); // Output will show the counts for "apple" and "banana"
    }

    public void runThreadLocalExample() throws InterruptedException {
        Runnable task = () -> {
            System.out.println("Thread: " + Thread.currentThread().getName() + ", ID: " + ThreadId.get());
        };

        Thread thread1 = new Thread(task, "Thread-1");
        Thread thread2 = new Thread(task, "Thread-2");
        Thread thread3 = new Thread(task, "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    public class ThreadId {

        private static final AtomicInteger nextId = new AtomicInteger(0);
        private static final ThreadLocal<Integer> threadId
                = ThreadLocal.withInitial(nextId::getAndIncrement);

        public static int get() {
            return threadId.get();
        }
    }

    private void runAtomicExample() throws InterruptedException {
        AtomicCounter counter = new AtomicCounter();
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Runnable decrementTask = () -> {
            for (int i = 0; i < 500; i++) {
                counter.decrement();
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);
        Thread thread3 = new Thread(decrementTask);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Final count: " + counter.getCount()); // Expected output: close to 1500
    }

    public class AtomicCounter {

        private final AtomicInteger count = new AtomicInteger(0);

        public void increment() {
            count.incrementAndGet(); // Atomically increment and return the new value
        }

        public void decrement() {
            count.decrementAndGet(); // Atomically decrement and return the new value
        }

        public int getCount() {
            return count.get(); // Get the current value
        }
    }

    private void runConcurrentLockExample() throws InterruptedException {
        ConcurrentLockCounter counter = new ConcurrentLockCounter();
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Runnable decrementTask = () -> {
            for (int i = 0; i < 500; i++) {
                counter.decrement();
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);
        Thread thread3 = new Thread(decrementTask);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Final count: " + counter.getCount()); // Expected output: close to 1500        
    }

    public class ConcurrentLockCounter {

        private int count = 0;
        private final Lock lock = new ReentrantLock();

        public void increment() {
            lock.lock(); // Acquire the lock
            try {
                count++;
            } finally {
                lock.unlock(); // Release the lock in a finally block to ensure it's always released
            }
        }

        public void decrement() {
            lock.lock();
            try {
                count--;
            } finally {
                lock.unlock();
            }
        }

        public int getCount() {
            lock.lock();
            try {
                return count;
            } finally {
                lock.unlock();
            }
        }
    }

    private void runSynchronizationExample() throws InterruptedException {
        CounterSynchronized counter = new CounterSynchronized();
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        };

        Runnable decrementTask = () -> {
            for (int i = 0; i < 500; i++) {
                counter.decrement();
            }
        };

        Thread thread1 = new Thread(incrementTask);
        Thread thread2 = new Thread(incrementTask);
        Thread thread3 = new Thread(decrementTask);

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("Final count: " + counter.getCount()); // Expected output: close to 1500        
    }

    public class CounterSynchronized {

        private int count = 0;

        // Synchronized method: only one thread can execute this method at a time
        public synchronized void increment() {
            count++;
        }

        // Synchronized block: only one thread can execute the code inside this block for a given object
        public void decrement() {
            synchronized (this) {
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }

    private void runImmutableExample() {
        //List<String> initialLabels = Arrays.asList("label1", "label2");
        //List<String> initialLabels = List.of("label1", "label2");
        //those above two are aosl immutable and they cannot be used        
        List<String> initialLabels = new java.util.ArrayList<>(List.of("label1", "label2"));

        ImmutablePoint point = new ImmutablePoint(10, 20, initialLabels);

        // Try to modify the original list (won't affect the ImmutablePoint)
        initialLabels.add("label3");
        System.out.println(point.getLabels()); // Output: [label1, label2]

        // Trying to modify the list obtained from ImmutablePoint will throw an exception
        try {
            point.getLabels().add("another label"); // This will throw UnsupportedOperationException
        } catch (UnsupportedOperationException e) {
            System.out.println("Cannot modify the labels of an ImmutablePoint.");
        }
    }

    public final class ImmutablePoint { // Mark the class as final to prevent subclassing that might introduce mutability

        private final int x;
        private final int y;
        private final List<String> labels; // Make sure the list itself is also immutable

        public ImmutablePoint(int x, int y, List<String> labels) {
            this.x = x;
            this.y = y;
            this.labels = Collections.unmodifiableList(new java.util.ArrayList<>(labels)); // Create a copy and make it unmodifiable
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public List<String> getLabels() {
            return labels;
        }

        // No setter methods are provided, ensuring immutability
    }
}
