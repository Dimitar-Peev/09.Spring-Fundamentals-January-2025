package com.paintingscollectors.service;

import com.paintingscollectors.model.service.PaintingServiceModel;
import com.paintingscollectors.model.view.HomeViewModel;

import java.util.List;

public interface PaintingService {

    boolean add(PaintingServiceModel paintingServiceModel);

    List<PaintingServiceModel> getMyPaintings(String username);

    void remove(String id, String username);

    List<PaintingServiceModel> getOtherPaintings(String username);

    void addToFavorites(String paintingId, String username);

    void votePainting(String paintingId, String username);

    List<PaintingServiceModel> getUserFavoritePaintings(String username);

    void removeFromFavorites(String paintingId, String username);

    List<PaintingServiceModel> getMostRatedPaintings();

    HomeViewModel getHomeViewModel();
}
