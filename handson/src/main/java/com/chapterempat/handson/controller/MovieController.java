package com.chapterempat.handson.controller;

import com.chapterempat.handson.model.Movie;
import com.chapterempat.handson.service.MovieServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
public class MovieController {

    @PostConstruct
    public void init() throws ParseException {
        this.mainMenu();
    }

    private Scanner scanner = new Scanner(System.in);

    @Autowired
    public MovieServices movieService;

    public void mainMenu() throws ParseException {
        System.out.println("Welcome to Bioskop Binar!!\n" +
                "Silahkan pilih menu\n" +
                "1. Lihat film sedang tayang\n" +
                "2. Tambahkan film\n" +
                "0. Keluar");
        System.out.print("=> ");
        int pilihan = scanner.nextInt();
        switch(pilihan) {
            case 1:
                this.showCurrentMovies();
                break;
            case 2:
                scanner.nextLine();
                this.addNewMovie();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Pilihan dengan benar coy\n!!!!!!!!!!!");
                this.mainMenu();
        }
    }

    public void showCurrentMovies() throws ParseException {
        System.out.println("Berikut adalah film yang sedang tayang saat ini");
        System.out.println("Nama Film \t | \t Jadwal \t | \t Sinopsis");
        List<Movie> movies = movieService.getCurrentMovies(new Date());
        movies.forEach(movie ->
                System.out.println(movie.getName()
                        + "\t | \t"
                        + movie.getSchedule()
                        + "\t | \t"
                        + movie.getInfo()));
        System.out.print("Pilih film yang ingin dilihat lebih detil => ");
        int pilihan = scanner.nextInt();
        String selectedMovie = movies.get(pilihan-1).getName();
        this.showFilmDetail(selectedMovie);
    }

    public void showFilmDetail(String input) throws ParseException {
        Movie movie = movieService.getMovieDetail(input);
        System.out.println("Nama Film : " + movie.getName());
        System.out.println("Poster Image : " + movie.getImage());
        System.out.println("Jadwal Tayang : " + movie.getSchedule());
        System.out.println("Seat tersedia : " + movie.getSeat());
        System.out.println("Sinopsis : " + movie.getInfo());
        System.out.println();
        System.out.println("1. Kembali ke menu utama");
        System.out.println("0. Keluar");
        System.out.print("=> ");
        int pilihan = scanner.nextInt();
        if(pilihan == 1) {
            this.mainMenu();
        }
        if(pilihan == 0) {
            System.exit(0);
        }
    }

    public void addNewMovie() throws ParseException {
        System.out.print("Nama Film : ");
        String movieName = scanner.nextLine();
        System.out.print("Poster Image : ");
        String urlImagePoster = scanner.nextLine();
        System.out.print("Seat tersedia : ");
        String seat = scanner.nextLine();
        System.out.print("Sinopsis : ");
        String synopsis = scanner.nextLine();
        movieService.addNewMovie(movieService.newMovieBuilding(movieName
                , urlImagePoster
                , seat
                , synopsis));
        System.out.println("film sudah ditambahkan!");
        this.mainMenu();
    }

}
