package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;
import jhess.Square;

import java.util.ArrayList;

public class King extends Piece {

    @Override
    public String toString() {
        return this.color == Color.WHITE ? "K" : "k";
    }

    public King(Color color, Square[] squares){
        super(color, squares);
    }

    @Override
    public PieceType pieceType() {
        return PieceType.KING;
    }

    public ArrayList<Integer> findReachableSquares(int sourceSquareCode){
        return null;
    }
}
