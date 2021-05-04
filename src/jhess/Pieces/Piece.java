package jhess.Pieces;

import jhess.Color;
import jhess.PieceType;
import jhess.Square;

import java.util.ArrayList;

public abstract class Piece {

    protected Square[] squares;

    protected Color color;

    Piece(Color color, Square[] squares){
        this.color = color;
        this.squares = squares;
    }

    public Color getColor() {
        return color;
    }

    public abstract String toString();

    public abstract PieceType pieceType();

    public abstract ArrayList<Integer> findReachableSquares(int sourceSquareCode);

    public boolean isSquareWithinBoard(int squareCode){
        return (squareCode & 0x88) == 0;
    }

    ///Very long function! split it as horizontalcontrolright and left functions
    public ArrayList<Integer> horizontalControl(int sourceSquareCode){
        ArrayList<Integer> legalSquares = new ArrayList<Integer>();
        for(int i = 1; i < 8; i++){
            int destinationSquareCode = sourceSquareCode + i;
            Square destinationSquare = squares[destinationSquareCode];
            if(!isSquareWithinBoard(destinationSquareCode))
                break;
            if(destinationSquare.getCurrentPiece() != null){
                if(destinationSquare.getCurrentPiece().getColor() == color){
                    break;
                }
                legalSquares.add(destinationSquareCode);
                break;
            }
            legalSquares.add(destinationSquareCode);
        }
        for(int i = 1; i < 8; i++){
            int destinationSquareCode = sourceSquareCode - i;
            Square destinationSquare = squares[destinationSquareCode];
            if(!isSquareWithinBoard(destinationSquareCode))
                break;
            if(destinationSquare.getCurrentPiece() != null){
                if(destinationSquare.getCurrentPiece().getColor() == color){
                    break;
                }
                legalSquares.add(destinationSquareCode);
                break;
            }
            legalSquares.add(destinationSquareCode);
        }
        return legalSquares;
    }

    //Very long function! split it as verticalcontrolup and down functions
    public ArrayList<Integer> verticalControl(int sourceSquareCode){
        ArrayList<Integer> legalSquares = new ArrayList<Integer>();
        int destinationSquareCode;

        for(int i = 0x10; i < 0x80; i = i + 0x10){
            destinationSquareCode = sourceSquareCode + i;
            if(!isSquareWithinBoard(destinationSquareCode))
                break;
            Square destinationSquare = squares[destinationSquareCode];
            if(destinationSquare.getCurrentPiece() != null){
                if(destinationSquare.getCurrentPiece().getColor() == color){
                    break;
                }
                legalSquares.add(destinationSquareCode);
                break;
            }
            legalSquares.add(destinationSquareCode);
        }
        for(int i = 0x10; i < 0x80; i = i + 0x10){
            destinationSquareCode = sourceSquareCode - i;
            if(!isSquareWithinBoard(destinationSquareCode))
                break;
            Square destinationSquare = squares[destinationSquareCode];
            if(destinationSquare.getCurrentPiece() != null){
                if(destinationSquare.getCurrentPiece().getColor() == color){
                    break;
                }
                legalSquares.add(destinationSquareCode);
                break;
            }
            legalSquares.add(destinationSquareCode);
        }
        return legalSquares;
    }
}
