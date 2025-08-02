/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.codesignal;

/**
 *
 * @author javaugi
 *
 * Simple Matrix Practice in Java Lesson Overview Welcome to today's lesson
 * covering Simple Matrix Practice. This is where we traverse the arena of
 * two-dimensional data structures, commonly known as matrices. Matrices play an
 * instrumental role in many domains of programming, such as machine learning,
 * computer vision, and game development, making it important for you to
 * understand how to effectively manipulate and traverse them.
 *
 * Quick Example To explore Java matrices, it's essential to understand that a
 * matrix is simply a two-dimensional array, with each row being an array. Given
 * this structure, we can easily access matrix elements with the indices of the
 * row and the column. Our practice problems will be based on similar logic,
 * where we traverse and manipulate matrix data.
 *
 * One practical exercise that we will cover is, given a sorted matrix where
 * each row and column is sorted in ascending order, we have to search for a
 * particular target value. This exercise enhances your problem-solving skills
 * and deepens your understanding of matrix traversal.
 *
 * Since the matrix is sorted both row-wise and column-wise, we can leverage
 * this property for an efficient search. Start from the top-right corner of the
 * matrix:
 *
 * If the current element equals the target, you've found the value. If the
 * current element is greater than the target, move left (one column back). If
 * the current element is less than the target, move down (one row forward).
 * Continue these steps until you either find the target or exhaust the search
 * space. This method ensures that each step narrows down the potential search
 * area efficiently.
 *
 * Here is a Java implementation of this logic:
 */

/*
What's Next?
Once you've absorbed the fundamental concepts of matrices and their operations, we're all set to dive into the practice exercises. 
We cannot stress enough the importance of practice when it comes to programming. Remember, our goal here is not to brute-force our 
way through problems but to build a solid understanding of matrix concepts, improve problem-solving skills, and write code that
is not only correct but also efficient and elegant.
 */
public class L1SimpleMatrixPractice {

    public static boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Start from the top-right corner
        int row = 0, col = cols - 1;

        while (row < rows && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] > target) {
                col--; // Move left
            } else {
                row++; // Move down
            }
        }

        return false; // Target not found
    } 
    
    public static boolean searchMatrix2(int[][] matrix, int target) {
        for (int[] loopRow: matrix) {
            for (int i: loopRow) {
                if (i == target) {
                    return true;
                }
            }            
        }
        return false; // Target not found
    }    

    public static boolean searchMatrixNew(int[][] matrix, int target) {
        // 1. Input validation
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        int rows = matrix.length;
        int cols = matrix[0].length;

        // 2. Early exit for out-of-range targets
        if (target < matrix[0][0] || target > matrix[rows - 1][cols - 1]) {
            return false;
        }

        // 3. Start from top-right corner
        int row = 0, col = cols - 1;
        
        //6. Benchmarking & Profiling
        long startTime = System.nanoTime();
        // Call method
        long endTime = System.nanoTime();
        while (row < rows && col >= 0) {
            int current = matrix[row][col];
            // 4. add debugging
            System.out.println("Checking [" + row + "][" + col + "] = " + current);
            if (current == target) {
                return true;
            } else if (current > target) {
                col--; // Move left
            } else {
                row++; // Move down
            }
        }
        System.out.println("Execution time: " + (endTime - startTime) + " ns");
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };

        System.out.println("1 calling searchMatrix for 5");
        int target = 5;
        boolean found = searchMatrix(matrix, target);

        if (found) {
            System.out.println("searchMatrix of 5 Target found");
        } else {
            System.out.println("searchMatrix of 5 Target not found");
        }
        
        System.out.println("2 calling searchMatrix2 for 5");        
        found = searchMatrix2(matrix, target);
        if (found) {
            System.out.println("searchMatrix2 of 5 Target found");
        } else {
            System.out.println("searchMatrix2 of 5 Target not found");
        }        
    }

    /*
Your searchMatrix method implements an efficient "Step-wise Search" algorithm for a 2D matrix where rows and columns are sorted. 
    However, here are several optimizations and improvements you can make to enhance readability, robustness, and performance:

1. Add Input Validation
Handle edge cases like empty matrix or null input:

java
Copy
public static boolean searchMatrix(int[][] matrix, int target) {
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }
    // Rest of the code...
}
2. Early Exit for Out-of-Range Targets
Check if the target is outside the matrix's value range to avoid unnecessary loops:

java
Copy
int rows = matrix.length;
int cols = matrix[0].length;

// Early exit if target is smaller than the first element or larger than the last
if (target < matrix[0][0] || target > matrix[rows - 1][cols - 1]) {
    return false;
}
3. Optimize Loop Conditions
The current loop works, but you can make the condition more explicit:

java
Copy
while (row < rows && col >= 0) {
    // ...
}
Alternatively, use:

java
Copy
while (row <= rows - 1 && col >= 0) {
    // ...
}
(No performance difference, but some find it clearer.)

4. Use Binary Search for Large Matrices
For very large matrices, binary search on rows/columns can be faster (though your approach is already optimal for average cases).
Example for row-wise binary search:

java
Copy
int low = 0, high = rows - 1;
while (low <= high) {
    int mid = low + (high - low) / 2;
    if (matrix[mid][0] == target) {
        return true;
    } else if (matrix[mid][0] < target) {
        low = mid + 1;
    } else {
        high = mid - 1;
    }
}
// Now search in the `high` row
5. Add Debug Logging (Optional)
For debugging, log the search path:

java
Copy
System.out.println("Checking [" + row + "][" + col + "] = " + matrix[row][col]);
6. Benchmarking & Profiling
If performance is critical, benchmark with:

java
Copy
long startTime = System.nanoTime();
// Call method
long endTime = System.nanoTime();
System.out.println("Execution time: " + (endTime - startTime) + " ns");
Final Optimized Code
java
Copy
public static boolean searchMatrix(int[][] matrix, int target) {
    // Input validation
    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
        return false;
    }

    int rows = matrix.length;
    int cols = matrix[0].length;

    // Early exit for out-of-range targets
    if (target < matrix[0][0] || target > matrix[rows - 1][cols - 1]) {
        return false;
    }

    // Start from top-right corner
    int row = 0, col = cols - 1;

    while (row < rows && col >= 0) {
        int current = matrix[row][col];
        if (current == target) {
            return true;
        } else if (current > target) {
            col--; // Move left
        } else {
            row++; // Move down
        }
    }
    return false;
}
Key Optimizations Summary
Improvement	Benefit
Input validation	Prevents NullPointerException and empty matrix errors.
Early exit	Skips loop if target is out of bounds.
Binary search option	Better for huge matrices (though your original is already O(m + n)).
Cleaner code	More readable and maintainable.
This method is already efficient (O(m + n) time, O(1) space), but these tweaks make it more robust. For most interview settings, your original solution is sufficient—just add input validation!

Would you like to explore a divide-and-conquer approach for this problem?    
     */
}
