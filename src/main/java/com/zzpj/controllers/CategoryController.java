package com.zzpj.controllers;

import com.zzpj.controllers.BaseController;
import com.zzpj.dtos.CategoryDto;
import com.zzpj.entities.Category;
import com.zzpj.services.interfaces.CategoryService;
import com.zzpj.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController extends BaseController<Category, CategoryDto> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.GET)
    ResponseEntity<List<CategoryDto>> findAll() {
        return super.findAll();
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity add(@Valid @RequestBody CategoryDto dto) {
        CategoryDto created = service.add(dto);
        return new ResponseEntity<CategoryDto>(created, HttpStatus.CREATED);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    ResponseEntity<CategoryDto> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    ResponseEntity update(@PathVariable Long id, @RequestBody CategoryDto dto) {
        return super.update(id, dto);
    }


    @Override
    @PreAuthorize("hasAuthority('" + Constants.ADMINISTRATOR +"')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable Long id){
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }

}