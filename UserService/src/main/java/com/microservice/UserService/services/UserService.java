package com.microservice.UserService.services;

import com.microservice.UserService.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUser();

    User getUser(String userId);

    // TODO: Delete
    boolean deleteUser(String userId);

    // TODO: Update
    User updateUser(User user, String userId);
}
