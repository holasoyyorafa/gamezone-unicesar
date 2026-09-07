package com.gamezone.model;

public class Client extends Person {

    private String email;

    public Client(String id, String name, String phone, String email) {
        super(id, name, phone);
        this.email = email;
    }

    @Override
    public String getRole() {
        return "Client";
    }
    public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}
}