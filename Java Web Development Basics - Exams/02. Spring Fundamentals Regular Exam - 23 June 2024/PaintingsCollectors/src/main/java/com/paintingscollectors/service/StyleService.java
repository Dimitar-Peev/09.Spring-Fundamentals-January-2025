package com.paintingscollectors.service;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleType;

public interface StyleService {

    void initializeData();

    Style findByName(StyleType name);
}
