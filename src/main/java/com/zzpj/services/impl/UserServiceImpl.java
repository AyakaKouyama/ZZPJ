package com.zzpj.services.impl;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.interfaces.UserService;
import org.modelmapper.ModelMapper;
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

    public UserServiceImpl(
            UserRepository repository,
            RoleRepository roleRepository,
            UserDetailsRepository userDetailsRepository,
            ModelMapper modelMapper,
            AuthenticationManager authenticationManager,
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
    public UserDto ConvertToDto(User entity) {
        return modelMapper.map(entity, UserDto.class);
    }

    @Override
    public User ConvertToEntity(UserDto dto) {
        String password = dto.getPasswordHash();
        User user = modelMapper.map(dto, User.class);
        user.setPasswordHash(passwordEncoder.encode(password));
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
    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new EntityNotFoundException("User with login " + login + " not found."));
    }
}
