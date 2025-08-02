/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.davidlee.api.Item;
import com.mycompany.davidlee.api.Receipt;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class ReceiptImpl implements Receipt {

    private List<Item> items;
    private double total;
    private double totalDiscount;
    private double finalTotal;

    public ReceiptImpl() {
        items = new ArrayList();
        total = 0;
        totalDiscount = 0;
    }

    /**
     * @return Currency formatted total ($X,XXX.XX) of all items
     */
    private String getFormattedTotal(double amount) {
        DecimalFormat df = new DecimalFormat("$#,###.##");  //set format
        return df.format(amount);
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
        total += item.getPrice();
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
        total -= item.getPrice();
    }

    @Override
    public void applyCoupons(List<Item> coupons) {
        for (Item coupon : coupons) {
            if (coupon.getCategory().equals(Item.Category.Discount)) {
                applyDiscount((DiscountItem) coupon);
            } else {
                applyBuyGetFree((BuyGetFreeItem) coupon);
            }
        }
        finalTotal = total - totalDiscount;
    }

    private void applyDiscount(DiscountItem coupon) {
        for (Item item : items) {
            if (!item.getName().equals(coupon.getName())) {
                continue;
            }
            totalDiscount += item.getPrice() * coupon.getDiscount();
        }
    }

    private void applyBuyGetFree(BuyGetFreeItem coupon) {
        int totalItems = 0;
        for (Item item : items) {
            if (totalItems >= coupon.getLimitAmout()) {
                break;
            }
            if (!item.getName().equals(coupon.getName())) {
                continue;
            }
            totalItems++;
        }

        double totalPrice = totalItems * coupon.getPrice();
        double discountPercentage = coupon.getFreeAmout() / (coupon.getGetAmount() + coupon.getFreeAmout());
        totalDiscount += totalPrice * discountPercentage;
    }

    @Override
    public List<Item> getItems() {
        return items;
    }

    @Override
    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public double getTotal() {
        return total;
    }

    @Override
    public double getTotalDiscount() {
        return totalDiscount;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n Total         : ").append(getFormattedTotal(total));
        sb.append("\n Total Savings : ").append(getFormattedTotal(totalDiscount));
        sb.append("\n You only paid : ").append(getFormattedTotal(finalTotal));
        return sb.toString();
    }

}
