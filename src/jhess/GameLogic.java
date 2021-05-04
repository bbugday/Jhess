package jhess;

public class GameLogic {

    private Color turn;

    public GameLogic(){
        turn = Color.WHITE;
    }

    public GameLogic(Color turn){
        this.turn = turn;
    }

    public void switchTurn(){
        turn = turn == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Color getTurn() {
        return turn;
    }

    public void setTurn(Color turn) {
        this.turn = turn;
    }

    public boolean isItThisColorsTurn(Color color){
        return turn == color;
    }

}
