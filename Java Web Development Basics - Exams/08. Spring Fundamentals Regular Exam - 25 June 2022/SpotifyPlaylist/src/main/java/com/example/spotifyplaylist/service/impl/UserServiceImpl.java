package com.example.spotifyplaylist.service.impl;

import com.example.spotifyplaylist.model.entity.Song;
import com.example.spotifyplaylist.model.entity.User;
import com.example.spotifyplaylist.model.service.UserServiceModel;
import com.example.spotifyplaylist.repository.SongRepository;
import com.example.spotifyplaylist.repository.UserRepository;
import com.example.spotifyplaylist.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;
    private final SongRepository songRepository;

    @Override
    public boolean register(UserServiceModel userServiceModel) {

        Optional<User> existingUser =
                this.userRepository.findByUsernameOrEmail(userServiceModel.getUsername(), userServiceModel.getEmail());

        if (existingUser.isPresent()) {
            log.info("Failed to create user account. User already exists.");
            return false;
        } else {
            User user = this.modelMapper.map(userServiceModel, User.class);
            user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
            this.userRepository.save(user);
            log.info("Successfully created new user account for username [%s] and id [%s]".formatted(user.getUsername(), user.getId()));
            return true;
        }
    }

    @Override
    public boolean login(UserServiceModel userServiceModel) {

        Optional<User> optionalUser = this.userRepository.findByUsername(userServiceModel.getUsername());
        if (optionalUser.isEmpty()) {
            log.warn("User not exists.");
            return false;
        }

        boolean passMatch = this.passwordEncoder.matches(userServiceModel.getPassword(), optionalUser.get().getPassword());
        if (!passMatch) {
            log.warn("Password does not match.");
            return false;
        }

        httpSession.setAttribute("userId", optionalUser.get().getId());
        httpSession.setAttribute("username", optionalUser.get().getUsername());
        httpSession.setAttribute("loggedIn", true);
        log.info("Successfully logged user account with username [%s]".formatted(userServiceModel.getUsername()));
        return true;
    }

    @Override
    public User findUserByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public void addToPlaylist(String username, String songId) {
        User user = userRepository.findByUsername(username).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);

        if (user != null && song != null && !user.getPlaylist().contains(song)) {
            user.addToPlaylist(song);
            userRepository.save(user);
            log.info("Successfully added song to playlist.");
        }
    }

    @Override
    public void clearPlaylist(){
        String username = httpSession.getAttribute("username").toString();
        User user = this.userRepository.findByUsername(username).orElse(null);

        if (user != null) {
            user.getPlaylist().clear();
            this.userRepository.save(user);
            log.info("Successfully removed songs from playlist.");
        }
    }
}
