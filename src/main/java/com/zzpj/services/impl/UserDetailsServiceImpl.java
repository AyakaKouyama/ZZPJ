package com.zzpj.services.impl;

import com.zzpj.entities.UserDetails;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.services.interfaces.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetailsRepository, UserDetails> implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository userDetailsRepository) {
        super(userDetailsRepository);
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails add(UserDetails userDetails) {
        userDetails.setVersion(0L);
        return userDetailsRepository.save(userDetails);
    }

    @Override
    public UserDetails update(UserDetails userDetails) {
        UserDetails userDetailsFromRepository = userDetailsRepository.findById(userDetails.getId())
                .orElseThrow(() -> entityNotFoundException(userDetails.getId()));
        userDetails.setVersion(userDetailsFromRepository.getVersion());
        return userDetailsRepository.save(userDetails);
    }

    private EntityNotFoundException entityNotFoundException(Long id) {
        return new EntityNotFoundException("Details with id " + id + " not found");
    }
}