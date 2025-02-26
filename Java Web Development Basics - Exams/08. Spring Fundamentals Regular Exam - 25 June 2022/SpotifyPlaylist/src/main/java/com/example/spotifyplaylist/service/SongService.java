package com.example.spotifyplaylist.service;

import com.example.spotifyplaylist.model.entity.StyleName;
import com.example.spotifyplaylist.model.service.SongServiceModel;
import com.example.spotifyplaylist.model.view.HomeViewModel;

import java.util.List;

public interface SongService {

    boolean add(SongServiceModel songServiceModel, String username);

    List<SongServiceModel> getSongsByStyle(StyleName style);

    List<SongServiceModel> getMyPlaylist();

    HomeViewModel getHomeViewModel();
}
