package jhess;

import jhess.Pieces.Piece;

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
}
