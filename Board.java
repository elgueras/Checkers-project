// Board.java
import java.io.*;
import java.util.Arrays;

// Represents the game board for checkers
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
                    //System.out.println("Initialized piece at (" + row + ", " + col + "): " + board[row][col]);
                }
            }
        }
        
        // Initialize white pieces
        for (int row = 5; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if ((row + col) % 2 == 0) {
                    board[row][col] = new Piece(Piece.Color.WHITE, "O", row, col);
                    //System.out.println("Initialized piece at (" + row + ", " + col + "): " + board[row][col]);
                }
            }
        }
    }

    /**
     * Test constructor to intentionally handicap one side of the board to test game logic that determines winner.
     * @param string the side of the board to favor
     */

    public Board(String s) {
        board = new Piece[8][8];
        
        // Initialize the board with null values
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board[row][col] = null;
            }
        }
        
        if (s.equals("X")) {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 8; col++) {
                    if ((row + col) % 2 == 0) {
                        board[row][col] = new Piece(Piece.Color.BLACK, "X", row, col);
                        //System.out.println("Initialized piece at (" + row + ", " + col + "): " + board[row][col]);
                    }
                }
            }
        } else if (s.equals("O")) {
            for (int row = 5; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    if ((row + col) % 2 == 0) {
                        board[row][col] = new Piece(Piece.Color.WHITE, "O", row, col);
                        // System.out.println("Initialized piece at (" + row + ", " + col + "): " +
                        // board[row][col]);
                    }
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
        return board[row][col]; // Assuming board is a 2D array of Piece objects
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
                removePiece(0, col);
                setPiece(0, col, new Piece(Piece.Color.WHITE_KING, "O*", 0, col));
                System.out.printf("There's a new king at row %d and column %d for player 2! \n", 0, col);
            }
        }
    
        for (int col = 0; col < 8; col++) {
            Piece piece = getPiece(7, col);
            if (piece != null && piece.getColor() == Piece.Color.BLACK) {
                removePiece(7, col);
                setPiece(7, col, new Piece(Piece.Color.BLACK_KING, "X*", 7, col));
                System.out.printf("There's a new king at row %d and column %d for player 1! \n", 7, col);
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

        checkPiecesInRow0And7();
    }

    // Inside the Board class

    public void jumpPiece(int fromRow, int fromCol, int toRow, int toCol) {
        Piece piece  = board[fromRow][fromCol];

        // remove piece from source pos
        board[fromRow][fromCol] = null;

        // place piece at new destination pos
        board[toRow][toCol] = piece;

        // update row and column of new piece in board
        piece.setRow(toRow);
        piece.setCol(toCol);

        // remove jumped piece from board

        // jumping up left
        if (fromRow > toRow && fromCol > toCol) {
            board[fromRow - 1][fromCol - 1] = null;
        // jumping up right
        } else if (fromRow > toRow && fromCol < toCol) {
            board[fromRow - 1][fromCol + 1] = null;
        // jumping down left
        } else if (fromRow < toRow && fromCol > toCol) {
            board[fromRow + 1][fromCol - 1] = null;
        // jumping down right
        } else {
            board[fromRow + 1][fromCol + 1] = null;
        }

        checkPiecesInRow0And7();
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
                sb.append((i + j) % 2 == 0 ? "|-" : "|O");
            }
            sb.append("|\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // Create a new board
        Board board = new Board();

        board.printBoard(board.getBoard());

        // Retrieve pieces using coordinates
        Piece pieceAt00 = board.getPiece(0, 0);
        Piece pieceAt50 = board.getPiece(5, 0);
        Piece pieceAt44 = board.getPiece(4, 4);
        Piece pieceAt76 = board.getPiece(7, 6);

        // Print information about the retrieved pieces
        System.out.println("Piece at (0, 0): " + pieceAt00);
        System.out.println("Piece at (5, 1): " + pieceAt50);
        System.out.println("Piece at (4, 4): " + pieceAt44);
        System.out.println("Piece at (7, 6): " + pieceAt76);
    }
    
        
    
        
}


