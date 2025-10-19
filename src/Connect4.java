package src;

public class Connect4 {
    private int[][] board;
    private int turns;
    private int redScore;
    private int yellowScore;
    
    public Connect4() {
        board = new int[6][7];
        turns = 1;
        redScore = 0;
        yellowScore = 0;
    }

    public int calculate(int x) {
        int column = x;
        if (column < 0 || column >= 7) {
            return -1;
        }
        int row;
        for (row = 5; row >= 0; row--) {
            if (board[row][column] == 0) {
                return row;
            }
        }
        return -1;
    }

    //when player plays, input coordinate then update the board;
    public int play(int x, int y) {
        int column = x;
        int row = y;

        if (column < 0 || column >= 7 || row < 0 || row >= 6) {
            System.out.println("Invalid Input");
            return -1;
        }

        if (board[row][column]!= 0) {
            System.out.println("Invalid Input");
            return -1;
        }

        boolean valid = (row == 5) || (board[row + 1][column] != 0);
        
        if (!valid) {
            System.out.println("Invalid Input");
            return -1;
        }

        board[row][column] = turns;
        turns = -turns;
        return 0;
    }

    public int[][] returnBoard() {
        return this.board;
    }

    public void resetBoard(boolean condition) {
        this.board = new int[6][7];
        if (condition) {
            redScore = 0;
            yellowScore = 0;
        }
        turns = 1;
    }

    public void addRed(){
        this.redScore++;
    }

    public int getRed(){
        return this.redScore;
    }

    public void addYellow(){
        yellowScore++;
    }

    public int getYellow(){
        return this.yellowScore;
    }
    
    public int getTurns() {
        return this.turns;
    }

    public boolean hasZero() {
        boolean zero = false;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 0) {
                    zero = true;
                    return zero;
                }
            }
        }
        return zero;
    }
}
