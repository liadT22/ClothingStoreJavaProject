package org.example.Classes.Employees;
import java.util.ArrayList;
import java.util.List;

import org.example.Classes.Enum.EmployeeType;

public class Manager extends Employee{
    private List<Employee> employees;
    private EmployeeManagement employeeManagement;
    public Manager(){}

    public Manager(String employeeID,
    String name,
    String branchID,
    String phoneNumber,
    String password,
    String accountNumber){
        super(employeeID, name,branchID, phoneNumber, password, accountNumber,EmployeeType.SHIFT_SUPERVISOR);
        this.employeeManagement= new EmployeeManagement();
        this.employees=employeeManagement.getEmployeesByBranch(branchID);

    }

    @Override
    public String toString() {
        
        return super.toString()+"\n"+"employees: "+employees.toString();

    }

    public List<Employee> getEmployees(){return this.employees;}

    public void updateEmployee(Employee employee){
        employeeManagement.updateEmployee(employee);
    }


}
