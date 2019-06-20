package com.zzpj.services.interfaces;

import com.zzpj.dtos.PublisherDto;
import com.zzpj.entities.Publisher;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface PublisherService extends BaseService<Publisher, PublisherDto> {

    PublisherDto findByName(String name);

}
