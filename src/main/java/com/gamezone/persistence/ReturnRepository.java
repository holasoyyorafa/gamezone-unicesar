package com.gamezone.persistence;

import com.gamezone.model.Product;
import com.gamezone.model.Return;
import com.gamezone.model.Sale;
import com.gamezone.service.ProductService;
import com.gamezone.service.SaleService;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for managing return persistence using a CSV file.
 */
public class ReturnRepository {
    private final String filePath = "data/returns.csv";
    private final SaleService saleService;
    private final ProductService productService;

    public ReturnRepository(SaleService saleService, ProductService productService) {
        this.saleService = saleService;
        this.productService = productService;
        File file = new File(filePath);
        file.getParentFile().mkdirs();
    }

    public List<Return> loadAll() {
        List<Return> returns = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return returns;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(";");
                if (parts.length < 5) continue;

                String id = parts[0];
                LocalDate date = LocalDate.parse(parts[1]);
                String saleId = parts[2];
                String reason = parts[3];
                
                Sale sale = saleService.findById(saleId);
                
                List<Product> products = new ArrayList<>();
                String[] prodIds = parts[4].split(",");
                for (String pId : prodIds) {
                    Product p = productService.findById(pId.trim());
                    if (p != null) products.add(p);
                }

                returns.add(new Return(id, date, sale, products, reason));
            }
        } catch (IOException e) {
            System.out.println("Error loading returns: " + e.getMessage());
        }
        return returns;
    }

    public void saveAll(List<Return> returns) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Return r : returns) {
                StringBuilder prodIds = new StringBuilder();
                if (r.getReturnedProducts() != null) {
                    for (int i = 0; i < r.getReturnedProducts().size(); i++) {
                        prodIds.append(r.getReturnedProducts().get(i).getId());
                        if (i < r.getReturnedProducts().size() - 1) prodIds.append(",");
                    }
                }
                writer.println(r.getId() + ";" + r.getDate() + ";" + 
                        (r.getOriginalSale() != null ? r.getOriginalSale().getId() : "") + ";" + 
                        r.getReason() + ";" + prodIds.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving returns: " + e.getMessage());
        }
    }
}