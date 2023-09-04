package com.example.ClothingStoreJavaProject.Classes;

public class Employee {
    private String employeeID;
    private String name;
    private String ssn;
    private String phone;
    private String accountNumber;
    private String branch;
    private String position;

    public Employee(String employeeID, String name, String ssn, String phone, String accountNumber, String branch, String position){
        this.employeeID = employeeID;
        this.name = name;
        this.ssn = ssn;
        this.phone = phone;
        this.accountNumber = accountNumber;
        this.branch = branch;
        this.position = position;
    }

    public String getEmployeeID(){
        return this.employeeID;
    }

    public String getName(){
        return this.name;
    }
}
