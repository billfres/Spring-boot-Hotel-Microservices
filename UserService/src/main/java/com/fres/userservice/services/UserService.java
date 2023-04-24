package com.fres.userservice.services;

import com.fres.userservice.models.User;

import java.util.List;

public interface UserService {

    User saveUser(User user);
    List<User> getAllUsers();
    User getUser(String userId);
}
