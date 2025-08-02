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
public abstract class AbstractCoupon implements Coupon, Product {

    private CouponCategory couponCategory;
    private Product product;
    private int getAmount;
    private int freeAmout;
    private int limitAmout;
    private double discountUnit;
    private double discountAmount;
    private double discountPercentage;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public CouponCategory getCouponCategory() {
        return couponCategory;
    }

    public void setCouponCategory(CouponCategory couponCategory) {
        this.couponCategory = couponCategory;
    }

    public int getGetAmount() {
        return getAmount;
    }

    public void setGetAmount(int getAmount) {
        this.getAmount = getAmount;
    }

    public int getFreeAmout() {
        return freeAmout;
    }

    public void setFreeAmout(int freeAmout) {
        this.freeAmout = freeAmout;
    }

    public int getLimitAmout() {
        return limitAmout;
    }

    public void setLimitAmout(int limitAmout) {
        this.limitAmout = limitAmout;
    }

    public double getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(double discountUnit) {
        this.discountUnit = discountUnit;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public abstract double getDiscount();

    public abstract void setDiscount(double discount);
}
