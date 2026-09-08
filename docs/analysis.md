# Analysis — GameZone Unicesar

## People

**1. Common vs specific attributes / hierarchy**
All people share `id`, `name`, and `phone`. Clients add `email` and `purchaseHistory`. Sellers add `employeeCode` and `shift`. This maps to a `Person` base class with `Client` and `Seller` as subclasses.

**2. Generic "Person" class**
Yes, `Person` should exist, but as an **abstract class**. It centralizes shared attributes/behavior and avoids duplication, but the system never deals with a person who is neither a client nor a seller — so it must not be instantiable directly.

## Products

**3. Common vs specific characteristics**
All products share `id`, `title`, `price`, and `stock`. `VideoGame` adds `platform`, `genre`, `ageRating`. `Console` adds `brand`, `model`, `generation`.

**4. Description behavior**
`Product` declares `describe()` as an **abstract method**. Each subclass provides its own implementation using **method overriding (polymorphism)**, guaranteeing every subclass supplies a description that includes its particular attributes.

## Sales and relationships

**5. Relationships involving Sale**
- `Sale` – `Client`: association (a sale references one client).
- `Sale` – `Seller`: association (a sale references one seller).
- `Sale` – `Product`: association with multiplicity `1..*` (a sale involves one or more products), not composition, since products exist independently of any sale.

**6. Who calculates the total**
`Sale` should calculate its own total, since the total is intrinsic data of the sale itself (sum of its products' prices). Delegating it elsewhere would break encapsulation and scatter business logic that belongs to the `Sale` entity.

## Business rules

**7. Minimum one product**
Enforced in the constructor/registration logic of `Sale` (model) and validated again in `SaleService` before persisting — the service layer is the right place to reject an invalid sale before it reaches persistence.

**8. Automatic inventory update**
When a sale is registered, `SaleService` invokes `ProductService` to decrease stock for each purchased product. This keeps the model layer free of cross-module logic while services coordinate the update.

## Layers

**9. Class-to-layer criteria**
- **Model**: domain entities (`Person`, `Client`, `Seller`, `Product`, `VideoGame`, `Console`, `Sale`).
- **Persistence**: classes reading/writing files (`ProductRepository`, `PersonRepository`, `SaleRepository`).
- **Service**: business rules and validation (`ProductService`, `PersonService`, `SaleService`).
- **UI**: console menu classes.
The criterion is responsibility: does the class represent business data (model), store/retrieve it (persistence), enforce rules (service), or interact with the user (ui)?

**10. Why file logic doesn't belong in the model**
Mixing persistence into domain classes couples business data to storage format, making the domain harder to test, reuse, or change storage strategy without touching core logic — violating separation of concerns.

**11. Allowed vs forbidden dependencies**
Allowed: `ui → service → persistence → model`. Forbidden: `model` depending on any other layer, `ui` accessing `persistence` directly. This keeps the domain independent and forces all data access through validated business rules.
