package com.example.spotifyplaylist.service.impl;

import com.example.spotifyplaylist.model.entity.Song;
import com.example.spotifyplaylist.model.entity.Style;
import com.example.spotifyplaylist.model.entity.StyleName;
import com.example.spotifyplaylist.model.entity.User;
import com.example.spotifyplaylist.model.service.SongServiceModel;
import com.example.spotifyplaylist.model.view.HomeViewModel;
import com.example.spotifyplaylist.model.view.SongViewModel;
import com.example.spotifyplaylist.repository.SongRepository;
import com.example.spotifyplaylist.service.SongService;
import com.example.spotifyplaylist.service.StyleService;
import com.example.spotifyplaylist.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository songRepository;
    private final ModelMapper modelMapper;
    private final StyleService styleService;
    private final UserService userService;
    private final HttpSession httpSession;

    @Override
    public boolean add(SongServiceModel songServiceModel, String username) {
        Style style = this.styleService.findByName(songServiceModel.getStyle());
        User user = this.userService.findUserByUsername(username);

        if (style != null && user != null) {
            Song song = this.modelMapper.map(songServiceModel, Song.class);
            song.setStyle(style);
            this.songRepository.save(song);
            log.info("Successfully added song.");
            return true;
        } else {
            log.info("Failed to add song.");
            return false;
        }
    }

    @Override
    public List<SongServiceModel> getSongsByStyle(StyleName style) {

        return songRepository.findSongsByStyleName(style)
                .stream()
                .map(song ->  this.modelMapper.map(song, SongServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SongServiceModel> getMyPlaylist() {

        String userId = httpSession.getAttribute("userId").toString();

        return songRepository.findFavoriteSongs(userId).stream()
                .map(song -> this.modelMapper.map(song, SongServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public HomeViewModel getHomeViewModel() {
        HomeViewModel homeViewModel = new HomeViewModel();

        List<SongViewModel> popList = getSongsByStyle(StyleName.POP).stream()
                .map(s -> modelMapper.map(s, SongViewModel.class))
                .collect(Collectors.toList());

        List<SongViewModel> rockList = getSongsByStyle(StyleName.ROCK)
                .stream()
                .map(s -> modelMapper.map(s, SongViewModel.class))
                .collect(Collectors.toList());

        List<SongViewModel> jazzlist = getSongsByStyle(StyleName.JAZZ)
                .stream()
                .map(s -> modelMapper.map(s, SongViewModel.class))
                .collect(Collectors.toList());

        List<SongViewModel> myPlaylist = getMyPlaylist()
                .stream()
                .map(s -> modelMapper.map(s, SongViewModel.class))
                .collect(Collectors.toList());

        int totalTimeSec = myPlaylist.stream().mapToInt(SongViewModel::getDuration).sum();

        homeViewModel.setPopList(popList);
        homeViewModel.setRockList(rockList);
        homeViewModel.setJazzList(jazzlist);
        homeViewModel.setMyPlaylist(myPlaylist);
        homeViewModel.setTotalDurationOfPlaylist(getResult(totalTimeSec));

        return homeViewModel;
    }

    private static String getResult(int totalTimeSec) {
        int hours = totalTimeSec / 3600;
        int minutes = (totalTimeSec % 3600) / 60;
        int seconds = totalTimeSec % 60;

        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
