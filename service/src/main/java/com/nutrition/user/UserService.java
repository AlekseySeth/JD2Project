package com.nutrition.user;

import com.nutrition.entity.user.User;

import java.util.List;

public interface UserService {

    Long save(User user);

    User findById(Long id);

    List<User> findAll();

    User findByEmail(String email);

    void update(User user);
}