package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Classes.Branch;
import org.example.Classes.BranchManagement;
import org.example.Classes.Customer.Customer;
import org.example.Classes.Customer.CustomerManagement;
import org.example.Classes.Employees.Manager;
import org.example.Classes.Enum.EmployeeType;
import org.example.Classes.Employees.Employee;
import org.example.Classes.Employees.EmployeeManagement;

import org.example.Classes.Enum.ProductCategory;
import org.example.Classes.Product;
import utils.JSONHelper;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JSONHelper.checkAndCreateFiles();
        try {
            checkBranches();
        } catch (Exception e) {
            System.out.println("Branches: " + e.getMessage());
        }
        try {
            checkCustomer();
        } catch (Exception e) {
            System.out.println("Customer: " + e.getMessage());
        }
        try {
            checkEmployee();
        } catch (Exception e) {
            System.out.println("Employee: " + e.getMessage());
        }
    }

    private static void checkBranches() throws FileNotFoundException, JsonProcessingException {
        BranchManagement br = new BranchManagement();
        Product p = new Product("1", "pants", ProductCategory.PANTS, 1.0, 5);
        Branch br1 = new Branch("1", "1");
        br.addBranch(br1);
        br.buyProductsForBranch("1", p);
        br.buyProductsForBranch("1", p);
        p.setQuantity(3);
        br.buyProductsFromBranch("1", p);
        br.buyProductsFromBranch("1", p);
        br.addBranch(br1);
    }
    private static void checkEmployee(){
        EmployeeManagement em = new EmployeeManagement();
        Employee e1 = new Employee("123", "Tamara Slouzky", "1", "0532747988", "tamara", "1234", EmployeeType.CASHIER);
        Employee e2 = new Employee("124", "Israel Israeli", "1", "0522747988", "Is123", "5555", EmployeeType.FLOOR);
        Manager m = new Manager("1", "Manager", "1", "0502747988", "Mm123", "987");

        em.addEmployee(e1);
        em.addEmployee(m);
        em.addEmployee(e2);
        // em.deleteEmployee("124");
        Employee e3 = new Employee("123", "Tamara Slouzky", "1", "0532747988", "tamara", "1234", EmployeeType.FLOOR);
        em.updateEmployee(e3);
        System.out.println(em.getEmployeeDetails("123"));
    }
    private static void checkCustomer() throws IOException {
        Customer customer = new Customer("5", "5", "5", "5");
        CustomerManagement.getInstance().onBuyProduct("1", "1", customer);
    }
}