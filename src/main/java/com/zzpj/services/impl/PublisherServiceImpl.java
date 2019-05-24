package com.zzpj.services.impl;


import com.zzpj.entities.Publisher;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.PublisherRepository;
import com.zzpj.services.interfaces.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublisherServiceImpl extends BaseServiceImpl<PublisherRepository, Publisher> implements PublisherService {

private final PublisherRepository publisherRepository;

        @Autowired
        public PublisherServiceImpl(PublisherRepository publisherRepository) {
            super(publisherRepository);
            this.publisherRepository = publisherRepository;
        }

        @Override
        public Publisher add(Publisher category) {
            if (publisherRepository.existsByName(category.getName())) {
            throw entityAlreadyExistsException(category.getName());
        }

        category.setVersion(0L);
        return publisherRepository.save(category);
        }

    @Override
    public Publisher findByName(String name) {
        return publisherRepository.findByName(name).orElseThrow(() -> entityNotFoundException(name));
        }

private EntityNotFoundException entityNotFoundException(String name) {
        return new EntityNotFoundException("Category with name " + name + " not found.");
        }

private EntityAlreadyExistsException entityAlreadyExistsException(String name) {
        return new EntityAlreadyExistsException("Category with name " + name + " already exists.");
        }
        }