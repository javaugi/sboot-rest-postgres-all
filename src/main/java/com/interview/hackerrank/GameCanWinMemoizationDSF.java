/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author javaugi
 */
public class GameCanWinMemoizationDSF {
    
     public static void main(String[] args) {
        //scanToAddGame();
        int[] leap = {3, 5, 3, 1};
        int[][] games = {{0, 0, 0, 0, 0}, {0, 0, 0, 1, 1, 1,}, {0, 0, 1, 1, 1, 0}, {0,1,0}};
        
        int leanNdx = 0;
        for (int[] game: games) {
            //System.out.println(canWin(leap[leanNdx], game) ? "YES" : "NO");
            System.out.println(canWinTheGame(leap[leanNdx], game) ? "YES" : "NO");
            //System.out.println(canWinTheGame1(leap[leanNdx], game) ? "YES" : "NO");
            leanNdx++;
        }
        //YES   YES     NO  NO
        
        //*
        System.out.println(canWin(3, new int[]{0,0,0,0,0}) ? "YES" : "NO");  // Should return true
        System.out.println(canWin(5, new int[]{0,0,0,1,1,1}) ? "YES" : "NO"); // Should return true
        System.out.println(canWin(3, new int[]{0,0,1,1,1,0}) ? "YES" : "NO"); // Should return false
        System.out.println(canWin(1, new int[]{0,1,0}) ? "YES" : "NO");       // Should return false
        // */
        System.out.println(canWin(2, new int[]{0,1,0,1,0, 1}) ? "YES" : "NO");  // Should return true
        System.out.println(canWin(6, new int[]{0,0,1,1,0,0,1,1,0,0}) ? "YES" : "NO"); // Should return false
        System.out.println(canWin(3, new int[]{0,0,1,1,0,0,1,1}) ? "YES" : "NO"); // Should return true

    }

    private static void scanToAddGame() {
        Scanner scan = new Scanner(System.in);
        int q = scan.nextInt();
        while (q-- > 0) {
            int n = scan.nextInt();
            int leap = scan.nextInt();
            
            int[] game = new int[n];
            for (int i = 0; i < n; i++) {
                game[i] = scan.nextInt();
            }

            System.out.println(canWin(leap, game) ? "YES" : "NO");
        }
        scan.close();
    }
    
    public static boolean canWinTheGame(int leap, int[] game) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[game.length];
        stack.push(0);
        
        while (!stack.isEmpty()) {
            int pos = stack.pop();   
            //true if all visited
            if (pos >= game.length) {
                return true;
            }
            //continue for those situations
            if (pos < 0 || game[pos] == 1 || visited[pos]) {
                continue;
            }
            //keep visiting pos + 1 first, the pos -1 and then pos + leap
            visited[pos] = true;
            stack.push(pos + 1);
            stack.push(pos - 1);
            stack.push(pos + leap);
        }
        
        return false;
    }
    
    
    /*
    Your code would:
        Start at index 0
        Move to index 1 (since game[1] == 0)
        Then move to index 2
        Then move to index 3
        Then move to index 4
        Then stop because it doesn't have logic to jump from index 4 to index 7 (which would win the game)
    The recursive solution fixes this by always considering the leap option at each step.    
    */
    public static boolean canWinTheGame1(int leap, int[] game) {
        // Return true if you can win the game; otherwise, return false.
        boolean returnValue = false;
        boolean doneLooping = false;
        int ndx = 0;
        int n = game.length;
        /*
        Proper Base Cases:
            Win if we reach or go beyond the end of the array
            Lose if we go out of bounds or hit a 1
            Skip if we've already visited this position        
        */
        
        System.out.println("leap=" + leap + "-game=" + Arrays.toString(game));
        while(!doneLooping) {
            if (ndx >= n || ndx < 0 || game[ndx] == 1) {
                doneLooping = true;
            } else if (ndx == 0) {
                if (game[1] == 0) {
                    ndx++;
                } else if (game[leap] == 0) {
                    ndx = ndx + leap;
                } else {
                    doneLooping = true;
                }
            } else if (ndx > 0 && ndx < n) { 
                if (game[ndx - 1] == 0) {
                    ndx--;
                }else  if (game[ndx + 1] == 0) {
                    ndx++;
                } else if (ndx + leap > n -1 || game[ndx + leap] == 1) {
                    doneLooping = true;
                } else if (game[ndx + leap] == 0){
                    ndx = ndx + leap;
                } else {
                    doneLooping = true;
                }
            } else {
                doneLooping = true;
            }
            System.out.println("ndx=" + ndx + "-doneLooping=" + doneLooping);
        }
        
        System.out.println("return ndx=" + ndx + "-doneLooping=" + doneLooping);
        return returnValue;
    }    
    
    public static boolean canWin(int leap, int[] game) {
        return canWinFromPosition(0, leap, game, new boolean[game.length]);
    }

    /*
    Explanation:
    Input Handling:
        Read the number of queries q.
        For each query, read n (size of array) and leap (jump size).
        Read the game array elements.
    DFS with Memoization:
        The canWinFromPosition method recursively checks all possible moves from the current position:
            Move forward 1 step: pos + 1
            Move backward 1 step: pos - 1 (only if it's valid)
            Jump forward leap steps: pos + leap
        A visited array tracks positions already checked to avoid infinite loops.
    Base Cases:
        If pos is beyond the array bounds (pos >= game.length), you win.
        If pos is negative, or the current cell is 1, or already visited, the move is invalid.
    Output:
        For each query, print "YES" if you can win, otherwise "NO".
    Key Points:
        Efficiency: The memoization (visited array) ensures each position is checked only once, making the solution efficient.
        Edge Cases: Handles all edge cases, including when you can immediately jump to the end or when no moves are possible.
        Correctness: The DFS explores all possible paths to determine if winning is possible.
    This solution efficiently checks all possible paths through the array while avoiding redundant checks, ensuring optimal performance for the given constraints.    
    
Key Improvements:
    Recursive DFS Approach: This explores all possible paths (forward, backward, and leap) from each position.
    Visited Tracking: The visited array prevents infinite loops by remembering which positions we've already checked.
Proper Base Cases:
    Win if we reach or go beyond the end of the array
    Lose if we go out of bounds or hit a 1
    Skip if we've already visited this position
Correct Movement Options:
    Always tries moving forward 1 step first
    Then tries moving backward 1 step
    Finally tries leaping forward
    */
    private static boolean canWinFromPosition(int pos, int leap, int[] game, boolean[] visited) {
        // Base cases
        if (pos >= game.length) {
            return true;
        }
        if (pos < 0 || game[pos] == 1 || visited[pos]) {
            return false;
        }

        visited[pos] = true; // Mark this position as visited

        // Try all possible moves
        return canWinFromPosition(pos + 1, leap, game, visited) ||
               canWinFromPosition(pos - 1, leap, game, visited) ||
               canWinFromPosition(pos + leap, leap, game, visited);
    }
}

/*
solution for this game please Let's play a game on an array! You're standing at index  of an -element array named . From some index  (where ), you can perform one of the following moves:

Move Backward: If cell  exists and contains a , you can walk back to cell .
Move Forward:
If cell  contains a zero, you can walk to cell .
If cell  contains a zero, you can jump to cell .
If you're standing in cell  or the value of , you can walk or jump off the end of the array and win the game.
In other words, you can move from index  to index , , or  as long as the destination index is a cell containing a . If the destination index is greater than , you win the game.

Function Description

Complete the canWin function in the editor below.

canWin has the following parameters:

int leap: the size of the leap
int game[n]: the array to traverse
Returns

boolean: true if the game can be won, otherwise false
Input Format

The first line contains an integer, , denoting the number of queries (i.e., function calls).
The  subsequent lines describe each query over two lines:

The first line contains two space-separated integers describing the respective values of  and .
The second line contains  space-separated binary integers (i.e., zeroes and ones) describing the respective values of .
Constraints

It is guaranteed that the value of  is always .
Sample Input

STDIN           Function
-----           --------
4               q = 4 (number of queries)
5 3             game[] size n = 5, leap = 3 (first query)
0 0 0 0 0       game = [0, 0, 0, 0, 0]
6 5             game[] size n = 6, leap = 5 (second query)
0 0 0 1 1 1     . . .
6 3
0 0 1 1 1 0
3 1
0 1 0
Sample Output

YES
YES
NO
NO
Explanation

We perform the following  queries:

For  and , we can walk and/or jump to the end of the array because every cell contains a . Because we can win, we return true.
For  and , we can walk to index  and then jump  units to the end of the array. Because we can win, we return true.
For  and , there is no way for us to get past the three consecutive ones. Because we cannot win, we return false.
For  and , there is no way for us to get past the one at index . Because we cannot win, we return false.
*/
