package org.example.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.Classes.Enum.CustomerType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CustomerManagement {
    private List<Customer> customers;

    private File customerFile;

    public CustomerManagement() throws IOException {
        this.customers = new ArrayList<>();
        // this.customerFile =  new File("C:\\temp\\customers.txt");
        // this.customerFile.createNewFile();
        // Scanner s = new Scanner(this.customerFile);
        // if(s.hasNext()){
        //     String json = s.nextLine();
        //     ObjectMapper objectMapper = new ObjectMapper();
        //     JsonNode jsonArray = objectMapper.readTree(json);
        //     for (JsonNode element : jsonArray) {
        //         Customer object = objectMapper.treeToValue(element, Customer.class);
        //         this.customers.add(object);
        //     }
        // }
        // s.close();
    }

    public void addCustomer(Customer customer) throws FileNotFoundException, JsonProcessingException {
        for (int i = 0; i < this.customers.size(); i++) {
            String str1 = this.customers.get(i).getCustomerID();
            String str2 = customer.getCustomerID();
            if (Objects.equals(str1, str2)) {
                throw new RuntimeException("Customer is already in the dataBase");
            }
        }
        this.customers.add(customer);
        PrintWriter pw = new PrintWriter(this.customerFile);
        ObjectMapper objectMapper = new ObjectMapper();
        String Json = new Gson().toJson(this.customers);
        System.out.println(Json);
        pw.print(Json);
        pw.close();
    }

    public void updateCustomer(Customer customer){
        for (int i = 0; i < this.customers.size(); i++) {
            if (this.customers.get(i).getCustomerID() == customer.getCustomerID()) {
                this.customers.set(i, customer);
            }
        }
    }

    public void deleteCustomer(String customerID){
        for (int i = 0; i < this.customers.size(); i++) {
            if (this.customers.get(i).getCustomerID() == customerID) {
                this.customers.remove(i);
            }
        }
    }

    public Customer getCustomerDetails(String customerID){
        for (int i = 0; i < this.customers.size(); i++) {
            if (this.customers.get(i).getCustomerID() == customerID) {
                return this.customers.get(i);
            }
        }
        throw new RuntimeException("customer is not in the database");
    }
}
