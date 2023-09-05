package org.example;

import com.google.gson.Gson;
import org.example.Classes.Employee;

public class Main {
    public static void main(String[] args) {
        Employee Liad= new Employee("1", "Liad", "111", "1", "1", "1", "1");
        String json = new Gson().toJson(Liad);
        System.out.println(json);
        Employee Liad2 = new Gson().fromJson(json, Employee.class);
        System.out.println(Liad2.getEmployeeID());
    }
}