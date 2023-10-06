package com.chapterempat.handson.repository;

import com.chapterempat.handson.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findByName(String name);
}
