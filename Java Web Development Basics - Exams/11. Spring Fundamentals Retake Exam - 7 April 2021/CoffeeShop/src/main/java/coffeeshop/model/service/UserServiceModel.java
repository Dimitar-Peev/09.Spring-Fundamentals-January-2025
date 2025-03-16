package coffeeshop.model.service;

import lombok.Data;

@Data
public class UserServiceModel {

    private String id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
}
