package com.paintingscollectors.init;

import com.paintingscollectors.service.StyleService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitialize implements CommandLineRunner {

    private StyleService styleService;

    @Override
    public void run(String... args) throws Exception {

        this.styleService.initializeData();
    }
}