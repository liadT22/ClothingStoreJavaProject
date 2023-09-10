package org.example.Classes.Customer;

import org.example.Classes.Enum.CustomerType;
import org.example.Classes.Product;

public class NewCustomer extends Customer{

    public NewCustomer(){
        super();
    }
    public NewCustomer(String customerID, String name, String postalCode, String phone){
        super(customerID, name, postalCode, phone);
        this.setCustomerType(CustomerType.NEW);
        this.setDiscountPercent(0);
    }
}
