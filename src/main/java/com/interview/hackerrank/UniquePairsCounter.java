package com.interview.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UniquePairsCounter {
    public static void main(String[] args) {
        // getPairs();
        String[] pairLeft = { "john", "john", "john", "mary", "mary" };
        String[] pairRight = { "tom", "mary", "tom", "anna", "anna" };
        printUniquePairs(pairLeft, pairRight);
        //printUniqueFromFile();
    }
    
    private static void printUniqueFromFile() {
        try (InputStream inputStream = UniquePairsCounter.class.getClassLoader().getResourceAsStream("hackerrank_input06.txt");
             // Create a BufferedReader
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            if (inputStream == null) {
                System.err.println("Resource file not found!");
                return;
            }

            Set<String> uniSet = new HashSet<>();    
            String line;
            int ndx = 0;
            while ((line = reader.readLine()) != null) {
                ndx++;
                if (ndx == 1) {
                    continue;
                }                
                String[] token2 = line.split("\\s+");
                uniSet.add(token2[0] + ", " + token2[1]);
                System.out.println(uniSet.size()); // Process each line here
            }
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    private static void printUniquePairs(String[] pairLeft, String[] pairRight) {
        Set<String> uni = new HashSet<>();
        for (int i = 0; i < pairLeft.length; i++) {
            uni.add(pairLeft[i] + ", " + pairRight[i]);
            //Pair pair = new Pair(pairLeft[i], pairRight[i]);
            //uni.add(pair.toString());
            System.out.println(uni.size());
        }
    }

    private static void getPairs() {
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();
        scanner.nextLine(); // Consume the newline after the integer input

        Set<Pair> pairs = new HashSet<>();

        for (int i = 0; i < t; i++) {
            String line = scanner.nextLine();
            String[] names = line.split(" ");
            Pair pair = new Pair(names[0], names[1]);
            pairs.add(pair);
            System.out.println(pairs.size());
        }

        scanner.close();
    }

    /*
     * Explanation:
     * Data Structure Choice:
     * We use a HashSet to store the pairs because it automatically handles
     * uniqueness and provides O(1) complexity for add/contains operations.
     * Pair Class:
     * We create a custom Pair class to represent each name pair.
     * The class overrides equals() and hashCode() methods to ensure proper
     * comparison of pairs:
     * Two pairs are equal if both names match in the same order
     * The hash code is computed using both strings to maintain consistency with
     * equals
     * Input Handling:
     * First read the number of pairs (t)
     * For each pair, read the line, split into two names
     * Create a new Pair object and add to the set
     * Print the current size of the set after each addition
     * Output:
     * The size of the set after each insertion gives the count of unique pairs so
     * far
     * The HashSet automatically handles duplicates, so adding an existing pair
     * won't change the size
     */

    static class Pair {
        String first;
        String second;

        public Pair(String first, String second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pair pair = (Pair) o;
            return first.equals(pair.first) && second.equals(pair.second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() * 31 + second.hashCode();
        }

        @Override
        public String toString() {
            return "first=" + first + ", second=" + second;
        }        
    }

    private static void scanner() {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String[] pair_left = new String[t];
        String[] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        // Using a Set to track unique pairs
        Set<String> uniquePairs = new HashSet<>();

        for (int i = 0; i < t; i++) {
            // Combine the pair into a single string for easy comparison
            String pair = pair_left[i] + "," + pair_right[i];
            uniquePairs.add(pair);
            System.out.println(uniquePairs.size());
        }

        s.close();
    }

    private static void scanner2() {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        String[] pair_left = new String[t];
        String[] pair_right = new String[t];

        for (int i = 0; i < t; i++) {
            pair_left[i] = s.next();
            pair_right[i] = s.next();
        }

        for (int i = 0; i < t; i++) {
            boolean isUnique = true;
            // Check if current pair exists in previous pairs
            for (int j = 0; j < i; j++) {
                if (pair_left[i].equals(pair_left[j]) &&
                        pair_right[i].equals(pair_right[j])) {
                    isUnique = false;
                    break;
                }
            }

            // Count all unique pairs up to current index
            int count = 0;
            for (int k = 0; k <= i; k++) {
                boolean unique = true;
                for (int l = 0; l < k; l++) {
                    if (pair_left[k].equals(pair_left[l]) &&
                            pair_right[k].equals(pair_right[l])) {
                        unique = false;
                        break;
                    }
                }
                if (unique)
                    count++;
            }

            System.out.println(count);
        }
        s.close();
    }
}
