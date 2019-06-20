package com.zzpj.controllers;

import com.zzpj.controllers.BaseController;
import com.zzpj.dtos.CategoryDto;
import com.zzpj.entities.Category;
import com.zzpj.services.interfaces.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, CategoryDto> {

    public CategoryController(CategoryService service) {
        super(service);
    }
}