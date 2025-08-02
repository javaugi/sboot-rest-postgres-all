/*
 * Copyright (C) 2023 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */

package com.sisllc.mathformulas.utils;
import com.abc.utils.AddressPoBoxVO;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate
 * Last Modified Author: $LastChangedBy
 */
public class CommonUtils {
    private static final Logger log = LoggerFactory.getLogger(CommonUtils.class);

    public static final String REC_RULES_INTERVAL_PATTERN = "INTERVAL=([0-9]*\\.?[0-9]+)";
    public static final String DD_MMM_YYYY_DASHES = "dd-MMM-yyyy";
    public static final String EMAIL_REG_EXPRESSION = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    public static final String US_PHONE_REG_EXPRESSION = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
    public static final String PHONE_INT_EXPRESSION = "^\\([0-9]{10})$";
    public static final String LAST_NAME_SEARCH_STATE_WIDE_REG_EX = "'''|,|-| |SR.|SR|JR|JR.|DR|DR.|III|II|111|11|\\.|\\(.+?\\)'";
    public static final String PO_BOX_REG_EXPRESSION = "((P(OST)?.?\\s*((O(FF(ICE)?)?)?.?\\s*(B(IN|OX|.?))|B(IN|OX))+))[\\w\\s*\\W]*";
    public static final String PHONE_NUMBER_ONLY_REGEX = "[^0-9]";

    public static Integer serviceEventIntervalFinder(String input) {
        Pattern pattern = Pattern.compile(REC_RULES_INTERVAL_PATTERN);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }

        return 1;
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

    public static String getMatchingLastName(String lastName) {
        Pattern pattern = Pattern.compile(LAST_NAME_SEARCH_STATE_WIDE_REG_EX, Pattern.CASE_INSENSITIVE);
        return RegExUtils.replaceAll(lastName, pattern, "");
    }

    public static PhoneNumberExtensionVO extractUsPhoneExtension(String phoneNumberExtension) {
        PhoneNumberExtensionVO phoneNumberExtensionVO = new PhoneNumberExtensionVO();
        // see PhoneFormatExtensionTest for example phone number with extensions from the CTS.VENDORVIEW_USERS.PHONE_NUMBER
        phoneNumberExtensionVO.setOriginalPhoneExtension(phoneNumberExtension);
        if (StringUtils.isBlank(phoneNumberExtension)) {
            phoneNumberExtensionVO.setValid(false);
            return phoneNumberExtensionVO;
        }

        //trim and remove country code
        phoneNumberExtension = phoneNumberExtension.trim();
        phoneNumberExtension = phoneNumberExtension.replaceAll("^1-", "");
        phoneNumberExtension = phoneNumberExtension.replaceAll("^1 ", "");
        phoneNumberExtension = phoneNumberExtension.replaceAll("^1\\(", "");
        if (StringUtils.isBlank(phoneNumberExtension)) {
            phoneNumberExtensionVO.setValid(false);
            return phoneNumberExtensionVO;
        }

        String phoneExtentionNumber = phoneNumberExtension.replaceAll(PHONE_NUMBER_ONLY_REGEX, "");
        if (StringUtils.isBlank(phoneExtentionNumber) || phoneExtentionNumber.length() < 10) {
            phoneNumberExtensionVO.setValid(false);
            return phoneNumberExtensionVO;
        }

        String phoneNumber = phoneExtentionNumber.substring(0, 9);
        String formattedPhone = phoneNumberToUSFormat(phoneExtentionNumber.substring(0, 10));
        //System.out.println("phoneNumber=" + phoneNumber + "-formattedPhone=" + formattedPhone);
        phoneNumberExtensionVO.setPhoneNumber(phoneNumber);
        phoneNumberExtensionVO.setFormattedPhoneNumber(formattedPhone);

        if (phoneExtentionNumber.length() > 10) {
            phoneNumberExtensionVO.setExtension(phoneExtentionNumber.substring(10));
        }

        return phoneNumberExtensionVO;
    }

    public static String join(Collection collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static String join(Iterator iterator, String separator) {

        // handle null, zero and one elements before building a buffer
        if (iterator == null) {
            return null;
        }
        if (!iterator.hasNext()) {
            return "";
        }
        Object first = iterator.next();
        if (!iterator.hasNext()) {
            return toString(first);
        }

        // two or more elements
        StringBuilder buf = new StringBuilder(256);
        if (first != null) {
            buf.append(first);
        }

        while (iterator.hasNext()) {
            if (separator != null) {
                buf.append(separator);
            }
            Object obj = iterator.next();
            if (obj != null) {
                buf.append(obj);
            }
        }
        return buf.toString();
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REG_EXPRESSION, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static String phoneNumberToUSFormat(String phone) {
        Pattern pattern = Pattern.compile(US_PHONE_REG_EXPRESSION, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(phone);
        String number = matcher.replaceFirst("($1) $2-$3");
        return number;
    }

    public static Long formatUSPhoneToNumbers(String phone) {
        String number = phone.replaceAll("[^0-9]", "");
        return Long.valueOf(number);
    }

    public static AddressPoBoxVO extractPoBoxFromAddress(String address) {
        AddressPoBoxVO addressPoBoxVO = new AddressPoBoxVO();
        // see AddressPoBoxTest for example addresses from the CTS.VENDOR.ADDRESS_1
        Pattern pattern = Pattern.compile(PO_BOX_REG_EXPRESSION, Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(address);
        if (match.find()) {
            String matchFound = match.group();
            String remaining = StringUtils.substringBefore(address, matchFound);
            addressPoBoxVO.setAddress(remaining);
            addressPoBoxVO.setPoBox(matchFound);
            addressPoBoxVO.setPoBoxFound(true);
        }

        if (addressPoBoxVO.isPoBoxFound() && address.length() != addressPoBoxVO.length()) {
            log.info("stringToSearch {} length {} \n return length {} return value {}", address, address.length(), addressPoBoxVO.length(), addressPoBoxVO);
        }
        return addressPoBoxVO;
    }

    public static Date getFiscalYearStartDateOctober1st() {
        Calendar october1st = Calendar.getInstance();
        october1st.set(Calendar.MONTH, Calendar.OCTOBER);
        october1st.set(Calendar.DATE, 1);

        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.MONTH) < Calendar.OCTOBER) {
            october1st.add(Calendar.YEAR, -1);
        }

        Date startDate = DateUtils.truncate(october1st.getTime(), Calendar.DATE);

        return startDate;
    }

    public static Date getFiscalYearEndDateSeptember30th() {
        Calendar september30th = Calendar.getInstance();
        september30th.set(Calendar.MONTH, Calendar.SEPTEMBER);
        september30th.set(Calendar.DATE, 30);

        Calendar now = Calendar.getInstance();
        if (now.get(Calendar.MONTH) > Calendar.SEPTEMBER) {
            september30th.add(Calendar.YEAR, 1);
        }

        Date endDate = DateUtils.truncate(september30th.getTime(), Calendar.DATE);

        return endDate;
    }

    public static String formattedBillingUnits(BigDecimal input) {
        if (input == null) {
            return null;
        }

        String formattedUnits = input.toPlainString();
        if (formattedUnits.endsWith(".00")) {
            formattedUnits = formattedUnits.substring(0, formattedUnits.indexOf("."));
        } else if (formattedUnits.endsWith("0")) {
            formattedUnits = formattedUnits.substring(0, (formattedUnits.length() - 1));
        }
        return formattedUnits;
    }

    public static String formattedBillingCost(BigDecimal input) {
        if (input == null) {
            return null;
        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance(Locale.US);
        return formatter.format(input);
    }

    public static String randomFormattedDob(Date dateOfBirth) {
        return DateFormatUtils.format(dateOfBirth, "MM/dd/yyyy");
    }

    public static int getLengthOfMonth(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().lengthOfMonth();
    }

    public static BigDecimal getLengthOfMonthAsBigDecimal(Date date) {
        return new BigDecimal(getLengthOfMonth(date));
    }

    public static Date truncateDate(Date dateToTruncate) {
        return DateUtils.truncate(dateToTruncate, Calendar.DATE);
    }

    public static long getDatesDiffInclusive(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    public static long getDatesDiffInclusive(Date startDate, Date endDate) {
        return getDatesDiffInclusive(userLocalDateOfDate(startDate), userLocalDateOfDate(endDate));
    }

    public static LocalDate userLocalDateOfDate(Date date) {
        return date.toInstant().atZone(getUserDefaultZoneId()).toLocalDate();
    }

    public static LocalDateTime userLocalDateTimeOfDate(Date date) {
        return date.toInstant().atZone(getUserDefaultZoneId()).toLocalDateTime();
    }

    private static ZoneId getUserDefaultZoneId() {
        return ZoneId.systemDefault();
    }

    public static Locale getUserDefaultLocale() {
        return Locale.getDefault();
    }

    public static boolean isDateBetween(Date target, Date start, Date end) {
        return !target.before(start) && !target.after(end);
    }

    public static Date dateOfUserLocalDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(getUserDefaultZoneId()).toInstant());
    }

    public static Date findSundayOfTheWeek(Date date) {
        LocalDate localDate = userLocalDateOfDate(date);
        return dateOfUserLocalDate(localDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)));
    }

    public static Date findSaturdayOfTheWeek(Date date) {
        LocalDate localDate = userLocalDateOfDate(date);
        return dateOfUserLocalDate(localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)));
    }

    public static Date getDateFromString(String strDate, String format) {

        Date dtDate = null;
        if (!strDate.equals("")) {
            try {
                DateFormat formatter = new SimpleDateFormat(format);
                dtDate = (Date) formatter.parse(strDate);
            } catch (ParseException e) {
            }
        }
        return dtDate;
    }

    public static String convertListToDatabaseInString(Object[] list) {
        StringBuilder sb = new StringBuilder("('");
        String commaSepValue = StringUtils.join(list, ", ");
        sb.append(commaSepValue.replaceAll(", ", "', '"));
        sb.append("')");

        return sb.toString();
    }

    public static String getStartDateBySelectedFiscalYear(int selectedFiscalYear) {
        Calendar october1st = Calendar.getInstance();
        october1st.set((selectedFiscalYear - 1), Calendar.OCTOBER, 1);
        Date startDate = DateUtils.truncate(october1st.getTime(), Calendar.DATE);

        return formatDate(startDate, DD_MMM_YYYY_DASHES);
    }

    public static String getEndDateBySelectedFiscalYear(int selectedFiscalYear) {
        Calendar september30th = Calendar.getInstance();
        september30th.set(selectedFiscalYear, Calendar.SEPTEMBER, 30);
        Date endDate = DateUtils.truncate(september30th.getTime(), Calendar.DATE);

        return formatDate(endDate, DD_MMM_YYYY_DASHES);
    }

    public static String formatDate(Date date, String format) {
        return new SimpleDateFormat(format, Locale.US).format(date);
    }

}
