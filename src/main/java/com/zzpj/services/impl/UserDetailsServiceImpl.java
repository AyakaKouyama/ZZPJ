package com.zzpj.services.impl;

import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.entities.UserDetails;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.services.interfaces.UserDetailsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends BaseServiceImpl<UserDetailsRepository, UserDetails, UserDetailsDto> implements UserDetailsService {

    private UserDetailsRepository userDetailsRepository;

    @Autowired
    public UserDetailsServiceImpl(UserDetailsRepository repository, ModelMapper modelMapper) {
        super(repository, modelMapper);
        this.userDetailsRepository = repository;
    }

    @Override
    public UserDetailsDto convertToDto(UserDetails entity) {
        return modelMapper.map(entity, UserDetailsDto.class);
    }

    @Override
    public UserDetails convertToEntity(UserDetailsDto dto) {
        return modelMapper.map(dto, UserDetails.class);
    }

    public UserDetailsDto update(UserDetailsDto userDetailsDto) {
        UserDetails userDetails = userDetailsRepository.findById(userDetailsDto.getId())
                .orElseThrow(() -> entityNotFoundException(userDetailsDto.getId(), "userDetails"));

        userDetails.setCity(userDetailsDto.getCity());
        userDetails.setCountry(userDetailsDto.getCountry());
        userDetails.setFirstName(userDetailsDto.getFirstName());
        userDetails.setFlatNumber(userDetailsDto.getFlatNumber());
        userDetails.setLastName(userDetailsDto.getLastName());
        userDetails.setPhoneNumber(userDetailsDto.getPhoneNumber());
        userDetails.setStreet(userDetailsDto.getStreet());
        userDetails.setStreetNumber(userDetailsDto.getStreetNumber());

        userDetailsRepository.save(userDetails);

        return convertToDto(userDetails);
    }
}