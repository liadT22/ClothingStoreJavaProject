package org.example.Classes;

import org.example.Classes.Enum.CustomerType;

public class Customer {
    private String customerID;
    private String name;
    private String postalCode;
    private String phone;
    private CustomerType customerType;

    public Customer(){}

    public Customer(String customerID, String name, String postalCode, String phone, CustomerType customerType){
        this.customerID = customerID;
        this.name = name;
        this.postalCode = postalCode;
        this.phone = phone;
        this.customerType = customerType;
    }
    public String getCustomerID(){
        return this.customerID;
    }

    public void setCustomerID(String customerID){
        this.customerID = customerID;
    }

    public void setPostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
