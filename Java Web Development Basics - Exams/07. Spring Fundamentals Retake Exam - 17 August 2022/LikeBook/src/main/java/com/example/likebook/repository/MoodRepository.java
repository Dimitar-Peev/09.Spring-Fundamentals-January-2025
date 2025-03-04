package com.example.likebook.repository;

import com.example.likebook.model.entity.Mood;
import com.example.likebook.model.entity.MoodName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MoodRepository extends JpaRepository<Mood, String> {

    Optional<Mood> findByName(MoodName name);
}
