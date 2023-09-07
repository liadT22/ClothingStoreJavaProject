package server;
import utils.JSONHelper;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT_NUMBER = 8080;

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }

    public void start() {
        System.out.println("Starting server...");

        // Initialize Resources
        JSONHelper.checkAndCreateFiles();

        // Bind to a Port and Start Listening for Connections
        try (ServerSocket serverSocket = new ServerSocket(PORT_NUMBER)) {
            System.out.println("Server started and listening on port " + PORT_NUMBER);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(new ClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            System.out.println("Error starting server: " + e.getMessage());
        }

        // Shutdown Hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Server shutting down...");
            // Additional graceful shutdown logic can be added here
        }));
    }
}
