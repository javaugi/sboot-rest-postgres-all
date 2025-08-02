/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.utils;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author david
 */
public class DomainUtils {
    private static final Logger log = LoggerFactory.getLogger(DomainUtils.class);
    public static final String EMAIL_REG_EXPRESSION = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";
    public static final String US_PHONE_REG_EXPRESSION = "^\\(?([0-9]{3})\\)?[-.\\s]?([0-9]{3})[-.\\s]?([0-9]{4})$";
    public static final String PHONE_INT_EXPRESSION = "^\\([0-9]{10})$";
    public static final String LAST_NAME_SEARCH_STATE_WIDE_REG_EX = "'''|,|-| |SR.|SR|JR|JR.|DR|DR.|III|II|111|11|\\.|\\(.+?\\)'";
    public static final String PO_BOX_REG_EXPRESSION = "((P(OST)?.?\\s*((O(FF(ICE)?)?)?.?\\s*(B(IN|OX|.?))|B(IN|OX))+))[\\w\\s*\\W]*";
    public static final String PHONE_NUMBER_ONLY_REGEX = "[^0-9]";

    public static String getMatchingLastName(String lastName) {
        Pattern pattern = Pattern.compile(LAST_NAME_SEARCH_STATE_WIDE_REG_EX, Pattern.CASE_INSENSITIVE);
        return RegExUtils.replaceAll(lastName, pattern, "");
    }

    public static PhoneNumberExtensionVO extractUsPhoneExtension(String phoneNumberExtension) {
        PhoneNumberExtensionVO phoneNumberExtensionVO = new PhoneNumberExtensionVO();
        // see PhoneFormatExtensionTest for example phone number with extensions
        phoneNumberExtensionVO.setOriginalPhoneExtension(phoneNumberExtension);
        if (StringUtils.isBlank(phoneNumberExtension)) {
            phoneNumberExtensionVO.setValid(false);
            return phoneNumberExtensionVO;
        }

        // trim and remove country code
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
        // System.out.println("phoneNumber=" + phoneNumber + "-formattedPhone=" +
        // formattedPhone);
        phoneNumberExtensionVO.setPhoneNumber(phoneNumber);
        phoneNumberExtensionVO.setFormattedPhoneNumber(formattedPhone);

        if (phoneExtentionNumber.length() > 10) {
            phoneNumberExtensionVO.setExtension(phoneExtentionNumber.substring(10));
        }

        return phoneNumberExtensionVO;
    }

    public static String join(Collection<String> collection, String separator) {
        if (collection == null) {
            return null;
        }
        return join(collection.iterator(), separator);
    }

    public static String join(Iterator<String> iterator, String separator) {

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
        Matcher matcher = pattern.matcher(phone.toString());
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
        Pattern pattern = Pattern.compile(PO_BOX_REG_EXPRESSION);
        Matcher match = pattern.matcher(address);
        if (match.find()) {
            String matchFound = match.group();
            String remaining = StringUtils.substringBefore(address, matchFound);
            addressPoBoxVO.setAddress(remaining);
            addressPoBoxVO.setPoBox(matchFound);
            addressPoBoxVO.setPoBoxFound(true);
        }

        if (addressPoBoxVO.isPoBoxFound() && address.length() != addressPoBoxVO.length()) {
            log.info("stringToSearch {} length {} \n return length {} return value {}", address, address.length(),
                    addressPoBoxVO.length(), addressPoBoxVO);
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
}
