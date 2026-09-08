package com.gamezone.persistence;

import com.gamezone.model.Console;
import com.gamezone.model.Product;
import com.gamezone.model.VideoGame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private final String filePath = "data/products.txt";

    public void save(List<Product> products) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Product p : products) {
                if (p instanceof VideoGame) {
                    VideoGame vg = (VideoGame) p;
                    writer.println(String.join("|", "VIDEOGAME", vg.getId(), vg.getTitle(),
                            String.valueOf(vg.getPrice()), String.valueOf(vg.getStock()),
                            vg.getPlatform(), vg.getGenre(), vg.getAgeRating()));
                } else if (p instanceof Console) {
                    Console c = (Console) p;
                    writer.println(String.join("|", "CONSOLE", c.getId(), c.getTitle(),
                            String.valueOf(c.getPrice()), String.valueOf(c.getStock()),
                            c.getBrand(), c.getModel(), c.getGeneration()));
                }
            }
            } catch (IOException e) {
               System.out.println("Error saving products: " + e.getMessage());
                  e.printStackTrace();
            }
    } 

    public List<Product> loadAll() {
        List<Product> products = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return products;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|", -1);
                     if (parts.length < 8) continue;
                if (parts[0].equals("VIDEOGAME")) {
                    products.add(new VideoGame(parts[1], parts[2], Double.parseDouble(parts[3]),
                            Integer.parseInt(parts[4]), parts[5], parts[6], parts[7]));
                } else if (parts[0].equals("CONSOLE")) {
                    products.add(new Console(parts[1], parts[2], Double.parseDouble(parts[3]),
                            Integer.parseInt(parts[4]), parts[5], parts[6], parts[7]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading products: " + e.getMessage());
        }

        return products;
    }
}