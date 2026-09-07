package com.gamezone;

import com.gamezone.persistence.SaleRepository;
import com.gamezone.service.SaleService;
import com.gamezone.ui.ConsoleUI;

/**
 * Entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        SaleRepository saleRepository = new SaleRepository();
        SaleService saleService = new SaleService(saleRepository);

        ConsoleUI consoleUI = new ConsoleUI(saleService);
        consoleUI.displayMainMenu();
    }
}