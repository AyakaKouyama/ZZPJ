package com.zzpj.services.impl;

import com.zzpj.dtos.CategoryDto;
import com.zzpj.entities.Category;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServiceImpl<CategoryRepository, Category, CategoryDto> implements CategoryService {

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        super(categoryRepository, modelMapper);
    }

    @Override
    public CategoryDto convertToDto(Category entity) {
        return modelMapper.map(entity, CategoryDto.class);
    }

    @Override
    public Category convertToEntity(CategoryDto dto) {
        return modelMapper.map(dto, Category.class);
    }
}
