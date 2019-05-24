package com.zzpj.repositories;

import com.zzpj.entities.Publisher;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {
    List<Publisher> findAll();

    Optional<Publisher> findById(Long id);

    boolean existsByName(String name);

    Optional<Publisher> findByName(String name);
}
