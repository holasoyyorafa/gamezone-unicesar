flowchart TD
    UI[UI Layer\nConsoleUI]
    Service[Service Layer\nSaleService, ProductService...]
    Persistence[Persistence Layer\nSaleRepository...]
    Model[Model Layer\nSale, Product, Person...]

    UI -->|depends on| Service
    Service -->|depends on| Persistence
    Service -->|depends on| Model
    Persistence -->|depends on| Model
