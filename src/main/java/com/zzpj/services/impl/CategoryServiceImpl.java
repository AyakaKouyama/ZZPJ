package com.zzpj.services.impl;

import com.zzpj.entities.Category;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryRepository, Category> implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        super(categoryRepository);
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category add(Category category) {
        if (categoryRepository.existsByName(category.getName())) {
            throw entityAlreadyExistsException(category.getName());
        }

        category.setVersion(0L);
        return categoryRepository.save(category);
    }

    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).orElseThrow(() -> entityNotFoundException(name));
    }

    private EntityNotFoundException entityNotFoundException(String name) {
        return new EntityNotFoundException("Category with name " + name + " not found.");
    }

    private EntityAlreadyExistsException entityAlreadyExistsException(String name) {
        return new EntityAlreadyExistsException("Category with name " + name + " already exists.");
    }
}
