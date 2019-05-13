package com.zzpj.services.interfaces;

import java.util.List;
import java.util.Optional;

public interface BaseService<TRepository, TModel> {
    // base service define base CRUD operation which won't need to be implemented by each service

    List<TModel> findAll();
    TModel add(TModel model);
    TModel save(Long id, TModel model);
    void deleteById(Long id);
    Optional<TModel> findById(Long id);
}
