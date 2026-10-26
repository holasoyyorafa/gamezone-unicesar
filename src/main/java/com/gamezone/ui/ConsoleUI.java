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
                    // Lógica para registrar control (llamar a AccessoryService)
                    System.out.println("[Registro de Control en desarrollo]");
                    break;
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