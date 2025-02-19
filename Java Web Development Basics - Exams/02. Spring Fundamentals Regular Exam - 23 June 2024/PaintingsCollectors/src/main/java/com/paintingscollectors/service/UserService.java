package com.paintingscollectors.service;

import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);

    void logout();

    User findUserByUsername(String username);
}
