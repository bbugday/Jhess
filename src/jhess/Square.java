package jhess;

import jhess.Pieces.Piece;

import java.util.ArrayList;

public class Square {
    private int code;

    public int getCode() {
        return code;
    }

    public Square(int code){
        this.code = code;
    }

    public Piece getCurrentPiece() {
        return currentPiece;
    }

    public void setCurrentPiece(Piece currentPiece) {
        this.currentPiece = currentPiece;
    }

    private Piece currentPiece;

    public String toString(){
        return this.currentPiece == null ? "." : this.currentPiece.toString();
    }

    public ArrayList<Integer> findReachableSquares(int sourceSquareCode){
        return currentPiece.findReachableSquares(sourceSquareCode);
    }
}
