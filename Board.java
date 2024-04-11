import java.io.*;
import java.util.Arrays;

public class Board {
    /**
     * @return
     */
    public String[][] buildBoard() {
        String[][] board = new String[8][8];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = (i + j) % 2 == 0? "-" : "X";
            }
        }

        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = (i + j) % 2 == 0? "-" : " ";
            }
        }

        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = (i + j) % 2 == 0? "-" : "O";
            }
        }
        return board;
    }

    /**
     * @param board
     */
    public String printBoard(String[][] board) {
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

    public String[][] getBoard() {
        return buildBoard();
    }
}