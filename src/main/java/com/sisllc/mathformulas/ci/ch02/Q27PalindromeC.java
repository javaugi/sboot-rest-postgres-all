package com.sisllc.mathformulas.ci.ch02;

import java.util.Objects;

public class Q27PalindromeC {

    public static void main(String[] args) {
        String[] words = {"12321", "Anna", "Madam", "Are we not drawn onward to new era",
            "A man, a plan, a cat, a ham, a yak, a yam, a hat, a canal-Panama!"};
        for (String word : words) {
            //word = word.replaceAll("[^a-zA-Z]", "").toLowerCase().replaceAll(" ", "");
            word = word.replaceAll("\\W", "").toLowerCase();
            System.out.println(word + ": " + isPalindrome2(word));
            System.out.println(word + ": " + isPalindrome(word.toCharArray()));
            System.out.println(word + ": " + isPalindrome(word.toCharArray(), 0, word.length() - 1));

        }

        Q27PalindromeC palindrome = new Q27PalindromeC();
        palindrome.runExample();
        palindrome.numberOfDigits(123454321);
    }

    void numberOfDigits(int num) {
        System.out.println("The number " + num + " lenght=" + String.valueOf(num).length());
        System.out.println("The number " + num + " lenght=" + (int) (Math.log10(num) + 1));
    }

    void runExample() {
        int num = 1234321;
        long reversed = reverse(num);
        System.out.println("The number " + num + " its reversed =" + reversed + " is a palindrome ?" + (num == reversed));
        System.out.println("The number " + num + " its reversed =" + reversed + " is a palindrome ?" + isPalindrome(num));
    }

    /*
    This seemed to work too, but did you consider the possibility that the reversed number
    might overflow? If it overflows, the behavior is language specific (For Java the number
    wraps around on overflow, but in C/C++ its behavior is undefined). Yuck.
    Of course, we could avoid overflow by storing and returning a type that has larger size
    than int (ie, long long). However, do note that this is language specific, and the larger
    type might not always be available on all languages.
     */
    public long reverse(int num) {
        if (num <= 0) {
            return 0;
        }
        int rev = 0;
        System.out.println("The original number is " + num);
        while (num != 0) {
            rev = rev * 10 + num % 10;
            num /= 10;
            System.out.println("num=" + num + " and rev=" + rev);
        }
        return rev;
    }

    /*
    We could construct a better and more generic solution. One pointer is that, we must start
    comparing the digits somewhere. And you know there could only be two ways, either
    expand from the middle or compare from the two ends.
    It turns out that comparing from the two ends is easier. First, compare the first and last
    digit. If they are not the same, it must not be a palindrome. If they are the same, chop off
    one digit from both ends and continue until you have no digits left, which you conclude
    that it must be a palindrome.
    35Now, getting and chopping the last digit is easy. However, getting and chopping the first
    digit in a generic way requires some thought. I will leave this to you as an exercise.
    Please think your solution out before you peek on the solution below.
     */
    private int getDivFromNumnber(int num) {
        //this will return 1 * 10 of power of num lenght - 1. Ex 1234321 -> 1000,000
        int div = 1;
        while (num / div >= 10) {
            div *= 10;
        }
        return div;
    }

    public boolean isPalindrome(int num) {
        if (num < 0) {
            return false;
        }
        int div = getDivFromNumnber(num);
        System.out.println("NOW num=" + num + " and the div=" + div);
        while (num != 0) {
            int left = num / div;
            int right = num % 10;
            System.out.println("LOOPING left=" + left + " and right=" + right + " and the num=" + num + " and the div=" + div);
            if (left != right) {
                return false;
            }
            num = (num % div) / 10; //this remove the front and last digit from the number
            div /= 100;
            System.out.println("LOOPING num=" + num + " and div=" + div);
        }
        return true;
    }

    public static boolean isPalindrome(char[] chars) {
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i] != chars[j]) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public static boolean isPalindrome(char[] chars, int i, int j) {
        if (i >= j) {
            return true;
        }
        if (chars[i] != chars[j]) {
            return false;
        }
        i++;
        j--;

        return isPalindrome(chars, i, j);
    }

    public static boolean isPalindrome(String word) {
        word = word.replaceAll("[^a-zA-Z]", "").toLowerCase().replaceAll(" ", "");
        StringBuilder sb = new StringBuilder();
        for (int i = word.length() - 1; i >= 0; i--) {
            sb.append(word.charAt(i));
        }
        System.out.println("word=\n ***** " + word + "\n reversed word =\n ***** " + sb.toString());
        return Objects.equals(word, sb.toString());
    }

    public static boolean isPalindrome2(String word) {
        word = word.replaceAll("\\W", "").toLowerCase();
        //String reversed = reverseRecursive(word.toCharArray(), 0, word.length() - 1);
        String reversed = reverse(word.toCharArray(), 0, word.length() - 1);
        System.out.println(" word=          " + word + "\n reversed word=   " + reversed + "\n isPalindrome=    " + Objects.equals(word, reversed));
        return Objects.equals(word, reversed);
    }

    public static String reverseRecursive(char[] chars, int i, int j) {
        if (i >= j) {
            return new String(chars);
        }
        char tmp = chars[i];
        chars[i] = chars[j];
        chars[j] = tmp;
        i++;
        j--;

        return reverseRecursive(chars, i, j);
    }

    public static String reverse(char[] chars, int i, int j) {
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }

        return new String(chars);
    }
}
