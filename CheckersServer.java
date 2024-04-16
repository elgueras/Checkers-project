import java.io.*;
import java.net.*;

public class CheckersServer {

    public static void main(String[] args) {
        Board board = new Board();

        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("Server started. Waiting for players...");

            while (true) {
                // Accept player 1 connection
                Socket player1Socket = serverSocket.accept();
                System.out.println("Player 1 connected.");

                // Send welcome message to player 1
                PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);
                player1Out.println("Welcome, you are Player 1.");

                // Accept player 2 connection
                Socket player2Socket = serverSocket.accept();
                System.out.println("Player 2 connected.");

                // Send welcome message to player 2
                PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);
                player2Out.println("Welcome, you are Player 2.");

                // Start a new thread to handle the game for this pair of players
                GameHandler gameHandler = new GameHandler(player1Socket, player2Socket, board);
                new Thread(gameHandler).start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class GameHandler implements Runnable {
    private Socket player1Socket;
    private Socket player2Socket;
    private Board board;

    public GameHandler(Socket player1Socket, Socket player2Socket, Board board) {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        this.board = board;
    }

    @Override
    public void run() {
        try {
            BufferedReader player1In = new BufferedReader(new InputStreamReader(player1Socket.getInputStream()));
            PrintWriter player1Out = new PrintWriter(player1Socket.getOutputStream(), true);

            BufferedReader player2In = new BufferedReader(new InputStreamReader(player2Socket.getInputStream()));
            PrintWriter player2Out = new PrintWriter(player2Socket.getOutputStream(), true);

            // Send initial board to both players
            player1Out.println("Current Board:");
            player1Out.println(board.toString());

            player2Out.println("Current Board:");
            player2Out.println(board.toString());

            while (true) {
                // Inside the game loop where moves are processed
                // Receive move from player 1
                String moveFromPlayer1 = player1In.readLine();
                // Process move from player 1
                MoveProcessor.processMove(moveFromPlayer1, board);

                // Send updated board to Player 2
                player2Out.println("Current Board:");
                player2Out.println(board.toString());

                // Prompt player 2 to make a move
                player2Out.println("Player 2, make your move (fromRow fromCol toRow toCol): ");

                // Receive move from player 2
                String moveFromPlayer2 = player2In.readLine();
                // Process move from player 2
                MoveProcessor.processMove(moveFromPlayer2, board);

                // Send updated board to Player 1
                player1Out.println("Current Board:");
                player1Out.println(board.toString());

                // Prompt player 1 to make a move
                player1Out.println("Player 1, make your move (fromRow fromCol toRow toCol): ");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
