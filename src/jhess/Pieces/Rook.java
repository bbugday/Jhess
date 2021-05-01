package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;
import jhess.Square;

import java.util.ArrayList;

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

    public ArrayList<Integer> findReachableSquares(int sourceSquareCode, Square[] squares){
        ArrayList<Integer> legalSquares = new ArrayList<Integer>();
        legalSquares.addAll(horizontalControl(sourceSquareCode, squares));
        legalSquares.addAll(verticalControl(sourceSquareCode, squares));
        return legalSquares;
    }


}
