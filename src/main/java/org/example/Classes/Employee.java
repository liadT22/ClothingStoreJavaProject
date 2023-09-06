package org.example.Classes;

public class Employee {
    private String employeeID;
    private String name;
    private String ssn;
    private String phone;
    private String accountNumber;
    private String branch;
    private String position;

    public Employee(){}
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

    public void setEmployeeID(String employeeID){
        this.employeeID = employeeID;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){this.name = name;}

    public void setSsn(String ssn){this.ssn = ssn;}

    public void setPhone(String phone){this.phone = phone;}

    public void setAccountNumber(String accountNumber){this.accountNumber = accountNumber;}

    public void setBranch(String branch){this.branch = branch;}

    public void setPosition(String position){this.position = position;}
}
