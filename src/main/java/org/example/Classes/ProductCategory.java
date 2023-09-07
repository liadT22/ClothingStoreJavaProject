package org.example.Classes;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class ProductCategory {
    private String categoryId;
    private String categoryName;

    public ProductCategory(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public JSONArray getProductsList() {
        JSONParser parser = new JSONParser();
        JSONArray productsList = new JSONArray();
        try {
            JSONObject catalog = (JSONObject) parser.parse(new FileReader("catalog.json"));
            JSONArray categories = (JSONArray) catalog.get("categories");

            // Iterate over categories to find the matching categoryID
            for (Object categoryObj : categories) {
                JSONObject category = (JSONObject) categoryObj;
                if (category.get("categoryID").equals(this.categoryId)) {
                    productsList = (JSONArray) category.get("products");
                    break;
                }
            }

            // Print the products list
            System.out.println("Products in category " + this.categoryName + ":");
            for (Object productObj : productsList) {
                JSONObject product = (JSONObject) productObj;
                System.out.println("Product ID: " + product.get("productID"));
                System.out.println("Name: " + product.get("name"));
                System.out.println("Price: " + product.get("price"));
                System.out.println("Sale Price: " + product.get("salePrice"));
                System.out.println("-----");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productsList;
    }
}
