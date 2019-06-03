package com.zzpj.services.interfaces;

import com.zzpj.entities.Opinion;
import com.zzpj.repositories.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpinionService extends BaseService<OpinionRepository, Opinion>{

    //List<Opinion> getByBookId(int bookId);

    //List<Opinion> getByUserId(int userId);

    Opinion add(Opinion opinion);

    Opinion update(Opinion opinion);
}
