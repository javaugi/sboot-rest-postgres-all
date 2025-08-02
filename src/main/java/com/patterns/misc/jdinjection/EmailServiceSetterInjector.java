/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.patterns.misc.jdinjection;

/**
 *
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public class EmailServiceSetterInjector implements MessageServiceInjector {

    @Override
    public Consumer getConsumer() {
        ConsumerSetterInjection app = new ConsumerSetterInjection();
        app.setService(new EmailServiceImpl());
        return app;
    }

}
