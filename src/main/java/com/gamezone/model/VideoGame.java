package com.gamezone.model;

/**
 * Represents a video game product with platform,
 * genre and age rating information.
 */
public class VideoGame extends Product {

    private String platform;
    private String genre;
    private String ageRating;

    public VideoGame(String id, String title, double price, int stock,
                      String platform, String genre, String ageRating) {
        super(id, title, price, stock);
        this.platform = platform;
        this.genre = genre;
        this.ageRating = ageRating;
    }

    @Override
    public String describe() {
        return getTitle() + " (" + platform + ", " + genre + ") - Rating: " + ageRating
                + " - $" + getPrice() + " - Stock: " + getStock();
    }

    public String getPlatform() {
        return platform;
    }

    public String getGenre() {
        return genre;
    }

    public String getAgeRating() {
        return ageRating;
    }
}