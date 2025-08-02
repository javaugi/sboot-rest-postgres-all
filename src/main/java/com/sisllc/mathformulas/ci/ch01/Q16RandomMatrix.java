package com.sisllc.mathformulas.ci.ch01;

import com.sisllc.mathformulas.ci.lib.AssortedMethods;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class Q16RandomMatrix {

    private static final Logger log = LoggerFactory.getLogger(Q16RandomMatrix.class);

    public static void rotate(int[][] matrix, int n) {
        for (int layer = 0; layer < n / 2; ++layer) {
            int first = layer;
            int last = n - 1 - layer;
            for (int i = first; i < last; ++i) {
                int offset = i - first;
                int top = matrix[first][i]; // save top

                // left -> top
                matrix[first][i] = matrix[last - offset][first];

                // bottom -> left
                matrix[last - offset][first] = matrix[last][last - offset];

                // right -> bottom
                matrix[last][last - offset] = matrix[i][last];

                // top -> right
                matrix[i][last] = top; // right <- saved top
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = AssortedMethods.randomMatrix(10, 10, 0, 9);
        AssortedMethods.printMatrix(matrix);
        rotate(matrix, 10);
        System.out.println();
        AssortedMethods.printMatrix(matrix);
    }

    public static int[][] genRandomMatrix(int n) {
        int[][] A = new int[n][n];
        Random R = new Random();

        int i, j;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                A[i][j] = R.nextInt(256);
            }
        }
        return A;
    }

}
