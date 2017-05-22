package com.epam.david;

import com.epam.david.controller.BookController;
import com.epam.david.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by David_Chaava on 5/22/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "123")
public class SimpleTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void findById() throws Exception {
        mockMvc.perform(get("/book/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("book"));
    }

}

