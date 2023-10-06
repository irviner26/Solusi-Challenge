package com.chapterempat.handson.service;

import com.chapterempat.handson.model.Movie;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public interface MovieServices {
    Movie newMovieBuilding(String i1,String i2,String i3,String i4) throws ParseException;
    List<Movie> getCurrentMovies(Date date);
    Boolean addNewMovie(Movie movie);
    Movie getMovieDetail(String selectedMovie);
}
