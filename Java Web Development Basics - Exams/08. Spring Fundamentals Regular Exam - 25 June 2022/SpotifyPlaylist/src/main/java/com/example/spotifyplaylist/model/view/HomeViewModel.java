package com.example.spotifyplaylist.model.view;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeViewModel {

    List<SongViewModel> popList = new ArrayList<>();
    List<SongViewModel> rockList = new ArrayList<>();
    List<SongViewModel> jazzList = new ArrayList<>();
    List<SongViewModel> myPlaylist = new ArrayList<>();
    String totalDurationOfPlaylist;
}
