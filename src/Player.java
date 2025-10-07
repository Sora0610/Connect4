package src;

public class Player {
    private boolean turn;
    private String symbol;

    public Player(boolean turn, String symbol){
        this.turn = turn;
        this.symbol = symbol;
    }

    public boolean getTurn(){
        return this.turn;
    }

    public String getSymbol(){
        return this.symbol;
    }
}
