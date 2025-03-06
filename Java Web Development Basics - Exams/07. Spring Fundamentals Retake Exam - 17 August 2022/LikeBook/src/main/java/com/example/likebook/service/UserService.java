package com.example.likebook.service;

import com.example.likebook.model.entity.User;
import com.example.likebook.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);

    void logout();

    User findUserById(String id);
}
