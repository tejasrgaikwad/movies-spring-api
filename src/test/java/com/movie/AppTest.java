package com.movie;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.domain.Movies;
import com.movie.service.MoviesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.MOCK, classes={ MovieApplication.class })
public class AppTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private MoviesService moviesServiceMock;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void should_CreateAccount_When_ValidRequest() throws Exception {
        Movies movie = new Movies(0, "Lagaan", 1999,  "9.8");

        List<Movies> moviesList = Arrays.asList(movie);
        when(moviesServiceMock.addMovies(any(List.class))).thenReturn(moviesList);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(moviesList);

        mockMvc.perform(post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(movie.getName())));
    }

//    @Test
//    public void should_GetAccount_When_ValidRequest() throws Exception {
//
//        /* setup mock */
//        Account account = new Account(12345L, EnumAccountType.SAVINGS, 5000.0);
//        when(accountServiceMock.loadAccount(12345L)).thenReturn(account);
//
//        mockMvc.perform(get("/api/account/12345")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.accountId").value(12345))
//                .andExpect(jsonPath("$.accountType").value("SAVINGS"))
//                .andExpect(jsonPath("$.balance").value(5000.0));
//    }
//
//    @Test
//    public void should_Return404_When_AccountNotFound() throws Exception {
//
//        /* setup mock */
//        when(accountServiceMock.loadAccount(12345L)).thenReturn(null);
//
//        mockMvc.perform(get("/api/account/12345")
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }

}