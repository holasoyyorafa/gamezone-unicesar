package com.gamezone.model;
/**
 * Represents a seller who works at the GameZone store.
 */
public class Seller extends Person {

    private String employeeCode;
    private String workShift;
/**
 * Creates a seller with personal and employee information.
 *
 * @param id identification number of the seller
 * @param name full name of the seller
 * @param phone contact phone number
 * @param employeeCode code assigned by the store
 * @param workShift work shift assigned to the seller
 */
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
 /**
 * Returns the seller role.
 *
 * @return the word Seller
 */
    public String getRole() {
        return "Seller";
    }
/**
 * Returns the employee code.
 *
 * @return employee code
 */
    public String getEmployeeCode() {
        return employeeCode;
    }
/** 
 * Updates the employee code.
 *
 * @param employeeCode new employee code
*/
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    /** 
 * Returns the assigned work shift.
 *
 * @return assigned work shift
 */
        public String getWorkShift() {
        return workShift;
    }
/** 
 *Updates the assigned work shift.* 
 * @param workShift new work shift
 */
    public void setWorkShift(String workShift) {
        this.workShift = workShift;
    }
}
