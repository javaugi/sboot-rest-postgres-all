package com.sisllc.mathformulas.impl;

/**
 * Keith Number To determine whether an n-digit number N is a Keith number,
 * create a Fibonacci-like sequence that starts with the n decimal digits of N,
 * putting the most significant digit first. Then continue the sequence, where
 * each subsequent term is the sum of the previous n terms. By definition, N is
 * a Keith number if N appears in the sequence thus constructed.
 *
 * A Keith number has the following integer sequence: 14, 19, 28, 47, 61, 75,
 * 197, 742, 1104, 1537, 2208, 2580, ....
 *
 * @author david
 *
 */
public class KeithNumber {

    public static void main(String[] args) {

        for (int x = 0; x < 5000; x++) {
            if (keith(x)) {
                System.out.println("****** KEITH=" + x);
            }
        }

        keith(75);
    }

    static boolean keith(int number) {

        if (number < 10) {
            return false;
        }

        int length = String.valueOf(number).length();

        int[] numbers = new int[length];

        // populate initial array
        int x = number;
        for (int i = 0, j = length; i < length; i++, j--) {
            int pow = (int) Math.pow(10, j - 1);
            numbers[i] = x / pow;
            x = x - (numbers[i] * pow);
            System.out.println("x=" + x + "-i=" + i + "-numbers[i] " + numbers[i]);
        }

        int total = 0;
        while (total < number) {
            total = numbers[0];
            for (int i = 1; i < length; i++) {
                total = total + numbers[i];
                numbers[i - 1] = numbers[i];
                System.out.println("for looping total=" + total + ": number=" + number + "\n " + print(numbers));
            }
            numbers[length - 1] = total;
            System.out.println("total=" + total + ": number=" + number + "\n " + print(numbers));
            if (total == number) {
                return true;
            }
        }
        return false;

    }

    private static String print(int[] numbers) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < numbers.length; i++) {
            sb.append(" " + numbers[i]);
        }
        return sb.toString();
    }

}
