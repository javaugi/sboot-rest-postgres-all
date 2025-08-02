/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.utils;

/**
 *
 * @author david
 */
public class PhoneNumberExtensionVO {
    
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
