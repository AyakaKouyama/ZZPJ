package com.zzpj.services.interfaces;

import java.util.List;

/**
 * base service define base CRUD operation which won't need to be implemented by each service
 *
 * @param <TRepository>
 * @param <TModel>
 */
public interface BaseService<TRepository, TModel> {

    List<TModel> findAll();

    TModel add(TModel model);

    TModel update(TModel model);

    void deleteById(Long id);

    TModel findById(Long id);
}
