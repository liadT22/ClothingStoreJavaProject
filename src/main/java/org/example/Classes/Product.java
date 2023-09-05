package org.example.Classes;

public class Product {
    private String productID;
    private String name;
    private Double price;
    private int quantity;

    public Product(String productID, String name, Double price, int quantity){
        this.productID = productID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductID(){
        return this.productID;
    }
    public String getName(){
        return  this.name;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }
    public void lowerQuantity(int quantity){
        this.quantity -= quantity;
    }
    public int getQuantity(){
        return this.quantity;
    }
}
