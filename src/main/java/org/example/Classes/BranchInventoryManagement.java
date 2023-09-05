package org.example.Classes;

import java.util.ArrayList;
import java.util.List;

public class BranchInventoryManagement {
    private List<Product> stock;

    public BranchInventoryManagement(){
        this.stock = new ArrayList<Product>();
    }

    public List<Product> displayStock(){
        return this.stock;
    }

    public void addProduct(Product product){
        stock.add(product);
    }

    public void buyProduct(String productID, int quantity){
        for(int i = 0; i < this.stock.size(); i++){
            if(stock.get(i).getProductID() == productID){
                stock.get(i).addQuantity(quantity);
            }
        }
    }
    public void sellProduct(String productID, int quantity){
        for(int i = 0; i < this.stock.size(); i++){
            if(stock.get(i).getProductID() == productID){
                if(stock.get(i).getQuantity() > quantity){
                    this.stock.get(i).lowerQuantity(quantity);
                }
                else{
                    throw new RuntimeException("You are trying to buy " + quantity + " " + this.stock.get(i).getName() + " but we only have " + stock.get(i).getQuantity() + " " + this.stock.get(i).getName() + " available in the store");
                }
            }
        }
    }
}
