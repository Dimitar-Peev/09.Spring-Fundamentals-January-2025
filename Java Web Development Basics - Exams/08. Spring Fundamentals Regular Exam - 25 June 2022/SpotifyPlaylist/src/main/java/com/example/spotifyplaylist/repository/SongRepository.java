package com.example.spotifyplaylist.repository;

import com.example.spotifyplaylist.model.entity.Song;
import com.example.spotifyplaylist.model.entity.StyleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, String> {

    List<Song> findSongsByStyleName(StyleName styleName);

    @Query(nativeQuery = true, value = "SELECT s.* FROM songs s JOIN user_songs us ON s.id = us.song_id WHERE us.user_id = :userId;")
    List<Song> findFavoriteSongs(String userId);
}
