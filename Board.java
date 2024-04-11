import java.io.*;
import java.util.Arrays;



public class Board {

    // The 2D array of Piece objects that represents the checkers board
    private Piece[][] board;

    // Constructor
    public Board() {
        board = new Piece[8][8];
        
        // Initialize the board with null values
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
        
        // Initialize black pieces
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    board[row][col] = new Piece(Piece.Color.BLACK, "X", row, col);
                }
            }
        }
        
        // Initialize white pieces
        for (int row = 5; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    board[row][col] = new Piece(Piece.Color.WHITE, "0", row, col);
                }
            }
        }
    }

    /**
     * Prints the current state of the board
     */
    public void printBoard() {
        System.out.println("   0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    System.out.print("|-");
                } else {
                    System.out.print("|" + piece.toString());
                }
            }
            System.out.println("|");
        }
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int row, int col) {
        // Check if the row and column are within the bounds of the board
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            // Return the piece at the specified row and column
            return board[row][col];
        }

        return null;
    }

    public void setPiece(int row, int col, Piece piece) {
        // Check if the row and column are within the bounds of the board
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            // Set the piece at the specified row and column
            board[row][col] = new Piece(piece.getColor(), piece.getType(), row, col);
        }

    }

    public void checkPiecesInRow0And7() {
        for (int col = 0; col < 8; col++) {
            Piece piece = getPiece(0, col);
            if (piece != null && piece.getColor() == Piece.Color.WHITE) {
                setPiece(0, col, new Piece(Piece.Color.WHITE_KING, "0", 0, col));
            }
        }
    
        for (int col = 0; col < 8; col++) {
            Piece piece = getPiece(7, col);
            if (piece != null && piece.getColor() == Piece.Color.BLACK) {
                setPiece(7, col, new Piece(Piece.Color.BLACK_KING, "X", 7, col));
            }
        }
    }
    
    public boolean isKing(Piece piece, int row, int col) {
        return piece.getColor() == Piece.Color.BLACK_KING || piece.getColor() == Piece.Color.WHITE_KING;
    }
    

    public void removePiece(int row, int col) {
        // Check if the row and column are within the bounds of the board
        if (row >= 0 && row < 8 && col >= 0 && col < 8) {
            // Set the piece at the specified row and column to null
            board[row][col] = null;
        }
    }

}


