package com.gamezone.ui;

import com.gamezone.service.SaleService;


public class ConsoleUI {
    private SaleService saleService;

    public ConsoleUI(SaleService saleService) {
        this.saleService = saleService;
    }

   
    public void displayMainMenu() {
        System.out.println("=== GameZone Unicesar ===");
        System.out.println("1. Product Management");
        System.out.println("2. Person Management");
        System.out.println("3. Sale Management");
        System.out.println("4. Exit");
    }
}