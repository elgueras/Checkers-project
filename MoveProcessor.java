public class MoveProcessor {

    public static void processMove(String moveString, Board board) {
        // Parse the moveString into action and coordinates
        String[] parts = moveString.split(" ");
        String action = parts[0];
        int fromRow = Integer.parseInt(parts[1]);
        int fromCol = Integer.parseInt(parts[2]);
        int toRow = Integer.parseInt(parts[3]);
        int toCol = Integer.parseInt(parts[4]);
    
        System.out.println("Received move: " + action + " from (" + fromRow + ", " + fromCol + ") to (" + toRow + ", " + toCol + ")");
    
        // Get the piece at the source position
        Piece piece = board.getPiece(fromRow, fromCol);
    
        // Print out the piece and its color for debugging
        System.out.println("Piece at source position: " + piece + ", Color: " + piece.getColor());
    
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
