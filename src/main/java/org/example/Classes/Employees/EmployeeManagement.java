package org.example.Classes.Employees;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import utils.JSONHelper;

public class EmployeeManagement {

    public EmployeeManagement() {
    }

    private List<Employee> getEmployeesFromFile() {
        try {
            String employeesJSON = JSONHelper.readFile("employees.json");
            List<Employee> employees = new ArrayList<Employee>();
            ObjectMapper objectMapper = new ObjectMapper();
            // if (employeesJSON != null) {
            JsonNode jsonArray = objectMapper.readTree(employeesJSON);
            for (JsonNode element : jsonArray) {
                Employee object = objectMapper.treeToValue(element, Employee.class);
                employees.add(object);
            }
            // }
            return employees;

        } catch (FileNotFoundException e) {
            return null;

        } catch (JsonProcessingException e) {
            return null;
        }

    }

    public List<Employee> getEmployeesByBranch(String branchID) {
        List<Employee> employees = getEmployeesFromFile();
        for (int i = 0; i < employees.size(); i++) {
            if (!employees.get(i).getBranchID().equals(branchID)) {
                employees.remove(i);
            }
        }
        return employees;

    }

    public void addEmployee(Employee employee) {
        List<Employee> employees = getEmployeesFromFile();
        if (employees != null) {
            for (int i = 0; i < employees.size(); i++) {
                if (Objects.equals(employees.get(i).getEmployeeID(), employee.getEmployeeID())) {
                    throw new RuntimeException("Employee is already exists");
                }
            }
        } else {
            employees = new ArrayList<Employee>(null);
        }
        employees.add(employee);
        String json = new Gson().toJson(employees);
        try {
            JSONHelper.writeToFile(json, "employees.json");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateEmployee(Employee employee) {
        try {
            String employeesJSON = JSONHelper.readFile("employees.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // find by id
        // write to json file with json helper

    }

    public void deleteEmployee(String employeeID) {
        // for (int i = 0; i < this.employees.size(); i++) {
        // if (this.employees.get(i).getEmployeeID() == employeeID) {
        // this.employees.remove(i);
        // }
        // }
    }

    public Employee getEmployeeDetails(String employeeID) {
        // for (int i = 0; i < this.employees.size(); i++) {
        // if (this.employees.get(i).getEmployeeID() == employeeID) {
        // return this.employees.get(i);
        // }
        // }
        throw new RuntimeException("Employee " + employeeID + " does not exist");
    }
}
