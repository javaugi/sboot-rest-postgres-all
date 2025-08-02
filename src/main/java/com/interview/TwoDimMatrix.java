/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
    
/**
 *
 * @author javaugi https://www.hackerrank.com/test
 */
public class TwoDimMatrix {

    private static final TwoDimMatrix main = new TwoDimMatrix();

    public static void main(String[] args) {
        //main.run1();
        main.run2();
        enhancedLooping();
    }
    
    private void run1() {
        create1();
        create2();
        create3();
    }

    private void run2() {
        String[] members = chatRoomMembers();
        String[][] roomInvites = chatRoomInfo();
                
        Map<String, Integer> map = new HashMap<>();
        //initMap(map, members);         
        
        for (String[] row : roomInvites) {
            processInvitees(map, row[2]);
        }
        
        printMap(map);
    }
    
    private void initMap(Map<String, Integer> map, String[] members) {
        for (String mem: members) {
            if (map.get(mem) == null) {
                map.put(mem, 0);
            }
        }         
    }
    
    private void processInvitees(Map<String, Integer> map, String inviteesArr) {
        String[] invitees = inviteesArr.split(",");
        for (String inviteeId: invitees) {
            inviteeId = inviteeId.trim();
            if ("ALL".equalsIgnoreCase(inviteeId)) {
                addToAll(map);
            } else {
                if (map.get(inviteeId) == null) {
                    map.put(inviteeId, 1);
                } else {
                    Integer entryValue = map.get(inviteeId);
                    entryValue++;
                    map.put(inviteeId, entryValue);
                }
            }
        }
    }
    
    private void addToAll(Map<String, Integer> map) {
        for (String key: map.keySet()) {
            Integer entryValue = map.get(key);
            if (entryValue == null) {
                entryValue = 0;
            }
            entryValue++;
            map.put(key, entryValue);
        }
    }
    
    private void printMap(Map<String, Integer> map) {
        System.out.print("\n Chat room invitees with the number of invite count \n");
        for (String key: map.keySet()) {
            System.out.println("Member=" + key + "-total invites=" + map.get(key));
        }
    }
    
    private String[] chatRoomMembers() {
        String[] members = {"id1", "id2", "id3", "id4", "id5", "id6", "id7", "id8", "id9", "id10"};
        System.out.print("\n Chat room members ...");
        for (var mem: members) {
            System.out.print(mem + "\t");
        }
        
        return members;
    }
    
    private String[][] chatRoomInfo() {
        String[][] roomInvites = {
            {"MSG", "id1", "id2"},
            {"MSG", "id2", "ALL, id1, id3"},
            {"MSG", "id1", "ALL, id2, id3"},
            {"MSG", "id2", "ALL, id5, id3"},
            {"MSG", "id3", "id1, id2, id5, id7"},
            {"MSG", "id4", "id1, id2, id5, id7"},
            {"MSG", "id1", "ALL, id2, id5, id7"},
            {"MSG", "id5", "id1, id2, ALL, id7"},
            {"MSG", "id7", "id1, id2, id5, id9"},
            {"MSG", "id6", "id1, id2, id5, id7"},
            {"MSG", "id4", "ALL, id2, id5, id7"},
            {"MSG", "id3", "id1, id2, id5, id7"},
            {"MSG", "id1", "ALL, id2, id5, id7"},
        };

        System.out.println("\n--- Printing roomInvites ---");
        for (int row = 0; row < roomInvites.length; row++) {
            for (int col = 0; col < roomInvites[row].length; col++) {
                System.out.print(roomInvites[row][col] + "\t");
            }
            System.out.println();
        }
        
        System.out.println("\n--- Printing roomInvites with enhanced loop ---");
        for (String[] row: roomInvites) {
            for (String element: row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }

        return roomInvites;
    }
    

    private static void enhancedLooping() {
        // 1. Declare and initialize a 2D String array with fixed dimensions.
        //    - The first dimension specifies the number of rows.
        //    - The second dimension specifies the number of columns in each row.
        String[][] stringArray = new String[3][4]; // 3 rows, 4 columns

        // 2. Populate the array with values.  You access elements using row and column indices.
        stringArray[0][0] = "Row 0, Column 0";
        stringArray[0][1] = "Row 0, Column 1";
        stringArray[0][2] = "Row 0, Column 2";
        stringArray[0][3] = "Row 0, Column 3";
        stringArray[1][0] = "Row 1, Column 0";
        stringArray[1][1] = "Row 1, Column 1";
        stringArray[1][2] = "Row 1, Column 2";
        stringArray[1][3] = "Row 1, Column 3";
        stringArray[2][0] = "Row 2, Column 0";
        stringArray[2][1] = "Row 2, Column 1";
        stringArray[2][2] = "Row 2, Column 2";
        stringArray[2][3] = "Row 2, Column 3";

        // 3. Print the array elements (optional, for demonstration).
        System.out.println("--- Using enhanced loops to print the array ---");
        for (String[] row : stringArray) {
            for (String element : row) {
                System.out.print(element + "\t"); // Use tab for spacing
            }
            System.out.println(); // Move to the next line after each row
        }

        // 4. Example of declaring and initializing with values directly (alternative).
        String[][] anotherStringArray = {
                {"A", "B", "C"},
                {"D", "E", "F"},
                {"G", "H", "I", "J"} //jagged array is allowed
        };

        System.out.println("\n--- Printing anotherStringArray ---");
        for (String[] row : anotherStringArray) {
            for (String element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }

        // 5. Example of creating a jagged array (rows can have different lengths).
        String[][] jaggedArray = new String[3][]; //only define number of rows
        jaggedArray[0] = new String[2]; // Row 0 has 2 columns
        jaggedArray[1] = new String[3]; // Row 1 has 3 columns
        jaggedArray[2] = new String[4]; // Row 2 has 4 columns

        jaggedArray[0][0] = "Jagged 0,0";
        jaggedArray[0][1] = "Jagged 0,1";
        jaggedArray[1][0] = "Jagged 1,0";
        jaggedArray[1][1] = "Jagged 1,1";
        jaggedArray[1][2] = "Jagged 1,2";
        jaggedArray[2][0] = "Jagged 2,0";
        jaggedArray[2][1] = "Jagged 2,1";
        jaggedArray[2][2] = "Jagged 2,2";
        jaggedArray[2][3] = "Jagged 2,3";

        System.out.println("\n--- Printing jaggedArray ---");
        for (String[] row : jaggedArray) {
            for (String element : row) {
                System.out.print(element + "\t");
            }
            System.out.println();
        }
    }
    
    
    private String[][] create1() {
        // 1. Declare and initialize a 2D String array with fixed dimensions.
        //    - The first dimension specifies the number of rows.
        //    - The second dimension specifies the number of columns in each row.
        String[][] stringArray = new String[3][4]; // 3 rows, 4 columns

        // 2. Populate the array with values.  You access elements using row and column indices.
        stringArray[0][0] = "Row 0, Column 0";
        stringArray[0][1] = "Row 0, Column 1";
        stringArray[0][2] = "Row 0, Column 2";
        stringArray[0][3] = "Row 0, Column 3";
        stringArray[1][0] = "Row 1, Column 0";
        stringArray[1][1] = "Row 1, Column 1";
        stringArray[1][2] = "Row 1, Column 2";
        stringArray[1][3] = "Row 1, Column 3";
        stringArray[2][0] = "Row 2, Column 0";
        stringArray[2][1] = "Row 2, Column 1";
        stringArray[2][2] = "Row 2, Column 2";
        stringArray[2][3] = "Row 2, Column 3";

        // 3. Print the array elements (optional, for demonstration).
        System.out.println("--- Using nested loops to print the array ---");
        for (int row = 0; row < stringArray.length; row++) {
            for (int col = 0; col < stringArray[row].length; col++) {
                System.out.print(stringArray[row][col] + "\t"); // Use tab for spacing
            }
            System.out.println(); // Move to the next line after each row
        }
        return stringArray;
    }

    private String[][] create2() {
        //Example of declaring and initializing with values directly (alternative).
        String[][] anotherStringArray = {
            {"A", "B", "C"},
            {"D", "E", "F"},
            {"G", "H", "I", "J"} //jagged array is allowed
        };

        System.out.println("\n--- Printing anotherStringArray ---");
        for (int row = 0; row < anotherStringArray.length; row++) {
            for (int col = 0; col < anotherStringArray[row].length; col++) {
                System.out.print(anotherStringArray[row][col] + "\t");
            }
            System.out.println();
        }

        return anotherStringArray;
    }

    private String[][] create3() {
        // 5. Example of creating a jagged array (rows can have different lengths).
        String[][] jaggedArray = new String[3][]; //only define number of rows
        jaggedArray[0] = new String[2]; // Row 0 has 2 columns
        jaggedArray[1] = new String[3]; // Row 1 has 3 columns
        jaggedArray[2] = new String[4]; // Row 2 has 4 columns

        jaggedArray[0][0] = "Jagged 0,0";
        jaggedArray[0][1] = "Jagged 0,1";
        jaggedArray[1][0] = "Jagged 1,0";
        jaggedArray[1][1] = "Jagged 1,1";
        jaggedArray[1][2] = "Jagged 1,2";
        jaggedArray[2][0] = "Jagged 2,0";
        jaggedArray[2][1] = "Jagged 2,1";
        jaggedArray[2][2] = "Jagged 2,2";
        jaggedArray[2][3] = "Jagged 2,3";

        System.out.println("\n--- Printing jaggedArray ---");
        for (int row = 0; row < jaggedArray.length; row++) {
            for (int col = 0; col < jaggedArray[row].length; col++) {
                System.out.print(jaggedArray[row][col] + "\t");
            }
            System.out.println();
        }

        return jaggedArray;
    }

}
