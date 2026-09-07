package com.gamezone.model;

public class Seller extends Person {

    private String employeeCode;
    private String workShift;

    public Seller(
            String id,
            String name,
            String phone,
            String employeeCode,
            String workShift) {

        super(id, name, phone);
        this.employeeCode = employeeCode;
        this.workShift = workShift;
    }

    @Override
    public String getRole() {
        return "Seller";
    }
}