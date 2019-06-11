package com.zzpj.services.impl;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.Category;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.CategoryRepository;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethodRepository, ShippingMethod, ShippingMethodDto> implements ShippingMethodService {

    public ShippingMethodServiceImpl(ShippingMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }
    @Override
    public ShippingMethodDto ConvertToDto(ShippingMethod entity) {
        return modelMapper.map(entity, ShippingMethodDto.class);
    }

    @Override
    public ShippingMethod ConvertToEntity(ShippingMethodDto dto) {
        return modelMapper.map(dto, ShippingMethod.class);
    }
}
