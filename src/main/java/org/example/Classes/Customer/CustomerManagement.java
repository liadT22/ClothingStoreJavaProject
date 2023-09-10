package org.example.Classes.Customer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Classes.Customer.Customer;
import org.example.Classes.Employees.Employee;
import org.example.Classes.Employees.Manager;
import org.example.Classes.Enum.CustomerType;
import org.example.Classes.Product;
import utils.JSONHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            try{
                Customer object = objectMapper.treeToValue(element, Customer.class);
                this.customers.add(object);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
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
                try{
                    this.customers.set(i, customer);
                    break;
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }
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
        for (Customer customer : this.customers) {
            if (Objects.equals(customer.getCustomerID(), customerID)) {
                return customer;
            }
        }
        throw new RuntimeException("customer is not in the database");
    }

    public void onBuyProduct(Product product, int amount, String customerID, String name, String postalCode, String phone) throws FileNotFoundException, JsonProcessingException {
        this.readFile();
        Customer upgradedCustomer = null;
        boolean isCustomerExist = false;
        for (Customer customer : this.customers) {
            if (Objects.equals(customer.getCustomerID(), customerID)) {
                isCustomerExist = true;
                if (customer.getCustomerType() == CustomerType.NEW) {
                    upgradedCustomer = new ReturningCustomer(customer.getCustomerID(), customer.getName(), customer.getPostalCode(), customer.getPhone());
                } else if (customer.getCustomerType() == CustomerType.RETURNING) {
                    upgradedCustomer = new VIPCustomer(customer.getCustomerID(), customer.getName(), customer.getPostalCode(), customer.getPhone());
                }
                assert upgradedCustomer != null;
                upgradedCustomer.buyProduct(product, amount);
                this.updateCustomer(upgradedCustomer);
                break;
            }
        }
        if(!isCustomerExist){
            Customer newCustomer = new NewCustomer(customerID, name, postalCode, phone);
            newCustomer.buyProduct(product, amount);
            addCustomer(newCustomer);
        }
    }
}
