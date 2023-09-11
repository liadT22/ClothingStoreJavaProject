package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.Classes.Branch;
import org.example.Classes.BranchManagement;
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
//        try {
//            checkCustomer();
//        } catch (Exception e) {
//            System.out.println("Customer: " + e.getMessage());
//        }
//        try {
//            checkEmployee();
//        } catch (Exception e) {
//            System.out.println("Employee: " + e.getMessage());
//        }
        try {
            checkBranches();
        } catch (Exception e) {
            System.out.println("Branches: " + e.getMessage());
        }

    }

    private static void checkBranches() throws FileNotFoundException, JsonProcessingException {
        BranchManagement br = new BranchManagement();
        Product p = new Product("1", "pants", ProductCategory.PANTS, 1.0, 5);
        Branch br1 = new Branch("1", "1");
        br.addBranch(br1);
        br.buyProductsForBranch("1", p);
        p.setQuantity(3);
        br.sellProductsFromBranch("1", p);
        br.sellProductsFromBranch("1", p);
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
        Product p = new Product("1", "1", ProductCategory.PANTS, 1.0, 5);
        CustomerManagement customManage = new CustomerManagement();
        customManage.onBuyProduct(p, "1", 2, "1","Inbar","2","1");
        customManage.onBuyProduct(p,"1", 2, "1","Inbar","1","1");
        customManage.onBuyProduct(p,"1", 2, "3","Inbar2","1","1");
        customManage.deleteCustomer("2");
        System.out.println(customManage.getCustomerDetails("1").getName());
        System.out.println(customManage.getCustomerDetails("3").getName());
    }
}