package com.example.spring_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// This service is dealing with orders
@Service
public class OrderService {

    private final UserService userService;

    @Autowired
    public OrderService(UserService userService) {
        this.userService = userService;
    }

    public void makeOrder() {

        System.out.println("Sending order to ");
    }
}
