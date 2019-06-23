package com.zzpj.services.impl;

import com.zzpj.dtos.ShippingMethodDto;
import com.zzpj.entities.ShippingMethod;
import com.zzpj.repositories.ShippingMethodRepository;
import com.zzpj.services.interfaces.ShippingMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShippingMethodServiceImpl extends BaseServiceImpl<ShippingMethodRepository, ShippingMethod, ShippingMethodDto> implements
        ShippingMethodService {

    ShippingMethodRepository shippingMethodRepository;

    @Autowired
    public ShippingMethodServiceImpl(ShippingMethodRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
        shippingMethodRepository = repository;
    }

    @Override
    public ShippingMethodDto convertToDto(ShippingMethod entity) {
        return modelMapper.map(entity, ShippingMethodDto.class);
    }

    @Override
    public ShippingMethod convertToEntity(ShippingMethodDto dto) {
        return modelMapper.map(dto, ShippingMethod.class);
    }

    @Override
    public List<ShippingMethodDto> sortField(String field) {
        List<ShippingMethod> shippingMethods = shippingMethodRepository.findAll();

        switch (field) {
            case ("price"): {
                List<ShippingMethod> sorted = shippingMethods.stream()
                        .sorted(Comparator.comparing(ShippingMethod::getPrice))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case ("name"): {
                List<ShippingMethod> sorted = shippingMethods.stream()
                        .sorted(Comparator.comparing(ShippingMethod::getName))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            default: {
                return shippingMethods.stream().map(this::convertToDto).collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<ShippingMethodDto> filterField(String field, String param) {
        List<ShippingMethodDto> dtos = null;

        switch (field) {
            case "priceLowerThan":
                dtos = priceFilter(param);
                break;
            case "phraseInName":
                dtos = phraseInNameFilter(param);
                break;
            default:
                List<ShippingMethod> shippingMethods = shippingMethodRepository.findAll();

                List<ShippingMethod> sorted = shippingMethods.stream().collect(Collectors.toList());
                dtos = sorted.stream().map(this::convertToDto).collect(Collectors.toList());
        }

        return dtos;
    }

    List<ShippingMethodDto> priceFilter(String price) {
        List<ShippingMethod> shippingMethods = shippingMethodRepository.findAll();
        List<ShippingMethod> sorted = shippingMethods.stream()
                .filter(b -> b.getPrice().compareTo(BigDecimal.valueOf(Double.valueOf(price))) <= 0)
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    List<ShippingMethodDto> phraseInNameFilter(String phrase) {
        List<ShippingMethod> shippingMethods = shippingMethodRepository.findAll();
        List<ShippingMethod> sorted = shippingMethods.stream()
                .filter(b -> b.getName().contains(phrase))
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
