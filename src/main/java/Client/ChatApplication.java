package Client;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatApplication {
    public static void main(String[] args) throws IOException {
        // Create a server socket to listen for connections
        ServerSocket serverSocket = new ServerSocket(12345);

        // Accept the first client connection
        Socket client1Socket = serverSocket.accept();
        System.out.println("Employee 1 connected.");

        // Create input and output streams for the first client
        BufferedReader client1In = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
        PrintWriter client1Out = new PrintWriter(client1Socket.getOutputStream(), true);

        // Accept the second client connection
        Socket client2Socket = serverSocket.accept();
        System.out.println("Employee 2 connected.");

        // Create input and output streams for the second client
        BufferedReader client2In = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));
        PrintWriter client2Out = new PrintWriter(client2Socket.getOutputStream(), true);

        // Create threads to handle communication in both directions
        Thread client1ToClient2 = new Thread(() -> {
            try {
                String message;
                while ((message = client1In.readLine()) != null) {
                    client2Out.println("Employee 1: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Thread client2ToClient1 = new Thread(() -> {
            try {
                String message;
                while ((message = client2In.readLine()) != null) {
                    client1Out.println("Employee 2: " + message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        // Start the communication threads
        client1ToClient2.start();
        client2ToClient1.start();
    }
}

