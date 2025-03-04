package com.example.likebook.service;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodName;

public interface MoodService {

    void initData();

    Mood findByName(MoodName name);
}
