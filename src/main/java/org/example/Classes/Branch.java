package org.example.Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.example.Classes.Employees.Employee;

public class Branch {
    private String branchID;
    private String location;
    private List<Employee> employees;
    private List<Product> inventory;

    public Branch(){}

    public Branch(String branchID, String location){
        this.branchID = branchID;
        this.location = location;
        this.employees = new ArrayList<Employee>();
        this.inventory = new ArrayList<Product>();
    }

    public void addEmployee(Employee employee){
        this.employees.add(employee);
    }
    public void addProduct(Product product){
        boolean isProductAdded = false;
        for(Product p : this.inventory){
            if(Objects.equals(p.getProductID(), product.getProductID())){
                p.setQuantity( p.getQuantity() + product.getQuantity());
                isProductAdded = true;
                break;
            }
        }
        if(!isProductAdded){
            this.inventory.add(product);
        }
    }

    public void removeProduct(Product product){
        for(Product p : this.inventory){
            if(Objects.equals(p.getProductID(), product.getProductID())){
                if(p.getQuantity() - product.getQuantity() == 0){
                    this.inventory.remove(p);
                }else if(p.getQuantity() - product.getQuantity() < 0){
                    throw new RuntimeException("There is only " + p.getQuantity() +" " + p.getName() + " in the store.");
                }else{
                    p.setQuantity(p.getQuantity() - product.getQuantity());
                }
                break;
            }
        }
    }

    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setInventory(List<Product> inventory) {
        this.inventory = inventory;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public String getBranchID() {
        return branchID;
    }

    public List<Product> getInventory() {
        return inventory;
    }
}
