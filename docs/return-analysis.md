# Return Module - Analysis and Architectural Design

## Guiding Questions & Answers

1. **What type of relationship exists between the Return class and the Sale class? Is it inheritance, association, aggregation, or composition? Justify.**
   - It is an **association** (specifically a unidirectional or bidirectional reference). It is not composition because the lifecycle of a `Sale` does not depend on a `Return` (a sale can exist without returns). It is not aggregation or inheritance; `Return` simply references an existing `Sale` object to track historical data.

2. **How is a partial return represented in the attributes of the Return class? What is stored in the returned products attribute?**
   - It is represented using a collection attribute (`List<Product> returnedProducts`). This list stores only the specific subset of products that the client is actually returning from the original sale, allowing partial returns while leaving other items with the client.

3. **In which layer of the system is the 30-day validation placed, and why? What Java mechanism is used to calculate the difference between two dates?**
   - It is placed in the **Service layer** (`ReturnService`), because business rules and validations belong to the application/business logic layer rather than the UI or persistence layers. The date difference is calculated using Java's `LocalDate` API, specifically `ChronoUnit.DAYS.between(saleDate, currentDate)` or `Period.between()`.

4. **Which existing method is reused for stock increment, and where is it invoked from? Why is it important to reuse existing methods instead of duplicating code?**
   - The new method `restoreStock(String productId, int quantity)` in `ProductService` (extending the inventory management logic) is invoked from `ReturnService`. Reusing methods prevents code duplication, centralizes stock modification logic, and ensures data consistency across inventory files.

5. **In which service class is the monthly balance report located, and why is this location coherent with layered architecture? What dependencies does it need?**
   - It is located in **`ReturnService`** (or a dedicated reporting/sales service). It is coherent with layered architecture because it consolidates domain data retrieved from both `SaleRepository` and `ReturnRepository`. It requires `ReturnRepository` and `SaleRepository` (or `SaleService`) as dependencies to fetch monthly transactions.