package com.example.likebook.service.impl;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodName;
import com.example.likebook.repository.MoodRepository;
import com.example.likebook.service.MoodService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MoodServiceImpl implements MoodService {

    private final MoodRepository moodRepository;

    @Override
    public void initData() {
        if (this.moodRepository.count() == 0) {
            List<Mood> moods = new ArrayList<>();

            for (MoodName value : MoodName.values()) {
                Mood mood = new Mood();
                mood.setName(value);
                mood.setDescription(String.format("Description for %s", value.name()));
                moods.add(mood);
            }

            moodRepository.saveAll(moods);
        }
    }

    @Override
    public Mood findByName(MoodName name) {
        return this.moodRepository.findByName(name).orElse(null);
    }
}
