package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import org.example.Classes.Employees.Employee;

public class Client {

    private String serverAddress;
    private int serverPort;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private String branchId;
    private Employee loggedInEmployee;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void connectToServer() throws IOException {
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
            closeSession();
        }
    }


    public boolean login() throws IOException, ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter ID: ");
        String ID = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Send LOGIN command to the server
        outputStream.writeObject("LOGIN");

        // Send credentials to the server for validation
        outputStream.writeObject(ID);
        outputStream.writeObject(password);

        // Receive the response from the server
        loggedInEmployee = (Employee) inputStream.readObject();

        if (loggedInEmployee != null) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Login failed. Please try again.");
            return false;
        }
    }

    public void displayMenu() throws IOException {
        while (true) {
            System.out.println("\n--- MENU ---");
            switch (loggedInEmployee.getPosition()) {
                case FLOOR:
                    displayFloorMenu();
                    break;
                case CASHIER:
                    displayCashierMenu();
                    break;
                case SHIFT_SUPERVISOR:
                    displayShiftSupervisorMenu();
                    break;
            }

            System.out.print("\nEnter your choice: ");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            handleMenuChoice(choice);
        }
    }

    private void displayFloorMenu() {
        System.out.println("1. View Products");
        System.out.println("2. Check Inventory");
        System.out.println("3. Logout");
    }

    private void displayCashierMenu() {
        System.out.println("1. View Products");
        System.out.println("2. Check Inventory");
        System.out.println("3. Process Sale");
        System.out.println("4. Logout");
    }

    private void displayShiftSupervisorMenu() {
        System.out.println("1. View Products");
        System.out.println("2. Check Inventory");
        System.out.println("3. Process Sale");
        System.out.println("4. View Sales Report");
        System.out.println("5. Manage Employees");
        System.out.println("6. Logout");
    }

    private void handleMenuChoice(int choice) throws IOException {
        switch (loggedInEmployee.getPosition()) {
            case FLOOR:
                handleFloorChoice(choice);
                break;
            case CASHIER:
                handleCashierChoice(choice);
                break;
            case SHIFT_SUPERVISOR:
                handleShiftSupervisorChoice(choice);
                break;
        }
    }

    private void handleFloorChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                // View Products
                break;
            case 2:
                // Check Inventory
                break;
            case 3:
                // Logout
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
    private void handleCashierChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                // View Products
                break;
            case 2:
                // Check Inventory
                break;
            case 3:
                // Process Sale
                break;
            case 4:
                // Logout
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void handleShiftSupervisorChoice(int choice) throws IOException {
        switch (choice) {
            case 1:
                // View Products
                break;
            case 2:
                // Check Inventory
                break;
            case 3:
                // Process Sale
                break;
            case 4:
                // View Sales Report
                break;
            case 5:
                // Manage Employees
                break;
            case 6:
                // Logout
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void logout() throws IOException {
        System.out.println("Logging out...");
        // Close connection, reset loggedInEmployee, etc.
        this.closeSession();
    }
    

    private Employee sendLoginRequestToServer(String ID, String password) {
        return new Employee();
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



    // ... Other methods like manageInventory(), manageCustomers(), viewReports(), etc.
    public void startClientSession() throws IOException, ClassNotFoundException {
        connectToServer();

        int loginAttempts = 3;
        boolean loggedIn = false;

        while (loginAttempts > 0 && !loggedIn) {
            loggedIn = login();
            if (!loggedIn) {
                loginAttempts--;
                if (loginAttempts > 0) {
                    System.out.println(loginAttempts + " attempts remaining.");
                }
            }
        }

        if (loggedIn) {
            displayMenu();
        } else {
            System.out.println("Exiting due to multiple failed login attempts.");
            closeSession();
        }
    }

    public void closeSession() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    public static void main(String[] args) {
        Client client = new Client("localhost", 8080);
        try {
            client.startClientSession();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
