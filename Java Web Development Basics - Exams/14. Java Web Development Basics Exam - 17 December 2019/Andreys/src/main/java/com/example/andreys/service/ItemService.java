package com.example.andreys.service;

import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {

    boolean add(ItemServiceModel itemServiceModel);

    List<ItemViewModel> findAllItems();

    ItemViewModel findItemById(String id);

    void delete(String id);
}
