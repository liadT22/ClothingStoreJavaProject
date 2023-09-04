package com.example.ClothingStoreJavaProject.Classes;

import java.util.ArrayList;
import java.util.List;

public class EmployeeManagement {
    private List<Employee> employees;

    public EmployeeManagement(){
        employees = new ArrayList<Employee>();
    }

    public void addEmployee(Employee employee) {
        this.employees.add(employee);
    }

    public void updateEmployee(Employee employee) {
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i).getEmployeeID() == employee.getEmployeeID()) {
                this.employees.set(i, employee);
            }
        }
    }

    public void deleteEmployee(String employeeID) {
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i).getEmployeeID() == employeeID) {
                this.employees.remove(i);
            }
        }
    }
    public Employee getEmployeeDetails(String employeeID) {
        for (int i = 0; i < this.employees.size(); i++) {
            if (this.employees.get(i).getEmployeeID() == employeeID) {
                return this.employees.get(i);
            }
        }
        throw new RuntimeException("Employee " + employeeID + " does not exist");
    }
}
