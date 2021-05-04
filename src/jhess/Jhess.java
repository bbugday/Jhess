package jhess;

import jhess.Pieces.Piece;
import java.util.ArrayList;

public class Jhess {

    private Board board;
    private Move move;

    public Jhess(){
        board = new Board();
        move = new Move(board.getSquares());
    }

    public boolean isSquareWithinBoard(int squareCode){
        return (squareCode & 0x88) == 0;
    }

    public ArrayList<Integer> findLegalMoves(int sourceSquareCode) {
        return move.findLegalMoves(sourceSquareCode);
    }

    public void move(int sourceSquareCode, int destinationSquareCode){
        move.makeMove(sourceSquareCode, destinationSquareCode);
    }

    public String ascii(){
        return board.toAscii();
    }

}
