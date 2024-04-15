

public class GameLogic {

    ///method for checking pieces; normal or king




    //method for checking jump -- implemented in Board.Java and called by MoveProcessor.java




    //method for checking winner/game over
    public boolean isWinner(Board board) {
        // Check if all the pieces in row 0 are 00
        int whiteCount = 0;
        int blackCount = 0;
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece piece = board.getPiece(row, col);
                if (piece!= null) {
                    if (piece.toString().contains("0")) {
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


}
