package com.zzpj.services.impl;

import com.zzpj.dtos.UserDto;
import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.entities.UserDetails;
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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public UserDto update(UserDto userDto) {
        User user = userRepository.findById(userDto.getId())
                .orElseThrow(() -> entityNotFoundException(userDto.getId(), "Book"));
        Role role = roleRepository.findById(userDto.getRole().getId())
                .orElseThrow(() -> entityNotFoundException(userDto.getRole().getId(), "Role"));
        UserDetails userDetails = userDetailsRepository.findById(userDto.getUserDetails().getId())
                .orElseThrow(() -> entityNotFoundException(userDto.getUserDetails().getId(), "UserDetails"));

        user.setLogin(userDto.getLogin());
        user.setEmail(userDto.getEmail());
        user.setRole(role);
        user.setUserDetails(userDetails);

        userRepository.save(user);

        return convertToDto(user);
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
        if (user.getPasswordHash() == null) {
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

    @Override
    public List<UserDto> sortField(String filed) {
        List<User> users = userRepository.findAll();
        switch (filed) {
            case ("login"): {
                List<User> sorted = users.stream()
                        .sorted(Comparator.comparing(User::getLogin))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case ("email"): {
                List<User> sorted = users.stream()
                        .sorted(Comparator.comparing(User::getEmail))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case ("roleName"): {
                List<User> sorted = users.stream()
                        .sorted(Comparator.comparing(u -> u.getRole().getName()))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case ("firstName"): {
                List<User> sorted = users.stream()
                        .sorted(Comparator.comparing(u -> u.getUserDetails().getFirstName()))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case ("lastName"): {
                List<User> sorted = users.stream()
                        .sorted(Comparator.comparing(u -> u.getUserDetails().getLastName()))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            case ("city"): {
                List<User> sorted = users.stream()
                        .sorted(Comparator.comparing(u -> u.getUserDetails().getCity()))
                        .collect(Collectors.toList());
                return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
            }
            default: {
                return users.stream().map(this::convertToDto).collect(Collectors.toList());
            }
        }
    }

    @Override
    public List<UserDto> filterField(String field, String param) {
        List<UserDto> dto = null;
        switch (field) {
            case "login":
                dto = this.loginFilter(param);
                break;
            case "email":
                dto = this.emailFilter(param);
                break;
            case "roleName":
                dto = this.roleNameFilter(param);
                break;
            case "firstName":
                dto = this.firstNameFilter(param);
                break;
            case "lastName":
                dto = this.lastNameFilter(param);
                break;
            case "city":
                dto = this.cityFilter(param);
                break;
            default:
                dto = noFilter();
        }
        return dto;
    }

    public List<UserDto> loginFilter(String login) {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream().filter(b -> b.getLogin().equals(login)).collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserDto> emailFilter(String email) {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream().filter(b -> b.getEmail().equals(email)).collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserDto> roleNameFilter(String roleName) {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream()
                .filter(b -> b.getRole().getName().equals(roleName))
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserDto> firstNameFilter(String firstName) {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream()
                .filter(b -> b.getUserDetails().getFirstName().equals(firstName))
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserDto> lastNameFilter(String lastName) {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream()
                .filter(b -> b.getUserDetails().getLastName().equals(lastName))
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserDto> cityFilter(String city) {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream()
                .filter(b -> b.getUserDetails().getCity().equals(city))
                .collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<UserDto> noFilter() {
        List<User> users = userRepository.findAll();
        List<User> sorted = users.stream().collect(Collectors.toList());
        return sorted.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
