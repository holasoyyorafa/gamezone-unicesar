# Promotion Module - Class Diagram

```mermaid
classDiagram
    class Promotion {
        #String id
        #String name
        #LocalDate startDate
        #LocalDate endDate
        +isActive(LocalDate date) boolean
        +calculateDiscount(Sale sale)* double
    }

    class PercentageDiscount {
        -double percentage
        +calculateDiscount(Sale sale) double
    }

    class CategoryDiscount {
        -double percentage
        -String targetCategory
        +calculateDiscount(Sale sale) double
    }

    class BulkPurchaseDiscount {
        -int minQuantity
        -double percentage
        +calculateDiscount(Sale sale) double
    }

    Promotion <|-- PercentageDiscount
    Promotion <|-- CategoryDiscount
    Promotion <|-- BulkPurchaseDiscount

    class PromotionRepository {
        +saveAll(List<Promotion> promotions) void
        +loadAll() List<Promotion>
    }

    class PromotionService {
        -PromotionRepository repository
        +registerPercentageDiscount(...)
        +registerCategoryDiscount(...)
        +registerBulkPurchaseDiscount(...)
        +listAllPromotions() List<Promotion>
        +listActivePromotions() List<Promotion>
        +findBestPromotionFor(Sale sale) Promotion
        +findById(String id) Promotion
    }

    PromotionService --> PromotionRepository
    PromotionService ..> Promotion : manages

    class Sale {
        -String id
        -String client
        -String seller
        -List<Product> products
        -String appliedPromotionName
        -double discountAmount
        -double total
        +generateReceipt() String
    }

    class SaleService {
        +registerSale(...)
    }

    SaleService --> Sale : creates
    SaleService --> PromotionService : uses
    Sale --> Product : contains