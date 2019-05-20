package com.zzpj.services.interfaces;

import com.zzpj.entities.Category;
import com.zzpj.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService extends BaseService<CategoryRepository, Category> {

    Category add(Category category);

    Category findByName(String name);
}
