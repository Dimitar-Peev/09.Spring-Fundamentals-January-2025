package com.paintingscollectors.model.service;

import com.paintingscollectors.model.entity.StyleType;
import com.paintingscollectors.model.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Getter
@Setter
@NoArgsConstructor
public class PaintingServiceModel {

    private String id;

    @NotBlank(message = "")
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;

    @NotBlank(message = "")
    @Size(min = 5, max = 30, message = "Author name must be between 5 and 30 characters!")
    private String author;

    @Size(max = 150)
    @NotBlank(message = "Please enter valid image URL!")
    @URL(message = "Please enter valid image URL!")
    private String imageUrl;

    @NotBlank(message = "You must select a style!")
    private StyleType style;

    private User owner;

    private boolean isFavorite;

    private int votes;

    private boolean canBeDeleted;
}
