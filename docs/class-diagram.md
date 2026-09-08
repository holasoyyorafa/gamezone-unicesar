classDiagram
    %% Model Layer
    class Person {
        <<abstract>>
        -String id
        -String name
        -String phone
        +Person(id, name, phone)
        +getDetails()* String
    }
    class Client {
        -String email
        +Client(id, name, phone, email)
        +getDetails() String
    }
    class Seller {
        -String employeeCode
        -String shift
        +Seller(id, name, phone, employeeCode, shift)
        +getDetails() String
    }
    
    class Product {
        <<abstract>>
        -String id
        -String title
        -double price
        -int stock
        +Product(...)
        +getDescription()* String
    }
    
    class Sale {
        -String date
        -Client client
        -Seller seller
        -List~Product~ products
        -double total
        +Sale(date, client, seller, products)
        +calculateTotal() void
        +getTotal() double
    }

    Person <|-- Client
    Person <|-- Seller
    Product <|-- VideoGame
    Product <|-- Console
    Sale --> "1" Client
    Sale --> "1" Seller
    Sale o-- "1..*" Product

    %% Persistence Layer
    class SaleRepository {
        +save(Sale sale) void
        +findAll() List~Sale~
    }

    %% Service Layer
    class SaleService {
        -SaleRepository repository
        -ProductService productService
        +registerSale(date, client, seller, products) void
        +getSalesHistory() List~Sale~
    }

    %% UI Layer
    class ConsoleUI {
        -SaleService saleService
        +displayMenu() void
    }

    ConsoleUI --> SaleService
    SaleService --> SaleRepository
    SaleService --> Sale
