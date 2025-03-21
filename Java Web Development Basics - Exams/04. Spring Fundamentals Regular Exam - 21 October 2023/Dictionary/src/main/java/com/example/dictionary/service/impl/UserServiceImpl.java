package com.example.dictionary.service.impl;

import com.example.dictionary.model.entity.User;
import com.example.dictionary.model.service.UserServiceModel;
import com.example.dictionary.repository.UserRepository;
import com.example.dictionary.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final HttpSession httpSession;

    @Override
    public boolean register(UserServiceModel userServiceModel) {

        Optional<User> existingUser =
                this.userRepository.findByUsernameOrEmail(userServiceModel.getUsername(), userServiceModel.getEmail());

        if (existingUser.isPresent()) {
            log.warn("Failed to create user account. User already exists.");
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
}
