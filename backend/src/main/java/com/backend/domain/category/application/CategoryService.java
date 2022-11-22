package com.backend.domain.category.application;

import com.backend.domain.category.dao.CategoryRepository;
import com.backend.domain.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
