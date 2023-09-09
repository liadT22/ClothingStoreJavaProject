package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.Classes.Customer;
import org.example.Classes.CustomerManagement;
import org.example.Classes.Employee;
import org.example.Classes.Enum.CustomerType;
import utils.JSONHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        JSONHelper.checkAndCreateFiles();
        CustomerManagement customManage = new CustomerManagement();
        Customer Inbar = new Customer("1","Inbar","1","1",CustomerType.NEW);
        Customer Inbar1 = new Customer("2","Inbar1","1","1",CustomerType.NEW);
        Customer Inbar2 = new Customer("3","Inbar2","1","1",CustomerType.NEW);
        Customer newInbar = new Customer("1","newInbar","2","2",CustomerType.NEW);
        try{
            customManage.addCustomer(Inbar);
            customManage.addCustomer(Inbar1);
            customManage.addCustomer(Inbar2);
            customManage.updateCustomer(newInbar);
            customManage.deleteCustomer("2");
            System.out.println(customManage.getCustomerDetails("1").getName());
            System.out.println(customManage.getCustomerDetails("3").getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}