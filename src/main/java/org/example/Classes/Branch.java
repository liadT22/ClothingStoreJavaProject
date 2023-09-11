package org.example.Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.example.Classes.Employees.Employee;

public class Branch implements Serializable {
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
                if(p.getQuantity() == 1){
                    this.inventory.remove(p);
                }else{
                    p.setQuantity(p.getQuantity() - 1);
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
