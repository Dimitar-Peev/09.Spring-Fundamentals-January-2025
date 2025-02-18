package com.paintingscollectors.service.impl;

import com.paintingscollectors.constants.Constants;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleType;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.service.StyleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StyleServiceImpl implements StyleService {

    private StyleRepository styleRepository;

    public void initializeData() {
        if (this.styleRepository.count() == 0) {
            List<Style> styles = new ArrayList<>();

            for (StyleType value : StyleType.values()) {
                Style style = new Style();
                style.setName(value);
                style.setDescription(getPaperInfo(value));
                styles.add(style);
            }

            this.styleRepository.saveAll(styles);
        }
    }

    private String getPaperInfo(StyleType name) {
        return switch (name) {
            case IMPRESSIONISM -> Constants.IMPRESSIONISM;
            case ABSTRACT -> Constants.ABSTRACT;
            case EXPRESSIONISM -> Constants.EXPRESSIONISM;
            case SURREALISM -> Constants.SURREALISM;
            case REALISM -> Constants.REALISM;
        };
    }

    @Override
    public Style findByName(StyleType name) {
        return this.styleRepository.findStyleByName(name).orElse(null);
    }
}
