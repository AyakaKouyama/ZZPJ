package com.zzpj.repositories;

import com.zzpj.entities.Category;
import com.zzpj.entities.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {

    List<PaymentStatus> findAll();

    Optional<PaymentStatus> findById(Long id);

    //boolean existsByName(String name);

    //Optional<PaymentStatus> findByName(String name);
}
