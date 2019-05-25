package com.zzpj.repositories;


import com.zzpj.entities.ShippingMethod;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShippingMethodRepository extends CrudRepository<ShippingMethod, Long> {

    List<ShippingMethod> findAll();

    Optional<ShippingMethod> findById(Long id);

    boolean existsByName(String name);

    Optional<ShippingMethod> findByName(String name);

}
