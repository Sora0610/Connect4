public class Player {
    private String name;
    private boolean turn;
    private String symbol;

    public Player(String name, boolean turn, String symbol){
        this.name = name;
        this.turn = turn;
        this.symbol = symbol;
    }

    public String getName(){
        return this.name;
    }

    public boolean getTurn(){
        return this.turn;
    }

    public String getSymbol(){
        return this.symbol;
    }
}
