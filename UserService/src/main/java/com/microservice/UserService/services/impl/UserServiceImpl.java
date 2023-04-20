package com.microservice.UserService.services.impl;

import com.microservice.UserService.entities.User;
import com.microservice.UserService.exceptions.ResourceNotFoundException;
import com.microservice.UserService.repositories.UserRepository;
import com.microservice.UserService.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: "+userId));
    }

    public boolean deleteUser(String userId){
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: "+userId));
        userRepository.deleteById(userId);
        return true;
    }

    public User updateUser(User user, String userId){
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server: "+userId));
        user1.setName(user.getName() != null ? user.getName() : user1.getName());
        return userRepository.save(user1);
    }
}
