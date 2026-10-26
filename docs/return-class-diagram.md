# Return Module - Class Diagram

```mermaid
classDiagram
    class Return {
        -String id
        -LocalDate date
        -Sale originalSale
        -List<Product> returnedProducts
        -String reason
        -double refundAmount
        +calculateRefundAmount() double
        +generateReturnReceipt() String
    }

    class Sale {
        -String id
        -LocalDate date
        -List<Product> products
        +canBeReturned() boolean
    }

    Return --> Sale : references

    class ReturnRepository {
        -String filePath
        +saveAll(List<Return> returns) void
        +loadAll() List<Return>
    }

    class ReturnService {
        -ReturnRepository returnRepository
        -SaleService saleService
        -ProductService productService
        +registerReturn(String saleId, List<String> productIds, String reason) Return
        +viewAllReturns() List<Return>
        +viewReturnsByCustomer(String customerId) List<Return>
        +viewReturnsBySale(String saleId) List<Return>
        +generateMonthlyBalance(int month, int year) double
    }

    ReturnService --> ReturnRepository
    ReturnService --> SaleService
    ReturnService --> ProductService
    ReturnService ..> Return : manages

    class ProductService {
        +restoreStock(String productId, int quantity) void
    }

    class ConsoleUI {
        +displayReturnSubmenu()
    }

    ConsoleUI --> ReturnService : uses