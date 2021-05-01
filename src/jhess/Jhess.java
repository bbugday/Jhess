package jhess;

import jhess.Pieces.King;
import jhess.Pieces.Piece;
import jhess.Pieces.Rook;

import java.security.InvalidParameterException;
import java.util.ArrayList;

public class Jhess {
    private Square[] squares = new Square[0x80];
    private Color turn = Color.WHITE;

    public Jhess(){
        for(int i = 0; i < 0x80; i++){
            this.squares[i] = new Square(i);
        }
        //These are for testing purposes. Make a real initial board later.
        this.squares[53].setCurrentPiece(new Rook(Color.BLACK));
        this.squares[54].setCurrentPiece(new Rook(Color.WHITE));
        this.squares[55].setCurrentPiece(new King(Color.WHITE));
    }

    public boolean isSquareWithinBoard(int squareCode){
        return (squareCode & 0x88) == 0;
    }

    public void move(int sourceSquareCode, int destinationSquareCode){
        Piece piece = squares[sourceSquareCode].getCurrentPiece();
        if(piece.getColor() != turn){
            throw new InvalidParameterException("It's " + turn.name() + "'s turn.");
        }
        if(findLegalMoves(sourceSquareCode).contains(destinationSquareCode)){
            squares[sourceSquareCode].setCurrentPiece(null);
            squares[destinationSquareCode].setCurrentPiece(piece);
        }
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
        reachableSquares = pieceOnSquare.findReachableSquares(squareCode, squares);
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

    public String ascii(){
        return
                "  +------------------------+\n" +
                        "8 | " + squares[112].toString() + "  " + squares[113].toString() + "  " + squares[114].toString() + "  " + squares[115].toString() + "  "   +
                        squares[116].toString() + "  " + squares[117].toString() + "  " + squares[118].toString() + "  " + squares[119].toString() + " |\n" +
                        "7 | " + squares[96].toString()  + "  " + squares[97].toString()  + "  " + squares[98].toString()  + "  " + squares[99].toString()  + "  "   +
                        squares[100].toString() + "  " + squares[101].toString() + "  " + squares[102].toString() + "  " + squares[103].toString() + " |\n" +
                        "6 | " + squares[80].toString()  + "  " + squares[81].toString()  + "  " + squares[82].toString()  + "  " + squares[83].toString()  + "  "   +
                        squares[84].toString()  + "  " + squares[85].toString()  + "  " + squares[86].toString()  + "  " + squares[87].toString()  + " |\n" +
                        "5 | " + squares[64].toString()  + "  " + squares[65].toString()  + "  " + squares[66].toString()  + "  " + squares[67].toString()  + "  "   +
                        squares[68].toString()  + "  " + squares[69].toString()  + "  " + squares[70].toString()  + "  " + squares[71].toString()  + " |\n" +
                        "4 | " + squares[48].toString()  + "  " + squares[49].toString()  + "  " + squares[50].toString()  + "  " + squares[51].toString()  + "  "   +
                        squares[52].toString()  + "  " + squares[53].toString()  + "  " + squares[54].toString()  + "  " + squares[55].toString()  + " |\n" +
                        "3 | " + squares[32].toString()  + "  " + squares[33].toString()  + "  " + squares[34].toString()  + "  " + squares[35].toString()  + "  "   +
                        squares[36].toString()  + "  " + squares[37].toString()  + "  " + squares[38].toString()  + "  " + squares[39].toString()  + " |\n" +
                        "2 | " + squares[16].toString()  + "  " + squares[17].toString()  + "  " + squares[18].toString()  + "  " + squares[19].toString()  + "  "   +
                        squares[20].toString()  + "  " + squares[21].toString()  + "  " + squares[22].toString()  + "  " + squares[23].toString()  + " |\n" +
                        "1 | " + squares[0].toString()   + "  " + squares[1].toString()   + "  " + squares[2].toString()   + "  " + squares[3].toString()   + "  "   +
                        squares[4].toString()   + "  " + squares[5].toString()   + "  " + squares[6].toString()   + "  " + squares[7].toString()   + " |\n" +
                        "+------------------------+\n" +
                        "    a  b  c  d  e  f  g  h";
    }

}
