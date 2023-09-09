package org.example.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.Classes.Enum.CustomerType;
import utils.JSONHelper;

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

    private void writeToFile() throws FileNotFoundException {
        JSONHelper.writeToFile(JSONHelper.convertListToJson(this.customers), "customers.json");
    }

    private void readFile() throws JsonProcessingException, FileNotFoundException {
        String json = JSONHelper.readFile("customers.json");
        this.customers.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(json);
        for (JsonNode element : jsonArray) {
            Customer object = objectMapper.treeToValue(element, Customer.class);
            this.customers.add(object);
        }
    }

    public CustomerManagement() throws IOException {
        this.customers = new ArrayList<>();
        this.readFile();
    }

    public void addCustomer(Customer customer) throws FileNotFoundException, JsonProcessingException {
        this.readFile();
        for (int i = 0; i < this.customers.size(); i++) {
            if (Objects.equals(this.customers.get(i).getCustomerID(), customer.getCustomerID())) {
                throw new RuntimeException("Customer is already in the dataBase");
            }
        }
        this.customers.add(customer);
        this.writeToFile();
    }

    public void updateCustomer(Customer customer) throws FileNotFoundException, JsonProcessingException {
        this.readFile();
        for (int i = 0; i < this.customers.size(); i++) {
            if (Objects.equals(this.customers.get(i).getCustomerID(), customer.getCustomerID())) {
                this.customers.set(i, customer);
            }
        }
        this.writeToFile();
    }

    public void deleteCustomer(String customerID) throws FileNotFoundException, JsonProcessingException {
        this.readFile();
        for (int i = 0; i < this.customers.size(); i++) {
            if (Objects.equals(this.customers.get(i).getCustomerID(), customerID)) {
                this.customers.remove(i);
                break;
            }
        }
        this.writeToFile();
    }

    public Customer getCustomerDetails(String customerID) throws FileNotFoundException, JsonProcessingException {
        this.readFile();
        for (int i = 0; i < this.customers.size(); i++) {
            if (Objects.equals(this.customers.get(i).getCustomerID(), customerID)) {
                return this.customers.get(i);
            }
        }
        throw new RuntimeException("customer is not in the database");
    }
}
