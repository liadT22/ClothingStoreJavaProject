package org.example.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.JSONHelper;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    public static List<SaleReport> readToFile() throws JsonProcessingException, FileNotFoundException {
        String json = JSONHelper.readFile("salesAnalytics.json");
        List<SaleReport> saleReportList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonArray = objectMapper.readTree(json);
        for (JsonNode element : jsonArray) {
            try{
                SaleReport object = objectMapper.treeToValue(element, SaleReport.class);
                saleReportList.add(object);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        return saleReportList;
    }

    public static void writeToFile(SaleReport saleReport) throws FileNotFoundException, JsonProcessingException {
        List<SaleReport> saleReportList = readToFile();
        saleReportList.add(saleReport);
        JSONHelper.writeToFile(JSONHelper.convertListToJson(saleReportList), "salesAnalytics.json");
    }

    public static List<SaleReport> getAnalyticsByBranch(String branchId){
        try {
            List<SaleReport> allAnalytics = readToFile();
            List<SaleReport> analyticsInBranch = new ArrayList<SaleReport>();

            for (SaleReport s : allAnalytics) {
                if (s.getBranchId().equals(branchId)) {
                    analyticsInBranch.add(s);
                }
            }
            return analyticsInBranch;
        }catch (FileNotFoundException | JsonProcessingException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static List<SaleReport> getAnalyticsByProduct(String productId){
        try {
            List<SaleReport> allAnalytics = readToFile();
            List<SaleReport> analyticsByProduct = new ArrayList<SaleReport>();

            for (SaleReport s : allAnalytics) {
                if (s.getProductID().equals(productId)) {
                    analyticsByProduct.add(s);
                }
            }
            return analyticsByProduct;
        }catch (FileNotFoundException | JsonProcessingException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

    public static List<SaleReport> getAnalyticsByCategory(String categoryName){
        try {
            List<SaleReport> allAnalytics = readToFile();
            List<SaleReport> analyticsByCategory = new ArrayList<SaleReport>();

            for (SaleReport s : allAnalytics) {
                if (s.getProductID().equals(categoryName)) {
                    analyticsByCategory.add(s);
                }
            }
            return analyticsByCategory;
        }catch (FileNotFoundException | JsonProcessingException e){
            System.out.println(e.getMessage());
            return null;
        }

    }

}
