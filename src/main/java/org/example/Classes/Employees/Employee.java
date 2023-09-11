package org.example.Classes.Employees;

import org.example.Classes.Enum.EmployeeType;

import java.io.Serializable;

public class Employee implements Serializable {
    // שם עובד, תז, מספר סניף, סיסמה, האם אדמין, מספר חשבון, תפקיד
    private String employeeID;
    private String name;
    private String branchID;
    private String phoneNumber;
    private String password;
    private String accountNumber;
    private EmployeeType position;
    private boolean isAdmin;

    private boolean isAdminPosition(EmployeeType position){
        if(position==EmployeeType.SHIFT_SUPERVISOR){
            return true;
        }
        else{
            return false;
        }
    }
    

    public Employee(){}
    public Employee(String employeeID,
    String name,
    String branchID,
    String phoneNumber,
    String password,
    String accountNumber,
    EmployeeType position){
        this.employeeID = employeeID;
        this.name = name;
        this.branchID = branchID;
        this.phoneNumber = phoneNumber;
        this.password=password;
        this.accountNumber=accountNumber;
        this.position = position;
       this.isAdmin=isAdminPosition(position);
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

    
    public String getBranchID(){
        return this.branchID;
    }
    public void setBranchID(String branchID){this.branchID = branchID;}

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phone){this.phoneNumber = phone;}

    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){ this.password=password;}

    public String getAccountNumber(){
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber){ this.accountNumber=accountNumber;}

    public EmployeeType getPosition(){
        return this.position;
    }

    public boolean getIsAdmin(){
        return this.isAdmin;
    }

    public void setPosition(EmployeeType position){
        this.position = position;
        this.isAdmin = isAdminPosition(position);
        
    }

    @Override
    public String toString() {
        return  "employeeID: "+this.employeeID+"\n"+
        "name: "+this.name+"\n"+
        "branchID: "+this.branchID+"\n"+
        "phoneNumber: "+this.phoneNumber+"\n"+
        "password: "+this.password+"\n"+
        "accountNumber: "+this.accountNumber+"\n"+
        "position: "+this.position ;
    }

    //modify files for admin
}
