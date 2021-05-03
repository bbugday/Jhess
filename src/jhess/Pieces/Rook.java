package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;
import jhess.Square;

import java.util.ArrayList;

public class Rook extends Piece{

    public Color getColor() {
        return color;
    }
    public Rook(Color color, Square[] squares){
        super(color, squares);
    }

    public String toString(){
        return this.color == Color.WHITE ? "R" : "r";
    }

    @Override
    public PieceType pieceType() {
        return PieceType.ROOK;
    }

    public ArrayList<Integer> findReachableSquares(int sourceSquareCode){
        ArrayList<Integer> legalSquares = new ArrayList<Integer>();
        legalSquares.addAll(horizontalControl(sourceSquareCode));
        legalSquares.addAll(verticalControl(sourceSquareCode));
        return legalSquares;
    }


}
