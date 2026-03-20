import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        String serverIP = "localhost";
        int port = 5000;

        try (Socket socket = new Socket(serverIP, port);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Step 1: Send message to server
            writer.println("Hello Server");
            System.out.println("Sent: Hello Server");

            // Step 2: Receive and display response
            String serverResponse = reader.readLine();
            System.out.println("Server Response: " + serverResponse);

        } catch (IOException ex) {
            System.out.println("Client Error: " + ex.getMessage());
        }
    }
}
