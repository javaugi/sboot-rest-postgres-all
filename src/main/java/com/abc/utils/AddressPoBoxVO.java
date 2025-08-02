/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abc.utils;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author david
 */
public class AddressPoBoxVO {
    
    String address;
    String poBox;
    boolean poBoxFound = false;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPoBox() {
        return poBox;
    }

    public void setPoBox(String poBox) {
        this.poBox = poBox;
    }

    public int length() {
        return StringUtils.length(address) + StringUtils.length(poBox);
    }

    public String getTrimmedAddress() {
        return StringUtils.stripEnd(StringUtils.trim(address), ",");
    }

    public String getTrimmedPoBox() {
        return StringUtils.trim(poBox);
    }

    public boolean isPoBoxFound() {
        return poBoxFound;
    }

    public void setPoBoxFound(boolean poBoxFound) {
        this.poBoxFound = poBoxFound;
    }

    @Override
    public String toString() {
        return "AddressPoBoxVO{" + "address=" + address + ", poBox=" + poBox + '}';
    }
}
