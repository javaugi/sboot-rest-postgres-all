/*
 * Copyright (C) 2019 Center for Information Management, Inc.
 *
 * This program is proprietary.
 * Redistribution without permission is strictly prohibited.
 * For more information, contact <http://www.ciminc.com>
 */
package com.spring5.service;

import com.spring5.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 *
 * @author david
 * @version $LastChangedRevision $LastChangedDate Last Modified Author:
 * $LastChangedBy
 */
public interface UserService {

    void save(User user);

    void saveAll(List<User> users);

    Optional<User> findById(Long id);

    Iterable<User> findAll();

    Iterable<User> findAll(int offset, int limit);

    public Iterable<User> findAll(Sort sort);

    public Page<User> findAll(Pageable pageable);
}
