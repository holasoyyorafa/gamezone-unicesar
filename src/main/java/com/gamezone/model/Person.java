package com.gamezone.model;
/**
 * Represents a person who interacts with the GameZone store.
 * This class contains the information shared by clients and sellers.
 */
public abstract class Person {

    private String id;
    private String name;
    private String phone;
    /**
 * Creates a person with basic personal information.
 *
 * @param identification number of the person
 * @param name full name of the person
 * @param phone contact phone*number
 */

    public Person(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    /**
 * Return the role of the person in the store.
 *
 * @return the role of the person
 */
    public abstract String getRole();
    /**
 * Returns the identification number.
 *
 * @return the identification number
 */
    public String getId() {
    return id;
}
/**
 * Updates the identification number.
 ** * @param id new identification number
 */
public void setId(String id) {
    this.id = id;
}
/**
 * Returns the full name.
 *
 * @return full name
 */

public String getName() {
    return name;
}
/**
 * Updates the full name.
 *
 * @param name new full name
 */

public void setName(String name) {
    this.name = name;
}
/**
 * Returns the contact phone number.
 *
 * @return contact phone number
 */
public String getPhone() {
    return phone;
}
/**
 * Updates the contact phone number.
 *
 * @param phone new contact phone number
 */

public void setPhone(String phone) {
    this.phone = phone;
}
}