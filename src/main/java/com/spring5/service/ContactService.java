/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.entity.Contact;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface ContactService {

    void save(Contact contact);

    void saveAll(List<Contact> contacts);

    Optional<Contact> findById(Long id);

    Iterable<Contact> findAll();

    Iterable<Contact> findAll(int offset, int limit);
}
