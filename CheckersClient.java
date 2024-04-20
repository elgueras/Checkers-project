import java.io.*;
import java.net.*;

public class CheckersClient {
    public static void main(String[] args) {
        boolean connected = false;

        while (!connected) {
            try {
                Socket socket = new Socket("localhost", 8080);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

                BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

                String response;
                while ((response = in.readLine()) != null) {
                    if (response.startsWith("Current Board:")) {
                        // Print the received board
                        System.out.println(response);
                        while ((response = in.readLine()) != null && !response.isEmpty()) {
                            System.out.println(response);
                        }
                        
                        // Prompt the user for input
                        System.out.print("Your move (in the format 'move/jump fromRow fromCol toRow toCol'): ");
                        String move = consoleReader.readLine();
                        out.println(move);
                    } else {
                        // Print other messages from the server
                        System.out.println(response);
                    }
                    
                    //System.out.println("Received: " + response); // Debug
                }

                socket.close();
            } catch (IOException e) {
                System.err.println("Connection reset. Attempting to reconnect...");
                try {
                    Thread.sleep(3000); // Wait for 3 seconds before attempting to reconnect
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
