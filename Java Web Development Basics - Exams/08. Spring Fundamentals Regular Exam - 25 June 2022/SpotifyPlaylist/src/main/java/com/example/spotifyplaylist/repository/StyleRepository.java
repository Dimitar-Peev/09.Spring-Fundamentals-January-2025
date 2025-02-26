package com.example.spotifyplaylist.repository;

import com.example.spotifyplaylist.model.entity.Style;
import com.example.spotifyplaylist.model.entity.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StyleRepository extends JpaRepository<Style, String> {

    Optional<Style> findByName(StyleName name);
}
