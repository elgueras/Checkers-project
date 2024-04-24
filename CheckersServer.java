//CheckersServer.java
import java.io.*;
import java.net.*;

// CheckersServer class manages the server-side operations for the checkers game.
public class CheckersServer {

    public static void main(String[] args) {
        Board board = new Board();
        
        //rigged board to expedite winner testing - only use for testing
        //Board board = new Board("X");

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for players...");

            while (true) {
                // Accept player 1 connection
                Socket player1Socket = serverSocket.accept();
                System.out.println("Player 1 connected.");

                // Send welcome message to player 1
                PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);
                player1Out.println("Welcome, you are Player 1. Your pieces are marked with X's.");

                // Accept player 2 connection
                Socket player2Socket = serverSocket.accept();
                System.out.println("Player 2 connected.");

                // Send welcome message to player 2
                PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);
                player2Out.println("Welcome, you are Player 2. Your pieces are marked with O's.");

                // Start a new thread to handle the game for this pair of players
                GameHandler gameHandler = new GameHandler(player1Socket, player2Socket, board);
                new Thread(gameHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// GameHandler class to manage the game logic for each pair of players
class GameHandler implements Runnable {
    private Socket player1Socket;
    private Socket player2Socket;
    private Board board;
    private boolean running = true;

    /**
     * Constructs a new GameHandler with the given player sockets and game board.
     * @param player1Socket socket for player 1
     * @param player2Socket socket for player 2
     * @param board the game board
     */
    public GameHandler(Socket player1Socket, Socket player2Socket, Board board) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        this.board = board;
    }

    // Method to run the game logic
    @Override
    public void run() {
        try {
            // Set up input and output streams for both players
            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);

            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);

            // Send initial board to player 1
            player1Out.println("Current Board:");
            player1Out.println(board.toString());

            while (running) {
                // Inside the game loop where moves are processed
                // Receive move from player 1
                String moveFromPlayer1 = player1In.readLine();
                // Process move from player 1
                MoveProcessor.processMove(moveFromPlayer1, board);
                player1Out.println("Received, please wait for Player 2's move.");

                // Check if game is over
                if (isWinner(board)) {
                    System.out.println("Player 1 has won!");
                    player1Out.println("Player 1 has won!");
                    player2Out.println("Player 1 has won!");
                    stopThread();
                    continue;
                }

                // Send updated board to Player 2
                player2Out.println("Current Board:");
                player2Out.println(board.toString());
                player2Out.println("Received, please wait for Player 1's move.");

                // Receive move from player 2
                String moveFromPlayer2 = player2In.readLine();
                // Process move from player 2
                MoveProcessor.processMove(moveFromPlayer2, board);

                // Check if game is over
                if (isWinner(board)) {
                    System.out.println("Player 2 has won!");
                    player1Out.println("Player 2 has won!");
                    player2Out.println("Player 2 has won!");
                    stopThread();
                }

                // Send updated board to Player 1
                player1Out.println("Current Board:");
                player1Out.println(board.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if there is a winner in the game.
     * @param board the game board
     * @return true if there is a winner, false otherwise
     */
    public boolean isWinner(Board board) {
        // Check if all the pieces in row 0 are 00
        int whiteCount = 0;
        int blackCount = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece != null) {
                    if (piece.toString().contains("O")) {
                        whiteCount++;
                    } else if (piece.toString().contains("X")) {
                        blackCount++;
                    }
                }
            }
        }

        // Check if there are only X's or only O's on the board
        if (whiteCount == 0 || blackCount == 0) {
            return true;
        }

        // If there are still both X's and O's on the board, there is no winner yet
        return false;
    }

    // Method to stop the game thread
    public void stopThread() {
        running = false;
    }
}
