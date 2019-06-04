package com.zzpj.services.impl;

import com.zzpj.entities.BaseEntity;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.services.interfaces.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BaseServiceImpl
        <TRepository extends CrudRepository<TModel, Long>, TModel extends BaseEntity, UDto>
        implements BaseService<TModel, UDto>
{

    protected TRepository repository;
    protected  ModelMapper modelMapper;

    public BaseServiceImpl(
            TRepository repository,
            ModelMapper modelMapper
    ){
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    private EntityNotFoundException entityNotFoundException(Long id){
        return new EntityNotFoundException("Entity with id " + id  + " not found.");
    }

    @Override
    public List<UDto> findAll() {
        List<TModel> modelList = (List<TModel>) repository.findAll();
        return modelList
                .stream()
                .map(entity ->
                        ConvertToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public UDto add(UDto dto) {
        TModel savedEntity = repository.save(ConvertToEntity(dto));
        savedEntity.setVersion(0L);
        return ConvertToDto(savedEntity);
    }

    @Override
    public UDto update(Long id, UDto dto) {
        TModel modelFromReposiotry = repository
                        .findById(id)
                        .orElseThrow(() -> entityNotFoundException(id));
        TModel editedModel = ConvertToEntity(dto);
        editedModel.setVersion(modelFromReposiotry.getVersion());
        editedModel.setId(id);
        TModel savedEntity = repository.save(editedModel);
        return ConvertToDto(savedEntity);
    }

    @Override
    public void deleteById(Long id) {
        if(!repository.existsById(id)){
            throw entityNotFoundException(id);
        }
        repository.deleteById(id);
    }

    @Override
    public UDto findById(Long id) {
        TModel model = repository
                .findById(id)
                .orElseThrow(() -> entityNotFoundException(id));
        return ConvertToDto(model);
    }

    @Override
    public TModel ConvertToEntity(UDto dto) {
        throw new UnsupportedOperationException("Method must be implemented in super class");
    }

    @Override
    public UDto ConvertToDto(TModel entity) {
        throw new UnsupportedOperationException("Method must be implemented in super class");
    }


}
