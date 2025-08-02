package com.sisllc.mathformulas.impl;

/**
 *
 * @author david An Armstrong number is an n-digit number that is equal to the
 * sum of the nth powers of its digits. For example: 371 = 33 +73 +13 9474 = 94
 * + 44 +74 + 44
 */
public class ArmstrongNumbers {

    public static void main(String[] args) {
        System.out.println("List of Armstrong Numbers under 10000:");
        for (int i = 0; i < 10000; i++) {
            if (isArmstrong(i)) {
                System.out.println(i);
            }
        }
    }

    private static boolean isArmstrong(int input) {

        int num = input;
        int length = String.valueOf(num).length();;
        int total = 0;
        int digit = 0;

        while (num != 0) {
            digit = num % 10;
            total = total + (int) Math.pow(digit, length);
            num = num / 10;
        }

        if (total == input) {
            return true;
        } else {
            return false;
        }
    }
}
