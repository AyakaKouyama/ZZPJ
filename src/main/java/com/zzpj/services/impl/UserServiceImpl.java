package com.zzpj.services.impl;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.exceptions.EmptyFieldException;
import com.zzpj.exceptions.EntityAlreadyExistsException;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserRepository, User, UserDto> implements UserService {

    private RoleRepository roleRepository;
    private UserDetailsRepository userDetailsRepository;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(
            UserRepository repository,
            RoleRepository roleRepository,
            UserDetailsRepository userDetailsRepository,
            ModelMapper modelMapper,
            @Lazy AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        super(repository, modelMapper);
        this.roleRepository = roleRepository;
        this.userDetailsRepository = userDetailsRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto convertToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public User convertToEntity(UserDto dto) {
        User user = modelMapper.map(dto, User.class);
        Role role = roleRepository.findById(dto.getRole().getId())
                .orElseThrow(() -> super.entityNotFoundException(dto.getRole().getId(), "Role"));
        user.setRole(role);

        return user;
    }

    @Override
    public Authentication authenticate(String login, String password) {
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, password));
    }

    @Override
    public UserDto add(UserDto user) {
        if (userRepository.existsByLogin(user.getLogin())) {
            throw new EntityAlreadyExistsException("User with login " + user.getLogin() + " already exists.");
        }
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new EntityAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }
        if(user.getPasswordHash() == null){
            throw new EmptyFieldException("Password cannot be null");
        }

        String hashedPassword = passwordEncoder.encode(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);
        user.setVersion(0L);
        User savedEntity = repository.save(convertToEntity(user));
        return convertToDto(savedEntity);
    }

    @Override
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with login " + login + " not found."));
    }
}
