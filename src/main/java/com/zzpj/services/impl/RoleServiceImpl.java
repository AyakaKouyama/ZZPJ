package com.zzpj.services.impl;

import com.zzpj.dtos.RoleDto;
import com.zzpj.entities.Role;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.services.interfaces.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository,Role, RoleDto> implements RoleService {

    @Autowired
    public RoleServiceImpl(RoleRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
    }

    @Override
    public RoleDto ConvertToDto(Role entity) {
        return modelMapper.map(entity, RoleDto.class);
    }

    @Override
    public Role ConvertToEntity(RoleDto dto) {
        return modelMapper.map(dto, Role.class);
    }
}
