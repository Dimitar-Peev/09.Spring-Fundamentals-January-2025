package com.example.spring_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    private final UserService userService;

    @Autowired
    public PaymentService(UserService userService) {
        this.userService = userService;
    }
}
