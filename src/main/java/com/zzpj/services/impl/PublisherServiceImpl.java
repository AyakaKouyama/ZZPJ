package com.zzpj.services.impl;


import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import com.zzpj.repositories.PublisherRepository;
import com.zzpj.services.interfaces.PublisherService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PublisherServiceImpl extends BaseServiceImpl<PublisherRepository,Publisher, PublisherDto> implements PublisherService {

    @Autowired
    public PublisherServiceImpl(PublisherRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public PublisherDto ConvertToDto(Publisher entity) {
        return modelMapper.map(entity, PublisherDto.class);
    }

    @Override
    public Publisher ConvertToEntity(PublisherDto dto) {
        return modelMapper.map(dto, Publisher.class);
    }

    @Override
    public PublisherDto findByName(String name) {
        Publisher publisher = repository
                                    .findByName(name)
                                    .orElseThrow(() -> super.entityNotFoundException("Publisher", name));

        return ConvertToDto(publisher);
    }
}
