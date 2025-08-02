package com.sisllc.mathformulas.impl;

public class SquareRootNewtons {

    public static void main(String[] args) {

        print(36);
    }

    private static void print(double c) {
        // relative error tolerance
        double epsilon = 1e-15;

        // estimate of the square root of c
        double t = c;
        // repeatedly apply Newton update step until desired precision is
        // achieved
        while (Math.abs(t - c / t) > epsilon * t) {
            t = (c / t + t) / 2.0;
            System.out.println("process ... " + t);
        }

        // print out the estimate of the square root of c
        System.out.println("final: " + t);
    }
}
