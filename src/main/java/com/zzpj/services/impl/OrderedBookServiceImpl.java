package com.zzpj.services.impl;

import com.zzpj.dtos.OrderedBookDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.OrderedBook;
import com.zzpj.entities.Purchase;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.BookRepository;
import com.zzpj.repositories.OrderedBookRepository;
import com.zzpj.repositories.PurchaseRepository;
import com.zzpj.services.interfaces.OrderedBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderedBookServiceImpl extends BaseServiceImpl<OrderedBookRepository, OrderedBook, OrderedBookDto> implements OrderedBookService {

    private BookRepository bookRepository;
    private PurchaseRepository purchaseRepository;

    @Autowired
    public OrderedBookServiceImpl(
            OrderedBookRepository repository,
            BookRepository bookRepository,
            PurchaseRepository purchaseRepository,
            ModelMapper modelMapper)
    {
        super(repository, modelMapper);
        this.bookRepository = bookRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public OrderedBookDto ConvertToDto(OrderedBook entity) {
        OrderedBookDto orderedBookDto = modelMapper.map(entity, OrderedBookDto.class);
        return orderedBookDto;
    }

    @Override
    public OrderedBook ConvertToEntity(OrderedBookDto dto) {
        OrderedBook orderedBook = modelMapper.map(dto, OrderedBook.class);
        Book book = bookRepository.findById(dto.getBook().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getBook().getId(), "Book"));
        Purchase purchase = purchaseRepository.findById(dto.getPurchase().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getPurchase().getId(), "Purchase"));
        orderedBook.setPurchase(purchase);
        orderedBook.setBook(book);

        return orderedBook;
    }

    @Override
    public List<OrderedBookDto> findByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> super.entityNotFoundException(bookId, "Book"));

        List<OrderedBook> obs = repository.findByBookId(bookId);

        return obs.stream()
                .map(ob -> ConvertToDto(ob))
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderedBookDto> findByPurchaseId(Long purchaseId) {
        Purchase user = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> super.entityNotFoundException(purchaseId, "Purchase"));

        List<OrderedBook> obs = repository.findByPurchaseId(purchaseId);

        return obs.stream()
                .map(ob -> ConvertToDto(ob))
                .collect(Collectors.toList());
    }
}
