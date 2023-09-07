package org.example.Classes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;

public class Product {
    private String productID;
    private String name;
    private ProductCategory productCategory;
    private double price;
    private double salePrice;


    //Constructor
    public Product(String productID, String name, ProductCategory productCategory, double price, int quantity, double salePrice) {
        this.productID = productID;
        this.name = name;
        this.productCategory = productCategory;
        this.price = price;
        this.salePrice = salePrice;
        this.addProductToCatalog();
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

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    //Methods
    private void addProductToCatalog() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject productsData = (JSONObject) parser.parse(new FileReader("catalog.json"));
            JSONArray categories = (JSONArray) productsData.get("categories");
            boolean categoryExists = false;
            boolean productExists = false;

            for (Object categoryObj : categories) {
                JSONObject category = (JSONObject) categoryObj;
                if (category.get("categoryID").equals(this.productCategory.getCategoryId())) {
                    categoryExists = true;
                    JSONArray products = (JSONArray) category.get("products");

                    for (Object productObj : products) {
                        JSONObject product = (JSONObject) productObj;
                        if (product.get("productID").equals(this.productID)) {
                            productExists = true;
                            break;
                        }
                    }

                    if (!productExists) {
                        JSONObject newProduct = new JSONObject();
                        newProduct.put("productID", this.productID);
                        newProduct.put("name", this.name);
                        newProduct.put("price", this.price);
                        newProduct.put("salePrice", this.salePrice);
                        products.add(newProduct);
                    }
                    break;
                }
            }

            if (!categoryExists) {
                JSONObject newCategory = new JSONObject();
                newCategory.put("categoryID", this.productCategory.getCategoryId());
                JSONObject newProduct = new JSONObject();
                newProduct.put("productID", this.productID);
                newProduct.put("name", this.name);
                newProduct.put("price", this.price);
                newProduct.put("salePrice", this.salePrice);
                JSONArray newProducts = new JSONArray();
                newProducts.add(newProduct);
                newCategory.put("products", newProducts);
                categories.add(newCategory);
            }

            // Save the updated JSON back to the file
            FileWriter file = new FileWriter("productsData.json");
            file.write(productsData.toJSONString());
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void updateBranchStockAfterSale(int quantitySold, String branchId){
        // Load the JSON file
        JSONParser stockParser = new JSONParser();
        try {
            JSONObject branchStock = (JSONObject) stockParser.parse(new FileReader("branchStock.json"));
            JSONArray products = (JSONArray) branchStock.get("products");

            // Iterate over products to find the matching productID
            for (Object productObj : products) {
                JSONObject product = (JSONObject) productObj;
                if (product.get("productID").equals(this.productID)) {
                    JSONArray branches = (JSONArray) product.get("branches");

                    // Iterate over branches to find the matching branchID
                    for (Object branchObj : branches) {
                        JSONObject branch = (JSONObject) branchObj;
                        if (branch.get("branchID").equals(branchId)) {
                            int currentQuantity = ((Long) branch.get("quantity")).intValue();
                            branch.put("quantity", currentQuantity - quantitySold);
                            break;
                        }
                    }
                    break;
                }
            }

            // Save the updated JSON back to the file
            FileWriter file = new FileWriter("branchStock.json");
            file.write(branchStock.toJSONString());
            file.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateSalesAnalyticsAfterSale(int quantitySold, String branchId){
        try {
            JSONParser analysisParser = new JSONParser();
            JSONObject salesAnalytics = (JSONObject) analysisParser.parse(new FileReader("salesAnalytics.json"));
            JSONObject saleData = new JSONObject();
            saleData.put("productId", this.productID);
            saleData.put("quantitySold", quantitySold);
            saleData.put("totalSalePrice", this.salePrice * quantitySold);
            salesAnalytics.put(System.currentTimeMillis(), saleData); // Using current time as a unique sale identifier
            FileWriter file = new FileWriter("salesAnalytics.json");
            file.write(salesAnalytics.toJSONString());
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void sellProduct(int quantitySold, String branchId) {
        // Update branchStock.json
        this.updateBranchStockAfterSale(quantitySold, branchId);
        // Update salesAnalytics.json
        this.updateSalesAnalyticsAfterSale(quantitySold, branchId);
    }

}
