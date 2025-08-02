/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.interview;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author javaugi
 */
public class DateTimeManipulationDemo {

    public static void main(String[] args) throws IOException {
        //dateConsoleInput();
        Solution.demoWithDates();
        
        System.out.println("randomInt10To20()=" + randomInt10To20() + "-randomInt20To30()=" + randomInt20To30());
    }

    public static int randomInt20To30() {
        // To get a random integer between 0 (inclusive) and a specified bound (exclusive)
        int bound = 100;
        int randomIntBounded = (int) (Math.random() * bound); // Gets a random integer from 0 to 99

        // To get a random integer within a specific range (e.g., between min and max inclusive)
        int min = 20;
        int max = 30;
        int randomIntInRange = (int) (Math.random() * (max - min + 1)) + min; // Gets a random integer from 10 to 20
        return randomIntInRange;
    }

    public static int randomInt10To20() {
        Random random = new Random(); // Create a Random object

        // To get a random integer within the full range of int
        int randomInt = random.nextInt();

        // To get a random integer between 0 (inclusive) and a specified bound (exclusive)
        int randomIntBounded = random.nextInt(100); // Gets a random integer from 0 to 99

        // To get a random integer within a specific range (e.g., between min and max inclusive)
        int min = 10;
        int max = 20;
        int randomIntInRange = random.nextInt(max - min + 1) + min; // Gets a random integer from 10 to 20
        return randomIntInRange;
    }

    public class Solution {

        /*
        public static void main(String[] args) throws IOException {
            //dateConsoleInput();
            demoWithDates();
        }
        // */
        public static void demoWithDates() {
            String results = Results3.findDay(7, 5, 2015);
            System.out.println("1st: " + results);

            Calendar cal = Calendar.getInstance();
            cal.set(2015, 7, 5);
            results = Result2.findDay(cal.getTime());
            System.out.println("2nd: " + results);

            Date date = new Date();
            for (int i = 0; i < 5; i++) {
                cal.setTime(date);
                int value = DateTimeManipulationDemo.randomInt10To20();
                cal.add(Calendar.DATE, value);
                            
                System.out.println("\n calling findDay from date=" + cal.getTime());
                results = Result2.findDay(cal.getTime());
                System.out.println("ith: " + results);
            }
        }

        public static void dateConsoleInput() throws IOException, NumberFormatException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int month = Integer.parseInt(firstMultipleInput[0]);

            int day = Integer.parseInt(firstMultipleInput[1]);

            int year = Integer.parseInt(firstMultipleInput[2]);

            String res = Result.findDay(month, day, year);

            bufferedWriter.write(res);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

    class Result0 {

        public static String findDay(int month, int day, int year) {
            if (month > 0) {
                month--;
            }
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            int firstDayOfWeek = cal.getFirstDayOfWeek();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            int diff = dayOfWeek - firstDayOfWeek;

            String[] daysArr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            String dayValue = daysArr[diff].toUpperCase();
            System.out.println(dayValue);
            return dayValue;
        }
    }

    class Result {

        /*
     * Complete the 'findDay' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER month
     *  2. INTEGER day
     *  3. INTEGER year
         */
        public static String findDay(int month, int day, int year) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            int firstDayOfWeek = cal.getFirstDayOfWeek();
            int diff = day - firstDayOfWeek - 1;

            String[] daysArr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

            //List<String> days = Arrays.asList("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday") ;
            //String[] daysArr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            System.out.println("diff=" + diff + "firstDayOfWeek=" + firstDayOfWeek + "-date=" + cal.getTime());
            return daysArr[diff].toUpperCase();
        }

    }

    class Result2 {

        /*
     * Complete the 'findDay' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER month
     *  2. INTEGER day
     *  3. INTEGER year
         */
        public static String findDay(Date date) {
            int diff = getDiffForDayOfWeek(date);

            String[] daysArr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String dayValue = daysArr[diff].toUpperCase();
            System.out.println("diff=" + diff + "-dayValue=" + dayValue + "-orig date =" + date);
            return dayValue;
        }

        private static int getDiffForDayOfWeek(Date date) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int firstDayOfWeek = cal.getFirstDayOfWeek();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            int diff = dayOfWeek - firstDayOfWeek;
            return diff;
        }

    }

    class Results3 {

        public static String findDay(int month, int day, int year) {
            Calendar cal = Calendar.getInstance();
            cal.set(year, month, day);
            int firstDayOfWeek = cal.getFirstDayOfWeek();
            int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
            int diff = dayOfWeek - firstDayOfWeek;

            String[] daysArr = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            String dayValue = daysArr[diff].toUpperCase();
            System.out.println(dayValue);
            System.out.println("diff=" + diff + "-dayValue=" + dayValue + "-orig date =" + cal.getTime());
            return dayValue;
        }
    }
}
