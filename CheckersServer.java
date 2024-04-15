import java.io.*;
import java.net.*;

public class CheckersServer {
    public static void main(String[] args) {
        Board board = new Board();

        System.out.println(board.boardToString(false));


        try {
            @SuppressWarnings("resource")
            ServerSocket serverSocket = new ServerSocket(8080);
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
            
            // Send initial board to both players
            player1Out.println("Current Board:");
            player1Out.println(board.boardToString(true));
        
            player2Out.println("Current Board:");
            player2Out.println(board.boardToString(false));
            

            while (true) {
                
                // Prompt player 1 to make a move
                player1Out.println("Player 1, make your move ( (move OR jump) fromRow fromCol toRow toCol): ");
            
                // Receive move from player 1
                String moveFromPlayer1 = player1In.readLine();
                System.out.println("Player 1 move: " + moveFromPlayer1);
                
                // Process move from player 1
                MoveProcessor.processMove(moveFromPlayer1, board);

                // Check if game is over -- not yet implemented 
            
                // Send updated board to Player 2
                player2Out.println("Current Board:");
                player2Out.println(board);
            
                // Prompt player 2 to make a move
                player2Out.println("Player 2, make your move (fromRow fromCol toRow toCol): ");
            
                // Receive move from player 2
                String moveFromPlayer2 = player2In.readLine();
                System.out.println("Player 2 move: " + moveFromPlayer2);
                
                // Process move from player 2
                MoveProcessor.processMove(moveFromPlayer2, board);

                // Check if game is over -- not yet implemented 
            
                // Send updated board to Player 1
                player1Out.println("Current Board:");
                player1Out.println(board);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}