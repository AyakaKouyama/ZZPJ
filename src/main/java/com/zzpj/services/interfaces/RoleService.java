package com.zzpj.services.interfaces;

import com.zzpj.dtos.RoleDto;
import com.zzpj.entities.Role;
import com.zzpj.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends BaseService<Role, RoleDto> {

}