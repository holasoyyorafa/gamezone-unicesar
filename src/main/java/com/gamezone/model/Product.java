package com.gamezone.model;

/**
 * Represents a generic product sold in GameZone.
 * Common base for VideoGame and Console.
 */
public abstract class Product {

    private String id;
    private String title;
    private double price;
    private int stock;

    public Product(String id, String title, double price, int stock) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Returns a text description of the product,
     * including its specific attributes.
     */
    public abstract String describe();

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}