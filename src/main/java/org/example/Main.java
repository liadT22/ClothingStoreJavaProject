package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.Classes.Customer;
import org.example.Classes.CustomerManagement;
import org.example.Classes.Employee;
import org.example.Classes.EmployeeManagement;
import org.example.Classes.Enum.CustomerType;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException, FileNotFoundException {
        Employee Liad= new Employee("1", "Liad", "111", "1", "1", "1", "1");
        Employee Liad1 = new Employee("2", "Liad1", "111", "1", "1", "1", "1");
        List<Employee> list = new ArrayList<>();
        list.add(Liad);
        list.add(Liad1);
        ObjectMapper objectMapper = new ObjectMapper();
        String Json = new Gson().toJson(list);
        System.out.println(Json);
        JsonNode jsonArray = objectMapper.readTree(Json);
        List<Employee> list1 = new ArrayList<>();
        for (JsonNode element : jsonArray) {
            Employee object = objectMapper.treeToValue(element, Employee.class);
            System.out.println(object.getName());
            list1.add(object);
        }
        CustomerManagement customeManage = new CustomerManagement();
//        Customer Inbar = new Customer("1","Inbar","1","1",CustomerType.NEW);
//        Customer Inbar1 = new Customer("2","Inbar1","1","1",CustomerType.NEW);
//        Customer Inbar2 = new Customer("3","Inbar2","1","1",CustomerType.NEW);
//        try{
//            customeManage.addCustomer(Inbar);
//            customeManage.addCustomer(Inbar1);
//            customeManage.addCustomer(Inbar2);
//        } catch (FileNotFoundException e) {
//            System.out.println(e.getMessage());
//        }
    }
}