package com.example.rentalservice;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.*;


@Service
public class RentalService {
    private final RestTemplate restTemplate;

    public RentalService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Movie getMovieById(Long id) {
        String url = "http://localhost:8080/get-movie/" + id;
        ResponseEntity<Movie> response = restTemplate.getForEntity(url, Movie.class);
        return response.getBody();
    }

    public List<Movie> getAllMovies() {
        String url = "http://localhost:8080/get-all-movies";
        ResponseEntity<Movie[]> response = restTemplate.getForEntity(url, Movie[].class);
        return Arrays.asList(response.getBody());
    }

    public Movie addMovie(Movie movie) {
        String url = "http://localhost:8080/add-movie";
        ResponseEntity<Movie> response = restTemplate.postForEntity(url, movie, Movie.class);
        return response.getBody();
    }

    public void setMovieAvailable(Long id) {
        String url = "http://localhost:8080/set-available/" + id;
        restTemplate.put(url, null);
    }

    public void deleteMovie(Long id) {
        String url = "http://localhost:8080/delete-movie/" + id;
        restTemplate.delete(url);
    }

    public Movie updateMovie(Long id, Movie movie) {
        String url = "http://localhost:8080/update-movie/" + id;
        restTemplate.put(url, movie);
        return getMovieById(id);
    }

}
