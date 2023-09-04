package com.example.ClothingStoreJavaProject.Classes;

import java.util.ArrayList;
import java.util.List;

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
