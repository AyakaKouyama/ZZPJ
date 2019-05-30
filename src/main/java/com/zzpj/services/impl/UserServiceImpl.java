package com.zzpj.services.impl;

import com.zzpj.entities.User;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User> implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
        this.userRepository = userRepository;
    }

    @Override
    public User add(User user) {
        user.setVersion(0L);
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        User userFromRepository = userRepository.findById(user.getId())
                .orElseThrow(() -> entityNotFoundException(user.getId()));
        user.setVersion(userFromRepository.getVersion());
        return userRepository.save(user);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("User with id " + id + " not found");
    }
}
