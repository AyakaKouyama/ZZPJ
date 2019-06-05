package com.zzpj.services.interfaces;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.User;
import com.zzpj.repositories.UserRepository;
import org.springframework.stereotype.Service;


@Service
public interface UserService extends BaseService<User, UserDto> {

}