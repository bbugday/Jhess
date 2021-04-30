package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;

public class Rook extends Piece{

    public Color getColor() {
        return color;
    }
    public Rook(Color color){
        super(color);
    }

    public String toString(){
        return this.color == color.WHITE ? "R" : "r";
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }


}
