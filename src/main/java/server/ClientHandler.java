package server;

import org.example.Classes.Employees.Employee;
import org.example.Classes.Employees.EmployeeManagement;
import org.example.Classes.Enum.EmployeeType;
import utils.Authentication;

import java.io.*;
import java.net.Socket;
import java.util.Set;

class ClientHandler implements Runnable {

    private final Socket clientSocket;
    private final Set<String> loggedInUsers;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Employee loggedInUser;




    public ClientHandler(Socket clientSocket, Set<String> loggedInUsers) throws IOException {
        this.clientSocket = clientSocket;
        this.loggedInUsers = loggedInUsers;
        outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        inputStream = new ObjectInputStream(clientSocket.getInputStream());
    }

    @Override
    public void run() {
        while (true) {
            try {
                String command = (String) inputStream.readObject();
                switch (command) {
                    case "LOGIN":
                        handleLogin();
                        break;
                    case "FETCH_EMPLOYEES":
                        handleFetchEmployees();
                        break;
                    case "ADD_EMPLOYEE":
                        handleAddEmployee();
                        break;
                    case "UPDATE_EMPLOYEE":
                        handleUpdateEmployee();
                        break;
                    case "UPDATE_INVENTORY":
                        handleUpdateInventory();
                        break;
                    case "FETCH_INVENTORY":
                        handleFetchInventory();
                        break;
                    case "ADD_CUSTOMER":
                        handleAddECustomer();
                        break;
                    case "UPDATE_CUSTOMER":
                        handleUpdateCustomer();
                        break;
                    case "PROCESS_SELL":
                        processSell();
                        break;
                    case "START_CHAT":
                        handleStartChat();
                        break;
                    case "GET_REPORT_BY_BRANCH":
                        handleGetReportByBranch();
                        break;
                    case "GET_REPORT_BY_PRODUCT":
                        handleGetReportByProduct();
                        break;
                    case "GET_REPORT_BY_CATEGROY":
                        handleGetReportByCategory();
                        break;
                    case "LOGOUT":
                        handleLogout();
                        break;
                        
                    // ... other command handlers
                    default:
                        System.out.println("Unknown command received.");
                }
            } catch (Exception e) {
                System.out.println("Error processing command: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.out.println("Error closing client socket: " + e.getMessage());
                }
            }
        }
    }

    private void handleUpdateCustomer() {
    }

    private void handleAddECustomer() {
    }

    private void handleGetReportByCategory() {
    }

    private void handleGetReportByProduct() {
    }

    private void handleGetReportByBranch() {
    }

    private void handleStartChat() {
    }

    private void processSell() {
    }

    private void handleAddEmployee() throws IOException, ClassNotFoundException {
        try{
            Employee newEmployee = (Employee) inputStream.readObject();
            EmployeeManagement em = new EmployeeManagement();
            em.addEmployee(newEmployee);
            outputStream.writeObject(true);
        } catch (IOException e){
            outputStream.writeObject(false);
        }

    }

    private void handleFetchInventory() {
    }

    private void handleUpdateInventory() {
    }

    private void handleUpdateEmployee() {
    }

    private void handleFetchEmployees() {

    }

    private void handleLogin() throws IOException, ClassNotFoundException {
        String ID = (String) inputStream.readObject();
        String password = (String) inputStream.readObject();

        // Check if user is already logged in
        synchronized (loggedInUsers) {
            if (loggedInUsers.contains(ID)) {
                outputStream.writeObject("User already logged in from another device.");
                return;
            }

            Employee employee = Authentication.authLogin(ID, password);
            if (employee != null) {
                loggedInUsers.add(ID);
                loggedInUser = employee;
            }
        }
        outputStream.writeObject(this.loggedInUser);
    }

    private void handleLogout() throws IOException {
        if (loggedInUser != null) {
            loggedInUsers.remove(loggedInUser.getEmployeeID());
            loggedInUser = null;
            outputStream.writeObject("Logout successful!");
        } else {
            outputStream.writeObject("No user is currently logged in.");
        }
    }




}
