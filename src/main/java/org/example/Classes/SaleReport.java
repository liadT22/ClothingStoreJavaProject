package org.example.Classes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import utils.JSONHelper;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SaleReport implements Serializable {

    private int saleReportID;
    private String productID;

    private String customerID;

    private String dateOfTheSale;

    private String employeeID;

    private String branchId;

    public SaleReport(){}

    public SaleReport(String productID, String customerID, String employeeID, String branchId) throws FileNotFoundException, JsonProcessingException {
        this.branchId = branchId;
        this.productID = productID;
        this. customerID = customerID;
        this.dateOfTheSale = new Date().toString();
        this.employeeID = employeeID;
        this.saleReportID = readToFile().size() + 1;
        writeToFile(this);
    }


    private List<SaleReport> readToFile() throws JsonProcessingException, FileNotFoundException {
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

    private void writeToFile(SaleReport saleReport) throws FileNotFoundException, JsonProcessingException {
        List<SaleReport> saleReportList = readToFile();
        saleReportList.add(saleReport);
        JSONHelper.writeToFile(JSONHelper.convertListToJson(saleReportList), "salesAnalytics.json");
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    private void setDateOfTheSale(String dateOfTheSale) {
        this.dateOfTheSale = dateOfTheSale;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    private void setSaleReportID(int saleReportID) {
        this.saleReportID = saleReportID;
    }
}
