package com.zzpj;

import com.zzpj.dtos.RoleDto;
import com.zzpj.dtos.UserDetailsDto;
import com.zzpj.dtos.UserDto;
import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.entities.UserDetails;
import com.zzpj.exceptions.EntityNotFoundException;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.repositories.UserDetailsRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    RoleRepository roleRepository;

    @Mock
    UserDetailsRepository userDetailsRepository;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    private ModelMapper modelMapper = new ModelMapper();

    @InjectMocks
    UserServiceImpl userService = new UserServiceImpl(userRepository, roleRepository, userDetailsRepository,
            modelMapper, authenticationManager, userRepository, passwordEncoder);

    @Test
    public void shouldThrowEntityNotFoundExceptionWhenRoleDoesNotExists() {
        User user = createUser();
        user.setVersion(0L);
        when(roleRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> userService.add(createUserDto()));
    }

    @Test
    public void shouldSortByLogin() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> sorted = userService.sortField("login");
        assertThat(sorted.get(0).getLogin()).isSameAs("login1");
        assertThat(sorted.get(1).getLogin()).isSameAs("login2");
        assertThat(sorted.get(2).getLogin()).isSameAs("login3");
    }

    @Test
    public void shouldSortByEmail() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> sorted = userService.sortField("email");
        assertThat(sorted.get(0).getEmail()).isSameAs("111@abc.pl");
        assertThat(sorted.get(1).getEmail()).isSameAs("222@abc.pl");
        assertThat(sorted.get(2).getEmail()).isSameAs("333@abc.pl");
    }

    @Test
    public void shouldSortByRoleName() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> sorted = userService.sortField("roleName");
        assertThat(sorted.get(0).getRole().getName()).isSameAs("ADMINISTRATOR");
        assertThat(sorted.get(1).getRole().getName()).isSameAs("CLIENT");
        assertThat(sorted.get(2).getRole().getName()).isSameAs("CLIENT");
    }

    @Test
    public void shouldSortByFirstName() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> sorted = userService.sortField("firstName");
        assertThat(sorted.get(0).getUserDetails().getFirstName()).isSameAs("A");
        assertThat(sorted.get(1).getUserDetails().getFirstName()).isSameAs("B");
        assertThat(sorted.get(2).getUserDetails().getFirstName()).isSameAs("C");
    }

    @Test
    public void shouldSortByLastName() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> sorted = userService.sortField("lastName");
        assertThat(sorted.get(0).getUserDetails().getLastName()).isSameAs("X");
        assertThat(sorted.get(1).getUserDetails().getLastName()).isSameAs("Y");
        assertThat(sorted.get(2).getUserDetails().getLastName()).isSameAs("Z");
    }

    @Test
    public void shouldSortByCity() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> sorted = userService.sortField("city");
        assertThat(sorted.get(0).getUserDetails().getCity()).isSameAs("Opole");
        assertThat(sorted.get(1).getUserDetails().getCity()).isSameAs("Warszawa");
        assertThat(sorted.get(2).getUserDetails().getCity()).isSameAs("Warszawa");
    }

    @Test
    public void shouldFilterByLogin() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> filtered = userService.loginFilter("login1");
        assertThat(filtered.size()).isEqualTo(1);
        assertThat(filtered.get(0).getLogin()).isSameAs("login1");
    }

    @Test
    public void shouldFilterByEmail() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> filtered = userService.emailFilter("email@email.com");
        assertThat(filtered.size()).isEqualTo(0);
    }

    @Test
    public void shouldFilterByRoleName() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> filtered = userService.roleNameFilter("CLIENT");
        assertThat(filtered.size()).isEqualTo(2);
        assertThat(filtered.get(0).getRole().getName()).isSameAs("CLIENT");
        assertThat(filtered.get(1).getRole().getName()).isSameAs("CLIENT");
    }

    @Test
    public void shouldFilterByFirstName() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> filtered = userService.firstNameFilter("C");
        assertThat(filtered.size()).isEqualTo(1);
        assertThat(filtered.get(0).getUserDetails().getFirstName()).isSameAs("C");
    }

    @Test
    public void shouldFilterByLastName() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> filtered = userService.lastNameFilter("Y");
        assertThat(filtered.size()).isEqualTo(1);
        assertThat(filtered.get(0).getUserDetails().getLastName()).isSameAs("Y");
    }

    @Test
    public void shouldFilterByCity() {
        when(userRepository.findAll()).thenReturn(createUserListForSortAndFilter());

        List<UserDto> filtered = userService.cityFilter("Warszawa");
        assertThat(filtered.size()).isEqualTo(2);
        assertThat(filtered.get(0).getUserDetails().getCity()).isSameAs("Warszawa");
        assertThat(filtered.get(1).getUserDetails().getCity()).isSameAs("Warszawa");
    }

    private User createUser() {
        User user = new User();

        user.setEmail("abcdefgh@abc.pl");
        user.setLogin("login");
        user.setId(1L);
        user.setPasswordHash("test123");

        UserDetails userDetails = new UserDetails();
        userDetails.setStreetNumber("1");
        userDetails.setStreet("Polna");
        userDetails.setPhoneNumber("123-123-123");
        userDetails.setLastName("Kowalski");
        userDetails.setFirstName("Jan");
        userDetails.setFlatNumber("1");
        userDetails.setCountry("Poland");
        userDetails.setCity("Warszawa");
        userDetails.setId(1L);

        Role role = new Role();
        role.setName("CLIENT");
        role.setId(1L);

        user.setRole(role);
        user.setUserDetails(userDetails);

        return user;
    }

    private UserDto createUserDto() {
        UserDto user = new UserDto();

        user.setEmail("abcdefgh@abc.pl");
        user.setLogin("login");
        user.setPasswordHash("test123");
        user.setId(1L);

        UserDetailsDto userDetails = new UserDetailsDto();
        userDetails.setStreetNumber("1");
        userDetails.setStreet("Polna");
        userDetails.setPhoneNumber("123-123-123");
        userDetails.setLastName("Kowalski");
        userDetails.setFirstName("Jan");
        userDetails.setFlatNumber("1");
        userDetails.setCountry("Poland");
        userDetails.setCity("Warszawa");
        userDetails.setId(1L);

        RoleDto role = new RoleDto();
        role.setName("CLIENT");
        role.setId(1L);

        user.setRole(role);
        user.setUserDetails(userDetails);

        return user;
    }

    private List<User> createUserListForSortAndFilter() {
        List<User> users = new ArrayList<>();

        User user = new User();
        user.setEmail("111@abc.pl");
        user.setLogin("login1");
        user.setId(1L);
        user.setPasswordHash("test123");
        UserDetails userDetails = new UserDetails();
        userDetails.setStreetNumber("1");
        userDetails.setStreet("Polna");
        userDetails.setPhoneNumber("123-123-123");
        userDetails.setLastName("Z");
        userDetails.setFirstName("A");
        userDetails.setFlatNumber("1");
        userDetails.setCountry("Poland");
        userDetails.setCity("Warszawa");
        userDetails.setId(1L);
        Role role = new Role();
        role.setName("CLIENT");
        role.setId(1L);
        user.setRole(role);
        user.setUserDetails(userDetails);

        User user2 = new User();
        user2.setEmail("333@abc.pl");
        user2.setLogin("login3");
        user2.setId(2L);
        user2.setPasswordHash("test123");
        UserDetails userDetails2 = new UserDetails();
        userDetails2.setStreetNumber("1");
        userDetails2.setStreet("Polna");
        userDetails2.setPhoneNumber("123-123-123");
        userDetails2.setLastName("Y");
        userDetails2.setFirstName("C");
        userDetails2.setFlatNumber("1");
        userDetails2.setCountry("Poland");
        userDetails2.setCity("Opole");
        userDetails2.setId(1L);
        user2.setRole(role);
        user2.setUserDetails(userDetails2);

        User user3 = new User();
        user3.setEmail("222@abc.pl");
        user3.setLogin("login2");
        user3.setId(3L);
        user3.setPasswordHash("test123");
        UserDetails userDetails3 = new UserDetails();
        userDetails3.setStreetNumber("1");
        userDetails3.setStreet("Polna");
        userDetails3.setPhoneNumber("123-123-123");
        userDetails3.setLastName("X");
        userDetails3.setFirstName("B");
        userDetails3.setFlatNumber("1");
        userDetails3.setCountry("Poland");
        userDetails3.setCity("Warszawa");
        userDetails3.setId(1L);
        Role role3 = new Role();
        role3.setName("ADMINISTRATOR");
        role3.setId(2L);
        user3.setRole(role3);
        user3.setUserDetails(userDetails3);

        users.add(user);
        users.add(user2);
        users.add(user3);
        return users;
    }
}
