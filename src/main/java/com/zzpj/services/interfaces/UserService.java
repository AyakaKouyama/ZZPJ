package com.zzpj.services.interfaces;

import com.zzpj.entities.User;
import com.zzpj.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends BaseService<UserRepository, User> {

    User add(User user);

    User update(User user);
}