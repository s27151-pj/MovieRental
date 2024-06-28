package com.example.rentalservice;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Details about the movie")
public class Movie {
    @Schema(description = "The unique ID of the movie", example = "1")
    private Long id;

    @Schema(description = "The name of the movie", example = "Inception")
    private String name;

    @Schema(description = "The category of the movie", example = "Science Fiction")
    private String category;

    @Schema(description = "The duration of the movie in minutes", example = "148")
    private int numberOfMinutes;

    @Schema(description = "Availability status of the movie", example = "true")
    private boolean isAvailable = false;

    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    // Default constructor
    public Movie() {
    }

    // Parameterized constructor
    public Movie(String name, String category) {
        this.name = name;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}