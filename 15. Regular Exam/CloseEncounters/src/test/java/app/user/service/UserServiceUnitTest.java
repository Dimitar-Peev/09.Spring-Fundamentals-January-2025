package app.user.service;

import app.user.model.User;
import app.user.repository.UserRepository;
import app.web.dto.RegisterRequest;
import app.web.dto.UserEditRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

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

        when(userRepository.findByUsername(any())).thenReturn(Optional.of(new User()));

        // When & Then
        assertThrows(RuntimeException.class, () -> userService.register(registerRequest));
        verify(userRepository, never()).save(any());
    }

}
