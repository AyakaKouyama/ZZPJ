package com.zzpj.services.interfaces;

import com.zzpj.entities.UserDetails;
import com.zzpj.repositories.UserDetailsRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsService extends BaseService<UserDetailsRepository, UserDetails> {

    UserDetails add(UserDetails userDetails);

    UserDetails update(UserDetails userDetails);
}
