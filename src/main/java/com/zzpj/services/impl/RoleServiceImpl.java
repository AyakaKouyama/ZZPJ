package com.zzpj.services.impl;

import com.zzpj.entities.Role;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.services.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleRepository, Role> implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        super(roleRepository);
        this.roleRepository = roleRepository;
    }

    @Override
    public Role add(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw entityAlreadyExistsException(role.getName());
        }

        role.setVersion(0L);
        return roleRepository.save(role);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> entityNotFoundException(name));
    }

    private EntityNotFoundException entityNotFoundException(String name) {
        return new EntityNotFoundException("Role with name " + name + " not found.");
    }

    private EntityAlreadyExistsException entityAlreadyExistsException(String name) {
        return new EntityAlreadyExistsException("Role with name " + name + " already exists.");
    }
}
