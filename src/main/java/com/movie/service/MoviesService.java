package com.movie.service;

import com.movie.domain.Movies;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MoviesService {
    public List<Movies> getAllMovies();

    public List<Movies>  addMovies(List<Movies> movies);

    public List<Movies> fetchMoviesBasedOnYear(Integer year);
    public List<Movies> fetchMoviesBasedOnRating(String rating);
    public List<Movies> updateMovies(List<Movies> list);

}
