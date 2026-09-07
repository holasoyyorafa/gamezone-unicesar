# Analysis - GameZone Unicesar

> This document must be written in English and contain the team's own
> answers, discussed and agreed upon before building the diagrams.
> Do not have an AI answer these questions directly (see the AI usage
> policy in the workshop statement).

## People in the system

1. What attributes are common to all people who interact with the store,
   and which are specific to each type of person? How is this distinction
   reflected in a class hierarchy?

  The common attributes for all people are name, identification (ID), and contact phone number. Specific attributes for clients include an email address and a purchase history. Specific attributes for sellers include an employee code and an assigned work shift. This distinction is reflected in a class hierarchy by creating a base class (Person) that holds the shared attributes, and two derived subclasses (Client and Seller) that inherit from the base class and define their own specific attributes.
  
2. Should there be a class representing a generic "person" without
   specifying a role? Why or why not? What implication does this decision
   have on the possibility of instantiating that class?

  Yes, there should be a generic Person class to group the shared attributes and avoid code duplication. However, because anyone interacting with the store always has a specific role (they are either a client or a seller), a generic person should not exist on its own. The implication of this design choice is that the Person class must be declared as abstract. This prevents the class from being instantiated directly, ensuring that the system only creates objects of the specific Client or Seller subclasses.

## Products in the system

3. What characteristics do all products sold by the store have in common,
   regardless of type? What characteristics are specific to each product type?

   _(answer)_

4. Each product type must be able to present a description integrating its
   particular characteristics. How should this behavior be declared in the
   base class to guarantee that all subclasses implement it in their own way?
   What OOP mechanism enables this?

   _(answer)_

## Sales and relationships between entities

5. A sale involves a customer, a seller, and one or more products. What
   kind of relationships exist between the Sale class and the other classes
   in the system? Are these relationships inheritance, association,
   composition, or another type? Justify.

   _(answer)_

6. Should Sale be responsible for calculating its own total, or should this
   responsibility fall to another class? Argue your decision.

   _(answer)_

## Business rules

7. How is it guaranteed in the design that a sale cannot be registered
   without at least one product? At what point in the system should this
   rule be validated?

   _(answer)_

8. How is the automatic inventory update reflected in the design when a
   sale is registered? Which classes are involved in this operation?

   _(answer)_

## Layered organization

9. The system must be organized into four layers: model, persistence,
   service, and UI. What kind of classes belong to each layer? What
   criterion determines which layer a class should belong to?

   _(answer)_

10. Why shouldn't the logic for saving and retrieving data from files be
    inside the domain classes? What problems arise when these
    responsibilities are mixed?

    _(answer)_

11. What dependencies are allowed between layers, and which are
    forbidden? Justify the direction of the allowed dependencies.

    _(answer)_
