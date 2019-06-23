package com.zzpj.services.interfaces;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserService extends BaseService<User, UserDto> {

   Authentication authenticate(String login, String password);

   User findByLogin(String login);

   List<UserDto> sortField(String field);

   List<UserDto> filterField(String field, String param);


}