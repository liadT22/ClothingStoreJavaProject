package server;

import org.example.Classes.Enum.EmployeeType;

import java.io.*;
import java.net.Socket;

class ClientHandler implements Runnable {

    private Socket clientSocket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private EmployeeType loggedInUserType;



    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
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
                    case "UPDATE_EMPLOYEES":
                        handleUpdateEmployees();
                        break;
                    case "UPDATE_INVENTORY":
                        handleUpdateInventory();
                        break;
                    case "FETCH_INVENTORY":
                        handleFetchInventory();
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

    private void handleFetchInventory() {
    }

    private void handleUpdateInventory() {
    }

    private void handleUpdateEmployees() {
    }

    private void handleFetchEmployees() {
    }

    private void handleLogin() throws IOException, ClassNotFoundException {
        String username = (String) inputStream.readObject();
        String password = (String) inputStream.readObject();

        boolean isValid = validateCredentials(username, password);
        outputStream.writeObject(isValid);
    }

    private boolean validateCredentials(String username, String password) {
//        // Fetch employee data from employees.json using JSONHelper
//        List<Employee> employees = JSONHelper.readEmployeesFromJSON();
//
//        for (Employee employee : employees) {
//            if (employee.getUsername().equals(username) && employee.getPassword().equals(password)) {
//                return true; // Credentials are valid
//            }
//        }
        return false; // Credentials are invalid
    }


}
