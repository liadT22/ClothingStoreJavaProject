package org.example.Classes;

import org.example.Classes.Enum.ProductCategory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Product {

    private String productID;
    private String name;
    private ProductCategory productCategory;
    private double buyPrice;

    private int quantity;

    private double sellPrice;

    public Product(){}


    //Constructor
    public Product(String productID, String name, ProductCategory productCategory, double buyPrice, int quantity) {
        this.productID = productID;
        this.name = name;
        this.productCategory = productCategory;
        this.buyPrice = buyPrice;
        this.quantity = quantity;
        double profit = 0.35;
        this.sellPrice = buyPrice + (buyPrice* profit);
    }

    //Getter and setters
    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductID(){
        return this.productID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return  this.name;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public void addPurchaseToSalesAnalytics(int quantitySold, String branchId){
        // Load the salesAnalytics.json file
        JSONParser parser = new JSONParser();
        try {
            JSONObject salesAnalytics = (JSONObject) parser.parse(new FileReader("salesAnalytics.json"));
            JSONArray sales = (JSONArray) salesAnalytics.get("sales");

            // Create a new sale entry
            JSONObject newSale = new JSONObject();
            newSale.put("productID", this.productID);
            newSale.put("branchID", branchId);
            newSale.put("quantitySold", quantitySold);
            newSale.put("sellPrice", this.sellPrice);
            newSale.put("totalAmount", this.sellPrice * quantitySold);

            // Add date-time for the sale
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            newSale.put("dateTime", now.format(formatter));

            // Add timestamp for the sale
            Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
            long timestamp = instant.getEpochSecond();
            newSale.put("timestamp", timestamp);

            // Add the new sale to the sales array
            sales.add(newSale);

            // Save the updated JSON back to the file
            FileWriter file = new FileWriter("salesAnalytics.json");
            file.write(salesAnalytics.toJSONString());
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
