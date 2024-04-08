// CheckersServer.java

import java.io.*;
import java.net.*;

public class CheckersServer {

    


    /**
     * @return
     */
    public static String[][] buildBoard() {
        String[][] board = new String[8][8];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = (i + j) % 2 == 0 ? "-" : "X";
            }
        }

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = (i + j) % 2 == 0 ? "-" : " ";
            }
        }

        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = (i + j) % 2 == 0 ? "-" : "O";
            }
        }
        return board;
    }

    /**
     * @param board
     */
    public static void printBoard(String[][] board) {
        System.out.println("   0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print("|" +board[i][j]);
            }
            System.out.println("|");
        }
    } 

    
    public static void main(String[] args) {
        String[][] board = buildBoard();
        printBoard(board);
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
