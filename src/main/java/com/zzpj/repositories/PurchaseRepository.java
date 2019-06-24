package com.zzpj.repositories;

import com.zzpj.entities.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    List<Purchase> findAll();

    Purchase save(Purchase purchase);

    Optional<Purchase> findById(Long id);

    Purchase saveAndFlush(Purchase purchase);
}
