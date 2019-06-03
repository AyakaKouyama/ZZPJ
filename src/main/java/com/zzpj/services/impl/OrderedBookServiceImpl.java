package com.zzpj.services.impl;

import com.zzpj.entities.OrderedBook;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.OrderedBookRepository;
import com.zzpj.services.interfaces.OrderedBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedBookServiceImpl extends BaseServiceImpl<OrderedBookRepository, OrderedBook> implements OrderedBookService {


    public OrderedBookServiceImpl(OrderedBookRepository orderedBookRepository) {
        super(orderedBookRepository);
    }

    @Override
    public List<OrderedBook> getByBookId(int bookId) {
        return  repository.getByBookId(bookId);
    }


    @Override
    public OrderedBook add(OrderedBook opinion) {
        opinion.setVersion(0L);
        return repository.save(opinion);
    }

    @Override
    public OrderedBook update(OrderedBook opinion) {
        OrderedBook bookFromRepository = repository.findById(opinion.getId())
                .orElseThrow(() -> entityNotFoundException(opinion.getId()));
        opinion.setVersion(bookFromRepository.getVersion());
        return repository.save(opinion);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("OrderedBook with id " + id + " not found");
    }
}
