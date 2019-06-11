package com.zzpj.services.interfaces;

import java.util.List;

public interface BaseService<TModel, UDto> {

    List<UDto> findAll();

    UDto add(UDto dto);

    UDto update(Long id, UDto dto);

    void deleteById(Long id);

    UDto findById(Long id);

    TModel ConvertToEntity(UDto dto);

    UDto ConvertToDto(TModel entity);
}
