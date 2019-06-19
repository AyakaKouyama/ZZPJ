package com.zzpj.repositories;

import com.zzpj.entities.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    List<Publisher> findAll();

    Optional<Publisher> findById(Long id);

    Optional<Publisher> findByName(String name);
}
