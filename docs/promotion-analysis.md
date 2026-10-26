# Promotion Module - Analysis and Design

## 1. Hierarchy and Polymorphism
The three types of promotions share common attributes (id, name, startDate, endDate) and behaviors (vigency validation). This is reflected through an abstract base class `Promotion` that encapsulates these commonalities. Polymorphism allows each concrete subclass (`PercentageDiscount`, `CategoryDiscount`, `BulkPurchaseDiscount`) to implement its own specific `calculateDiscount(Sale sale)` logic. Consequently, the rest of the system (such as the service layer) interacts with the generic `Promotion` type without needing to know or handle the concrete subclasses explicitly.

## 2. Abstract Method Declaration
The `calculateDiscount(Sale sale)` method is declared as `abstract` inside the `Promotion` class. This forces all concrete subclasses to provide their own implementation of the method, guaranteeing that any promotion object instantiated in the system will safely provide a valid discount calculation behavior.

## 3. Location of Selection Logic
The business logic to select the best promotion (`findBestPromotionFor`) is located within `PromotionService` in the service layer. This location complies with the architectural principle of separating business logic from persistence and presentation layers. It avoids bloating the `Sale` model with business rules and keeps the console menu (`ConsoleMenu`) strictly focused on user interaction.

## 4. Modifications to Sale and Receipt
The `Sale` class requires two new private attributes: `appliedPromotionName` (String) and `discountAmount` (double), along with their respective getters and setters[cite: 18]. The `generateReceipt` method must be updated to display the subtotal, the applied discount name and amount, and the final total[cite: 18]. These additions are strictly additive and do not break any existing functionality in the system.

## 5. Vigency Validation
The vigency validation is performed using the `isActive(LocalDate date)` method inside the `Promotion` class itself. The `PromotionService` utilizes this method to filter out expired or future promotions before evaluating which one offers the highest discount.