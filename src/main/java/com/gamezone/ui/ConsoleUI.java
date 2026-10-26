package com.gamezone.ui;

import com.gamezone.model.Client;
import com.gamezone.model.Console;
import com.gamezone.model.Product;
import com.gamezone.model.Promotion;
import com.gamezone.model.Seller;
import com.gamezone.model.VideoGame;
import com.gamezone.persistence.PromotionRepository;
import com.gamezone.service.PersonService;
import com.gamezone.service.ProductService;
import com.gamezone.service.PromotionService;
import com.gamezone.service.SaleService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based user interface for GameZone Unicesar.
 * Handles the main menu and the product, person, sale and promotion submenus.
 */
public class ConsoleUI {

    private final SaleService saleService;
    private final ProductService productService;
    private final PersonService personService;
    private final PromotionService promotionService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(SaleService saleService, ProductService productService, PersonService personService) {
        this.saleService = saleService;
        this.productService = productService;
        this.personService = personService;
        this.promotionService = new PromotionService(new PromotionRepository());
    }

    /** Starts the main application loop. */
    public void run() {
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int option = readInt("Choose an option: ");
            switch (option) {
                case 1 -> displayProductSubmenu();
                case 2 -> displayPersonSubmenu();
                case 3 -> displaySaleSubmenu();
                case 4 -> displayPromotionSubmenu();
                case 5 -> {
                    exit = true;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option, try again.");
            }
        }
        scanner.close();
    }

    public void displayMainMenu() {
        System.out.println("\n=== GameZone Unicesar ===");
        System.out.println("1. Product Management");
        System.out.println("2. Person Management");
        System.out.println("3. Sale Management");
        System.out.println("4. Promotion Management");
        System.out.println("5. Exit");
    }

    // ---------------- PRODUCTS ----------------

    public void displayProductSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Product Submenu ---");
            System.out.println("1. Register video game");
            System.out.println("2. Register console");
            System.out.println("3. List products");
            System.out.println("4. Update stock");
            System.out.println("5. Back");
            int option = readInt("Choose an option: ");
            switch (option) {
                case 1 -> registerVideoGame();
                case 2 -> registerConsole();
                case 3 -> listProducts();
                case 4 -> updateStock();
                case 5 -> back = true;
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private void registerVideoGame() {
        System.out.println("-- New video game --");
        String id = readLine("ID: ");
        String title = readLine("Title: ");
        double price = readDouble("Price: ");
        int stock = readInt("Stock: ");
        String platform = readLine("Platform: ");
        String genre = readLine("Genre: ");
        String ageRating = readLine("Age rating: ");
        try {
            productService.registerProduct(new VideoGame(id, title, price, stock, platform, genre, ageRating));
            System.out.println("Video game registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register video game: " + e.getMessage());
        }
    }

    private void registerConsole() {
        System.out.println("-- New console --");
        String id = readLine("ID: ");
        String title = readLine("Title: ");
        double price = readDouble("Price: ");
        int stock = readInt("Stock: ");
        String brand = readLine("Brand: ");
        String model = readLine("Model: ");
        String generation = readLine("Generation: ");
        try {
            productService.registerProduct(new Console(id, title, price, stock, brand, model, generation));
            System.out.println("Console registered successfully.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register console: " + e.getMessage());
        }
    }

    private void listProducts() {
        List<Product> products = productService.listProducts();
        if (products.isEmpty()) {
            System.out.println("No products registered yet.");
            return;
        }
        System.out.println("-- Product list --");
        for (Product p : products) {
            System.out.println(p.describe());
        }
    }

    private void updateStock() {
        String id = readLine("Product ID: ");
        int quantity = readInt("Quantity to subtract: ");
        boolean updated = productService.updateStock(id, quantity);
        System.out.println(updated ? "Stock updated." : "Could not update stock (product not found or insufficient stock).");
    }

    // ---------------- PEOPLE ----------------

    public void displayPersonSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Person Submenu ---");
            System.out.println("1. Register client");
            System.out.println("2. Register seller");
            System.out.println("3. List clients");
            System.out.println("4. List sellers");
            System.out.println("5. Back");
            int option = readInt("Choose an option: ");
            switch (option) {
                case 1 -> registerClient();
                case 2 -> registerSeller();
                case 3 -> listClients();
                case 4 -> listSellers();
                case 5 -> back = true;
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private void registerClient() {
        System.out.println("-- New client --");
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        String phone = readLine("Phone: ");
        String email = readLine("Email: ");
        boolean ok = personService.registerClient(new Client(id, name, phone, email));
        System.out.println(ok ? "Client registered successfully." : "Could not register client (ID already used).");
    }

    private void registerSeller() {
        System.out.println("-- New seller --");
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        String phone = readLine("Phone: ");
        String employeeCode = readLine("Employee code: ");
        String workShift = readLine("Work shift: ");
        boolean ok = personService.registerSeller(new Seller(id, name, phone, employeeCode, workShift));
        System.out.println(ok ? "Seller registered successfully." : "Could not register seller (ID already used).");
    }

    private void listClients() {
        List<Client> clients = personService.listClients();
        if (clients.isEmpty()) {
            System.out.println("No clients registered yet.");
            return;
        }
        clients.forEach(c -> System.out.println(c.toString()));
    }

    private void listSellers() {
        List<Seller> sellers = personService.listSellers();
        if (sellers.isEmpty()) {
            System.out.println("No sellers registered yet.");
            return;
        }
        sellers.forEach(s -> System.out.println(s.toString()));
    }

    // ---------------- SALES ----------------

    public void displaySaleSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Sale Submenu ---");
            System.out.println("1. Register sale");
            System.out.println("2. Back");
            int option = readInt("Choose an option: ");
            switch (option) {
                case 1 -> registerSale();
                case 2 -> back = true;
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private void registerSale() {
        String clientId = readLine("Client ID: ");
        Object clientObj = personService.findById(clientId);
        if (!(clientObj instanceof Client client)) {
            System.out.println("Client not found.");
            return;
        }

        String sellerId = readLine("Seller ID: ");
        Object sellerObj = personService.findById(sellerId);
        if (!(sellerObj instanceof Seller seller)) {
            System.out.println("Seller not found.");
            return;
        }

        List<Product> products = new ArrayList<>();
        boolean addingProducts = true;
        while (addingProducts) {
            String productId = readLine("Product ID (blank to finish): ");
            if (productId.isBlank()) {
                addingProducts = false;
                continue;
            }
            Product product = productService.findById(productId);
            if (product == null) {
                System.out.println("Product not found, try again.");
                continue;
            }
            products.add(product);
        }

        String date = readLine("Sale date (e.g. 2026-09-07): ");

        try {
            saleService.registerSale(date, client, seller, products);
            System.out.println("Sale registered successfully.");
        } catch (Exception e) {
            System.out.println("Could not register sale: " + e.getMessage());
        }
    }

    // ---------------- PROMOTIONS ----------------

    public void displayPromotionSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Promotion Submenu ---");
            System.out.println("1. Register percentage discount");
            System.out.println("2. Register category discount");
            System.out.println("3. Register bulk purchase discount");
            System.out.println("4. List all promotions");
            System.out.println("5. List active promotions");
            System.out.println("6. Back");
            int option = readInt("Choose an option: ");
            switch (option) {
                case 1 -> registerPercentageDiscount();
                case 2 -> registerCategoryDiscount();
                case 3 -> registerBulkPurchaseDiscount();
                case 4 -> listAllPromotions();
                case 5 -> listActivePromotions();
                case 6 -> back = true;
                default -> System.out.println("Invalid option, try again.");
            }
        }
    }

    private void registerPercentageDiscount() {
        System.out.println("-- New Percentage Discount --");
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        LocalDate startDate = LocalDate.parse(readLine("Start date (YYYY-MM-DD): "));
        LocalDate endDate = LocalDate.parse(readLine("End date (YYYY-MM-DD): "));
        double percentage = readDouble("Percentage (e.g. 15.0): ");
        try {
            promotionService.registerPercentageDiscount(id, name, startDate, endDate, percentage);
            System.out.println("Percentage discount registered successfully.");
        } catch (Exception e) {
            System.out.println("Could not register promotion: " + e.getMessage());
        }
    }

    private void registerCategoryDiscount() {
        System.out.println("-- New Category Discount --");
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        LocalDate startDate = LocalDate.parse(readLine("Start date (YYYY-MM-DD): "));
        LocalDate endDate = LocalDate.parse(readLine("End date (YYYY-MM-DD): "));
        double percentage = readDouble("Percentage (e.g. 20.0): ");
        String category = readLine("Target category (VIDEOGAME or CONSOLE): ");
        try {
            promotionService.registerCategoryDiscount(id, name, startDate, endDate, percentage, category);
            System.out.println("Category discount registered successfully.");
        } catch (Exception e) {
            System.out.println("Could not register promotion: " + e.getMessage());
        }
    }

    private void registerBulkPurchaseDiscount() {
        System.out.println("-- New Bulk Purchase Discount --");
        String id = readLine("ID: ");
        String name = readLine("Name: ");
        LocalDate startDate = LocalDate.parse(readLine("Start date (YYYY-MM-DD): "));
        LocalDate endDate = LocalDate.parse(readLine("End date (YYYY-MM-DD): "));
        int minQuantity = readInt("Minimum quantity: ");
        double percentage = readDouble("Percentage (e.g. 10.0): ");
        try {
            promotionService.registerBulkPurchaseDiscount(id, name, startDate, endDate, minQuantity, percentage);
            System.out.println("Bulk purchase discount registered successfully.");
        } catch (Exception e) {
            System.out.println("Could not register promotion: " + e.getMessage());
        }
    }

    private void listAllPromotions() {
        List<Promotion> promotions = promotionService.listAllPromotions();
        if (promotions.isEmpty()) {
            System.out.println("No promotions registered yet.");
            return;
        }
        System.out.println("-- All Promotions --");
        for (Promotion p : promotions) {
            System.out.println("ID: " + p.getId() + " | Name: " + p.getName() + " | Start: " + p.getStartDate() + " | End: " + p.getEndDate());
        }
    }

    private void listActivePromotions() {
        List<Promotion> promotions = promotionService.listActivePromotions();
        if (promotions.isEmpty()) {
            System.out.println("No active promotions found.");
            return;
        }
        System.out.println("-- Active Promotions --");
        for (Promotion p : promotions) {
            System.out.println("ID: " + p.getId() + " | Name: " + p.getName() + " | Start: " + p.getStartDate() + " | End: " + p.getEndDate());
        }
    }

    // ---------------- INPUT HELPERS ----------------

    private String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}