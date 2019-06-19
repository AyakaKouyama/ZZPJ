package com.zzpj.repositories;

import com.zzpj.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User save(User user);

    Optional<User> findByLogin(String login);
    //boolean existsByName(String name);
}
