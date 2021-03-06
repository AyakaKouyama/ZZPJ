package com.zzpj.services.impl;

import com.zzpj.entities.BaseEntity;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.services.interfaces.BaseService;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.stream.Collectors;

public class BaseServiceImpl
        <TRepository extends JpaRepository<TModel, Long>, TModel extends BaseEntity, UDto>
        implements BaseService<TModel, UDto> {

    protected TRepository repository;
    protected ModelMapper modelMapper;

    public BaseServiceImpl(
            TRepository repository,
            ModelMapper modelMapper
    ) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    protected EntityNotFoundException entityNotFoundException(Long id, String name) {
        return new EntityNotFoundException(name + " with id " + id + " not found.");
    }

    protected EntityNotFoundException entityNotFoundException(String entity, String value) {
        return new EntityNotFoundException(entity + " with " + value + " doesn't exists.");
    }

    @Override
    public List<UDto> findAll() {
        List<TModel> modelList = repository.findAll();
        return modelList
                .stream()
                .map(entity ->
                        convertToDto(entity))
                .collect(Collectors.toList());
    }

    @Override
    public UDto add(UDto dto) {
        TModel savedEntity = repository.save(convertToEntity(dto));
        savedEntity.setVersion(0L);
        return convertToDto(savedEntity);
    }

  /*  @Override
    public UDto update(Long id, UDto dto) {
        TModel modelFromRepository = repository
                .findById(id)
                .orElseThrow(() -> entityNotFoundException(id, "Entity"));
        TModel editedModel = convertToEntity(dto);
        editedModel.setVersion(modelFromRepository.getVersion());
        TModel savedEntity = repository.save(editedModel);
        return convertToDto(savedEntity);
    } */

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw entityNotFoundException(id, "Entity");
        }
        repository.deleteById(id);
    }

    @Override
    public UDto findById(Long id) {
        TModel model = repository
                .findById(id)
                .orElseThrow(() -> entityNotFoundException(id, "Entity"));
        return convertToDto(model);
    }

    @Override
    public TModel convertToEntity(UDto dto) {
        throw new UnsupportedOperationException("Method must be implemented in super class");
    }

    @Override
    public UDto convertToDto(TModel entity) {
        throw new UnsupportedOperationException("Method must be implemented in super class");
    }
}
