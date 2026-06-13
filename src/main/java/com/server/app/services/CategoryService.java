package com.server.app.services;

import com.server.app.dto.category.CategoryResponse;
import com.server.app.mapper.CategoryMapper;
import com.server.app.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
