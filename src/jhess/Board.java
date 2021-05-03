package jhess;

import jhess.Pieces.King;
import jhess.Pieces.Rook;

public class Board {

    public Square[] squares = new Square[0x80];

    public Board(){
        for(int i = 0; i < 0x80; i++){
            this.squares[i] = new Square(i);
        }
        //These are for testing purposes. Make a real initial board later.
        this.squares[53].setCurrentPiece(new Rook(Color.BLACK, squares));
        this.squares[54].setCurrentPiece(new Rook(Color.WHITE, squares));
        this.squares[55].setCurrentPiece(new King(Color.WHITE, squares));
    }
}
