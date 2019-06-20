package com.zzpj.services.interfaces;

import com.zzpj.dtos.CategoryDto;
import com.zzpj.entities.Category;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.services.interfaces.BaseService;
import org.springframework.stereotype.Service;

public interface CategoryService extends BaseService<Category, CategoryDto> {

}
