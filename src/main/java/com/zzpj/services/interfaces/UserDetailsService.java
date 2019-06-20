package com.zzpj.services.interfaces;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

public interface UserDetailsService extends BaseService<UserDetails, UserDetailsDto> {

}
