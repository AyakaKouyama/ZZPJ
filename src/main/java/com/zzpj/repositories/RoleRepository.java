package com.zzpj.repositories;

import com.zzpj.entities.Category;
import com.zzpj.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{
    List<Role> findAll();

    Optional<Role> findById(Long id);

    boolean existsByName(String name);

    Optional<Role> findByName(String name);

}
