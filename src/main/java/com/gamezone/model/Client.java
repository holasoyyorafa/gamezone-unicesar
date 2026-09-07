package com.gamezone.model;
/**
 * Represents a client registered in the GameZone store.
 */
public class Client extends Person {

    private String email;
/**
 * Creates client with personal and contact information.
 * @param id identification number of the client
 * @param name full name of the client
 * @param phone contact phone number
 * @param email email address of the client
 */
    public Client(String id, String name, String phone, String email) {
        super(id, name, phone);
        this.email = email;
    }

    @Override
    /**
 * Returns the client role.
 *
 * @return the word Client
 */
    public String getRole() {
        return "Client";
    }
    /**
 * Returns the client's email address.
 *
 * @return email*address
 */
    public String getEmail() {
    return email;
}
/**
 * Updates the client's email address.
 *
 * @param email new email address
 */
public void setEmail(String email) {
    this.email = email;
}
@Override
public String toString() {
    return "Client {"
            + super.toString()
            + ", email: " + email
            + "}";
}
}