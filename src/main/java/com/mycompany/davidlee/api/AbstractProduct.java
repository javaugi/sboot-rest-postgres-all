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
public abstract class AbstractProduct implements Product {

    private String id;
    private String name;
    private ProductCategory productCategory;
    private double unitPrice;
    private double perUnit;
    private double totalUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getPerUnit() {
        return perUnit;
    }

    public void setPerUnit(double perUnit) {
        this.perUnit = perUnit;
    }

    public double getTotalUnit() {
        return totalUnit;
    }

    public void setTotalUnit(double totalUnit) {
        this.totalUnit = totalUnit;
    }

    public abstract double getPrice();

    public abstract void setPrice(double unitPrice);
}
