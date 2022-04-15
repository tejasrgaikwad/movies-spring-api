package com.movie.controller;

import java.util.List;

import com.movie.domain.Movies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.movie.service.MoviesService;

@RestController
@RequestMapping("/movies")
public class MoviesController {

	private static final Logger LOG = LoggerFactory.getLogger(MoviesController.class);

	@Autowired
	private MoviesService moviesService;

	@RequestMapping( method = RequestMethod.POST)
	public List<Movies> addMovie(@RequestBody List<Movies> movies) {
		LOG.info("Add a Movie...");
		return moviesService.addMovies(movies);
//		return moviesService.getAllMovies();
	}

	@RequestMapping(value="/update", method = RequestMethod.POST)
	public List<Movies> updateMovies(@RequestBody List<Movies> movies) {
		LOG.info("Add a Movie...");
		return moviesService.updateMovies(movies);
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<Movies> getAllMovies() {
		LOG.info("Fetch all the Movies...");
		return moviesService.getAllMovies();
	}

	@RequestMapping(value = "/year/{year}", method = RequestMethod.GET)
	public List<Movies> getMoviesByYear(@PathVariable("year") Integer year) {
		LOG.info("Get Movies by Year : " + year);
		return moviesService.fetchMoviesBasedOnYear(year);
	}

	@RequestMapping(value = "/rating/{rating}", method = RequestMethod.GET)
	public List<Movies> getMoviesByRating(@PathVariable("rating") String rating) {
		LOG.info("Get movies by rating : "+ rating);
		return moviesService.fetchMoviesBasedOnRating(rating);
	}
}
