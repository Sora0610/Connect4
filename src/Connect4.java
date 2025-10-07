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

    //when player plays, input coordinate then update the board;
    public void play(int x, int y) {
        // index out of bound when 1,1 , can place even if there's nothing under, can replace
        if (x < 1 || x > 7 || y < 1 || y > 6) {
            System.out.println("Invalid Input");
            return;
        }

        if((board[y-1][x-1] == 0 && board[y-2][x-1] != 0) || board[5][x-1] == 0){
            board[y-1][x-1] = turns;
        } else {
            System.out.println("Invalid Input");
        }
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
}
