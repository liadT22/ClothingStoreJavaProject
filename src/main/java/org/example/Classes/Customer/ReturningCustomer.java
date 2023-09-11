package org.example.Classes.Customer;

import org.example.Classes.Enum.CustomerType;
import org.example.Classes.Product;

public class ReturningCustomer extends Customer{

    public ReturningCustomer(){super();}

    public ReturningCustomer(String customerID, String name, String postalCode, String phone){
        super(customerID, name, postalCode, phone);
        this.setCustomerType(CustomerType.RETURNING);
        this.setDiscountPercent(0.05);
    }

}
