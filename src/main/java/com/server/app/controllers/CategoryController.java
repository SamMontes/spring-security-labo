package com.server.app.controllers;

import com.server.app.dto.category.CategoryResponse;
import com.server.app.services.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/finanzas")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/categorias")
    public ResponseEntity<List<CategoryResponse>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}
