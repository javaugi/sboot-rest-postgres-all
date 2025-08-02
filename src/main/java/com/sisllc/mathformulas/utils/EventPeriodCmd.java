/*
 * Copyright (C) 2023 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */

package com.sisllc.mathformulas.utils;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate
 * Last Modified Author: $LastChangedBy
 */
public class EventPeriodCmd {
    private static final Logger log = LoggerFactory.getLogger(EventPeriodCmd.class);

    int intervalByWeeks = 0;
    int numberOfEventDates = 0;
    LocalDate initialStartDate;
    LocalDate targetPeriodStart;
    LocalDate targetPeriodEnd;
    LocalDate targetPeriodUntil;

    LocalDate firstEventDate;
    LocalDate lastEventDate;
    LocalDate eventPeriodSunday;
    LocalDate eventPeriodSaturday;

    public EventPeriodCmd(int intervalByWeeks, LocalDate initialStartDate, LocalDate targetPeriodStart, LocalDate targetPeriodEnd) {
        this(intervalByWeeks, initialStartDate, targetPeriodStart, targetPeriodEnd, null);
    }

    public EventPeriodCmd(int intervalByWeeks, LocalDate initialStartDate, LocalDate targetPeriodStart, LocalDate targetPeriodEnd, LocalDate targetPeriodUntil) {
        this.intervalByWeeks = intervalByWeeks;
        this.initialStartDate = initialStartDate;
        this.targetPeriodStart = targetPeriodStart;
        this.targetPeriodEnd = targetPeriodEnd;
        this.targetPeriodUntil = targetPeriodUntil;
        setup();
    }

    private void setup() {
        if (targetPeriodUntil != null) {
            targetPeriodEnd = targetPeriodUntil;
        }
    }

    public EventPeriodCmd(int intervalByWeeks, Date initialStartDate, Date targetPeriodStart, Date targetPeriodEnd, Date targetPeriodUntil) {
        this(intervalByWeeks, userLocalDateOfDate(initialStartDate), userLocalDateOfDate(targetPeriodStart), userLocalDateOfDate(targetPeriodEnd), userLocalDateOfDate(targetPeriodUntil));
    }

    private static LocalDate userLocalDateOfDate(Date date) {
        return (date == null) ? null : date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public int getNumberOfEventDates() {
        return numberOfEventDates;
    }

    public void setNumberOfEventDates(int numberOfEventDates) {
        this.numberOfEventDates = numberOfEventDates;
    }

    public int getIntervalByWeeks() {
        return intervalByWeeks;
    }

    public void setIntervalByWeeks(int intervalByWeeks) {
        this.intervalByWeeks = intervalByWeeks;
    }

    public LocalDate getInitialStartDate() {
        return initialStartDate;
    }

    public void setInitialStartDate(LocalDate initialStartDate) {
        this.initialStartDate = initialStartDate;
    }

    public LocalDate getTargetPeriodStart() {
        return targetPeriodStart;
    }

    public void setTargetPeriodStart(LocalDate targetPeriodStart) {
        this.targetPeriodStart = targetPeriodStart;
    }

    public LocalDate getTargetPeriodEnd() {
        return targetPeriodEnd;
    }

    public void setTargetPeriodEnd(LocalDate targetPeriodEnd) {
        this.targetPeriodEnd = targetPeriodEnd;
    }

    public LocalDate getTargetPeriodUntil() {
        return targetPeriodUntil;
    }

    public void setTargetPeriodUntil(LocalDate targetPeriodUntil) {
        this.targetPeriodUntil = targetPeriodUntil;
    }

    public LocalDate getFirstEventDate() {
        return firstEventDate;
    }

    public void setFirstEventDate(LocalDate firstEventDate) {
        this.firstEventDate = firstEventDate;
    }

    public LocalDate getLastEventDate() {
        return lastEventDate;
    }

    public void setLastEventDate(LocalDate lastEventDate) {
        this.lastEventDate = lastEventDate;
    }

    public LocalDate getEventPeriodSunday() {
        return eventPeriodSunday;
    }

    public void setEventPeriodSunday(LocalDate eventPeriodSunday) {
        this.eventPeriodSunday = eventPeriodSunday;
    }

    public LocalDate getEventPeriodSaturday() {
        return eventPeriodSaturday;
    }

    public void setEventPeriodSaturday(LocalDate eventPeriodSaturday) {
        this.eventPeriodSaturday = eventPeriodSaturday;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n EventPeriodCmd{");
        sb.append("numberOfEventDates=").append(numberOfEventDates);
        sb.append(", intervalByWeeks=").append(intervalByWeeks);
        sb.append(", initialStartDate=").append(initialStartDate);
        sb.append(", targetPeriodStart=").append(targetPeriodStart);
        sb.append(", targetPeriodEnd=").append(targetPeriodEnd);
        sb.append(", targetPeriodUntil=").append(targetPeriodUntil);
        sb.append(", firstEventDate=").append(firstEventDate);
        sb.append(", lastEventDate=").append(lastEventDate);
        sb.append(", eventPeriodSunday=").append(eventPeriodSunday);
        sb.append(", eventPeriodSaturday=").append(eventPeriodSaturday);
        sb.append('}');
        return sb.toString();
    }

}
