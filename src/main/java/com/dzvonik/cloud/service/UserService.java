package com.dzvonik.cloud.service;

import com.dzvonik.cloud.model.User;

public interface UserService {

    // User findByName(String name);

    boolean existsByUsername(String username);

    void save(String username, String password);

}
