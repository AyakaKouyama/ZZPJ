package com.zzpj.services.impl;

import com.zzpj.entities.Opinion;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.OpinionRepository;
import com.zzpj.services.interfaces.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl extends BaseServiceImpl<OpinionRepository, Opinion> implements OpinionService {

    private final OpinionRepository opinionRepository;

    @Autowired
    public OpinionServiceImpl(OpinionRepository opinionRepository){
        super(opinionRepository);
        this.opinionRepository = opinionRepository;
    }
    @Override
    public List<Opinion> getByBookId(int bookId) {
        return  opinionRepository.getByBookId(bookId);
    }


    @Override
    public Opinion add(Opinion opinion) {
        opinion.setVersion(0L);
        return opinionRepository.save(opinion);
    }

    @Override
    public Opinion update(Opinion opinion) {
        Opinion bookFromRepository = opinionRepository.findById(opinion.getId())
                .orElseThrow(() -> entityNotFoundException(opinion.getId()));
        opinion.setVersion(bookFromRepository.getVersion());
        return opinionRepository.save(opinion);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Opinion with id " + id + " not found");
    }
}