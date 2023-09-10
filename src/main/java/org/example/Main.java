package org.example;

import org.example.Classes.Customer.Customer;
import org.example.Classes.Customer.CustomerManagement;
import org.example.Classes.Customer.NewCustomer;
import org.example.Classes.Employees.Manager;
import org.example.Classes.Enum.EmployeeType;
import org.example.Classes.Employees.Employee;
import org.example.Classes.Employees.EmployeeManagement;

import org.example.Classes.Enum.EproductCategory;
import org.example.Classes.Product;
import org.example.Classes.ProductCategory;
import utils.JSONHelper;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JSONHelper.checkAndCreateFiles();
        EmployeeManagement em = new EmployeeManagement();
        CustomerManagement customManage = new CustomerManagement();
        Product p = new Product("1", "1", EproductCategory.PANTS, 1.0, 5);
        Employee e1 = new Employee("123", "Tamara Slouzky", "1", "0532747988", "tamara", "1234", EmployeeType.CASHIER);
        Employee e2 = new Employee("124", "Israel Israeli", "1", "0522747988", "Is123", "5555", EmployeeType.FLOOR);
         Manager m = new Manager("1", "Manager", "1", "0502747988", "Mm123", "987");
        try {
            customManage.onBuyProduct(p, 2, "1","Inbar","1","1");
            customManage.onBuyProduct(p, 2, "1","Inbar","1","1");
             customManage.onBuyProduct(p, 2, "3","Inbar2","1","1");
            customManage.deleteCustomer("2");
             System.out.println(customManage.getCustomerDetails("1").getName());
             System.out.println(customManage.getCustomerDetails("3").getName());
            em.addEmployee(e1);
            em.addEmployee(m);
            em.addEmployee(e2);

            // em.deleteEmployee("124");
             Employee e3 = new Employee("123", "Tamara Slouzky", "1", "0532747988", "tamara", "1234", EmployeeType.FLOOR);
            em.updateEmployee(e3);
            System.out.println(em.getEmployeeDetails("123"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}