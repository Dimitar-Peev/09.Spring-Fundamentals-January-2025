package com.example.spring_core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Application {

//    @Autowired
//    ApplicationContext applicationContext;

    public static void main(String[] args) {

        ApplicationContext applicationContext = SpringApplication.run(Application.class, args);

        UserService userService = applicationContext.getBean(UserService.class);

        PaymentService paymentService = applicationContext.getBean(PaymentService.class);
        OrderService orderService = applicationContext.getBean(OrderService.class);

        System.out.println(userService.getUserHomeAddress());

    }

}
