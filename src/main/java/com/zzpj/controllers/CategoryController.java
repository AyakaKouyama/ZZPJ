package com.zzpj.controllers;

import com.zzpj.dtos.CategoryDto;
import com.zzpj.entities.Category;
import com.zzpj.services.interfaces.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryController(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categoryDtos = categoryService.findAll()
                .stream()
                .map(book ->
                        modelMapper.map(book, CategoryDto.class)
                )
                .collect(
                        Collectors.toList());
        return ResponseEntity.ok(categoryDtos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return ResponseEntity.ok(categoryDto);
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity addCategory(@Valid @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        categoryService.add(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        category.setId(id);
        categoryService.update(category);
        return ResponseEntity.ok().build();
    }
}
