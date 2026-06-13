package com.server.app.mapper;

import com.server.app.dto.category.CategoryRequest;
import com.server.app.dto.category.CategoryResponse;
import com.server.app.entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryRequest dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity.setType(dto.getType());

        if (dto.getParentCategoryId() != null) {
            Category parent = new Category();
            parent.setId(dto.getParentCategoryId());
            entity.setParentCategory(parent);
        }
        return entity;
    }

    public CategoryResponse toDto(Category entity) {
        CategoryResponse dto = new CategoryResponse();
        dto.setName(entity.getName());
        dto.setType(entity.getType());
        return dto;
    }
}
