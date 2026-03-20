
import java.io.*;
import java.net.*;

public class MultiThreadedServer {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is started and listening on port " + port);

            while (true) {
                // Accept connection from a client
                Socket socket = serverSocket.accept();
                System.out.println("Status: New client connected.");

                // Create and start a new thread for the client
                new ClientHandler(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Server Error: " + ex.getMessage());
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            // Receive message from client
            String clientMsg = reader.readLine();
            System.out.println("Received: " + clientMsg);

            // Send response back to client
            writer.println("Hello Client");

            socket.close();
        } catch (IOException ex) {
            System.out.println("Thread Error: " + ex.getMessage());
        }
    }
}
