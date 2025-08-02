/*
 * Copyright (C) 2018 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.mycompany.davidlee.impl;

/**
 *
 *
 * @author javaugi
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class WeightItem extends ItemImpl {

    private double weightInPound;
    private double pricePerPound;

    public WeightItem(String name, double weightInPound, double pricePerPound) {
        super(name, weightInPound * pricePerPound);
        this.weightInPound = weightInPound;
        this.pricePerPound = pricePerPound;
        super.setCategory(Category.Weight);
    }

    public double getWeightInPound() {
        return weightInPound;
    }

    public void setWeightInPound(double weightInPound) {
        this.weightInPound = weightInPound;
    }

    public double getPricePerPound() {
        return pricePerPound;
    }

    public void setPricePerPound(double pricePerPound) {
        this.pricePerPound = pricePerPound;
    }

}
