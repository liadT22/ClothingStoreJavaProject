package org.example.Classes.Customer;


import org.example.Classes.BranchManagement;
import org.example.Classes.Enum.CustomerType;
import org.example.Classes.Product;

import java.io.Serializable;


public class Customer implements Serializable {
    private String customerID;
    private String name;
    private String postalCode;
    private String phone;
    private CustomerType customerType;

    private double discountPercent;

    public Customer(){}

    public Customer(String customerID, String name, String postalCode, String phone){
        this.customerID = customerID;
        this.name = name;
        this.postalCode = postalCode;
        this.phone = phone;
    }
    public String getCustomerID(){
        return this.customerID;
    }

    public String getName() {
        return name;
    }

    public CustomerType getCustomerType(){
        return this.customerType;
    }

    public String getPhone() {
        return phone;
    }

    public String getPostalCode() {
        return postalCode;
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

    public double getDiscountPercent() {
        return discountPercent;
    }

    protected void setCustomerType(CustomerType customerType) {
        this.customerType = customerType;
    }

    protected void setDiscountPercent(double discountPercent){
        this.discountPercent = discountPercent;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void buyProduct(Product product, String branchID){
        double finalPrice = product.getSellPrice();
        try{
            BranchManagement.getInstance().buyProductsFromBranch(branchID, product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finalPrice -= finalPrice * this.getDiscountPercent();
        System.out.println("You need to pay: " + finalPrice + "$");
    }
}
