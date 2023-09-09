package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.Classes.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSONHelper {
    private static final String DIRECTORY_PATH = "src/main/resources/";

    public static void checkAndCreateFiles() {
        checkAndCreateFile("productsData.json");
        checkAndCreateFile("branchStock.json");
        checkAndCreateFile("salesAnalytics.json");
        checkAndCreateFile("employees.json");
        checkAndCreateFile("branchesData.json");
        checkAndCreateFile("customers.json");
    }

    private static void checkAndCreateFile(String filename) {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(DIRECTORY_PATH + filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println(filename + " created in " + DIRECTORY_PATH);
            } catch (Exception e) {
                System.out.println("Error creating " + filename + ": " + e.getMessage());
            }
        } else {
            System.out.println(filename + " already exists in " + DIRECTORY_PATH);
        }
    }

    public static String readFile(String fileName) throws FileNotFoundException {
        File file = new File(DIRECTORY_PATH + fileName);
        String json = "";
        Scanner s = new Scanner(file);
        if(s.hasNext()) {
            json = s.nextLine();
        }
        s.close();
        return json;
    }

    public static void writeToFile(String json, String fileName) throws FileNotFoundException {
        File file = new File(DIRECTORY_PATH + fileName);
        PrintWriter pw = new PrintWriter(file);
        pw.print(json);
        pw.close();
    }

    public static <T> String convertListToJson(List<T> customers){
        return new Gson().toJson(customers);
    }

}
