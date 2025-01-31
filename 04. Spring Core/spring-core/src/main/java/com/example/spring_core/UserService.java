package com.example.spring_core;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

// This class deals with users
@Service
//@Scope("prototype") // creates new instance
public class UserService {

    public String getUserHomeAddress() {

        return "Sofia, Borovo";
    }
}
