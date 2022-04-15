package com.movie;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.movie.controller.MoviesController;
import com.movie.domain.Movies;
import com.movie.service.MoviesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MoviesController.class)
@RunWith(SpringRunner.class)
public class MovieApplicationTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MoviesService moviesService;


    Movies movie = new Movies(0, "Lagaan", 1999,  "9.8");
    @Test
    public void createMovie() throws Exception {
        List<Movies> moviesList = Arrays.asList(movie);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(moviesList);

        Mockito.when(moviesService.addMovies(any())).thenReturn(moviesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/movies")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1) ));
    }

    @Test
    public void getAllMovies() throws Exception {
        List<Movies> moviesList = Arrays.asList(movie);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(moviesList);

        Mockito.when(moviesService.getAllMovies()).thenReturn(moviesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/movies")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1) ));
    }


    @Test
    public void updateMovies() throws Exception {
        List<Movies> moviesList = Arrays.asList(movie);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(moviesList);

        Mockito.when(moviesService.updateMovies(any())).thenReturn(moviesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/movies/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1) ));
    }

    @Test
    public void getMoviesByRating() throws Exception {
        List<Movies> moviesList = Arrays.asList(movie);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(moviesList);

        Mockito.when(moviesService.fetchMoviesBasedOnRating(any(String.class))).thenReturn(moviesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/movies/rating/9.8")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1) ));
    }

    @Test
    public void getMoviesByYear() throws Exception {
        List<Movies> moviesList = Arrays.asList(movie);
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(moviesList);

        Mockito.when(moviesService.fetchMoviesBasedOnYear(any(Integer.class))).thenReturn(moviesList);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/movies/year/1999")
                        .contentType(MediaType.APPLICATION_JSON))

                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1) ));
    }
}
