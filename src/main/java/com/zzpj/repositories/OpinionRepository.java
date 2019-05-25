package com.zzpj.repositories;

import com.zzpj.entities.Opinion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends CrudRepository<Opinion, Long> {
    List<Opinion> findAll();

    Opinion save(Opinion opinion);

    List<Opinion> getByBookId(int bookId);

    //List<Opinion> getByUserId(int userId);
}
