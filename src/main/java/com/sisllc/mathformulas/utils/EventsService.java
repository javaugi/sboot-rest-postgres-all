/*
 * Copyright (C) 2023 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */

package com.sisllc.mathformulas.utils;
import static com.sisllc.mathformulas.utils.CommonUtils.REC_RULES_INTERVAL_PATTERN;
import static com.sisllc.mathformulas.utils.CommonUtils.dateOfUserLocalDate;
import static com.sisllc.mathformulas.utils.CommonUtils.userLocalDateOfDate;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate
 * Last Modified Author: $LastChangedBy
 */
public class EventsService {
    private static final Logger log = LoggerFactory.getLogger(EventsService.class);

    public static Integer serviceEventIntervalFinder(String input) {
        Pattern pattern = Pattern.compile(REC_RULES_INTERVAL_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }

        return 1;
    }

    public static Date getWeeklyAnytimeStartDate(Date serviceEventStart, Date periodStart, Date periodEnd, int intervalByWeeks) {
        if (!serviceEventStart.before(periodStart)) {
            return serviceEventStart;
        }

        LocalDate serviceEventStartLocal = userLocalDateOfDate(serviceEventStart);
        LocalDate periodStartLocal = userLocalDateOfDate(periodStart);

        boolean dateInEffectiveInterval = true;
        LocalDate intervalEndingSaturday = getIntervalEndingSaturday(serviceEventStartLocal, intervalByWeeks);
        long daysBetween = ChronoUnit.DAYS.between(serviceEventStartLocal, periodStartLocal);

        LocalDate firstEventInPeriod = null;
        LocalDate intervalStartSunday;
        while (serviceEventStartLocal.isBefore(periodStartLocal)) {
            if (dateInEffectiveInterval) {
                while (dateInEffectiveInterval && daysBetween-- >= 0 && !serviceEventStartLocal.isAfter(intervalEndingSaturday)) {
                    if (!serviceEventStartLocal.isBefore(periodStartLocal)) {
                        firstEventInPeriod = serviceEventStartLocal;
                        break;
                    }
                    serviceEventStartLocal = serviceEventStartLocal.plusDays(1);
                    if (serviceEventStartLocal.isAfter(intervalEndingSaturday)) {
                        break;
                    }
                }
                dateInEffectiveInterval = !dateInEffectiveInterval;
            }

            if (firstEventInPeriod == null) {
                // get to next interval
                intervalStartSunday = serviceEventStartLocal;
                intervalEndingSaturday = serviceEventStartLocal.plusDays(7 * intervalByWeeks - 1);
                daysBetween = ChronoUnit.DAYS.between(intervalStartSunday, intervalEndingSaturday);
                if (!dateInEffectiveInterval) {
                    //get to next effective interval
                    intervalStartSunday = serviceEventStartLocal;
                    serviceEventStartLocal = serviceEventStartLocal.plusDays(7 * intervalByWeeks);
                    intervalEndingSaturday = serviceEventStartLocal.plusDays(7 * intervalByWeeks - 1);
                    daysBetween = ChronoUnit.DAYS.between(intervalStartSunday, intervalEndingSaturday);
                    dateInEffectiveInterval = true;
                }
                if (dateInEffectiveInterval && !serviceEventStartLocal.isBefore(periodStartLocal) && !serviceEventStartLocal.isAfter(intervalEndingSaturday)) {
                    firstEventInPeriod = serviceEventStartLocal;
                }
            }
        }

        return (firstEventInPeriod != null) ? dateOfUserLocalDate(firstEventInPeriod) : null;
    }

    public static Date getWeeklyAnytimeEndDate(Date serviceEventStart, Date periodEnd, Date serviceEventRepeatUntil, int intervalByWeeks) {
        if (serviceEventRepeatUntil != null && serviceEventRepeatUntil.before(periodEnd)) {
            periodEnd = serviceEventRepeatUntil;
        }

        LocalDate serviceEventStartLocal = userLocalDateOfDate(serviceEventStart);
        LocalDate periodEndLocal = userLocalDateOfDate(periodEnd);
        LocalDate intervalEndingSaturday = getIntervalEndingSaturday(serviceEventStartLocal, intervalByWeeks);
        long daysBetween = ChronoUnit.DAYS.between(serviceEventStartLocal, periodEndLocal);
        boolean dateInEffectiveInterval = true;

        LocalDate firstEvenEndtInPeriod = null;
        LocalDate intervalStartSunday;
        while (!serviceEventStartLocal.isAfter(periodEndLocal)) {
            if (dateInEffectiveInterval) {
                while (dateInEffectiveInterval && daysBetween-- >= 0 && !serviceEventStartLocal.isAfter(intervalEndingSaturday)
                        && !serviceEventStartLocal.isAfter(periodEndLocal)) {
                    if (!serviceEventStartLocal.isAfter(intervalEndingSaturday)) {
                        firstEvenEndtInPeriod = serviceEventStartLocal;
                    }
                    serviceEventStartLocal = serviceEventStartLocal.plusDays(1);
                    if (serviceEventStartLocal.isAfter(intervalEndingSaturday) || serviceEventStartLocal.isAfter(periodEndLocal)) {
                        break;
                    }
                }
                dateInEffectiveInterval = !dateInEffectiveInterval;
            }

            if (!dateInEffectiveInterval) {
                //get to next effective interval
                intervalStartSunday = serviceEventStartLocal;
                serviceEventStartLocal = serviceEventStartLocal.plusDays(7 * intervalByWeeks);
                intervalEndingSaturday = serviceEventStartLocal.plusDays(7 * intervalByWeeks - 1);
                daysBetween = ChronoUnit.DAYS.between(intervalStartSunday, intervalEndingSaturday);
                dateInEffectiveInterval = true;
                if (dateInEffectiveInterval && !serviceEventStartLocal.isAfter(periodEndLocal) && !serviceEventStartLocal.isAfter(intervalEndingSaturday)) {
                    firstEvenEndtInPeriod = serviceEventStartLocal;
                }
            }
        }

        return (firstEvenEndtInPeriod != null) ? dateOfUserLocalDate(firstEvenEndtInPeriod) : null;
    }

    private static LocalDate getNextSaturday(LocalDate date) {
        return date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
    }

    public static LocalDate getIntervalEndingSaturday(LocalDate serviceEventStartLocal, int intervalByWeeks) {
        LocalDate nextSaturday = getNextSaturday(serviceEventStartLocal);
        return nextSaturday.plusDays((intervalByWeeks - 1) * 7);
    }

    public static int findDaysBetweenDates(Date startDate, Date endDate, int intervalByWeeks) {
        int result = 0;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        Date nextSaturday;

        while (!calendar.getTime().after(endDate)) {
            boolean doneWeeklyCount = false;
            nextSaturday = weekendSaturdayFinder(intervalByWeeks, calendar.getTime());
            if (calendar.getTime().after(endDate)) {
                calendar.add(Calendar.DATE, intervalByWeeks * 7);
                doneWeeklyCount = true;
            }
            while (!doneWeeklyCount) {
                if (!calendar.getTime().after(endDate) && !calendar.getTime().after(nextSaturday)) {
                    result++;
                    calendar.add(Calendar.DATE, 1);
                } else {
                    calendar.add(Calendar.DATE, intervalByWeeks * 7);
                    doneWeeklyCount = true;
                }
            }
        }

        return result;
    }

    private static Date weekendSaturdayFinder(int intervalByWeeks, Date startDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);

        for (int i = 0; i < intervalByWeeks; i++) {
            weekendSaturdayFinder(calendar);
            if ((i + 1) < intervalByWeeks) {
                calendar.add(Calendar.DATE, 1);
            }
        }

        return calendar.getTime();
    }

    private static Calendar weekendSaturdayFinder(Calendar calendar) {
        LocalDate localDate = userLocalDateOfDate(calendar.getTime());
        while (localDate.getDayOfWeek() != DayOfWeek.SATURDAY) {
            localDate = localDate.plusDays(1);
            calendar.add(Calendar.DATE, 1);
        }
        return calendar;
    }

    public static LocalDate getLastDayOfBiWeeklyEvents(int year, int month, int startDay) {
        LocalDate startDate = LocalDate.of(year, month, startDay);

        // Calculate the last day of the month
        LocalDate lastDayOfMonth = startDate.with(TemporalAdjusters.lastDayOfMonth());

        // Calculate the last day of bi-weekly events
        LocalDate lastDayOfBiWeeklyEvents = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        return lastDayOfBiWeeklyEvents;
    }

    public static LocalDate getLastDayOfBiWeeklyEvents(LocalDate startDate, int year, int month) {
        LocalDate lastDayOfMonth = LocalDate.of(year, month, 1).with(TemporalAdjusters.lastDayOfMonth());
        LocalDate lastDayOfBiWeeklyEvents = lastDayOfMonth.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        if (startDate.isAfter(lastDayOfBiWeeklyEvents)) {
            return lastDayOfBiWeeklyEvents;
        } else {
            return startDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        }
    }

    public static Date getFirstDateOfEventsByWeekInterval(Date initialStartDate, Date periodStart, Date periodEnd, int intervalByWeeks) {
        LocalDate initialStartDateLocal = userLocalDateOfDate(initialStartDate);
        LocalDate periodStartLocal = userLocalDateOfDate(periodStart);
        LocalDate periodEndLocal = userLocalDateOfDate(periodEnd);
        return dateOfUserLocalDate(getFirstEventDate(initialStartDateLocal, intervalByWeeks, periodStartLocal, periodEndLocal));
    }

    public static LocalDate getFirstEventDate(LocalDate initialStartDate, int intervalByWeeks, LocalDate periodStartDate, LocalDate periodEndDate) {
        // Assign the desired event date to the initial start date
        LocalDate firstEventDate = initialStartDate;

        // Move event date to the next occurrence of the desired day of week
        firstEventDate = firstEventDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));

        // Adjust the event date based on the bi-weekly interval
        boolean isEventPeriod = true;
        while (firstEventDate.isBefore(periodStartDate)) {
            firstEventDate = firstEventDate.plusWeeks(intervalByWeeks);
            isEventPeriod = !isEventPeriod;
        }

        if (firstEventDate.isAfter(periodEndDate)) {
            return null; // No event within the given period
        }

        if (!isEventPeriod && firstEventDate.isAfter(periodStartDate)) {
            // the event period is skipped
            firstEventDate = periodStartDate;
        }

        return firstEventDate;
    }

    public static Date getLastDateOfEventsByWeekInterval(Date initialStartDate, Date periodEnd, Date periodUntil, int intervalByWeeks) {
        LocalDate initialStartDateLocal = userLocalDateOfDate(initialStartDate);
        LocalDate periodEndLocal = userLocalDateOfDate(periodEnd);
        if (periodUntil != null) {
            periodEndLocal = userLocalDateOfDate(periodUntil);
        }

        return dateOfUserLocalDate(getLastEventDate(initialStartDateLocal, intervalByWeeks, periodEndLocal));
    }


    public static LocalDate getLastEventDate(LocalDate initialStartDate, int intervalByWeeks, LocalDate periodEndDate) {
        // Assign the desired event date to the initial start date
        LocalDate lastEventDate = initialStartDate;

        // Move last event date to the previous occurrence of the desired day of week
        lastEventDate = lastEventDate.plusWeeks(intervalByWeeks);
        lastEventDate = lastEventDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        // Adjust the last event date based on the bi-weekly interval
        boolean isEventPeriod = true;
        while (!lastEventDate.isAfter(periodEndDate)) {
            lastEventDate = lastEventDate.plusWeeks(intervalByWeeks);
            isEventPeriod = !isEventPeriod;
        }

        if (lastEventDate.isAfter(periodEndDate)) {
            if (isEventPeriod) {
                lastEventDate = periodEndDate;
            } else {
                lastEventDate = lastEventDate.minusWeeks(intervalByWeeks);
            }
        }

        return lastEventDate;
    }

    public static int getNumberOfEventDatesByWeekInterval(Date initialStartDate, int intervalByWeeks, Date periodStart, Date periodEnd, Date periodUntil) {
        LocalDate initialStartDateLocal = userLocalDateOfDate(initialStartDate);
        LocalDate periodStartLocal = userLocalDateOfDate(periodStart);
        LocalDate periodEndLocal = userLocalDateOfDate(periodEnd);
        if (periodUntil != null) {
            periodEndLocal = userLocalDateOfDate(periodUntil);
        }
        return getNumberOfEventDatesByWeekInterval(initialStartDateLocal, intervalByWeeks, periodStartLocal, periodEndLocal);
    }

    public static int getNumberOfEventDatesByWeekInterval(LocalDate initialStartDate, int intervalByWeeks, LocalDate periodStart, LocalDate periodEnd) {
        LocalDate firstEventDate = getFirstEventDate(initialStartDate, intervalByWeeks, periodStart, periodEnd);
        LocalDate lastEventDate = getLastEventDate(initialStartDate, intervalByWeeks, periodEnd);
        log.info("intervalByWeeks {} firstEventDate {} lastEventDate {} \n initialStartDate {} periodStart {} periodEnd {}", intervalByWeeks, firstEventDate, lastEventDate, initialStartDate, periodStart, periodEnd);
        return getNumberOfEventDatesByWeekInterval(intervalByWeeks, periodStart, firstEventDate, lastEventDate);
    }

    public static int getNumberOfEventDatesByWeekInterval(int intervalByWeeks, LocalDate periodStart, LocalDate firstEventDate, LocalDate lastEventDate) {
        log.info("1 intervalByWeeks {} periodStart {} firstEventDate {} lastEventDate {}", intervalByWeeks, periodStart, firstEventDate, lastEventDate);

        boolean isEventPeriod = true;
        LocalDate priorFirstEventDate;
        int numberOfEventDates = 0;
        // Count the number of event dates within the period
        while (firstEventDate.isBefore(lastEventDate) || firstEventDate.isEqual(lastEventDate)) {
            priorFirstEventDate = firstEventDate;
            firstEventDate = firstEventDate.plusWeeks(intervalByWeeks);
            log.info("2-1 looping numberOfEventDates {} isEventPeriod {} firstEventDate {} priorFirstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate, priorFirstEventDate);
            if (isEventPeriod) {
                numberOfEventDates = numberOfEventDates + intervalByWeeks * 7;
                if (priorFirstEventDate.isBefore(periodStart)) {
                    numberOfEventDates = numberOfEventDates - getNumberOfDatesBetweenTwoDates(priorFirstEventDate, periodStart);
                }
            }
            log.info("2-2 looping numberOfEventDates {} isEventPeriod {} firstEventDate {} priorFirstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate, priorFirstEventDate);
            isEventPeriod = !isEventPeriod;
        }

        log.info("3 numberOfEventDates {} isEventPeriod {} firstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate);
        if (isEventPeriod) {
            numberOfEventDates = numberOfEventDates - getNumberOfDatesBetweenTwoDates(lastEventDate, firstEventDate);
            log.info("4-1 numberOfEventDates {} isEventPeriod {} firstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate);
        } else {
            firstEventDate = firstEventDate.minusWeeks(intervalByWeeks);
            log.info("4-2 numberOfEventDates {} isEventPeriod {} firstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate);
            numberOfEventDates = numberOfEventDates - intervalByWeeks * 7;
            log.info("4-3 numberOfEventDates {} isEventPeriod {} firstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate);
            numberOfEventDates = numberOfEventDates + getNumberOfDatesBetweenTwoDates(firstEventDate, lastEventDate);
            log.info("4-4 numberOfEventDates {} isEventPeriod {} firstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate);
        }

        log.info("5 numberOfEventDates {} isEventPeriod {} firstEventDate {}", numberOfEventDates, isEventPeriod, firstEventDate);
        return numberOfEventDates;
    }

    public static int getNumberOfDatesBetweenTwoDates(LocalDate startDate, LocalDate endDate) {
        long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate) + 1;
        return Long.valueOf(numberOfDays).intValue();
    }

    public static void main(String[] args) {
        int intervalByWeeks = 3;
        LocalDate initialStartDate = LocalDate.of(2023, 2, 6);
        LocalDate targetPeriodStart = LocalDate.of(2023, 4, 1);
        LocalDate targetPeriodEnd = LocalDate.of(2023, 4, 30);

        EventPeriodCmd eventPeriod = new EventPeriodCmd(intervalByWeeks, initialStartDate, targetPeriodStart, targetPeriodEnd);
        eventPeriod = getTargetPeriodEventDates(eventPeriod);
        eventPeriod = getNumberOfEventDates(eventPeriod);
        log.info("{}", eventPeriod);
    }

    public static EventPeriodCmd getNumberOfEventPeriodDates(EventPeriodCmd eventPeriod) {
        int numberOfEventDates = 0;

        if (eventPeriod.getFirstEventDate() == null) {
            eventPeriod = getTargetPeriodEventDates(eventPeriod);
        }

        int intervalByWeeks = eventPeriod.getIntervalByWeeks();
        LocalDate firstEventDate = eventPeriod.getFirstEventDate();
        LocalDate lastEventDate = eventPeriod.getLastEventDate();
        LocalDate eventPeriodSaturday = eventPeriod.getEventPeriodSaturday();

        int countOfDatesBetween;
        boolean doneWeeklyCount;
        while (!firstEventDate.isAfter(lastEventDate)) {
            doneWeeklyCount = false;
            while (!doneWeeklyCount) {
                if (!firstEventDate.isAfter(eventPeriodSaturday)) {
                    countOfDatesBetween = getNumberOfDatesBetweenTwoDates(firstEventDate, eventPeriodSaturday);
                    numberOfEventDates = numberOfEventDates + countOfDatesBetween;
                    firstEventDate = firstEventDate.plusDays(countOfDatesBetween);
                } else {
                    firstEventDate = firstEventDate.plusWeeks(intervalByWeeks);
                    eventPeriodSaturday = firstEventDate.plusWeeks(intervalByWeeks).with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
                    if (eventPeriodSaturday.isAfter(lastEventDate)) {
                        eventPeriodSaturday = lastEventDate;
                    }

                    doneWeeklyCount = true;
                }
            }
        }

        eventPeriod.setNumberOfEventDates(numberOfEventDates);
        return eventPeriod;
    }

    public static EventPeriodCmd getNumberOfEventDates(EventPeriodCmd eventPeriod) {
        int numberOfEventDates = 0;

        int intervalByWeeks = eventPeriod.getIntervalByWeeks();
        LocalDate initialStartDate = eventPeriod.getInitialStartDate();
        LocalDate targetPeriodStart = eventPeriod.getTargetPeriodStart();
        LocalDate targetPeriodEnd = eventPeriod.getTargetPeriodEnd();
        if (eventPeriod.getFirstEventDate() == null) {
            eventPeriod = getTargetPeriodEventDates(eventPeriod);
        }

        LocalDate firstEventDate = eventPeriod.getFirstEventDate();
        LocalDate lastEventDate = eventPeriod.getLastEventDate();
        LocalDate firstEventPeriodSunday = eventPeriod.getEventPeriodSunday();

        log.info("1 getNumberOfEventDates {}", eventPeriod);

        long daysOffset = ChronoUnit.DAYS.between(initialStartDate, targetPeriodStart);
        long targetPeriodDays = ChronoUnit.DAYS.between(firstEventDate, lastEventDate) + 1; // Including the end date

        long offsetIntervals = daysOffset / (intervalByWeeks * 7);
        long targetIntervals = targetPeriodDays / (intervalByWeeks * 7);

        long totalEventDays = targetIntervals * (intervalByWeeks * 7) + (daysOffset % (intervalByWeeks * 7));
        log.info("2 daysOffset {} targetPeriodDays {} offsetIntervals {} targetIntervals {} totalEventDays {}",
                daysOffset, targetPeriodDays, offsetIntervals, targetIntervals, totalEventDays);

        if (targetPeriodDays % (intervalByWeeks * 7) < ((intervalByWeeks * 7) - (daysOffset % (intervalByWeeks * 7)))) {
            totalEventDays += targetPeriodDays % (intervalByWeeks * 7);
        } else {
            totalEventDays += (intervalByWeeks * 7) - (daysOffset % (intervalByWeeks * 7));
        }
        log.info("3 daysOffset {} targetPeriodDays {} offsetIntervals {} targetIntervals {} totalEventDays {}",
                daysOffset, targetPeriodDays, offsetIntervals, targetIntervals, totalEventDays);

        numberOfEventDates = (int) totalEventDays;

        eventPeriod.setNumberOfEventDates(numberOfEventDates);

        return eventPeriod;
    }

    public static EventPeriodCmd getTargetPeriodEventDates(EventPeriodCmd eventPeriodCmd) {
        int intervalByWeeks = eventPeriodCmd.getIntervalByWeeks();
        LocalDate initialStartDate = eventPeriodCmd.getInitialStartDate();
        LocalDate targetPeriodEnd = eventPeriodCmd.getTargetPeriodEnd();

        LocalDate lastEventDate = getLastEventDate(initialStartDate, intervalByWeeks, targetPeriodEnd);
        eventPeriodCmd.setLastEventDate(lastEventDate);

        eventPeriodCmd = getFirstEventDates(eventPeriodCmd);

        return eventPeriodCmd;
    }

    public static EventPeriodCmd getFirstEventDates(EventPeriodCmd eventPeriodCmd) {
        int intervalByWeeks = eventPeriodCmd.getIntervalByWeeks();
        LocalDate initialStartDate = eventPeriodCmd.getInitialStartDate();
        LocalDate targetPeriodStart = eventPeriodCmd.getTargetPeriodStart();
        LocalDate lastEventDate = eventPeriodCmd.getLastEventDate();

        // Assign the desired event date to the initial start date
        boolean isEventPeriod = true;
        LocalDate firstEventDate = initialStartDate;

        // Move the first event date to the occurrence of the desired day of week - period start date Sunday
        firstEventDate = firstEventDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        // Adjust the event date based on the bi-weekly interval
        while (firstEventDate.isBefore(targetPeriodStart)) {
            firstEventDate = firstEventDate.plusWeeks(intervalByWeeks);

            isEventPeriod = !isEventPeriod;
        }

        LocalDate eventPeriodSunday = firstEventDate;
        LocalDate eventPeriodSaturday = eventPeriodSunday.plusWeeks(intervalByWeeks).with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));

        if (!isEventPeriod && firstEventDate.isAfter(targetPeriodStart)) {
            firstEventDate = targetPeriodStart;
            eventPeriodSunday = eventPeriodSunday.minusWeeks(intervalByWeeks).with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
            eventPeriodSaturday = eventPeriodSunday.plusWeeks(intervalByWeeks).with(TemporalAdjusters.previousOrSame(DayOfWeek.SATURDAY));
        }

        if (eventPeriodSaturday.isAfter(lastEventDate)) {
            eventPeriodSaturday = lastEventDate;
        }

        eventPeriodCmd.setFirstEventDate(firstEventDate);
        eventPeriodCmd.setEventPeriodSunday(eventPeriodSunday);
        eventPeriodCmd.setEventPeriodSaturday(eventPeriodSaturday);

        return eventPeriodCmd;
    }
}
