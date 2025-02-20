package com.paintingscollectors.model.view;

import com.paintingscollectors.model.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaintingViewModel {

    private String id;
    private String name;
    private String author;
    private String style;
    private String imageUrl;
    private User owner;
    private boolean isFavorite;
    private int votes;
    private boolean canBeDeleted;
}
