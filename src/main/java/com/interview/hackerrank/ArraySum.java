/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author javaugi
 */
public class ArraySum {

    public static void main(String[] args) {
        int[] arr = {1, -2, 4, -5, 1};
        System.out.println("\n Calling totalNegative=" + totalNegative(arr) + " with array=" + Arrays.toString(arr));
        //total negative = 9

        System.out.println("arr[3] value=" + arr[3] + " from arr=" + Arrays.toString(arr));
        List<Integer> intList = Arrays.stream(arr)
                .boxed()
                //.sorted()
                .collect(Collectors.toList());
        System.out.println("get(3) value=" + intList.get(3) + " from list=" + intList);

        int[] arr2 = {255, -77, 601, 89, -993, -307, 300, 452, -312, 400, -993, 831, 790, 236, 981, 274, 167, 676, -134, -906, 139, -537, -159, 483, 398, 253, 583, 348, 582, 481, 398, -504, 459, 39, 650, 424, 511, 581, 303, 142, -300, 796, 183, -609, 432, 33, -846, -101, 421, 602, -789, 214, 692, -971, 212, 752, -564, -747, -396, 217, 448, 364, -139, 304, -309, 337, 989, 751, 698, 381, 892, -774, 34, 557, 231, 612, -377, -677, 497, -781, -944, 608, 21, 967, 787, -334, 835, 136, 335, -4, -468, -301, -296, 65, -664, -303, 317, 893, 624, 115};
        System.out.println("\n Calling totalNegative=" + totalNegative(arr2) + " with array=" + Arrays.toString(arr2));
        //total negative = 425
    }

    public static void scannerWithPrinter() {
        Scanner scanner = new Scanner(System.in);
        // Read the number of lines
        int n = scanner.nextInt();

        ArrayList<ArrayList<Integer>> lines = new ArrayList<>();
        // Read each line
        for (int i = 0; i < n; i++) {
            int d = scanner.nextInt();
            ArrayList<Integer> line = new ArrayList<>();
            for (int j = 0; j < d; j++) {
                line.add(scanner.nextInt());
            }
            lines.add(line);
        }

        // Read the number of queries
        int q = scanner.nextInt();
        // Process each query
        for (int i = 0; i < q; i++) {
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            // Adjust for 1-based indexing to 0-based
            try {
                int value = lines.get(x - 1).get(y - 1);
                System.out.println(value);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("ERROR!");
            }
        }

        scanner.close();
    }

    public static void scanPrint() {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();

        List<List<Integer>> list = new ArrayList<>();

        java.util.stream.IntStream.range(0, n).forEach(i -> {
            String line = scan.nextLine();
            if (line != null && !line.isEmpty()) {
                String[] tokens = line.split(" ");
                if (!"0".equals(tokens[0])) {
                    String[] tokensUpd = Arrays.copyOfRange(tokens, 1, tokens.length);
                    List<Integer> list0 = new ArrayList<>();
                    for (String str : tokensUpd) {
                        list0.add(Integer.parseInt(str));
                    }
                    list.add(list0);
                } else {
                    list.add(new ArrayList<>());
                }
            }
        }
        );

        int q = scan.nextInt();
        scan.nextLine();
        List<List<Integer>> queries = new ArrayList<>();
        
        java.util.stream.IntStream.range(0, q).forEach(i -> {
            String line = scan.nextLine();
            if (line != null && !line.isEmpty()) {
                String[] tokens = line.split(" ");
                List<Integer> list0 = new ArrayList<>();
                for (String str : tokens) {
                    list0.add(Integer.parseInt(str));
                }
                queries.add(list0);
            }
        }
        );

        for (List<Integer> qList : queries) {
            int x = qList.get(0);
            int y = qList.get(1);
            List<Integer> xList = list.get(x - 1);
            if (xList.size() > y) {
                System.out.println(xList.get(y));
            } else {
                System.out.println("ERROR!");
            }
        }

    }

    private static void dim2ArrayToList() {
        int[][] arr2d = {{1, 1, 1, 0, 0, 0}, {0, 1, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0,}, {0, 0, 2, 4, 4, 0}, {0, 0, 0, 2, 0, 0}, {0, 0, 1, 2, 4, 0}};
        List<List<Integer>> listList1 = Arrays.stream(arr2d)
                .map(row -> IntStream.of(row)
                    .boxed()
                    .collect(Collectors.toList())
                )
                .collect(Collectors.toList());

        int[] arr = {1, -2, 4, -5, 1};
        System.out.println("\n Calling totalNegative=" + totalNegative(arr) + " with array=" + Arrays.toString(arr));
        //total negative = 9        
        System.out.println("arr[3] value=" + arr[3] + " from arr=" + Arrays.toString(arr));

        List<Integer> intList = Arrays.stream(arr)
                .boxed()
                //.sorted()
                .collect(Collectors.toList());
        System.out.println("get(3) value=" + intList.get(3) + " from list=" + intList);
    }

    private static void scanner() {
        Scanner scan = new Scanner(System.in);
        int a = scan.nextInt();
        scan.nextLine();
        String line = scan.nextLine();
        String[] arrStr = line.split(" ");
        int[] arr = new int[a];
        for (int i = 0; i < arrStr.length; i++) {
            arr[i] = Integer.parseInt(arrStr[i]);
        }
    }

    private static int totalNegative(int[] arr) {
        int tNegative = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                //both work
                int[] sub = Arrays.copyOfRange(arr, i, j);
                //int[] sub = Arrays.copyOfRange(arr, i, (j + 1));
                int t = totalSum(sub);
                if (t < 0) {
                    tNegative++;
                }
                //System.out.println("Looping     i=" + i  + "    -j=" + j  + "   -t=" + t+ " -tNegative=" + tNegative + "    - with array=" + Arrays.toString(sub));
            }
        }

        return tNegative;
    }

    private static int totalSum(int[] sub) {
        int t = 0;
        for (int k = 0; k < sub.length; k++) {
            t += sub[k];
        }
        return t;
    }
}
