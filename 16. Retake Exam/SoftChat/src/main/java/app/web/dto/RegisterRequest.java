package app.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {

    @NotBlank(message = "")
    @Size(min = 4, max = 20, message = "Username must be at least 4 characters")
    private String username;

    @NotBlank(message = "")
    @Size(min = 4, max = 20, message = "Password must be at least 4 characters")
    private String password;

    @NotBlank(message = "")
    @Email(message = "Enter valid email address")
    private String email;

}