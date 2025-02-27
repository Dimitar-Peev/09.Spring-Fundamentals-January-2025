package com.example.spotifyplaylist.service;

import com.example.spotifyplaylist.model.entity.User;
import com.example.spotifyplaylist.model.service.UserServiceModel;

public interface UserService {

    boolean register(UserServiceModel userServiceModel);

    boolean login(UserServiceModel userServiceModel);

    void logout();

    User findUserByUsername(String username);

    void addToPlaylist(String username, String songId);

    void clearPlaylist();
}
