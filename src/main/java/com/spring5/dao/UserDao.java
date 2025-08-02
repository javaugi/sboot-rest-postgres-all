package com.spring5.dao;

import com.spring5.entity.User;
import java.util.List;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface UserDao {

    void save(User user);

    void saveAll(List<User> users);

    Optional<User> findById(Long id);

    public List<User> findAll();

    Iterable<User> findAll(int offset, int limit);

    Iterable<User> findAll(Sort sort);

    Page<User> findAll(Pageable pageable);

    List<User> findByName(String name);

    List<User> findByName(String name, int limit, int offset);
}
