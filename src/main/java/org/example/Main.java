package org.example;

import org.example.Classes.Employees.Manager;
import org.example.Classes.Enum.CustomerType;
import org.example.Classes.Enum.EmployeeType;
import org.example.Classes.Customer;
import org.example.Classes.CustomerManagement;
import org.example.Classes.Employees.Employee;
import org.example.Classes.Employees.EmployeeManagement;

import utils.JSONHelper;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JSONHelper.checkAndCreateFiles();
        EmployeeManagement em = new EmployeeManagement();
        // CustomerManagement customManage = new CustomerManagement();
        Employee e1 = new Employee("123", "Tamara Slouzky", "1", "0532747988", "tamara", "1234", EmployeeType.CASHIER);
        Employee e2 = new Employee("124", "Israel Israeli", "1", "0522747988", "Is123", "5555", EmployeeType.FLOOR);
         Manager m = new Manager("1", "Manager", "1", "0502747988", "Mm123", "987");
        // Customer Inbar = new Customer("1","Inbar","1","1",CustomerType.NEW);
        // Customer Inbar1 = new Customer("2","Inbar1","1","1",CustomerType.NEW);
        // Customer Inbar2 = new Customer("3","Inbar2","1","1",CustomerType.NEW);
        // Customer newInbar = new Customer("1","newInbar","2","2",CustomerType.NEW);

        try {
            // customManage.addCustomer(Inbar);
            // customManage.addCustomer(Inbar1);
            // customManage.addCustomer(Inbar2);
            // customManage.updateCustomer(newInbar);
            // customManage.deleteCustomer("2");
            // System.out.println(customManage.getCustomerDetails("1").getName());
            // System.out.println(customManage.getCustomerDetails("2").getName());
            // System.out.println(customManage.getCustomerDetails("3").getName());
            em.addEmployee(e1);
            em.addEmployee(m);
            em.addEmployee(e2);

           
            // System.out.println("m" + m.toString());
            // em.addEmployee(m);

            // em.deleteEmployee("124");
            //  Employee e3 = new Employee("123", "Tamara Slouzky", "1", "0532747988", "tamara", "1234", EmployeeType.FLOOR);
            // em.updateEmployee(e3);
            // em.addEmployee(e2);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}