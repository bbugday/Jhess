package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;

public class King extends Piece {

    @Override
    public String toString() {
        return this.color == color.WHITE ? "K" : "k";
    }

    public King(Color color){
        super(color);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }
}
