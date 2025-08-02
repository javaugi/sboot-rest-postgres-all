/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.BitSet;
import java.util.Scanner;

/**
 *
 * @author javaugi
 */
public class BitSetOperations {

    public static void main(String[] args) {
        mainDemo();

        //8  1000
        //5  0011
        System.out.println("AND (&): Returns 1 if both corresponding bits are 1, otherwise 0.                   \n example: 8 & 5=" + (8 & 5));
        //0     0000
        System.out.println("OR (|): Returns 1 if at least one of the corresponding bits is 1, otherwise 0.      \n exapmle: 8 | 5=" + (8 | 5));
        //13    1011
        System.out.println("XOR (^): Returns 1 if the corresponding bits are different, otherwise 0.            \n exmaple: 8 ^ 5=" + (8 ^ 5));
        //13    1011
        System.out.println("NOT (~): Inverts all the bits of a number (1 becomes 0, and 0 becomes 1).           \n eample: ~5=" + (~5));
        // -6   1100

        int a = 5;  // Binary: 0101
        int b = 3;  // Binary: 0011
        System.out.println(a & b);  // Output: 1 (0101 & 0011 = 0001)
        System.out.println(a | b);  // Output: 7 (0101 | 0011 = 0111)
        System.out.println(a ^ b);  // Output: 6 (0101 ^ 0011 = 0110)
        System.out.println(~a);     // Output: -6 (Inverts 0101 to 1010, which is -6 in two's complement)
    }

    private static void mainDemo() throws NumberFormatException {
        //scanner();

        int[] intArr = {5, 10, 10};
        String[][] arrs = {{"AND 1 2", "SET 1 4", "FLIP 2 2", "OR 2 1"},
        {"OR 1 2", "AND 2 1", "OR 1 2", "AND 2 1", "OR 1 2", "AND 2 1", "OR 1 2", "AND 2 1", "OR 1 2", "AND 2 1"},
        {"SET 1 1", "SET 1 2", "SET 1 0", "AND 1 2", "SET 2 1", "SET 2 2", "SET 2 0", "AND 2 1"}};

        int[][][] results = {{{0, 0}, {1, 0}, {1, 1}, {1, 2}},
        {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}},
        {{1, 0}, {2, 0}, {3, 0}, {0, 0}, {0, 1}, {0, 2}, {0, 3}},};

        int ndx = 0;
        for (String[] arr : arrs) {
            doBitSetOp(intArr[ndx], arr);
            ndx++;
        }

        System.out.println("doBitSetOpDemo ...");
        doBitSetOpDemo();
    }

    private static void doBitSetOpDemo() {
        BitSet bitSet = new BitSet(8); // Creates a BitSet with 8 bits, initially all set to 0

        bitSet.set(2); // Sets the bit at index 2 to 1
        System.out.println("Initial BitSet with set to 2 : " + bitSet + "-cardinality=" + bitSet.cardinality()); // Output: {2}     1
        bitSet.set(5); // Sets the bit at index 5 to 1
        System.out.println("Initial BitSet with set to 5 : " + bitSet + "-cardinality=" + bitSet.cardinality()); // Output: {2, 5}  2

        System.out.println("Initial BitSet: " + bitSet + "-cardinality=" + bitSet.cardinality()); // Output: {2, 5}                 2

        bitSet.flip(2); // Flips the bit at index 2 (1 becomes 0)
        System.out.println("BitSet after flipping index 2: " + bitSet + "-cardinality=" + bitSet.cardinality()); // Output: {5}     1

        bitSet.flip(3); // Flips the bit at index 3 (0 becomes 1)
        System.out.println("BitSet after flipping index 3: " + bitSet + "-cardinality=" + bitSet.cardinality()); // Output: {3, 5}    2

        bitSet.flip(10); // Flips the bit at index 10 (0 becomes 1, automatically extends the BitSet size)
        System.out.println("BitSet after flipping index 10: " + bitSet + "-cardinality=" + bitSet.cardinality());  //{3, 5, 10}         3     
    }

    private static void doBitSetOp(int n, String[] arr) throws NumberFormatException {
        //The first line contains 2 space-separated integers, N (the length of both BitSets B1 and B2)
        //and M (the number of operations to perform), respectively.
        //String[] arr = {"AND 1 2","SET 1 4","FLIP 2 2", "OR 2 1"};
        //int n = 5;
        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);

        for (String str : arr) {
            String[] toekns = str.split(" ");
            String operation = toekns[0];
            int operand1 = Integer.parseInt(toekns[1]);
            int operand2 = Integer.parseInt(toekns[2]);

            switch (operation) {
                case "AND" -> {
                    if (operand1 == 1) {
                        b1.and(b2);
                    } else {
                        b2.and(b1);
                    }
                }
                case "OR" -> {
                    if (operand1 == 1) {
                        b1.or(b2);
                    } else {
                        b2.or(b1);
                    }
                }
                case "XOR" -> {
                    if (operand1 == 1) {
                        b1.xor(b2);
                    } else {
                        b2.xor(b1);
                    }
                }
                case "FLIP" -> {
                    if (operand1 == 1) {
                        b1.flip(operand2);
                    } else {
                        b2.flip(operand2);
                    }
                }
                case "SET" -> {
                    if (operand1 == 1) {
                        b1.set(operand2);
                    } else {
                        b2.set(operand2);
                    }
                }
            }

            //System.out  .println("Operation=" + operation + "-operand1=" + operand1 + "-operand2=" + operand2);
            //System.out  .println("B1: " + b1 + "-cardinality=" + b1.cardinality() + "-size=" + b1.size() + "-length=" + b1.length());
            //System.out  .println("B2: " + b2 + "-cardinality=" + b2.cardinality() + "-size=" + b2.size() + "-length=" + b2.length());
            System.out.println(b1.cardinality() + " " + b2.cardinality());
            //System.out.println();
        }
    }

    private static void scanner() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // Size of BitSets
        int m = scanner.nextInt(); // Number of operations

        BitSet b1 = new BitSet(n);
        BitSet b2 = new BitSet(n);

        for (int i = 0; i < m; i++) {
            String operation = scanner.next();
            int operand1 = scanner.nextInt();
            int operand2 = scanner.nextInt();

            switch (operation) {
                case "AND":
                    if (operand1 == 1) {
                        b1.and(b2);
                    } else {
                        b2.and(b1);
                    }
                    break;
                case "OR":
                    if (operand1 == 1) {
                        b1.or(b2);
                    } else {
                        b2.or(b1);
                    }
                    break;
                case "XOR":
                    if (operand1 == 1) {
                        b1.xor(b2);
                    } else {
                        b2.xor(b1);
                    }
                    break;
                case "FLIP":
                    if (operand1 == 1) {
                        b1.flip(operand2);
                    } else {
                        b2.flip(operand2);
                    }
                    break;
                case "SET":
                    if (operand1 == 1) {
                        b1.set(operand2);
                    } else {
                        b2.set(operand2);
                    }
                    break;
            }

            System.out.println(b1.cardinality() + " " + b2.cardinality());
        }
        scanner.close();
    }
    /*
Explanation:
Initialization:
    We create two BitSets b1 and b2 of size n (all bits initialized to 0)
    Read the number of operations m
Operation Processing:
    For each operation, we read the command and operands
    Using a switch statement, we perform the appropriate BitSet operation:
        AND, OR, XOR: Perform the binary operation between the two BitSets
        FLIP: Toggles the bit at the specified index
        SET: Sets the bit at the specified index to 1
Output:
    After each operation, we print the number of set bits (1s) in each BitSet using cardinality()
    The output is formatted as two space-separated integers

Key Features:
BitSet Operations:
    and(), or(), xor() perform bitwise operations
    flip() toggles a specific bit
    set() sets a specific bit to 1
    cardinality() counts the number of set bits
Efficiency:
    BitSet operations are very efficient in both time and space
    Each operation is O(n) in worst case but optimized in practice
Sample Execution:
For the input:
    5 4
    AND 1 2
    SET 1 4
    FLIP 2 2
    OR 2 1
The program will produce the output:
    0 0
    1 0
    1 1
    1 2
This matches the expected output in the problem statement, demonstrating the correct implementation of all BitSet operations.    

     */

}
/*
Java's BitSet class implements a vector of bit values (i.e.:  () or  ()) that grows as needed, allowing us to easily manipulate bits while 
    optimizing space (when compared to other collections). Any element having a bit value of  is called a set bit.

Given  BitSets,  and , of size  where all bits in both BitSets are initialized to , perform a series of  operations. After each operation,
    print the number of set bits in the respective BitSets as two space-separated integers on a new line.

Input Format
    The first line contains  space-separated integers,  (the length of both BitSets  and ) and  (the number of operations to perform), respectively.
    The M subsequent lines each contain an operation in one of the following forms:
    AND 
    OR 
    XOR 
    FLIP
    SET 
In the list above, <set> is the integer 1 or 2, where 1 denotes B1 and 2 denotes B2.
 is an integer denoting a bit's index in the BitSet corresponding to .

For the binary operations , , and , operands are read from left to right and the BitSet resulting from the operation replaces the contents of the first operand. For example:

AND 2 1
 is the left operand, and  is the right operand. This operation should assign the result of  to .

Constraints

Output Format

After each operation, print the respective number of set bits in BitSet  and BitSet  as  space-separated integers on a new line.

Sample Input

5 4
AND 1 2
SET 1 4
FLIP 2 2
OR 2 1
Sample Output

0 0
1 0
1 1
1 2
Explanation

Initially: , , , and . At each step, we print the respective number of set bits in  and  as a pair of space-separated integers on a new line.



, 
The number of set bits in  and  is .


Set  to  ().
, .
The number of set bits in  is  and  is .


Flip  from  () to  ().
, .
The number of set bits in  is  and  is .


.
, .
The number of set bits in  is  and  is .
*/
