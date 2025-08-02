package com.sisllc.mathformulas.ci.ch01;

public class BitManipulation {

    public static void main(String[] args) {
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 128, Integer.MIN_VALUE};
        for (int i = 0; i < numbers.length; i++) {
            System.out.println(Integer.toBinaryString(numbers[i]));
            System.out.println(Integer.bitCount(numbers[i]));
            System.out.println("number=" + numbers[i] + "\n -is power of two?=" + isPowerOfTwo(numbers[i])
                    + "\n -is power of two?=" + isPowerOfTwo2(numbers[i])
                    + "\n -is power of two?=" + isPowerOfTwo3(numbers[i])
                    + "\n -is power of two?=" + isPower(2, numbers[i])
                    + "\n -is power of two?=" + isPower2(2, numbers[i]));
        }
    }

    private static boolean isPowerOfTwo(int n) {
        if (n == 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }
        return n == 1;
    }

    private static boolean isPowerOfTwo2(int number) {
        return number > 0 && ((number & (number - 1)) == 0);
    }

    private static boolean isPowerOfTwo3(int number) {
        return number > 0 && Integer.bitCount(number) == 1;
    }

    public static boolean isPower(int x, int y) {
        // Repeatedly compute power of x
        int pow = 1;
        while (pow < y) {
            pow = pow * x;
        }

        // Check if power of x becomes y
        System.out.println("number=" + y + "-pow=" + pow + "-x=" + x);
        return (pow == y);
    }

    public static boolean isPower2(int x, int y) {
        // logarithm function to calculate value
        if (x == 1) {
            return y == 1;
        }

        if (x == 2) {
            return isPowerOfTwo2(y);
        }

        int res1 = (int) Math.log(y) / (int) Math.log(x);

        // Note : this is double
        double res2 = Math.log(y) / Math.log(x);

        // compare and both are equal
        return (res1 == res2);
    }

}
