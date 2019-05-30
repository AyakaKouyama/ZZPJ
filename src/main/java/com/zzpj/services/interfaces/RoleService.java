package com.zzpj.services.interfaces;

import com.zzpj.entities.Role;
import com.zzpj.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleService extends BaseService<RoleRepository, Role> {

    Role add(Role role);

    Role findByName(String name);
}