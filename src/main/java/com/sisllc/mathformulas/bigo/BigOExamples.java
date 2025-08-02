package com.sisllc.mathformulas.bigo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
Big-O:

    Describes how the algorithm scales and performs, in terms of either the execution time required or the space used.
    Is relative representation of complexity. This allows you to reduce an algorithm to a variable which in turn allows 
    you to easily compare it to another.
    Describes an upper limit on the growth of a function, in the other words the “worst case scenario”.

    There is also Big-Omega notation which looks at the lower bound / “best case scenario” stating that the algorithm will 
    take at least X amount of time and Big-Theta which is tight bound to both lower and upper / “average”.

Some quick observations in determining Big-O:

    A Sequence of statements, or things like conditional checks are constant: O(1)
    A loop of statements result in : O(n) n being the number of loop executions.
    Nested loops are multiplied together: O(n2) where n is the times the outer loop executes and m is the times the inner 
    loop executes.

Comparing the common notation examples:

n	Constant O(1)	Logarithmic O(log n) Linear O(n)	Linear Logarithmic O(n log n)	Quadractic O(n2)	Cubic O(n3)
1           1	1	1	1	1	1
2           1	1	2	2	4	8
4           1	2	4	8	16	64
8           1	3	8	24	64	512
16          1	4	16	64	256	4,096
1,024       1	10	1,024	10,240	1,048,576	1,073,741,824
1,048,576   1	20	1,048,576	20,971,520	1012	1016


Common Data Structures and Relative functions: Lists and Sets:

Structure	    get         add         remove      contains
ArrayList	    O(1)        O(1)*	    O(n)        O(n)
LinkedList	    O(n)        O(1)	    O(1)        O(n)
HashSet         O(1)        O(1)        O(1)        O(1)
LinkedHashSet	O(1)        O(1)	    O(1)        O(1)
TreeSet         O(log n)    O(log n)	O(log n)    O(log n)

 */
/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class BigOExamples {

    private static final Logger log = LoggerFactory.getLogger(BigOExamples.class);

    /**
     * Constant. O(1)
     *
     * @param n the n
     * @return the int
     */
    public int constant(int n) {

        if (n > 1) {
            return n;
        } else {
            return 0;
        }
    }

    /**
     * Linear. O(n)
     *
     * @param n the n
     * @return the int
     */
    public int linear(int n) {
        int sum = 0;
        for (int j = 0; j < n; j++) {
            sum += j;

        }
        return sum;
    }

    /**
     * Quadratic. O(n^2)
     * sum of sq1 + sq2 + sq33 + sq4 + ....
     *
     * @param n the n
     * @return the int
     */
    public int quadratic(int n) {
        int sum = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                sum += j * k;
            }
        }
        return sum;
    }

    /**
     * Cubic. O(n^3)
     *
     * @param n the n
     * @return the int
     */
    public int cubic(int n) {
        int sum = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                for (int l = 0; l < n; l++) {
                    sum += j * k / (l + 1);
                }
            }
        }
        return sum;
    }

    /**
     * Logarithmic. O(log n). Binary Search.
     *
     * @param data the to search
     * @param key the key
     * @return the int
     */
    public int logarithmic(Integer[] data, int key) {
        int startIndex = 0;
        int endIndex = data.length - 1;

        while (startIndex < endIndex) {
            int midIndex = (endIndex - startIndex / 2) + startIndex;
            int midValue = data[midIndex];

            if (key > midValue) {
                startIndex = midIndex++;
            } else if (key < midValue) {
                endIndex = midIndex - 1;
            } else {
                return data[midIndex];
            }
        }
        return -1;
    }

    /**
     * Linear Logarithmic. O(n log n). Quick Sort.
     *
     * @param data the to search
     * @param key the key
     * @return the int
     */
    public Integer linearLogarithmic(Integer[] data) {

        QuickSort<Integer> sorter = new QuickSort<Integer>();
        sorter.sort(data);

        return data[0];
    }

}
