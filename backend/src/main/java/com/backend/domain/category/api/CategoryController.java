package com.backend.domain.category.api;

import com.backend.domain.category.application.CategoryService;
import com.backend.domain.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity create(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.create(category));

    }
}
