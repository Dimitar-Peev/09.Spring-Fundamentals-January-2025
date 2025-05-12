package app.web.mapper;

import app.user.model.User;
import app.web.dto.UserEditRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DtoMapperUTest {

    @Test
    void whenMappingUserToUserEditRequest_ReturnsCorrectAnswers(){

        // Given
        User user = User.builder()
                .firstName("Dimitar")
                .lastName("Peev")
                .email("peev@abv.bg")
                .password("123456")
                .profilePicture("www.image.com")
                .build();

        // When
        UserEditRequest resultDto = DtoMapper.mapUserToUserEditRequest(user);

        // Then
        assertNotNull(resultDto);
        assertEquals(user.getFirstName(), resultDto.getFirstName());
        assertEquals(user.getLastName(), resultDto.getLastName());
        assertEquals(user.getEmail(), resultDto.getEmail());
        assertEquals(user.getPassword(), resultDto.getPassword());
        assertEquals(user.getProfilePicture(), resultDto.getProfilePicture());

    }
}
