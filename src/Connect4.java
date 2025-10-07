package src;

public class Connect4 {
    private String[][] board;
    private boolean turns;

    public Connect4() {
        board = new String[6][7];
        turns = true;
    }

    //when player plays, input coordinate then update the board;
    public void play(int x, int y) {
        
    }

    //print the board
    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
