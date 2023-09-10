package org.example.Classes.Customer;

import org.example.Classes.Enum.CustomerType;
import org.example.Classes.Product;

public class VIPCustomer extends Customer{

    public VIPCustomer(){super();}

    public VIPCustomer(String customerID, String name, String postalCode, String phone){
        super(customerID, name, postalCode, phone);
        this.setCustomerType(CustomerType.VIP);
        this.setDiscountPercent(0.10);
    }
}
