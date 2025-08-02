/*
 * Copyright (C) 2023 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */

package com.sisllc.mathformulas.utils;
import static com.sisllc.mathformulas.utils.CommonUtils.dateOfUserLocalDate;
import static com.sisllc.mathformulas.utils.CommonUtils.findDaysBetweenDates;
import static com.sisllc.mathformulas.utils.CommonUtils.serviceEventIntervalFinder;
import static com.sisllc.mathformulas.utils.CommonUtils.userLocalDateOfDate;
import static com.sisllc.mathformulas.utils.EventsService.getFirstDateOfEventsByWeekInterval;
import static com.sisllc.mathformulas.utils.EventsService.getLastDateOfEventsByWeekInterval;
import static com.sisllc.mathformulas.utils.EventsService.getNumberOfEventPeriodDates;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate
 * Last Modified Author: $LastChangedBy
 */
public class EventsServiceTest {

    private static final Logger log = LoggerFactory.getLogger(EventsServiceTest.class);
    
    public static List<String> recurrenceRules = Arrays.asList(new String[]{
        "FREQ=WEEKLY;WKST=SU;INTERVAL=1;BYDAY=MO,SU,FR,TH,WE,SA,TU",
        "FREQ=MONTHLY;INTERVAL=1;BYDAY=MO;BYSETPOS=3",
        "FREQ=DAILY;INTERVAL=1",
        "FREQ=MONTHLY;UNTIL=20230326;INTERVAL=1",
        "FREQ=MONTHLY;INTERVAL=1;BYDAY=TU;BYSETPOS=1",
        "FREQ=YEARLY;INTERVAL=1;BYMONTH=3",
        "FREQ=WEEKLY;WKST=SU;INTERVAL=2",
        "FREQ=DAILY;UNTIL=20230801;INTERVAL=1",
        "FREQ=WEEKLY;WKST=SU;INTERVAL=1",
        "FREQ=WEEKLY;WKST=SU;INTERVAL=1;BYDAY=MO,FR,WE",
        "FREQ=MONTHLY;INTERVAL=1"
    });
    public static List<Integer> recurrenceIntervals = Arrays.asList(new Integer[]{1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1});

    public static List<Integer> intervals = Arrays.asList(new Integer[]{2, 3, 4});
    public static String format = "MM/dd/yyyy";
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    public static String startDateStr = "02/06/2023";
    public static List<String> periodStartList = Arrays.asList(new String[]{"03/01/2023", "04/01/2023", "05/01/2023"});
    public static List<String> periodEndList = Arrays.asList(new String[]{"03/31/2023", "04/30/2023", "05/31/2023"});
    public static String[][] startDateResults = {
        {"", "", ""}, {"", "", ""},
        {"03/05/2023", "04/02/2023", "05/01/2023"}, // interval 2 start date
        {"03/19/2023", "04/01/2023", "05/01/2023"}, // interval 3 start date
        {"03/01/2023", "04/02/2023", "05/28/2023"},};   // interval 4 start date
    public static String[][] endDateResults = {
        {"", "", ""}, {"", "", ""},
        {"03/18/2023", "04/30/2023", "05/31/2023"}, // interval 2 end date
        {"03/31/2023", "04/30/2023", "05/20/2023"}, // interval 3 end date
        {"03/04/2023", "04/29/2023", "05/31/2023"},};   // interval 4 end date
    public static int[][] daysDiffs = {
        {0, 0, 0}, {0, 0, 0},
        {14, 15, 17}, // interval 2 days diff
        {13, 9, 20}, // interval 3 days diff
        {4, 28, 4}};  // interval 4 days diff
    
    @Test
    public void testExtractInterval() {
        for (int i = 0; i < recurrenceRules.size(); i++) {
            String token = recurrenceRules.get(i);
            int interval = recurrenceIntervals.get(i);
            Integer result = serviceEventIntervalFinder(token);
            //log.info("\n** Found token {} with interval {}", token, interval);
            assertTrue(interval == result);
        }
        for (String token: recurrenceRules) {
            Integer interval = serviceEventIntervalFinder(token);
            log.info("\n** Found token {} with interval {}", token, interval);
            assertTrue(interval > 0);
        }
        
    }
    
    @Test
    public void testFindDaysBetweenDates() {
        String startDateStr0301 = "03/01/2023";
        String endDateStr = "03/28/2023"; 
        LocalDate localStartDate = LocalDate.parse(startDateStr0301, formatter);
        LocalDate localEndDate = LocalDate.parse(endDateStr, formatter);
        Date startDate = dateOfUserLocalDate(localStartDate);
        Date endDate = dateOfUserLocalDate(localEndDate);
        
        int daysFrequency2 = findDaysBetweenDates(startDate, endDate, 2);
        int daysFrequency3 = findDaysBetweenDates(startDate, endDate, 3);
        int daysFrequency4 = findDaysBetweenDates(startDate, endDate, 4);
        /*
        log.info("\n** daysFrequency2 {} from eventFrequency=2 startDate {} endDate {}", daysFrequency2, startDate, endDate);
        log.info("\n** daysFrequency3 {} from eventFrequency=3 startDate {} endDate {}", daysFrequency3, startDate, endDate);
        log.info("\n** daysFrequency4 {} from eventFrequency=4 startDate {} endDate {}", daysFrequency4, startDate, endDate);
        // */
        assertEquals(daysFrequency2, 14);
        assertEquals(daysFrequency3, 18);
        assertEquals(daysFrequency4, 25);
    }

    @Test
    public void testFindWeeklyAnytimeStartDate() {
        String periodStartDateStr, periodEndDateStr;
        LocalDate localPeriodStartDate, localPeriodEndDate;
        Date periodStartDate, periodEndDate;

        LocalDate localStartDate = LocalDate.parse(startDateStr, formatter);
        Date startDate = dateOfUserLocalDate(localStartDate);
        Date resultStartDate;
        int interval;

        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.get(i); j++) {
                interval = intervals.get(i);
                for (int k = 0; k < periodStartList.size(); k++) {
                    periodStartDateStr = periodStartList.get(k);
                    localPeriodStartDate = LocalDate.parse(periodStartDateStr, formatter);
                    periodStartDate = dateOfUserLocalDate(localPeriodStartDate);
                    periodEndDateStr = periodEndList.get(k);
                    localPeriodEndDate = LocalDate.parse(periodEndDateStr, formatter);
                    periodEndDate = dateOfUserLocalDate(localPeriodEndDate);
                    //resultStartDate = getWeeklyAnytimeStartDate(startDate, periodStartDate, periodEndDate, interval);
                    resultStartDate = getFirstDateOfEventsByWeekInterval(startDate, periodStartDate, periodEndDate, interval);
                    String resultStartDateStr = startDateResults[interval][k];
                    //log.info("\n interval {} k {} resultStartStr {} resultStartDate {} StartDate {} periodEndDate {}", interval, k, resultStartDateStr, resultStartDate, startDate, periodEndDate);
                    assertTrue(userLocalDateOfDate(resultStartDate).isEqual(LocalDate.parse(resultStartDateStr, formatter)));
                }
            }
        }
    }

    @Test
    public void testFindWeeklyAnytimeEndDate() {
        String periodEndDateStr;
        LocalDate localPeriodEndDate;
        Date periodEndDate;

        LocalDate localStartDate = LocalDate.parse(startDateStr, formatter);
        Date resultEndDate;
        Date startDate = dateOfUserLocalDate(localStartDate);
        int interval = 0;
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.get(i); j++) {
                interval = intervals.get(i);
                for (int k = 0; k < periodStartList.size(); k++) {
                    periodEndDateStr = periodEndList.get(k);
                    localPeriodEndDate = LocalDate.parse(periodEndDateStr, formatter);
                    periodEndDate = dateOfUserLocalDate(localPeriodEndDate);
                    //resultEndDate = getWeeklyAnytimeEndDate(startDate, periodEndDate, null, interval);
                    resultEndDate = getLastDateOfEventsByWeekInterval(startDate, periodEndDate, null, interval);
                    String resultEndDateStr = endDateResults[interval][k];
                    //log.info("\n interval {} k {} resultEndDateStr {} resultEndDate {} StartDate {} periodEndDate {}", interval, k, resultEndDateStr, resultEndDate, startDate, periodEndDate);
                    assertTrue(userLocalDateOfDate(resultEndDate).isEqual(LocalDate.parse(resultEndDateStr, formatter)));
                }
            }
        }
    }

    @Test
    public void testFindDaysBetweenSeStartEndDates() {
        String periodStartDateStr, periodEndDateStr;
        LocalDate localPeriodStartDate, localPeriodEndDate;
        Date periodStartDate, periodEndDate;

        LocalDate localStartDate = LocalDate.parse(startDateStr, formatter);
        Date resultStartDate, resultEndDate;
        Date startDate = dateOfUserLocalDate(localStartDate);
        int intervalByWeeks = 0, daysDiff = 0, daysDiffResult = 0;
        for (int i = 0; i < intervals.size(); i++) {
            for (int j = 0; j < intervals.get(i); j++) {
                intervalByWeeks = intervals.get(i);
                for (int k = 0; k < periodStartList.size(); k++) {
                    periodStartDateStr = periodStartList.get(k);
                    localPeriodStartDate = LocalDate.parse(periodStartDateStr, formatter);
                    periodStartDate = dateOfUserLocalDate(localPeriodStartDate);

                    periodEndDateStr = periodEndList.get(k);
                    localPeriodEndDate = LocalDate.parse(periodEndDateStr, formatter);
                    periodEndDate = dateOfUserLocalDate(localPeriodEndDate);

                    /*
                    resultStartDate = getWeeklyAnytimeStartDate(startDate, periodStartDate, periodEndDate, interval);
                    resultEndDate = getWeeklyAnytimeEndDate(startDate, periodEndDate, null, interval);
                    daysDiff = findDaysBetweenDates(resultStartDate, resultEndDate, interval);
                    // */

                    //daysDiff = getNumberOfEventDatesByWeekInterval(startDate, intervalByWeeks, periodStartDate, periodEndDate, null);
                    EventPeriodCmd eventPeriod = new EventPeriodCmd(intervalByWeeks, localStartDate, localPeriodStartDate, localPeriodEndDate);
                    eventPeriod = getNumberOfEventPeriodDates(eventPeriod);
                    daysDiffResult = daysDiffs[intervalByWeeks][k];
                    log.info("\n *** daysDiffResult {} k {} {}", daysDiffResult, k, eventPeriod);
                    assertEquals(daysDiffResult, eventPeriod.getNumberOfEventDates());
                }
            }
        }
    }

}
