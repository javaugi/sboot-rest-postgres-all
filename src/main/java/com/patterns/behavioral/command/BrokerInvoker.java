/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.behavioral.command;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class BrokerInvoker {

    private List<OrderCommand> orderList = new ArrayList<OrderCommand>();

    public void takeOrder(OrderCommand order) {
        orderList.add(order);
    }

    public void placeOrders() {

        for (OrderCommand order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
