package app.user.service;

import app.exception.DomainException;
import app.subscription.service.SubscriptionService;
import app.user.model.User;
import app.user.model.UserRole;
import app.user.repository.UserRepository;
import app.wallet.service.WalletService;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SubscriptionService subscriptionService;
    private final WalletService walletService;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       SubscriptionService subscriptionService, WalletService walletService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.subscriptionService = subscriptionService;
        this.walletService = walletService;
    }

    @Transactional
    public User register(RegisterRequest registerRequest) {

        // Validate the username
        Optional<User> optionUser = userRepository.findByUsername(registerRequest.getUsername());
        if (optionUser.isPresent()) {
            throw new DomainException("Username [%s] already exist.".formatted(registerRequest.getUsername()));
        }

        // Create the user account
        User user = userRepository.save(initializeUser(registerRequest));

        // Initialize the default wallet and subscription
        subscriptionService.createDefaultSubscription(user);
        walletService.createNewWallet(user);

        log.info("Successfully create new user account for username [%s] and id [%s]".formatted(user.getUsername(), user.getId()));

        // Return the created user
        return user;
    }

    private User initializeUser(RegisterRequest registerRequest) {

        return User.builder()
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.USER)
                .isActive(true)
                .country(registerRequest.getCountry())
                .createdOn(LocalDateTime.now())
                .updatedOn(LocalDateTime.now())
                .build();
    }


    public User login(LoginRequest loginRequest) {

        Optional<User> optionUser = userRepository.findByUsername(loginRequest.getUsername());
        if (optionUser.isEmpty()) {
            throw new DomainException("Username or password are incorrect.");
        }

        User user = optionUser.get();
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new DomainException("Username or password are incorrect.");
        }

        return user;
    }

}
