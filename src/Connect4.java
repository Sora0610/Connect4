package src;

public class Connect4 {
    private char[][] board;
    private boolean turns;

    public Connect4() {
        board = new char[6][7];
        turns = true;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '□';
            }
        }
    }

    //when player plays, input coordinate then update the board;
    public void play(int x, int y, String player) {
        if (board[x][y-1] == 0 || board[x][y] != 0) {
            System.out.print("Invalid location");
        }
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
