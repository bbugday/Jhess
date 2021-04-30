package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;

public abstract class Piece {
    public Color getColor() {
        return color;
    }
    public abstract String toString();
    Color color;
    Piece(Color color){
        this.color = color;
    }
    public abstract PieceType pieceType();
}
