package com.powerup.square.infraestructure.out.jpa.mapper;

import com.powerup.square.domain.model.Category;
import com.powerup.square.infraestructure.out.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Optional;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryMapper {
    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
}
