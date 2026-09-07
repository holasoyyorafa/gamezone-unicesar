package com.gamezone.service;

import com.gamezone.model.Sale;
import com.gamezone.model.Client;
import com.gamezone.model.Seller;
import com.gamezone.model.Product;
import com.gamezone.persistence.SaleRepository;
import java.util.List;


public class SaleService {
    private SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

   
   public void registerSale(String date, Client client, Seller seller, List<Product> products) throws Exception {
        if (products == null || products.isEmpty()) {
            throw new Exception("A sale must contain at least one product.");
        }

        // Verificación de stock suficiente
        for (Product p : products) {
            if (p.getStock() < 1) {
                throw new Exception("Insufficient stock for product: " + p.getId());
            }
        }

        // Actualización de inventario
        for (Product p : products) {
            p.setStock(p.getStock() - 1);
        }

        Sale sale = new Sale(date, client, seller, products);
        sale.calculateTotal();
        saleRepository.save(sale);
    }
}