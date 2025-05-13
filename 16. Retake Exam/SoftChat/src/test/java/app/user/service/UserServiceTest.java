package app.user.service;

import app.user.model.User;
import app.user.repository.UserRepository;
import app.web.dto.LoginRequest;
import app.web.dto.RegisterRequest;
import app.web.dto.UserEditRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void givenMissingUserFromDatabase_whenEditUserDetails_thenExceptionIsThrown() {

        // Given
        UUID userId = UUID.randomUUID();
        UserEditRequest dto = UserEditRequest.builder().build();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> userService.editUserDetails(userId, dto));
    }

    @Test
    void givenExistingUser_whenEditTheirProfile_thenChangeTheirDetailsAndSaveToDatabase() {

        // Given
        UUID userId = UUID.randomUUID();
        UserEditRequest dto = UserEditRequest.builder()
                .firstName("Dimitar")
                .lastName("Peev")
                .email("peev@abv.bg")
                .profilePicture("www.image.com")
                .build();
        User user = User.builder().build();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // When
        userService.editUserDetails(userId, dto);

        // Then
        assertEquals("Dimitar", user.getFirstName());
        assertEquals("Peev", user.getLastName());
        assertEquals("peev@abv.bg", user.getEmail());
        assertEquals("www.image.com", user.getProfilePicture());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void givenExistingUsername_whenRegister_thenExceptionIsThrown() {

        // Given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Dimitar");
        registerRequest.setPassword("123456");
        registerRequest.setEmail("dimitar@abv.bg");

        when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User()));

        // When & Then
        assertThrows(RuntimeException.class, () -> userService.register(registerRequest));
    }

    @Test
    void givenNonExistingUsername_whenRegister_thenSaveToDatabase() {

        // Given
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername("Dimitar");
        registerRequest.setPassword("123456");
        registerRequest.setEmail("dimitar@abv.bg");

        User user = User.builder()
                .id(UUID.randomUUID())
                .build();

        when(userRepository.findByUsername(registerRequest.getUsername())).thenReturn(Optional.empty());
        when(userRepository.save(any())).thenReturn(user);

        // When
        userService.register(registerRequest);

        // Then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void givenExistingUsersInDatabase_whenGetAllUsers_thenReturnThemAll() {

        // Give
        List<User> userList = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> users = userService.getAllUsers();

        // Then
        assertThat(users).hasSize(2);
    }

    @Test
    void login_ShouldReturnUser_WhenCredentialsAreValid() {

        // Given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test_user2");
        loginRequest.setPassword("password123");

        User user = User.builder()
                .id(UUID.randomUUID())
                .username("test_user1")
                .password("encodedPassword")
                .build();

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);

        // When
        User loggedInUser = userService.login(loginRequest);

        // Then
        assertNotNull(loggedInUser);
        assertEquals(user.getUsername(), loggedInUser.getUsername());
    }

    @Test
    void login_ShouldThrowException_WhenUserNotFound() {

        // Given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("test_user2");
        loginRequest.setPassword("password123");

        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> userService.login(loginRequest));
    }


}
