package com.zzpj.services.impl;

import com.zzpj.entities.OrderedBook;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.OrderedBookRepository;
import com.zzpj.services.interfaces.OrderedBookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderedBookServiceImpl extends BaseServiceImpl<OrderedBookRepository, OrderedBook> implements OrderedBookService {

    private final OrderedBookRepository orderedBookRepository;

    public OrderedBookServiceImpl(OrderedBookRepository orderedBookRepository) {
        super(orderedBookRepository);
        this.orderedBookRepository = orderedBookRepository;
    }

    @Override
    public List<OrderedBook> getByBookId(int bookId) {
        return  orderedBookRepository.getByBookId(bookId);
    }


    @Override
    public OrderedBook add(OrderedBook opinion) {
        opinion.setVersion(0L);
        return orderedBookRepository.save(opinion);
    }

    @Override
    public OrderedBook update(OrderedBook opinion) {
        OrderedBook bookFromRepository = orderedBookRepository.findById(opinion.getId())
                .orElseThrow(() -> entityNotFoundException(opinion.getId()));
        opinion.setVersion(bookFromRepository.getVersion());
        return orderedBookRepository.save(opinion);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("OrderedBook with id " + id + " not found");
    }
}
