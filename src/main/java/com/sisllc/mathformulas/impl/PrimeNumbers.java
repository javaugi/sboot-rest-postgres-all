package com.sisllc.mathformulas.impl;

import java.util.ArrayList;
import java.util.List;

public class PrimeNumbers {

    public static void main(String args[]) {
        for (int x = 2; x < 50; x++) {
            if (isPrime(x)) {
                System.out.print(x + ", ");
            }
        }
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public int product(int l, int r) {
        List<Integer> list = new ArrayList();
        for (int i = l; i <= r; i++) {
            if (isPrime(i)) {
                list.add(i);
            }
            System.out.println("list=" + list);
        }
        int returnValue = 1;
        for (Integer value : list) {
            returnValue *= value;
            System.out.println("value=" + value + "-returnValue=" + returnValue);
        }
        return returnValue;
    }
}
