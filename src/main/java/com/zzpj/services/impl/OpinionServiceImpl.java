package com.zzpj.services.impl;

import com.zzpj.dtos.OpinionDto;
import com.zzpj.entities.Book;
import com.zzpj.entities.Opinion;
import com.zzpj.entities.User;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.BookRepository;
import com.zzpj.repositories.OpinionRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.interfaces.BookService;
import com.zzpj.services.interfaces.OpinionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpinionServiceImpl extends BaseServiceImpl<OpinionRepository, Opinion, OpinionDto> implements OpinionService {

    private UserRepository userRepository;
    private BookRepository bookRepository;

    @Autowired
    public OpinionServiceImpl(OpinionRepository opinionRepository, UserRepository userRepository, BookRepository bookRepository, ModelMapper modelMapper){
        super(opinionRepository, modelMapper);
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public OpinionDto ConvertToDto(Opinion entity) {
        return modelMapper.map(entity, OpinionDto.class);
    }

    @Override
    public Opinion ConvertToEntity(OpinionDto dto) {
        Opinion opinion = modelMapper.map(dto, Opinion.class);
        Book book = bookRepository.findById(dto.getBook().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getBook().getId(), "Opinion"));
        User user = userRepository.findById(dto.getUser().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getUser().getId(), "Opinion"));
        opinion.setBook(book);
        opinion.setUser(user);

        return opinion;
    }

    @Override
    public List<OpinionDto> findByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> super.entityNotFoundException(userId, "User"));

        List<Opinion> opinions = repository.findByUserId(userId);

        return opinions.stream()
                .map(opinion -> ConvertToDto(opinion))
                .collect(Collectors.toList());
    }

    @Override
    public List<OpinionDto> findByBookId(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> super.entityNotFoundException(bookId, "Book"));

        List<Opinion> opinions = repository.findByBookId(bookId);

        return opinions.stream()
                .map(opinion -> ConvertToDto(opinion))
                .collect(Collectors.toList());
    }

    @Override
    public int getAverageRateForBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> super.entityNotFoundException(bookId, "Book"));

        return repository.getAverageRateForBook(bookId);
    }
}
