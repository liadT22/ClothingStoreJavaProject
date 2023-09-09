package org.example.Classes;

import java.util.ArrayList;
import java.util.List;

import org.example.Classes.Employees.Employee;

public class Branch {
    private String branchID;
    private String location;
    private List<Employee> employees;
    private List<Product> inventory;

    public Branch(String branchID, String location){
        this.branchID = branchID;
        this.location = location;
        this.employees = new ArrayList<Employee>();
        this.inventory = new ArrayList<Product>();
    }
}
