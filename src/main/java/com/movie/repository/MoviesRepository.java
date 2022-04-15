package com.movie.repository;

import com.movie.domain.Movies;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

}
