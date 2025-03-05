package com.bonappetit.model.view;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class HomeViewModel {

    List<RecipeViewModel> favourites = new ArrayList<>();
    List<RecipeViewModel> cocktails = new ArrayList<>();
    List<RecipeViewModel> mainDishes = new ArrayList<>();
    List<RecipeViewModel> desserts = new ArrayList<>();
}
