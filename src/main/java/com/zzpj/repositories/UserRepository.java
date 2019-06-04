package com.zzpj.repositories;

import com.zzpj.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findAll();

    User save(User user);

    //boolean existsByName(String name);
}
