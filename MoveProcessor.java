public class MoveProcessor {

    // Inside the MoveProcessor class
    public static void processMove(String move, Board board) {
        // Parse move string into fromRow, fromCol, toRow, toCol
        String[] parts = move.split(" ");
        int fromRow = Integer.parseInt(parts[0]);
        int fromCol = Integer.parseInt(parts[1]);
        int toRow = Integer.parseInt(parts[2]);
        int toCol = Integer.parseInt(parts[3]);

        // Move the piece on the board
        board.movePiece(fromRow, fromCol, toRow, toCol);
    }


 
}
