package org.example.Classes.Employees;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import utils.JSONHelper;

public class EmployeeManagement {

    public EmployeeManagement() {
    }

    public List<Employee> getEmployeesFromFile() {
        try {
            String employeesJSON = JSONHelper.readFile("employees.json");
            List<Employee> employees = new ArrayList<Employee>();
            ObjectMapper objectMapper = new ObjectMapper();
            if (employeesJSON != null) {
                JsonNode jsonArray = objectMapper.readTree(employeesJSON);
                for (JsonNode element : jsonArray) {
                    JsonNode isAdminNode = element.get("isAdmin");
                    System.out.println(isAdminNode);
                    Employee object;
                    if (isAdminNode != null && isAdminNode.isBoolean()) {
                        if (isAdminNode.asBoolean()) {
                            object = objectMapper.treeToValue(element, Manager.class);
                        } else {
                            object = objectMapper.treeToValue(element, Employee.class);
                        }
                        System.out.println(object);
                        employees.add(object);
                    }

                }
            }
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
            employees = new ArrayList<Employee>();
        }
        String employeeJsonString = new Gson().toJson(employee);
        JsonObject jsonObject = JsonParser.parseString(employeeJsonString).getAsJsonObject();
        if (employee instanceof Manager) {
            jsonObject.remove("employees");
            jsonObject.remove("employeeManagement");
        }

        String json = new Gson().toJson(employees);
        JsonArray employeesArray = JsonParser.parseString(json).getAsJsonArray();
        employeesArray.add(jsonObject);
        String updatedEmployeesJson = employeesArray.toString();
        try {
            JSONHelper.writeToFile(updatedEmployeesJson, "employees.json");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateEmployee(Employee employee) {
        List<Employee> employees = getEmployeesFromFile();
        int i = 0;
        boolean isUserFound = false;
        if (employees != null) {
            while (i < employees.size() && !isUserFound) {
                if (Objects.equals(employees.get(i).getEmployeeID(), employee.getEmployeeID())) {
                    employees.remove(i);
                    isUserFound = true;
                }
                i++;
            }
        } else {
            employees = new ArrayList<Employee>();
        }
        if (isUserFound) {
            employees.add(employee);
            String json = new Gson().toJson(employees);
            try {
                JSONHelper.writeToFile(json, "employees.json");
            } catch (FileNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Could not find user to update");
        }

    }

    public void deleteEmployee(String employeeID) {
        List<Employee> employees = getEmployeesFromFile();
        int i = 0;
        boolean isUserFound = false;
        System.out.println("employees.length" + employees.size());
        if (employees != null) {
            while (i < employees.size() && !isUserFound) {
                if (Objects.equals(employees.get(i).getEmployeeID(), employeeID)) {
                    employees.remove(i);
                    isUserFound = true;
                }
                i++;
            }
            System.out.println("employees.length2" + employees.size());
            if (isUserFound) {
                String json = new Gson().toJson(employees);
                try {
                    JSONHelper.writeToFile(json, "employees.json");
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        System.out.println("Could not find user to delete");

    }

    public Employee getEmployeeDetails(String employeeID) {
        List<Employee> employees = getEmployeesFromFile();
        int i = 0;
        if (employees != null) {
            while (i < employees.size()) {
                if (Objects.equals(employees.get(i).getEmployeeID(), employeeID)) {
                    return employees.get(i);
                }
                i++;
            }
        }
        System.out.println("Could not find user with this ID");
        return null;
    }
}
