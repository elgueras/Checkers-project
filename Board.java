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
    public String printBoard(Piece[][] board) {
        StringBuilder s = new StringBuilder();
        s.append("   0 1 2 3 4 5 6 7\n");
        
        System.out.println("   0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            s.append(i + " ");
            // System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                s.append("|" + board[i][j]);
                // System.out.print("|" + board[i][j]);
            }
            s.append("|\n");
            //System.out.println("|");
        }
        return s.toString();

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

    public void movePiece(int fromRow, int fromCol, int toRow, int toCol) {
        // Get the piece at the source position
        Piece piece = board[fromRow][fromCol];
    
        // Remove the piece from the source position
        board[fromRow][fromCol] = null;
    
        // Place the piece at the destination position
        board[toRow][toCol] = piece;
    
        // Update the row and column of the moved piece
        piece.setRow(toRow);
        piece.setCol(toCol);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("   0 1 2 3 4 5 6 7\n");
        for (int i = 0; i < 8; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < 8; j++) {
                Piece piece = board[i][j];
                if (piece == null) {
                    sb.append("|-");
                } else {
                    sb.append("|").append(piece.getColorAsString());
                }
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    public String boardToString(boolean isPlayer1) {
        StringBuilder sb = new StringBuilder();
        sb.append("   0 1 2 3 4 5 6 7\n");
        for (int i = 0; i < 3; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < 8; j++) {
                sb.append((i + j) % 2 == 0 ? "|-" : "|X");
            }
            sb.append("|\n");
        }
        for (int i = 3; i < 5; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < 8; j++) {
                sb.append((i + j) % 2 == 0 ? "|-" : "| ");
            }
            sb.append("|\n");
        }
        for (int i = 5; i < 8; i++) {
            sb.append(i).append(" ");
            for (int j = 0; j < 8; j++) {
                sb.append((i + j) % 2 == 0 ? "|-" : "|0");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }
    
        
    
        
}


