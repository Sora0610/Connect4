package src;
import java.util.HashMap;

public class Connect4 {
    private int[][] board;
    private int turns;
    private HashMap<Integer, Character> symbols;

    public Connect4() {
        board = new int[6][7];
        turns = 1;
        symbols = new HashMap<>();
        symbols.put(0, '□');
        symbols.put(1, '●');
        symbols.put(-1, '○');
    }

    // maybe used later
    public int calculate(int x) {
        int column = x - 1;
        int row;
        for (row = 5; row >= 0; row--) {
            if (board[row][column] == 0) {
                return row;
            }
        }
        // will be invalid in play function
        return -1;
    }

    //when player plays, input coordinate then update the board;
    public void play(int x, int y) {
        int column = x - 1;
        int row = y - 1;

        if (column < 0 || column >= 7 || row < 0 || row >= 6) {
            System.out.println("Invalid Input");
            return;
        }

        if (board[row][column]!= 0) {
            System.out.println("Invalid Input");
            return;
        }

        boolean valid = (row == 5) || (board[row + 1][column] != 0);
        
        if (!valid) {
            System.out.println("Invalid Input");
            return;
        }

        board[row][column] = turns;
        turns = -turns;
    }

    //print the board
    public void printBoard() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(symbols.get(board[i][j]));
            }
            System.out.println();
        }
    }

    public int[][] returnBoard() {
        return this.board;
    }
}
