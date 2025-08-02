package com.sisllc.mathformulas.impl;

/**
 * Triangle Numbers A triangular number is the number of dots in an equilateral
 * triangle evenly filled with dots. For example, three dots can be arranged in
 * a triangle; thus three is a triangle number. The nth triangle number is the
 * number of dots in a triangle with n dots on a side.
 * 
 * @author david
 *
 */
public class TriangleNumbers {
	public static void main(String[] args) {

		for (int i = 1; i < 10; i++) {
			int n = i * (i + 1) / 2;
			System.out.println("n:" + i + " triangle number:" + n);
		}

	}
}
