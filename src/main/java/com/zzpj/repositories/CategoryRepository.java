package com.zzpj.repositories;

import com.zzpj.entities.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    List<Category> findAll();

    Optional<Category> findById(Long id);

    boolean existsByName(String name);

    Optional<Category> findByName(String name);
}
