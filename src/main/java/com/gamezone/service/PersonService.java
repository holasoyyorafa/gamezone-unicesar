package com.gamezone.service;

import com.gamezone.model.Client;
import com.gamezone.model.Person;
import com.gamezone.model.Seller;
import com.gamezone.persistence.PersonRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * Provides registration and query operations for clients and sellers.
 */
public class PersonService {

    private final PersonRepository personRepository;
   private final List<Person> people;
  /**
 * Creates the service and loads the previously stored people.
 *
 * @param personRepository repository used for person persistence
 */
public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
    this.people = new ArrayList<>();
    loadPeople();
}
/**
 * Registers a new client if the identification number is not already used.
 *
 * @param client client to register
 * @return true if the client was registered, otherwise false
 */
public boolean registerClient(Client client) {
    if (client == null || hasPersonWithId(client.getId())) {
        return false;
    }

    return savePerson(client);
}
/**
 * Registers a new seller if the identification number is not already used.
 *
 * @param seller seller to register
 * @return true if the seller was registered, otherwise false
 */

public boolean registerSeller(Seller seller) {
    if (seller == null || hasPersonWithId(seller.getId())) {
        return false;
    }

    return savePerson(seller);
}
/**
 * Searches for a person using the identification number.
 *
 * @param id identification number to search
 * @return the matching person, or null if no person was found
 */
public Person findById(String id) {
    if (id == null) {
        return null;
    }

    for (Person person : people) {
        if (id.equals(person.getId())) {
            return person;
        }
    }

    return null;
}
/**
 * Returns all registered clients.
 *
 * @return list of registered clients
 */

public List<Client> listClients() {
    List<Client> clients = new ArrayList<>();

    for (Person person : people) {
        if (person instanceof Client) {
            clients.add((Client) person);
        }
    }

    return clients;
}
/**
 * Returns all registered sellers.
 *
 * @return list of registered sellers
 */
public List<Seller> listSellers() {
    List<Seller> sellers = new ArrayList<>();

    for (Person person : people) {
        if (person instanceof Seller) {
            sellers.add((Seller) person);
        }
    }

    return sellers;
}

private boolean hasPersonWithId(String id) {
    return findById(id) != null;
}private boolean savePerson(Person person) {
    try {
        personRepository.save(person);
        people.add(person);
        return true;
    } catch (IOException exception) {
        System.out.println(
                "The person could not be saved: "
                        + exception.getMessage());
        return false;
    }
}

private void loadPeople() {
    try {
        people.addAll(personRepository.load());
    } catch (IOException exception) {
        System.out.println(
                "The people could not be loaded: "
                        + exception.getMessage());
    }
}
}