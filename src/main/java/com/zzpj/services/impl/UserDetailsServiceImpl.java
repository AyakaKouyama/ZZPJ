package com.zzpj.services.impl;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.services.interfaces.UserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetailsRepository, UserDetails, UserDetailsDto> implements UserDetailsService {

    public UserDetailsServiceImpl(UserDetailsRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public UserDetailsDto ConvertToDto(UserDetails entity) {
        return modelMapper.map(entity, UserDetailsDto.class);
    }

    @Override
    public UserDetails ConvertToEntity(UserDetailsDto dto) {
        return modelMapper.map(dto, UserDetails.class);
    }
}