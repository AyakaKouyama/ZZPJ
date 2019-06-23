package com.zzpj.services.interfaces;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;

public interface UserDetailsService extends BaseService<UserDetails, UserDetailsDto> {
    UserDetailsDto update(UserDetailsDto userDetailsDto);

}
