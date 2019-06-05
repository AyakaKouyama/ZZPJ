package com.zzpj.services.impl;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.entities.UserDetails;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User, UserDto> implements UserService {

    private RoleRepository roleRepository;
    private UserDetailsRepository userDetailsRepository;

    public UserServiceImpl(
            UserRepository repository,
            RoleRepository roleRepository,
            UserDetailsRepository userDetailsRepository,
            ModelMapper modelMapper)
    {
        super(repository, modelMapper);
        this.roleRepository = roleRepository;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDto ConvertToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public User ConvertToEntity(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        Role role = roleRepository.findById(dto.getRole().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getRole().getId(), "Role"));
        UserDetails userDetails = userDetailsRepository.findById(dto.getUserDetails().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getUserDetails().getId(), "UserDetails"));
        user.setRole(role);
        user.setUserDetails(userDetails);

        return user;
    }
}
