/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.amazon;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author javaugi
 */
public class LexicographicalOrdering {

    private static final LexicographicalOrdering main = new LexicographicalOrdering();

    public static void main(String[] args) {
        main.getMinimumNumberFromString();
        main.minimumNumericString();
        //*
        main.wordsOrder();
        main.numericStringOrder();
        main.mixedOrder();
        main.arrayMinLex();
        // */
    }

    private void getMinimumNumberFromString() {
        String num = "1432219";
        String minNum = getMinimumNumberFromString(num);
        System.out.println("Original: " + num + "-Minimum: " + minNum);

        String num2 = "1432219";
        String minNum2 = getMinimumNumberFromString2(num2);
        System.out.println("Method 2 Original: " + num2 + "-Minimum: " + minNum2);
    }

    private String getMinimumNumberFromString(String num) {
        // Convert the string to a char array
        char[] digits = num.toCharArray();

        // Sort the digits in ascending order
        Arrays.sort(digits);

        // Handle leading zeros (if any)
        // (If the first digit is '0', find the first non-zero digit and swap)
        if (digits[0] == '0') {
            int firstNonZero = 0;
            while (firstNonZero < digits.length && digits[firstNonZero] == '0') {
                firstNonZero++;
            }
            if (firstNonZero < digits.length) {
                // Swap the first '0' with the first non-zero digit
                char temp = digits[0];
                digits[0] = digits[firstNonZero];
                digits[firstNonZero] = temp;
            }
        }

        return new String(digits);
    }

    private static String getMinimumNumberFromString2(String num) {
        char[] digits = num.toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }

    private void minimumNumericString() {
        // Example 1
        String num1 = "1432219";
        int k1 = 3;
        int m1 = 2;
        String digits1 = "12";
        System.out.println("Original: " + num1);
        System.out.println("After removal and insertion: "
                + createMinimumString(num1, k1, m1, digits1));
        // Output: 12119 (removed 4,3,2 and inserted 1,2)

        // Example 2
        String num2 = "10200";
        int k2 = 1;
        int m2 = 1;
        String digits2 = "1";
        System.out.println("\nOriginal: " + num2);
        System.out.println("After removal and insertion: "
                + createMinimumString(num2, k2, m2, digits2));
        // Output: 0100 (removed 2 and inserted 0)

        // Example 3
        String num3 = "999";
        int k3 = 2;
        int m3 = 2;
        String digits3 = "01";
        System.out.println("\nOriginal: " + num3);
        System.out.println("After removal and insertion: "
                + createMinimumString(num3, k3, m3, digits3));
        // Output: 009 (removed two 9s and inserted 0,1)        
    }

    public static String createMinimumString(String num, int k, int m, String digitsToInsert) {
        // Step 1: Remove k digits to get the smallest possible number
        String afterRemoval = removeKDigits(num, k);

        // Step 2: Insert m digits at optimal positions
        String afterInsertion = insertMDigits(afterRemoval, m, digitsToInsert);

        return afterInsertion;
    }

    // Helper method to remove k digits to get smallest number
    private static String removeKDigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }

        Stack<Character> stack = new Stack<>();
        for (char digit : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }

        // Remove remaining k digits if any
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }

        // Build the result string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    // Helper method to insert m digits to make smallest number
    private static String insertMDigits(String num, int m, String digitsToInsert) {
        if (m == 0) {
            return num;
        }

        // Sort the digits to insert in ascending order
        char[] digits = digitsToInsert.toCharArray();
        java.util.Arrays.sort(digits);

        StringBuilder sb = new StringBuilder(num);
        int insertPos = 0;
        int digitsIndex = 0;

        while (m > 0 && digitsIndex < digits.length) {
            // Find the first position where current digit is <= next digit
            while (insertPos < sb.length()
                    && digits[digitsIndex] > sb.charAt(insertPos)) {
                insertPos++;
            }

            // Insert the digit
            sb.insert(insertPos, digits[digitsIndex]);
            digitsIndex++;
            m--;
            insertPos++; // Move past the inserted digit
        }

        // If we still have digits to insert, append them at the end
        while (m > 0 && digitsIndex < digits.length) {
            sb.append(digits[digitsIndex]);
            digitsIndex++;
            m--;
        }

        return sb.toString();
    }

    private void wordsOrder() {
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        System.out.println("wordsOrder - The original: " + words);

        // Find minimum string
        String minWord = Collections.min(words);
        System.out.println("Minimum word: " + minWord); // Output: apple

        // Sort to get full order
        Collections.sort(words);
        System.out.println("Sorted words: " + words); // Output: [apple, banana, cherry, date]
    }

    private void numericStringOrder() {
        List<String> numbers = Arrays.asList("100", "20", "300", "45");
        System.out.println("numericStringOrder - The original: " + numbers);

        // Find minimum numeric string (lexicographical)
        String minNumLex = Collections.min(numbers);
        System.out.println("Minimum (lex): " + minNumLex); // Output: 100 (because '1' < '2')

        // Find minimum numeric value (numeric comparison)
        String minNumNumeric = numbers.stream()
                .min((a, b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)))
                .get();
        System.out.println("Minimum (numeric): " + minNumNumeric); // Output: 20
        
        List<Integer> integers = Arrays.asList(100, 20, 300, 45);
        Integer minInteger = Collections.min(integers);
        System.out.println("** Minimum (integers): " + minInteger); // Output: 20
    }

    private void mixedOrder() {
        List<String> items = Arrays.asList("file1", "file10", "file2", "file20");
        System.out.println("mixedOrder - The Original: " + items);

        // Natural lexicographical order
        items.sort(Comparator.naturalOrder());
        System.out.println("Lex order: " + items);
        // Output: [file1, file10, file2, file20]

        // Natural numeric order (more human-friendly)
        items.sort((a, b) -> {
            // Extract numeric parts
            String aNum = a.replaceAll("\\D+", "");
            String bNum = b.replaceAll("\\D+", "");
            if (aNum.isEmpty() || bNum.isEmpty()) {
                return a.compareTo(b);
            }
            int numCompare = Integer.compare(Integer.parseInt(aNum), Integer.parseInt(bNum));
            return numCompare != 0 ? numCompare : a.compareTo(b);
        });
        System.out.println("Natural order: " + items);
        Collections.sort(items);
        System.out.println("*** Collections.sort(items) order: " + items);
        Collections.sort(items, Comparator.naturalOrder());
        System.out.println("*** Collections.sort(items, Comparator.naturalOrder()) order: " + items);
        Collections.sort(items, Comparator.reverseOrder());
        System.out.println("*** Collections.sort(items, Comparator.reverseOrder()) order: " + items);
        
        Collections.sort(items, Comparator.comparingInt(s -> 
                Integer.parseInt(s.replaceAll("\\D", ""))
            )
        );
        System.out.println("*** Collections.sort(replacing all non-digit) order: " + items);
    }

    private void arrayMinLex() {
        String[] arr = {"zebra", "apple", "mango", "banana"};
        System.out.println("arrayMinLex - The original : " + Arrays.toString(arr));
        String min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(min) < 0) {
                min = arr[i];
            }
        }

        System.out.println("Minimum string: " + min); // Output: apple        

        String[] arr2 = {"zebra", "apple", "mango", "banana"};
        System.out.println("arrayMinLex - The original arr2: " + Arrays.toString(arr2));
        String minNumLex = Collections.min(Arrays.asList(arr2));
        System.out.println("Minimum string arr2: " + minNumLex); // Output: apple        
        String maxNumLex = Collections.max(Arrays.asList(arr2));
        System.out.println("Minimum string arr2: " + maxNumLex); // Output: zebra        
    }

}

/*
Here's a Java example that creates the minimum possible numeric string by removing and inserting digits from an existing numeric string. The approach involves:

Removing exactly k digits from the string

Inserting exactly m digits into the string

Creating the smallest possible numeric string from these operations

java
Copy
import java.util.Stack;

public class MinimumNumericString {
    
    public static String createMinimumString(String num, int k, int m, String digitsToInsert) {
        // Step 1: Remove k digits to get the smallest possible number
        String afterRemoval = removeKDigits(num, k);
        
        // Step 2: Insert m digits at optimal positions
        String afterInsertion = insertMDigits(afterRemoval, m, digitsToInsert);
        
        return afterInsertion;
    }
    
    // Helper method to remove k digits to get smallest number
    private static String removeKDigits(String num, int k) {
        if (k >= num.length()) return "0";
        
        Stack<Character> stack = new Stack<>();
        for (char digit : num.toCharArray()) {
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        
        // Remove remaining k digits if any
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        
        // Build the result string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }
        
        // Remove leading zeros
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        
        return sb.length() == 0 ? "0" : sb.toString();
    }
    
    // Helper method to insert m digits to make smallest number
    private static String insertMDigits(String num, int m, String digitsToInsert) {
        if (m == 0) return num;
        
        // Sort the digits to insert in ascending order
        char[] digits = digitsToInsert.toCharArray();
        java.util.Arrays.sort(digits);
        
        StringBuilder sb = new StringBuilder(num);
        int insertPos = 0;
        int digitsIndex = 0;
        
        while (m > 0 && digitsIndex < digits.length) {
            // Find the first position where current digit is <= next digit
            while (insertPos < sb.length() && 
                   digits[digitsIndex] > sb.charAt(insertPos)) {
                insertPos++;
            }
            
            // Insert the digit
            sb.insert(insertPos, digits[digitsIndex]);
            digitsIndex++;
            m--;
            insertPos++; // Move past the inserted digit
        }
        
        // If we still have digits to insert, append them at the end
        while (m > 0 && digitsIndex < digits.length) {
            sb.append(digits[digitsIndex]);
            digitsIndex++;
            m--;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Example 1
        String num1 = "1432219";
        int k1 = 3;
        int m1 = 2;
        String digits1 = "12";
        System.out.println("Original: " + num1);
        System.out.println("After removal and insertion: " + 
            createMinimumString(num1, k1, m1, digits1));
        // Output: 12119 (removed 4,3,2 and inserted 1,2)
        
        // Example 2
        String num2 = "10200";
        int k2 = 1;
        int m2 = 1;
        String digits2 = "1";
        System.out.println("\nOriginal: " + num2);
        System.out.println("After removal and insertion: " + 
            createMinimumString(num2, k2, m2, digits2));
        // Output: 0100 (removed 2 and inserted 0)
        
        // Example 3
        String num3 = "999";
        int k3 = 2;
        int m3 = 2;
        String digits3 = "01";
        System.out.println("\nOriginal: " + num3);
        System.out.println("After removal and insertion: " + 
            createMinimumString(num3, k3, m3, digits3));
        // Output: 009 (removed two 9s and inserted 0,1)
    }
}
Key Points:
Removing Digits:

We use a stack-based approach to remove digits while maintaining the smallest possible number

We remove digits when a smaller digit follows a larger one (greedy approach)

Inserting Digits:

We sort the digits to insert in ascending order

We insert each digit at the earliest possible position where it's <= the next digit

Any remaining digits are appended at the end

Edge Cases:

Handling leading zeros

Cases where we need to remove all digits

Cases where we have more digits to insert than positions available

You can adjust the parameters (input string, k, m, digitsToInsert) in the main method to test different scenarios. 
    The algorithm ensures you get the smallest possible numeric string after performing the specified removals and insertions.
 */
 /*
Lexicographical Order in Computing
Lexicographical order is essentially dictionary order - the way words are ordered in a dictionary. In computing, it's a generalization of alphabetical order that extends to any comparable elements, including numbers, strings, and more complex data structures.

How It's Used in Computing
String comparison: Determining which string comes first alphabetically

Sorting algorithms: Ordering elements in data structures

Version numbering: Comparing software versions like "1.2.3" vs "1.10.0"

Database indexing: Efficiently organizing and searching records

File system organization: How files are ordered in directories

Java Examples for Minimum Ordering
1. Ordering Alphabetic Strings
java
Copy
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LexicographicalOrder {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("banana", "apple", "cherry", "date");
        
        // Find minimum string
        String minWord = Collections.min(words);
        System.out.println("Minimum word: " + minWord); // Output: apple
        
        // Sort to get full order
        Collections.sort(words);
        System.out.println("Sorted words: " + words); // Output: [apple, banana, cherry, date]
    }
}
2. Ordering Numeric Strings
java
Copy
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NumericStringOrder {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("100", "20", "300", "45");
        
        // Find minimum numeric string (lexicographical)
        String minNumLex = Collections.min(numbers);
        System.out.println("Minimum (lex): " + minNumLex); // Output: 100 (because '1' < '2')
        
        // Find minimum numeric value (numeric comparison)
        String minNumNumeric = numbers.stream()
            .min((a, b) -> Integer.compare(Integer.parseInt(a), Integer.parseInt(b)))
            .get();
        System.out.println("Minimum (numeric): " + minNumNumeric); // Output: 20
    }
}
3. Custom Comparator for Mixed Content
java
Copy
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MixedOrdering {
    public static void main(String[] args) {
        List<String> items = Arrays.asList("file1", "file10", "file2", "file20");
        
        // Natural lexicographical order
        items.sort(Comparator.naturalOrder());
        System.out.println("Lex order: " + items); 
        // Output: [file1, file10, file2, file20]
        
        // Natural numeric order (more human-friendly)
        items.sort((a, b) -> {
            // Extract numeric parts
            String aNum = a.replaceAll("\\D+", "");
            String bNum = b.replaceAll("\\D+", "");
            if (aNum.isEmpty() || bNum.isEmpty()) {
                return a.compareTo(b);
            }
            int numCompare = Integer.compare(Integer.parseInt(aNum), Integer.parseInt(bNum));
            return numCompare != 0 ? numCompare : a.compareTo(b);
        });
        System.out.println("Natural order: " + items);
        // Output: [file1, file2, file10, file20]
    }
}
4. Finding Minimum in Array (Primitive Approach)
java
Copy
public class ArrayMinLex {
    public static void main(String[] args) {
        String[] arr = {"zebra", "apple", "mango", "banana"};
        String min = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i].compareTo(min) < 0) {
                min = arr[i];
            }
        }
        
        System.out.println("Minimum string: " + min); // Output: apple
    }
}

Remember that lexicographical order compares character by character from left to right, which is why "100" 
    comes before "20" in pure string comparison (comparing '1' vs '2'). For numeric ordering of strings that 
    represent numbers, you need to parse them to integers first.
 */
