/**
     * Displays the accessory management submenu and handles user choices.
     */
    public void showAccessoryMenu() {
        int option = 0;
        do {
            System.out.println("\n=== GESTIÓN DE ACCESORIOS ===");
            System.out.println("1. Registrar un nuevo control");
            System.out.println("2. Registrar un nuevo cable");
            System.out.println("3. Registrar una nueva memoria");
            System.out.println("4. Listar todos los accesorios");
            System.out.println("5. Listar accesorios por tipo");
            System.out.println("6. Consultar accesorios compatibles con una consola");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            
            option = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (option) {
                case 1:
                    /**
     * Handles the interactive workflow for registering a new sale,
     * allowing the selection of both traditional products and accessories.
     */
    public void registerSaleFlow() {
        try {
            System.out.println("\n=== REGISTRO DE NUEVA VENTA ===");
            // 1. Solicitar cliente y vendedor (según la lógica existente)
            // ...
            
            List<Product> purchasedItems = new ArrayList<>();
            int option = 0;
            //aqui registramos los productos o los accesorios
            do {
                System.out.println("\n¿Qué tipo de ítem desea agregar a la venta?");
                System.out.println("1. Producto tradicional (Consola/Videojuego)");
                System.out.println("2. Accesorio (Control/Cable/Memoria)");
                System.out.println("0. Finalizar selección de ítems y procesar venta");
                System.out.print("Seleccione una opción: ");
                option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                if (option == 1) {
                    // Lógica para listar productos y agregarlos a purchasedItems
                    System.out.println("[Seleccione el producto del inventario tradicional...] ");
                    // Ejemplo: productService.listAllProducts(), etc.
                } else if (option == 2) {
                    // Lógica para listar accesorios y agregarlos a purchasedItems
                    System.out.println("[Seleccione el accesorio del inventario de accesorios...] ");
                    // Ejemplo: accessoryService.listAllAccessories(), etc.
                }
            } while (option != 0);

            if (!purchasedItems.isEmpty()) {
                // Llamar al servicio de ventas unificado que actualizamos en el Commit 1
                // saleService.registerSale(date, client, seller, purchasedItems);
                System.out.println("¡Venta registrada con éxito!");
            } else {
                System.out.println("La venta fue cancelada porque no contenía ítems.");
            }

        } catch (Exception e) {
            System.out.println("Error al registrar la venta: " + e.getMessage());
        }
    }
                case 2:
                    // Lógica para registrar cable
                    System.out.println("[Registro de Cable en desarrollo]");
                    break;
                case 3:
                    // Lógica para registrar memoria
                    System.out.println("[Registro de Memoria en desarrollo]");
                    break;
                case 4:
                    // Listar todos
                    System.out.println("[Listando todos los accesorios]");
                    break;
                case 5:
                    // Listar por tipo
                    System.out.println("[Listando accesorios por tipo]");
                    break;
                case 6:
                    // Consultar compatibilidad con consola
                    System.out.println("[Consultando compatibilidad]");
                    break;
                case 0:
                    System.out.println("Regresando al menú principal...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (option != 0);
    }