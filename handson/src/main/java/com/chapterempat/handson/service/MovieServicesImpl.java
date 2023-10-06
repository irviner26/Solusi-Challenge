package com.chapterempat.handson.service;

import com.chapterempat.handson.model.Movie;
import com.chapterempat.handson.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MovieServicesImpl implements MovieServices {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Movie newMovieBuilding(String i1, String i2, String i3, String i4) {
        return Movie.builder()
                .name(i1)
                .image(i2)
                .schedule(new Date())
                .seat(i3)
                .info(i4)
                .build();
    }

    @Override
    public List<Movie> getCurrentMovies(Date date) {
        Date requestDate = Optional.ofNullable(date)
                .orElse(new Date());
        return movieRepository.findAll();
    }

    @Override
    public Boolean addNewMovie(Movie movie) {
        return Optional.ofNullable(movie)
                .map(newMovie -> movieRepository.save(movie))
                .map(Objects::nonNull)
                .orElse(Boolean.FALSE);
    }

    @Override
    public Movie getMovieDetail(String selectedMovie) {
        return movieRepository.findByName(selectedMovie);
    }
}
