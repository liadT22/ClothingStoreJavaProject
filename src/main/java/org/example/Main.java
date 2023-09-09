package org.example;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.example.Classes.Customer;
import org.example.Classes.CustomerManagement;
import org.example.Classes.Employee;
import org.example.Classes.Enum.CustomerType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Employee Liad= new Employee();
        Employee Liad1 = new Employee();
        List<Employee> list = new ArrayList<>();
        list.add(Liad);
        list.add(Liad1);
        ObjectMapper objectMapper = new ObjectMapper();
        String Json = new Gson().toJson(list);
        System.out.println("Json"+Json);
        JsonNode jsonArray = objectMapper.readTree(Json);
        List<Employee> list1 = new ArrayList<>();
        for (JsonNode element : jsonArray) {
            Employee object = objectMapper.treeToValue(element, Employee.class);
            System.out.println(object.getName());
            list1.add(object);
        }
        CustomerManagement customManage = new CustomerManagement();
        Customer Inbar = new Customer("1","Inbar","1","1",CustomerType.NEW);
        Customer Inbar1 = new Customer("2","Inbar1","1","1",CustomerType.NEW);
        Customer Inbar2 = new Customer("3","Inbar2","1","1",CustomerType.NEW);
        try{
            customManage.addCustomer(Inbar);
            customManage.addCustomer(Inbar1);
            customManage.addCustomer(Inbar2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}