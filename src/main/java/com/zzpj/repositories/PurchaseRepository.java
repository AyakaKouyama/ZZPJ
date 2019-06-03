package com.zzpj.repositories;

import com.zzpj.entities.Purchase;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends CrudRepository<Purchase, Long> {

    List<Purchase> findAll();

    Purchase save(Purchase purchase);

    Optional<Purchase> findById(Long id);
}
