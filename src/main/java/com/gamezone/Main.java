package com.gamezone;

import com.gamezone.persistence.PersonRepository;
import com.gamezone.persistence.SaleRepository;
import com.gamezone.service.PersonService;
import com.gamezone.service.ProductService;
import com.gamezone.service.SaleService;
import com.gamezone.ui.ConsoleUI;

/**
 * Entry point for the application.
 */
public class Main {
    public static void main(String[] args) {
        SaleRepository saleRepository = new SaleRepository();
        ProductService productService = new ProductService();
        SaleService saleService = new SaleService(saleRepository, productService);

        PersonRepository personRepository = new PersonRepository("data/people.txt");
        PersonService personService = new PersonService(personRepository);

        ConsoleUI consoleUI = new ConsoleUI(saleService, productService, personService);
        consoleUI.run();
    }
}
