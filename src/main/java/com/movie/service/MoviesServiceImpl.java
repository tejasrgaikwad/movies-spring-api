package com.movie.service;

import com.movie.domain.Movies;
import com.movie.repository.MoviesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MoviesServiceImpl implements MoviesService {

	private static final Logger LOG = LoggerFactory.getLogger(MoviesServiceImpl.class);

	@Autowired
	private MoviesRepository moviesRepository;

	public List<Movies> getAllMovies() {
		List<Movies> list = (List<Movies>) moviesRepository.findAll();
		return list;
	}

	public List<Movies>  addMovies(List<Movies> movies) {
		return moviesRepository.saveAll(movies);
	}

	public List<Movies> fetchMoviesBasedOnYear(Integer year) {
		List<Movies> list = getAllMovies();
		return list.stream()
				.filter(movie -> movie.getYear().equals(year))
				.collect(Collectors.toList());
	}

	public List<Movies> fetchMoviesBasedOnRating(String rating) {
		return getAllMovies().stream()
				.filter(movie -> movie.getRating().equalsIgnoreCase(rating))
				.collect(Collectors.toList());
	}

	public List<Movies> updateMovies(List<Movies> list) {
		List<Movies> savedMoviesList = getAllMovies();
		list.parallelStream().forEach(obj -> {
			savedMoviesList.stream().filter(savedMovie -> savedMovie.getName()
					.equalsIgnoreCase(obj.getName())).forEach(foundMovie ->
					{
						foundMovie.setName(obj.getName());
						foundMovie.setYear(obj.getYear());
						foundMovie.setRating(obj.getRating());
						moviesRepository.save(foundMovie);
					});
		});
		return getAllMovies();
	}

}
