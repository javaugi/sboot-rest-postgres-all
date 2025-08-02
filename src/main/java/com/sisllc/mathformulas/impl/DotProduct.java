package com.sisllc.mathformulas.impl;

/**
 * Dot Product In mathematics, the dot product, or scalar product (or sometimes
 * inner product in the context of Euclidean space), is an algebraic operation
 * that takes two equal-length sequences of numbers (usually coordinate vectors)
 * and returns a single number. This operation can be defined either
 * algebraically or geometrically.
 *
 *
 * Questions answered by this page: Java dot product of two arrays. Dot product
 * calculation in Java. java example of Scalar Product. Compute the dot product
 * of two vectors
 *
 * @author david
 *
 */
public class DotProduct {

    public static void main(String[] args) {
        double[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        double[] b = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

        System.out.println(dotProduct(a, b));
    }

    public static double dotProduct(double[] a, double[] b) {
        double sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i] * b[i];
        }
        return sum;
    }
}
