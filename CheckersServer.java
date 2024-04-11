//CheckersServer.java

import java.io.*;
import java.net.*;

public class CheckersServer {
    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();

        try {
            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for players...");

            Socket player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected.");

            Socket player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected.");

            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);

            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);

            player1Out.println("Welcome, you are Player 1.");
            player2Out.println("Welcome, you are Player 2.");

            while (true) {
                // Game logic goes here
                String moveFromPlayer1 = player1In.readLine();
                System.out.println("Player 1 move: " + moveFromPlayer1);

                // Send move to Player 2
                player2Out.println(moveFromPlayer1);

                String moveFromPlayer2 = player2In.readLine();
                System.out.println("Player 2 move: " + moveFromPlayer2);

                // Send move to Player 1
                player1Out.println(moveFromPlayer2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}