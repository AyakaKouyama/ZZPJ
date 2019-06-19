package com.zzpj;

import com.zzpj.repositories.CategoryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    @Test
    public void shouldReturnCreated_Book() throws Exception{
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.CATEGORY));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.BOOK))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("test")));
    }

    @Test
    public void shouldReturnOkAndBookList() throws Exception {
        //Given
        mockMvc.perform(post("/categories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.CATEGORY));

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONConstants.BOOK))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("test")));
        //

        mockMvc.perform(get("/books")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));

    }
}
