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
public interface Product {

    static enum ProductCategory {
        Bagged, Counted, Weighted;
    }

    /*
    private String id;
    private String name;
    private ProductCategory category;
    private double unitPrice;
    private double perUnit;
    private double totalUnit;
    private double price;
    // */
    String getId();

    void setId(String id);

    String getName();

    void setName(String name);

    ProductCategory getProductCategory();

    void setProductCategory(ProductCategory category);

    double getUnitPrice();

    void setUnitPrice(double unitPrice);

    double getPerUnit();

    void setPerUnit(double perUnit);

    double getTotalUnit();

    void setTotalUnit(double totalUnit);

    double getPrice();

    void setPrice(double price);
}
