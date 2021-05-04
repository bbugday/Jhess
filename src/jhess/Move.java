package jhess;

import jhess.Pieces.Piece;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Move {

    private Square[] squares;
    private GameLogic gameLogic;

    public Move(Square[] squares){
        this.squares = squares;
        gameLogic = new GameLogic();
    }

    public void makeMove(int sourceSquareCode, int destinationSquareCode){
        Piece piece = findPieceWithSquareCode(sourceSquareCode);
        Color movingPieceColor = piece.getColor();
        if(!gameLogic.isItThisColorsTurn(movingPieceColor)){
            throw new InvalidParameterException("It's " + gameLogic.getTurn().name() + "'s turn.");
        }
        if(findLegalMoves(sourceSquareCode).contains(destinationSquareCode)){
            squares[sourceSquareCode].setCurrentPiece(null);
            squares[destinationSquareCode].setCurrentPiece(piece);
        }
        gameLogic.switchTurn();
    }

    public ArrayList<Integer> findLegalMoves(int sourceSquareCode) {
        ArrayList<Integer> legalSquares = new ArrayList<Integer>();
        legalSquares = findReachableSquares(sourceSquareCode);
        legalSquares.removeIf(squareCode -> isCheckAfterMove(sourceSquareCode, squareCode));
        return legalSquares;
    }

    public ArrayList<Integer> findReachableSquares(int squareCode){
        ArrayList<Integer> reachableSquares = new ArrayList<Integer>();
        Piece pieceOnSquare = findPieceWithSquareCode(squareCode);
        reachableSquares = pieceOnSquare.findReachableSquares(squareCode);
        return reachableSquares;
    }

    public Piece findPieceWithSquareCode(int squareCode){
        return findSquareWithSquareCode(squareCode).getCurrentPiece();
    }

    public Square findSquareWithSquareCode(int squareCode){
        return squares[squareCode];
    }

    public ArrayList<Integer> attacking(int squareCode, Color attackingColor){
        ArrayList<Integer> attackingSquares = new ArrayList<Integer>();
        for(int i = 0; i < 0x80; i++){
            if(squares[i].getCurrentPiece() != null && squares[i].getCurrentPiece().getColor() == attackingColor){
                if(findReachableSquares(i).contains(squareCode)){
                    attackingSquares.add(i);
                }
            }
        }
        return attackingSquares;
    }

    public boolean isCheckAfterMove(int sourceSquareCode, int destinationSquareCode){
        Square sourceSquare = squares[sourceSquareCode];
        Square destinationSquare = squares[destinationSquareCode];
        Piece temp = destinationSquare.getCurrentPiece();

        destinationSquare.setCurrentPiece(sourceSquare.getCurrentPiece());
        sourceSquare.setCurrentPiece(null);

        boolean check = isCheck(destinationSquare.getCurrentPiece().getColor());

        sourceSquare.setCurrentPiece(destinationSquare.getCurrentPiece());
        destinationSquare.setCurrentPiece(temp);

        return check;
    }

    public boolean isCheck(Color kingColor){
        Color attackingColor = kingColor == Color.WHITE ? Color.BLACK : Color.WHITE;
        int kingPositionCode = findKingPosition(kingColor);
        return !attacking(kingPositionCode, attackingColor).isEmpty();
    }

    public int findKingPosition(Color kingColor){
        for(int i = 0; i < 0x80; i++){
            Piece piece = squares[i].getCurrentPiece();
            if(piece != null && piece.pieceType() == PieceType.KING && piece.getColor() == kingColor){
                return i;
            }
        }
        //create exception instead
        return -1;
    }

}
