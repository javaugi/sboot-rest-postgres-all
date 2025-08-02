/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.structural.composite;

import java.util.List;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface Property {

    public void purchase();

    public void sell();

    public void print();

    public void add(Property property);

    public void remove(Property property);

    public float getPrice();

    public void setPrice(float price);

    public String getAddress();

    public void setAddress(String address);

    public String getBuilder();

    public void setBuilder(String builder);

    public AbstractProperty.PropertyType getType();

    public void setType(AbstractProperty.PropertyType type);

    public List<Property> getProperties();

    public void setProperties(List<Property> properties);

}
