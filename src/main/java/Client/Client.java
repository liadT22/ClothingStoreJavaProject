package Client;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import org.example.Classes.Customer.Customer;
import org.example.Classes.Customer.CustomerManagement;
import org.example.Classes.Employees.Employee;
import org.example.Classes.Enum.EmployeeType;
import org.example.Classes.Product;

public class Client {

    private final String serverAddress;
    private final int serverPort;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Employee loggedInEmployee;

    public Client(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    public void sendDataToServer(Object data) {
        try {
            outputStream.writeObject(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

    public void closeSession() throws IOException {
        outputStream.close();
        inputStream.close();
        socket.close();
    }

    public void displayMenu() throws IOException {
        while (loggedInEmployee != null) {
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
        System.out.println("1. View Product");
        System.out.println("2. Manage Customers");
        System.out.println("3. Fetch Inventory");
        System.out.println("4. Start Chat");
        System.out.println("5. Logout");
    }

    private void displayCashierMenu() {
        System.out.println("1. View Product");
        System.out.println("2. Manage Customers");
        System.out.println("3. Fetch Inventory");
        System.out.println("4. Process Sell");
        System.out.println("5. Start Chat");
        System.out.println("6. Logout");
    }

    private void displayShiftSupervisorMenu() {
        System.out.println("1. View Product");
        System.out.println("2. Manage Customers");
        System.out.println("3. Fetch Inventory");
        System.out.println("4. Process Sell");
        System.out.println("5. View Sales Report");
        System.out.println("6. Manage Employees");
        System.out.println("7. Start Chat");
        System.out.println("8. Logout");
    }

    private void handleMenuChoice(int choice) {
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

    private void handleFloorChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    onShowProductIfExist();
                    break;
                case 2:
                    // Manage Customers
                    break;
                case 3:
                    onFetchAllProductsInBranch();
                    break;
                case 4:
                    startChat();
                    break;
                case 5:
                    // Logout
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void handleCashierChoice(int choice) {
        switch (choice) {
            case 1:
                onShowProductIfExist();
                break;
            case 2:
                onFetchAllProductsInBranch();
                break;
            case 3:
                onSellProduct();
                break;
            case 4:
                // Logout
                logout();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private void handleShiftSupervisorChoice(int choice) {
        try {
            switch (choice) {
            case 1:
                onShowProductIfExist();
                break;
            case 2:
                // Manage Customers
                break;
            case 3:
                onFetchAllProductsInBranch();
                break;
            case 4:
                onSellProduct();
                break;
            case 5:
                // View Sales Report
                manageSalesReports();
                break;
            case 6:
                // Manage Employees
                manageEmployees();
                break;
            case 7:
                 //outputStream.writeObject("START_CHAT");
                  // try {
                  // Object a = inputStream.readObject();
                  // System.out.println(a);
                  // } catch (ClassNotFoundException e) {
                  // // TODO Auto-generated catch block
                  // e.printStackTrace();
                  // }
                    startChat();
                    break;
                case 8:
                    // Logout
                    logout();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void manageSalesReports() {
        System.out.println("\n--- Sales Reports ---");
        System.out.println("1. Sales By Branch");
        System.out.println("2. Sales By Product");
        System.out.println("3. Sales By Category");
        System.out.println("4. Back");
        System.out.print("\nEnter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleSalesReportsChoice(choice);
    }

    private void handleSalesReportsChoice(int choice) {
        switch (choice) {
            case 1:
                // Sales By Branch
                break;
            case 2:
                // Sales By Product
                break;
            case 3:
                // Sales By Category
                break;
            case 4:
                // Back
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void manageEmployees() {
        System.out.println("\n--- Manage Employees ---");
        System.out.println("1. Fetch Employees");
        System.out.println("2. Add Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Back");
        System.out.print("\nEnter your choice: ");
        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        handleManageEmployeesChoice(choice);
    }

    private void handleManageEmployeesChoice(int choice) {
        switch (choice) {
            case 1:
                // Fetch Employees
                fetchAllEmployeesInBranch();
                break;
            case 2:
                // Add Employee
                addNewEmployee();
                break;
            case 3:
                // Update Employee
                break;
            case 4:
                // Back
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private String getProductIDFromUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id: ");
        return scanner.nextLine();

    }

    private void onSellProduct(){
        System.out.println("\n--- Sell Product ---");
        try{
            outputStream.writeObject("PROCESS_SELL");
            String productID = getProductIDFromUser();
            Customer customer = getCustomerDetails();
            outputStream.writeObject(productID);
            outputStream.writeObject(customer);
        }catch (Exception e){
            System.out.println("onSellProduct: " + e.getMessage());
        }
    }

    private Customer getCustomerDetails(){
        Scanner scanner = new Scanner(System.in);
        Customer customer = null;
        System.out.println("Enter customer ID: ");
        try {
            customer = CustomerManagement.getInstance().getCustomerDetails(scanner.nextLine());
        }catch (Exception ignored){
        }
        if(customer != null){
            System.out.println("Welcome back " + customer.getName());
            return customer;
        }
        else{
            customer = new Customer();
            System.out.println("Enter New Customer Data:");

            System.out.println("ID:");
            customer.setCustomerID(scanner.nextLine());

            System.out.println("Name:");
            customer.setName(scanner.nextLine());

            System.out.println("Phone number:");
            customer.setPhone(scanner.nextLine());

            System.out.println("Postal code:");
            customer.setPostalCode(scanner.nextLine());
            return customer;
        }
    }


    private void onShowProductIfExist(){
        try{
            System.out.println("Enter product id: ");
            Scanner scanner = new Scanner(System.in);
            String productID = scanner.nextLine();
            outputStream.writeObject("SHOW_PRODUCT");
            outputStream.writeObject(productID);
            Product product = (Product) inputStream.readObject();
            if(product != null){
                System.out.println(product);
            }else{
                System.out.println("No such product exist");
            }

        }catch (Exception e){

        }
    }

    private void onFetchAllProductsInBranch(){
        try{
            outputStream.writeObject("FETCH_INVENTORY");
            List<Product> productsInBranch = (List<Product>) inputStream.readObject();
            System.out.println("List of Product:");
            System.out.println("-------------------");
            for (Product pr : productsInBranch) {
                System.out.println("\n" + pr.toString());
            }
            System.out.println("-------------------");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void fetchAllEmployeesInBranch() {
        try {
            outputStream.writeObject("FETCH_EMPLOYEES");
            List<Employee> employeesInBranch = (List<Employee>) inputStream.readObject();
            System.out.println("List of Employees:");
            System.out.println("-------------------");
            for (Employee emp : employeesInBranch) {
                System.out.println("\n" + emp.toString());
            }
            System.out.println("-------------------");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addNewEmployee() {
        Employee newEmployee = new Employee();
        System.out.println("Enter New Employee Data:");
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID:");
        newEmployee.setEmployeeID(scanner.nextLine());

        System.out.println("Name:");
        newEmployee.setName(scanner.nextLine());

        System.out.println("BranchID:");
        newEmployee.setBranchID(scanner.nextLine());

        System.out.println("Phone Number:");
        newEmployee.setPhoneNumber(scanner.nextLine());

        // For password without displaying in console
        Console console = System.console();
        if (console != null) {
            char[] passwordArray = console.readPassword("Password: ");
            newEmployee.setPassword(new String(passwordArray));
        } else {
            System.out.print("Password: ");
            newEmployee.setPassword(scanner.nextLine());
        }

        System.out.println("Account Number:");
        newEmployee.setAccountNumber(scanner.nextLine());

        while (true) {
            System.out.println("Position (1.Floor; 2.Cashier; 3.Shift Manager):");
            String positionCode = scanner.nextLine();

            switch (positionCode) {
                case "1":
                    newEmployee.setPosition(EmployeeType.FLOOR);
                    break;
                case "2":
                    newEmployee.setPosition(EmployeeType.CASHIER);
                    break;
                case "3":
                    newEmployee.setPosition(EmployeeType.SHIFT_SUPERVISOR);
                    break;
                default:
                    System.out.println("Wrong position input, try again.");
                    continue;
            }
            break;
        }

        sendDataToServer("ADD_EMPLOYEE");
        sendDataToServer(newEmployee);

        // Receive the response from the server
        boolean response = false;
        try {
            response = (boolean) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        if (response) {
            System.out.println("Employee added successfully");
        } else {
            System.out.println("Something didn't work, try again");
        }
    }

    private void logout(){
        try {
        System.out.println("Logging out...");
        // Close connection, reset loggedInEmployee, etc.
        outputStream.writeObject("LOGOUT");
        this.loggedInEmployee = null;
        Object response = inputStream.readObject();
        System.out.println(response);
        this.closeSession();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    private void startChat() {
        sendDataToServer("START_CHAT");
        try {
            Object loggedInUsers = inputStream.readObject();
            if (loggedInUsers instanceof Set) {
                Iterator<String> iterator = ((Set) loggedInUsers).iterator();
                System.out.println("Available users:");
                // Iterate using the iterator
                while (iterator.hasNext()) {
                    String user = iterator.next();
                    if (!user.equals(loggedInEmployee.getEmployeeID())) {
                        System.out.println("\n" + user);
                    }
                }
                System.out.print("\nWith who you want to chat? (insert ID) ");
                Scanner scanner = new Scanner(System.in);
                String userToChat = scanner.next();
                sendDataToServer("GET_EMPLOYEE_DETAILS");
                sendDataToServer(userToChat);
                Object employee = (Object) inputStream.readObject();
                System.out.println("You chose to talk to: " + employee);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
