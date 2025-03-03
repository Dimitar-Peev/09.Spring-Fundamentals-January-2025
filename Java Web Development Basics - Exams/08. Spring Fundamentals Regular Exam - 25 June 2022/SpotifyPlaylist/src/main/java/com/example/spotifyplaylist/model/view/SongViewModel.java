package com.example.spotifyplaylist.model.view;

import lombok.Data;

@Data
public class SongViewModel {

    private String id;
    private String performer;
    private String title;
    private int duration;
    private String durationInMinutes;

    public String getDurationInMinutes() {
        return String.format("%d:%02d", duration / 60, duration % 60);
    }
}
