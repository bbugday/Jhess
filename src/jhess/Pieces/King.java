package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;
import jhess.Square;

import java.util.ArrayList;

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

    public ArrayList<Integer> findReachableSquares(int sourceSquareCode, Square[] squares){
        return null;
    }
}
