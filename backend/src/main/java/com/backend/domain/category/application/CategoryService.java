package com.backend.domain.category.application;

import com.backend.domain.category.dao.CategoryRepository;
import com.backend.domain.category.domain.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;


    public Category create(Category category) {
        return categoryRepository.save(category);
    }
}
