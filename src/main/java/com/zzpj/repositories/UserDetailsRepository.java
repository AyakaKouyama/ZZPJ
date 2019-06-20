package com.zzpj.repositories;

import com.zzpj.entities.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    List<UserDetails> findAll();

    Optional<UserDetails> findById(Long id);

    //boolean existsByName(String name);

    //Optional<UserDetails> findByName(String name);

}