public class MoveProcessor {

    // Inside the MoveProcessor class
    public static void processMove(String move, Board board) {
        // Parse move string into (move or jump), fromRow, fromCol, toRow, toCol
        String[] parts = move.split(" ");
        String action = parts[0];
        int fromRow = Integer.parseInt(parts[1]);
        int fromCol = Integer.parseInt(parts[2]);
        int toRow = Integer.parseInt(parts[3]);
        int toCol = Integer.parseInt(parts[4]);


        // Move the piece on the board
        if (action.equalsIgnoreCase("move")) {
            board.movePiece(fromRow, fromCol, toRow, toCol);
        } else if (action.equalsIgnoreCase("jump")) {
            board.jumpPiece(fromRow, fromCol, toRow, toCol);
        } else {
            throw new IllegalArgumentException("Unknown action " + action);
        }
       
    }


 
}
