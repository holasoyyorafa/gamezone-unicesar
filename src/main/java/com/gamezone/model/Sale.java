package com.gamezone.model;

import java.time.LocalDate;
import java.util.List;


public class Sale {
    private String date;
    private Client client;
    private Seller seller;
    private List<Product> products;
    private double total;

    
    public Sale(String date, Client client, Seller seller, List<Product> products) {
        this.date = date;
        this.client = client;
        this.seller = seller;
        this.products = products;
    }


    
    public void calculateTotal() {
        this.total = 0;
        if (products != null) {
            for (Product product : products) {
                this.total += product.getPrice();
            }
        }
    }

    public String getDate() {
        return date;
    }

    public Client getClient() {
        return client;
    }

    public Seller getSeller() {
        return seller;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotal() {
        return total;
    }
    
    public boolean canBeReturned() {
        if (date == null) return false;
        try {
            LocalDate saleDate = LocalDate.parse(date);
            LocalDate today = LocalDate.now();
            long daysBetween = java.time.temporal.ChronoUnit.DAYS.between(saleDate, today);
            return daysBetween >= 0 && daysBetween <= 30;
        } catch (Exception e) {
            return false;
      
      }
    }
    @Override
    public String toString() {
        return "Sale {date: " + date
                + ", client: " + (client != null ? client.getName() : "N/A")
                + ", seller: " + (seller != null ? seller.getName() : "N/A")
                + ", products: " + (products != null ? products.size() : 0)
                + ", total: $" + total
                + "}";
    }
}