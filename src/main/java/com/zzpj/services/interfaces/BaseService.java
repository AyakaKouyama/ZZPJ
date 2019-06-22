package com.zzpj.services.interfaces;

import java.util.List;

public interface BaseService<TModel, UDto> {

    List<UDto> findAll();

    UDto add(UDto dto);

    void deleteById(Long id);

    UDto findById(Long id);

    TModel convertToEntity(UDto dto);

    UDto convertToDto(TModel entity);
}
