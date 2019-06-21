package com.zzpj;

import com.zzpj.entities.Role;
import com.zzpj.entities.User;
import com.zzpj.repositories.RoleRepository;
import com.zzpj.repositories.UserRepository;
import com.zzpj.security.JwtAuthenticationResponse;
import com.zzpj.security.JwtTokenProvider;
import com.zzpj.security.UserPrincipal;
import com.zzpj.services.interfaces.RoleService;
import com.zzpj.services.interfaces.UserService;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

  /*  @Before
    public void init() throws Exception
    {
        mockMvc.perform(post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.ROLE_CLIENT));

        mockMvc.perform(post("/roles")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.ROLE_ADMINISTRATOR));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.USER_CLIENT));

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.USER_ADMINISTRATOR));
    }

    public String generateToken(String role){

        String login = role.equals(Constants.CLIENT) ? "test" : "test2";
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 604800000);
        Claims claims = Jwts.claims().setSubject(login);

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(authority);

        claims.put("roles", authoritiesList);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, "JWTSuperSecretKey")
                .compact();
    } */

    @Test
    public void shouldReturnCreated_Book() throws Exception{
      /*  mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.CATEGORY));

        mockMvc.perform(post("/books")
                //.header(HttpHeaders.AUTHORIZATION, "Bearer " + generateToken(Constants.ADMINISTRATOR))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.BOOK))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("test"))); */
    }

    @Test
    public void shouldReturnOkAndBookList() throws Exception {
      /*  //Given
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.CATEGORY));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.BOOK));
        //

        mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test"))); */

    }
}
