/*
 * Copyright (C) 2018 Strategic Information Systems, LLC.
 *
 */
package com.mycompany.davidlee.api;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface Coupon {

    static final int LIMIT_AMOUNT = 1000;

    static enum CouponCategory {
        Discount, DiscountByUnits, DiscountPercentage, BuyGetFree;
    }


    /*
    int getAmount;
    int freeAmout;
    int limitAmout;
    double discountUnit;
    double discountAmount;
    double discountPercentage;
    double discount;
    // */
    CouponCategory getCouponCategory();

    void setCouponCategory(CouponCategory category);

    int getGetAmount();

    void setGetAmount(int getAmount);

    int getFreeAmout();

    void setFreeAmout(int freeAmout);

    int getLimitAmout();

    void setLimitAmout(int limitAmout);

    double getDiscountUnit();

    void setDiscountUnit(double discountUnit);

    double getDiscountAmount();

    void setDiscountAmount(double discountAmount);

    double getDiscountPercentage();

    void setDiscountPercentage(double discountPercentage);

    double getDiscount();

    void setDiscount(double discount);
}
