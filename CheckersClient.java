// CheckersClient.java

import java.io.*;
import java.net.*;

public class CheckersClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            String response;
            while ((response = in.readLine()) != null) {
                System.out.println(response);

                if (response.startsWith("Welcome")) {
                    System.out.println("Waiting for other player to join...");
                }
                System.out.print("Your move: ");
                String move = consoleReader.readLine();
                out.println(move);

                if (move.equalsIgnoreCase("exit")) {
                    break;
                }

            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
