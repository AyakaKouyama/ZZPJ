package com.zzpj.services.interfaces;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Opinion;
import com.zzpj.repositories.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OpinionService extends BaseService<Opinion, OpinionDto>{

    List<OpinionDto> findByUserId(Long userId);

    List<OpinionDto> findByBookId(Long bookId);

    int getAverageRateForBook(Long bookId);
}
