package com.example.andreys.service.impl;

import com.example.andreys.model.entity.Category;
import com.example.andreys.model.entity.Item;
import com.example.andreys.model.service.ItemServiceModel;
import com.example.andreys.model.view.ItemViewModel;
import com.example.andreys.repository.ItemRepository;
import com.example.andreys.service.CategoryService;
import com.example.andreys.service.ItemService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Controller
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final CategoryService categoryService;

    @Override
    public boolean add(ItemServiceModel itemServiceModel) {

        Category category = this.categoryService.findByCategoryName(itemServiceModel.getCategory().getName());

        if (category != null) {
            Item item = this.modelMapper.map(itemServiceModel, Item.class);
            item.setCategory(category);
            this.itemRepository.save(item);
            log.info("Successfully added item.");
            return true;
        } else {
            log.info("Failed to add item.");
            return false;
        }

    }

    @Override
    public List<ItemViewModel> findAllItems() {
        return this.itemRepository.findAll()
                .stream()
                .map(this::newItemViewModel)
                .toList();
    }

    @Override
    public ItemViewModel findItemById(String id) {
        return this.itemRepository.findById(id)
                .map(this::newItemViewModel)
                .orElse(null);
    }

    private ItemViewModel newItemViewModel(Item item) {
        ItemViewModel itemViewModel = this.modelMapper.map(item, ItemViewModel.class);
        itemViewModel.setImageUrl(String.format("/img/%s-%s.jpg", item.getGender(), item.getCategory().getName()));
        return itemViewModel;
    }

    @Override
    public void delete(String id) {
        this.itemRepository.deleteById(id);
    }

}
