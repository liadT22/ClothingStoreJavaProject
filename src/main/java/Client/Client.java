package Client;

import java.io.*;
import java.net.Socket;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.example.Classes.Enum.EmployeeType;
import shared.*;

public class Client {

    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String branchId;
    private String employeeId;
    private EmployeeType loggedInUserType;
    private String loggedInEmployeeID;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connectToServer() {
        try {
            // Step 1: Create a socket and establish a connection to the server
            socket = new Socket(serverAddress, serverPort);
            System.out.println("Connected to the server!");

            // Step 2: Set up the data streams
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            // From here, the client can send/receive data using the streams
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error connecting to the server.");
        }
    }


    public boolean login() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Send LOGIN command to the server
        outputStream.writeObject(Commands.LOGIN);

        // Send credentials to the server for validation
        outputStream.writeObject(username);
        outputStream.writeObject(password);

        // Send these credentials to the server for validation
        loggedInEmployeeID = sendLoginRequestToServer(username, password);

        if (loggedInEmployeeID != null) {
            System.out.println("Login successful!");
            // Here, you'd also get the user type from the server using the loggedInEmployeeID
            loggedInUserType = getUserTypeFromServer(loggedInEmployeeID);
            return true;
        } else {
            System.out.println("Login failed. Please try again.");
            return false;
        }
    }

    private String sendLoginRequestToServer(String username, String password) {
        return "a";
    }

    // This method sends a request to the server to get the user type
    private EmployeeType getUserTypeFromServer(String username) {
        // Logic to communicate with the server and fetch the user type
        // For now, I'm just returning a dummy value. Replace this with actual server communication.
        return EmployeeType.FLOOR;
    }

    public Object handleServerResponse() {
        try {
            return inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void sendDataToServer(Object data) {
        try {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayMenu() {
        while (true) {  // Keep displaying the menu until the user chooses to exit
            List<String> availableActions = Permission.getPermissions().get(loggedInUserType);
            System.out.println("\nChoose an action:");
            for (int i = 0; i < availableActions.size(); i++) {
                System.out.println((i + 1) + ". " + availableActions.get(i));
            }
            System.out.println((availableActions.size() + 1) + ". Exit");  // Add an option to exit

            int choice = handleMenuChoice(availableActions);
            if (choice == availableActions.size() + 1) {
                System.out.println("Exiting...");
                break;  // Exit the loop and end the session
            } else {
                executeAction(availableActions.get(choice - 1));
            }
        }
    }

    private int handleMenuChoice(List<String> availableActions) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        while (choice < 1 || choice > availableActions.size() + 1) {
            System.out.print("Enter your choice (1-" + (availableActions.size() + 1) + "): ");
            try {
                choice = scanner.nextInt();
                if (choice < 1 || choice > availableActions.size() + 1) {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.next();  // Clear the invalid input
            }
        }
        return choice;
    }

    private void executeAction(String action) {
        switch (action) {
            case "Add New Product":
                // Call method to add new product
                break;
            case "Remove Product":
                // Call method to remove product
                break;
            case "View Sales Report":
                // Call method to view sales report
                break;
            case "Manage Employees":
                // Call method to manage employees
                break;
            case "Chat with Branch":
                // Call method to chat with another branch
                break;
            case "Logout":
                // Call method to logout
                break;
            // ... handle other actions ...
            default:
                System.out.println("Unknown action.");
        }
    }


    // ... Other methods like manageInventory(), manageCustomers(), viewReports(), etc.

    public static void main(String[] args) {
        Client client = new Client("localhost", 8080);
        client.connectToServer();
        // ... Further logic like login, displaying menu, etc.
    }
}
