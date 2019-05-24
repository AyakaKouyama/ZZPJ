package com.zzpj.services.interfaces;

import com.zzpj.entities.Publisher;
import com.zzpj.repositories.PublisherRepository;
import org.springframework.stereotype.Service;

@Service
public interface PublisherService extends BaseService<PublisherRepository, Publisher> {

    Publisher add(Publisher category);

    Publisher findByName(String name);
}
