package org.example.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.JSONHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BranchManagement {
    private List<Branch> branches;

    private static BranchManagement branchInstance = null;

    public static BranchManagement getInstance() throws FileNotFoundException, JsonProcessingException {
        if (branchInstance == null)
            branchInstance = new BranchManagement();
        return branchInstance;
    }

    public BranchManagement() throws FileNotFoundException, JsonProcessingException {
        this.branches = new ArrayList<Branch>();
        this.readBranchFile();
    }

    private void writeToBranchesFile() throws FileNotFoundException {
        JSONHelper.writeToFile(JSONHelper.convertListToJson(this.branches), "branches.json");
    }
    private void writeToProductFile(Product product) throws FileNotFoundException, JsonProcessingException {
        boolean isNewProduct = true;
        String json = JSONHelper.readFile("products.json");
        List<Product> products = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(json);
        for (JsonNode element : jsonArray) {
            Product object = objectMapper.treeToValue(element, Product.class);
            if(Objects.equals(object.getProductID(), product.getProductID())){
                isNewProduct = false;
            products.add(object);
            }
        }
        if(isNewProduct){
            products.add(product);
        }
        JSONHelper.writeToFile(JSONHelper.convertListToJson(products), "products.json");
    }


    private void readBranchFile() throws JsonProcessingException, FileNotFoundException {
        String json = JSONHelper.readFile("branches.json");
        this.branches.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(json);
        for (JsonNode element : jsonArray) {
            try{
                Branch object = objectMapper.treeToValue(element, Branch.class);
                this.branches.add(object);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    private List<Product> readAndGetProductFile() throws FileNotFoundException, JsonProcessingException {
        String json = JSONHelper.readFile("products.json");
        List<Product> allProducts = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(json);
        for (JsonNode element : jsonArray) {
            try{
                Product object = objectMapper.treeToValue(element, Product.class);
                allProducts.add(object);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return allProducts;
    }


    public void addBranch(Branch branch) throws FileNotFoundException, JsonProcessingException {
        this.readBranchFile();
        for(Branch b : this.branches){
            if(Objects.equals(b.getBranchID(), branch.getBranchID())){
                throw new RuntimeException("Branch already exist");
            }
        }
        branches.add(branch);
        this.writeToBranchesFile();
    }

    public List<Product> showAllProducts() throws FileNotFoundException, JsonProcessingException {
        return readAndGetProductFile();
    }

    public Product getProductIfExist(String productID, String branchID) throws FileNotFoundException, JsonProcessingException {
        for(Branch b : this.branches){
            if(Objects.equals(b.getBranchID(), branchID)){
                for (Product p : b.getInventory()){
                    if(Objects.equals(p.getProductID(), productID)){
                        return p;
                    }
                }
            }
        }
        return null;
    }

    public List<Product> showProductListFromSpecificBranch(String branchID) throws FileNotFoundException, JsonProcessingException {
        this.readBranchFile();
        for(Branch branch : this.branches){
            if(Objects.equals(branch.getBranchID(), branchID)){
                return branch.getInventory();
            }
        }
        throw new RuntimeException("branchID: " + branchID + " does not exist");
    }

    public void buyProductsForBranch(String branchID, Product product) throws FileNotFoundException, JsonProcessingException {
        this.readBranchFile();
        boolean isProductExist = false;
        for(Branch branch : this.branches){
            if(Objects.equals(branch.getBranchID(), branchID)){
                for(Product p : branch.getInventory()){
                    if(Objects.equals(p.getProductID(), product.getProductID())) {
                        isProductExist = true;
                        p.setQuantity(p.getQuantity() + product.getQuantity());
                    }
                }
                if(!isProductExist){
                    branch.addProduct(product);
                }
                writeToBranchesFile();
                return;
            }
        }
        throw new RuntimeException("branchID: " + branchID + " does not exist");
    }

    public void buyProductsFromBranch(String branchID, Product product) throws FileNotFoundException, JsonProcessingException {
        this.readBranchFile();
        for(Branch branch : this.branches){
            if(Objects.equals(branch.getBranchID(), branchID)){
                branch.removeProduct(product);
                this.writeToBranchesFile();
                return;
            }
        }
        throw new RuntimeException("branchID: " + branchID + " does not exist");
    }
}
