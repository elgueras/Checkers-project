
public class Piece {
    public enum Color {
        BLACK,
        WHITE,
        BLACK_KING,
        WHITE_KING
    }

    // The type of the piece (0, X, or null for an empty square)
    private String type;

    // The color of the piece (BLACK, WHITE, BLACK_KING, or WHITE_KING)
    private Color color;

    // The row and column of the piece on the board
    private int row;
    private int col;

    // Constructor
    public Piece(Color color, String type, int row, int col) {
        this.color = color;
        this.type = type;
        this.row = row;
        this.col = col;
    }


    // Getters and setters for the type, color, row, and col fields

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        if (type.equals("X")) {
            return "X";  // Black piece representation
        } else if (type.equals("O")) {
            return "O";  // White piece representation
        } else {
            return "-";  // Empty cell representation
        }
    }

    public String getColorAsString() {
        if (color == Color.BLACK) {
            return "X";
        } else if (color == Color.WHITE) {
            return "O";
        } else if (color == Color.BLACK_KING) {
            return "XX";
        } else if (color == Color.WHITE_KING) {
            return "OO";
        } else {
            return "-";
        }
    }
    


}