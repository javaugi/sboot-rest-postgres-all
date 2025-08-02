/*
 * Copyright (C) 2023 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.  
 * For more information, contact <http://www.ciminc.com>
 */

package com.sisllc.mathformulas.utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate
 * Last Modified Author: $LastChangedBy
 */
public class PhoneNumberExtensionVO {
    private static final Logger log = LoggerFactory.getLogger(PhoneNumberExtensionVO.class);

    String originalPhoneExtension;
    String phoneNumber;
    String formattedPhoneNumber;
    String extension;
    boolean valid = true;

    public String getOriginalPhoneExtension() {
        return originalPhoneExtension;
    }

    public void setOriginalPhoneExtension(String originalPhoneExtension) {
        this.originalPhoneExtension = originalPhoneExtension;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFormattedPhoneNumber() {
        return formattedPhoneNumber;
    }

    public void setFormattedPhoneNumber(String formattedPhoneNumber) {
        this.formattedPhoneNumber = formattedPhoneNumber;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return "PhoneNumberExtensionVO{" + "originalPhoneExtension=" + originalPhoneExtension + ", formattedPhoneNumber=" + formattedPhoneNumber + ", extension=" + extension + '}';
    }

}
