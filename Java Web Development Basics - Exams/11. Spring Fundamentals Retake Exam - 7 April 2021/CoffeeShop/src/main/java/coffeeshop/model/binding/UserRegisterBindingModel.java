package coffeeshop.model.binding;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @NotNull
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters.")
    private String username;

    private String firstName;

    @NotNull
    @Size(min = 5, max = 20, message = "Last name length must be between 5 and 20 characters.")
    private String lastName;

    @NotNull
    @Email(message = "Enter valid email address")
    private String email;

    @NotNull
    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String password;

    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String confirmPassword;
}
