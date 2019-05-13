package com.zzpj.services.impl;

import com.zzpj.services.interfaces.BaseService;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<TRepository extends CrudRepository<TModel, Long>, TModel> implements BaseService<TRepository, TModel> {

    protected TRepository repository;

    public BaseServiceImpl(TRepository repository){
        this.repository = repository;
    }

    @Override
    public List<TModel> findAll() {
        return (List<TModel>) repository.findAll();
    }

    @Override
    public TModel add(TModel model) {
        return repository.save(model);
    }

    @Override
    public TModel save(Long id, TModel model) {
        return repository.save(model);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<TModel> findById(Long id) {
        return repository.findById(id);
    }

}
