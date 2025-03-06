package com.example.likebook.service.impl;

import com.example.likebook.model.entity.User;
import com.example.likebook.model.service.UserServiceModel;
import com.example.likebook.repository.UserRepository;
import com.example.likebook.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

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

        User user = this.userRepository.findByUsername(userServiceModel.getUsername());
        if (user == null) {
            log.warn("User not exists.");
            return false;
        }

        boolean passMatch = this.passwordEncoder.matches(userServiceModel.getPassword(), user.getPassword());
        if (!passMatch) {
            log.warn("Password does not match.");
            return false;
        }

        httpSession.setAttribute("user_id", user.getId());
        httpSession.setAttribute("username", user.getUsername());
        log.info("Successfully logged user account with username [%s]".formatted(userServiceModel.getUsername()));
        return true;
    }

    @Override
    public void logout() {
        httpSession.invalidate();
    }

    @Override
    public User findUserById(String id) {
        return this.userRepository.findById(id).orElse(null);
    }
}
