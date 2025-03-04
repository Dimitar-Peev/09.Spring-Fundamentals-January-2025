package com.example.likebook.init;

import com.example.likebook.service.MoodService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitialize implements CommandLineRunner {

    private final MoodService moodService;

    @Override
    public void run(String... args) {

        this.moodService.initData();
    }
}
