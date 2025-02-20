package com.paintingscollectors.model.view;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeViewModel {

    List<PaintingViewModel> myPaintings = new ArrayList<>();

    List<PaintingViewModel> otherPaintings = new ArrayList<>();

    List<PaintingViewModel> userFavoritePaintings = new ArrayList<>();

    List<PaintingViewModel> mostRatedPaintings = new ArrayList<>();
}
