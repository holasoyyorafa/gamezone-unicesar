package com.gamezone.service;

import com.gamezone.model.Accessory;
import com.gamezone.model.Client;
import com.gamezone.model.Product;
import com.gamezone.model.Promotion;
import com.gamezone.model.Sale;
import com.gamezone.model.Seller;
import com.gamezone.persistence.PromotionRepository;
import com.gamezone.persistence.SaleRepository;
import java.util.List;

/**
 * Service class for managing sales business logic, stock verification, 
 * and inventory updates for both traditional products and accessories.
 */
public class SaleService {
    private SaleRepository saleRepository;
    private ProductService productService;
    private AccessoryService accessoryService;

    /**
     * Constructs a SaleService with the required repositories and dependent services.
     * 
     * @param saleRepository the sale repository
     * @param productService the product service
     * @param accessoryService the accessory service
     */
    public SaleService(SaleRepository saleRepository, ProductService productService, AccessoryService accessoryService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.accessoryService = accessoryService;
    }

    /**
     * Registers a new sale containing products or accessories, validating stock 
     * and delegating inventory updates to the respective service.
     * 
     * @param date the date of the sale
     * @param client the client making the purchase
     * @param seller the seller handling the transaction
     * @param products the list of items (products or accessories)
     * @throws Exception if validation fails
     */
    public void registerSale(String date, Client client, Seller seller, List<Product> products) throws Exception {
        if (products == null || products.isEmpty()) {
            throw new Exception("A sale must contain at least one product or accessory.");
        }

        // Stock verification for all items
        for (Product p : products) {
            if (p.getStock() < 1) {
                throw new Exception("Insufficient stock for item: " + p.getId());
            }
        }

        // Inventory update delegating to the corresponding service based on type
        for (Product p : products) {
            if (p instanceof Accessory) {
                accessoryService.updateStock(p.getId(), p.getStock() - 1);
            } else {
                productService.updateStock(p.getId(), p.getStock() - 1);
            }
        }

        Sale sale = new Sale(date, client, seller, products);
        sale.calculateTotal();

        // Promotion evaluation and best discount application
        PromotionRepository promoRepo = new PromotionRepository();
        PromotionService promoService = new PromotionService(promoRepo);
        Promotion bestPromo = promoService.findBestPromotionFor(sale);

        if (bestPromo != null) {
            double discount = bestPromo.calculateDiscount(sale);
            sale.setAppliedPromotionName(bestPromo.getName());
            sale.setDiscountAmount(discount);
            sale.setTotal(sale.getTotal() - discount);
        }

        saleRepository.save(sale);
    }
}