package com.gamezone.service;

import com.gamezone.model.Product;
import com.gamezone.persistence.ProductRepository;

import java.util.List;

/**
 * Handles business logic for products: registration,
 * listing, and stock management.
 */
public class ProductService {

    private ProductRepository repository = new ProductRepository();
    private List<Product> products;

    public ProductService() {
        products = repository.loadAll();
    }

    public void registerProduct(Product product) {
        products.add(product);
        repository.save(products);
    }

    public List<Product> listProducts() {
        return products;
    }

    public boolean updateStock(String productId, int quantity) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                if (p.getStock() < quantity) {
                    return false;
                }
                p.setStock(p.getStock() - quantity);
                repository.save(products);
                return true;
            }
        }
        return false;
    }

    /**
     * Finds a product by its id.
     * Returns null if no product matches.
     */
    public Product findById(String productId) {
        for (Product p : products) {
            if (p.getId().equals(productId)) {
                return p;
            }
        }
        return null;
    }
}