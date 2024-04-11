import java.io.*;
import java.net.*;

public class CheckersClient {
    public static void main(String[] args) {
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

                    if (response.contains("Player 1")) {
                        // Prompt player 1 to make a move
                        System.out.print("Your move: ");
                        String move = consoleReader.readLine();
                        out.println(move);
                    } else if (response.contains("Player 2")) {
                        // Prompt player 2 to make a move
                        System.out.print("Your move: ");
                        String move = consoleReader.readLine();
                        out.println(move);
                    }
                } else {
                    // Print other messages from the server
                    System.out.println(response);
                }
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
