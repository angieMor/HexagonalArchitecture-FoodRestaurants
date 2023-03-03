package com.powerup.square.domain.usecase;

import com.powerup.square.domain.api.ICategoryServicePort;
import com.powerup.square.domain.model.Category;
import com.powerup.square.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {
    private final ICategoryPersistencePort categoryPersistencePort;
    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }
    @Override
    public void saveCategory(Category category) {
        categoryPersistencePort.saveCategory(category);
    }
    @Override
    public List<Category> getAllCategory() {
        return categoryPersistencePort.getAllCategory();
    }
    @Override
    public Category getCategory(Long id) {
        return categoryPersistencePort.getCategory(id);
    }
    @Override
    public boolean existByName(String name) {
        return categoryPersistencePort.existByName(name);
    }
}

