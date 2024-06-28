package com.example.rentalservice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@Tag(name = "Rental Service", description = "Operations pertaining to rentals in Rental Service")
@RequestMapping("/rentals")
public class RentalController {
    private RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping("/get-all-movies")
    @Operation(summary = "View a list of available movies", description = "Get a list of all available movies", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = rentalService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @PostMapping("/add-movie")
    @Operation(summary = "Add a new movie", description = "Add a new movie to the list", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully added movie")
    })
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Movie.class))),
            @ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(hidden = true)))
    })
    public ResponseEntity<Movie> addMovie(
            @Parameter(description = "Movie object to be added", required = true) @RequestBody Movie movie) {
        Movie savedMovie = rentalService.addMovie(movie);
        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping("/get-movie/{id}")
    @Operation(summary = "Get a movie by ID", description = "Retrieve a movie by its ID", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved movie"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movie not found")
    })
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = rentalService.getMovieById(id);
        return ResponseEntity.ok(movie);
    }

    @PutMapping("/set-available/{id}")
    @Operation(summary = "Set a movie as available", description = "Set the status of a movie as available", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully set movie as available")
    })
    public ResponseEntity<Void> setMovieAvailable(
            @Parameter(description = "ID of the movie to set as available", required = true) @PathVariable Long id) {
        rentalService.setMovieAvailable(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-movie/{id}")
    @Operation(summary = "Delete a movie by ID", description = "Delete a movie by its ID", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully deleted movie"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movie not found")
    })
    public ResponseEntity<Void> deleteMovie(
            @Parameter(description = "ID of the movie to delete", required = true) @PathVariable Long id) {
        rentalService.deleteMovie(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-movie/{id}")
    @Operation(summary = "Update a movie by ID", description = "Update the details of a movie by its ID", responses = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully updated movie"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Movie not found")
    })
    public ResponseEntity<Movie> updateMovie(
            @Parameter(description = "ID of the movie to update", required = true) @PathVariable Long id,
            @Parameter(description = "Updated movie object", required = true) @RequestBody Movie updatedMovie) {
        Movie movie = rentalService.updateMovie(id, updatedMovie);
        return ResponseEntity.ok(movie);
    }
}