package com.zzpj.services.interfaces;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.User;
import org.springframework.security.core.Authentication;

public interface UserService extends BaseService<User, UserDto> {

   Authentication authenticate(String login, String password);

   User findByLogin(String login);

}