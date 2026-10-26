package com.gamezone.ui;

import com.gamezone.model.Client;
import com.gamezone.model.Console;
import com.gamezone.model.Product;
import com.gamezone.model.Seller;
import com.gamezone.model.VideoGame;
import com.gamezone.persistence.ReturnRepository;
import com.gamezone.service.PersonService;
import com.gamezone.service.ProductService;
import com.gamezone.service.ReturnService;
import com.gamezone.service.SaleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Console-based user interface for GameZone Unicesar.
 * Handles the main menu and the product, person, sale and return submenus.
 */
public class ConsoleUI {

    private final SaleService saleService;
    private final ProductService productService;
    private final PersonService personService;
    private final ReturnService returnService;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleUI(SaleService saleService, ProductService productService, PersonService personService) {
        this.saleService = saleService;
        this.productService = productService;
        this.personService = personService;
        this.returnService = new ReturnService(new ReturnRepository(saleService, productService), saleService, productService);
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
                case 4 -> displayReturnSubmenu();
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
        System.out.println("4. Return Management");
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

    // ---------------- RETURNS ----------------

    public void displayReturnSubmenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Gestión de Devoluciones ---");
            System.out.println("1. Registrar nueva devolución");
            System.out.println("2. Consultar todas las devoluciones");
            System.out.println("3. Consultar devoluciones por cliente");
            System.out.println("4. Consultar devoluciones por venta");
            System.out.println("5. Consultar balance mensual");
            System.out.println("6. Volver");
            int option = readInt("Seleccione una opción: ");
            switch (option) {
                case 1 -> registerReturn();
                case 2 -> listAllReturns();
                case 3 -> listReturnsByCustomer();
                case 4 -> listReturnsBySale();
                case 5 -> showMonthlyBalance();
                case 6 -> back = true;
                default -> System.out.println("Opción inválida, intente de nuevo.");
            }
        }
    }

    private void registerReturn() {
        System.out.println("-- Registrar Devolución --");
        String saleId = readLine("ID de la venta original: ");
        List<String> productIds = new ArrayList<>();
        while (true) {
            String pId = readLine("ID del producto a devolver (dejar en blanco para terminar): ");
            if (pId.isBlank()) break;
            productIds.add(pId);
        }
        String reason = readLine("Motivo de la devolución: ");

        try {
            com.gamezone.model.Return ret = returnService.registerReturn(saleId, productIds, reason);
            System.out.println("¡Devolución registrada con éxito!");
            System.out.println(ret.generateReturnReceipt());
        } catch (Exception e) {
            System.out.println("No se pudo registrar la devolución: " + e.getMessage());
        }
    }

    private void listAllReturns() {
        List<com.gamezone.model.Return> returns = returnService.viewAllReturns();
        if (returns.isEmpty()) {
            System.out.println("No hay devoluciones registradas.");
            return;
        }
        System.out.println("-- Listado de Devoluciones --");
        for (com.gamezone.model.Return r : returns) {
            System.out.println("ID: " + r.getId() + " | Fecha: " + r.getDate() + " | Reembolso: $" + r.getRefundAmount());
        }
    }

    private void listReturnsByCustomer() {
        String customerId = readLine("ID del cliente: ");
        List<com.gamezone.model.Return> returns = returnService.viewReturnsByCustomer(customerId);
        if (returns.isEmpty()) {
            System.out.println("No hay devoluciones para este cliente.");
            return;
        }
        returns.forEach(r -> System.out.println("ID: " + r.getId() + " | Venta: " + r.getOriginalSale().getId() + " | Monto: $" + r.getRefundAmount()));
    }

    private void listReturnsBySale() {
        String saleId = readLine("ID de la venta: ");
        List<com.gamezone.model.Return> returns = returnService.viewReturnsBySale(saleId);
        if (returns.isEmpty()) {
            System.out.println("No hay devoluciones asociadas a esta venta.");
            return;
        }
        returns.forEach(r -> System.out.println("ID: " + r.getId() + " | Motivo: " + r.getRefundAmount()));
    }

    private void showMonthlyBalance() {
        int month = readInt("Ingrese el mes (1-12): ");
        int year = readInt("Ingrese el año (ej. 2026): ");
        double balance = returnService.generateMonthlyBalance(month, year);
        System.out.println("\n=== BALANCE MENSUAL (" + month + "/" + year + ") ===");
        System.out.println("Ingreso Neto (Ventas - Devoluciones): $" + balance);
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