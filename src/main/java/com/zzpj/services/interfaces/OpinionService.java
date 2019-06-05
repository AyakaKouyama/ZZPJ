package com.zzpj.services.interfaces;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Opinion;
import com.zzpj.repositories.OpinionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OpinionService extends BaseService<Opinion, OpinionDto>{
    
}
