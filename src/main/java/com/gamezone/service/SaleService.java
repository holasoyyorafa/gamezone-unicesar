package com.gamezone.service;

import com.gamezone.model.Sale;
import com.gamezone.model.Client;
import com.gamezone.model.Seller;
import com.gamezone.model.Product;
import com.gamezone.persistence.SaleRepository;
import java.util.List;


public class SaleService {
    private SaleRepository saleRepository;
    private ProductService productService;

    public SaleService(SaleRepository saleRepository, ProductService productService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
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

        // Actualización de inventario (se persiste en disco a través de ProductService)
        for (Product p : products) {
            productService.updateStock(p.getId(), 1);
        }

        Sale sale = new Sale(date, client, seller, products);
        sale.calculateTotal();
        saleRepository.save(sale);
    }
}