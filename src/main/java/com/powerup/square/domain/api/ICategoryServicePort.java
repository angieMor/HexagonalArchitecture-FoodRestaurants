package com.powerup.square.domain.api;

import com.powerup.square.domain.model.Category;

import java.util.List;

public interface ICategoryServicePort {

    void saveCategory(Category category);
    List<Category> getAllCategory();
    Category getCategory(Long id);
    boolean existByName(String name);
}
