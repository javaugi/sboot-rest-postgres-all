/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.spring5.dronesystem;

import java.util.concurrent.atomic.AtomicLong;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author javaugi
 */
@Getter
@Setter
@ToString
public class OperationStats {

    private final AtomicLong count = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();
    private final AtomicLong minTime = new AtomicLong(Long.MAX_VALUE);
    private final AtomicLong maxTime = new AtomicLong(Long.MIN_VALUE);

    public static void main(String[] args) {
        OperationStats main = new OperationStats(10);
        main.test();
    }
    
    private void test() {
        count.incrementAndGet();
        System.out.println("count.incrementAndGet()=" + count.incrementAndGet());
        System.out.println("totalTime.addAndGet(5)=" + totalTime.addAndGet(5));
        System.out.println("minTime.accumulateAndGet(5, Math::min)=" + minTime.accumulateAndGet(5, Math::min));
        System.out.println("maxTime.accumulateAndGet(5, Math::max)=" + maxTime.accumulateAndGet(5, Math::max));
    }
    
    
    public OperationStats(long initialDuration) {
        recordDuration(initialDuration);
    }

    public void recordDuration(long duration) {
        count.incrementAndGet();
        totalTime.addAndGet(duration);
        minTime.accumulateAndGet(duration, Math::min);
        maxTime.accumulateAndGet(duration, Math::max);
    }

    public double getAverageTime() {
        return totalTime.get() / (double) count.get();
    }
}
