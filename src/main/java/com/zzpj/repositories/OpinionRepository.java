package com.zzpj.repositories;

import com.zzpj.entities.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findAll();

    Opinion save(Opinion opinion);

    List<Opinion> findByBookId(Long bookId);

    List<Opinion> findByUserId(Long userId);

    int getAverageRateForBook(Long bookId);
}
