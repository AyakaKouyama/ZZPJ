package com.zzpj;

import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.repositories.UserRepository;
import com.zzpj.utils.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Before
    public void init() {
        User administrator = new User();
        Role roleAdmin = new Role();
        administrator.setLogin("admin");
        administrator.setPasswordHash(passwordEncoder.encode("test"));
        roleAdmin.setName("ADMINISTRATOR");
        administrator.setRole(roleAdmin);
        when(userRepository.findByLogin("admin")).thenReturn(Optional.of(administrator));
    }

    public String generateToken(String role) {
        String login = role.equals(Constants.CLIENT) ? "client" : "admin";
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 1800000);
        Claims claims = Jwts.claims().setSubject(login);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(authority);

        claims.put("roles", authoritiesList);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512,
                        "r468cwiU4C4lJSI2tMrsxEA32UON6SPNixVzd2YtTJXSzFNaAGymEjeIXu9z66-jhHW_n-YG5uP-hWXX55M3r32spAXQtnfD15f-Q8d_ejEQObHJDxcbyXc0toL9wg56dPl3FTAjF0B_ByLvU2yHBwO3kh6hY-Tl4e-hdB282vKBnBDBtr8_cL1pm_P2jFFlXufW4LqWWuqkGrnTSR4D8Gjn4R_vztgVvWztZKpwdo60_3Xa-_VJcHQ2LgvDHfgLKbzXTtNrNsqWLdDL98hXs7ElgPHfYOco3wmvSOcoqGLMOn06rV7jSQDRKj76A6vfiPE9A5biIl-nXI7oDrT5Uw")
                .compact();
    }

    @Test
    public void shouldReturnCreated_Category() throws Exception {
        String token = generateToken(Constants.ADMINISTRATOR);

        mockMvc.perform(post("/categories")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.CATEGORY))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnOkAndBookList() throws Exception {
        String token = generateToken(Constants.ADMINISTRATOR);

        mockMvc.perform(post("/categories")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.CATEGORY));

        mockMvc.perform(get("/categories")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }
}
