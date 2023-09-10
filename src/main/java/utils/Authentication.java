package utils;

import org.example.Classes.Employees.Employee;
import org.example.Classes.Employees.EmployeeManagement;

public class Authentication {

    public static Employee authLogin(String employeeID, String password){
        return EmployeeManagement.authLogin(employeeID, password);
    }
}
