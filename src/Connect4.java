package src;

public class Connect4 {
<<<<<<< HEAD
    private int[][] board;
    private boolean turns;

    public Connect4() {
        board = new int[6][7];
=======
    private String[][] board;
    private boolean turns;

    public Connect4() {
        board = new String[6][7];
>>>>>>> b01021c5905c0e1b3ac3a7f0b7e4c69dfbf394c3
        turns = true;
    }

    //when player plays, input coordinate then update the board;
<<<<<<< HEAD
    public void play(int x, int y, String player) {
        if (board[x][y-1] == 0 || board[x][y] != 0) {
            System.out.print("Invalid location");
        }
=======
    public void play(int x, int y) {
        
>>>>>>> b01021c5905c0e1b3ac3a7f0b7e4c69dfbf394c3
    }

    //print the board
    public void printBoard() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
<<<<<<< HEAD
                System.out.print(board[i][j] + " ");
=======
                System.out.print(board[i][j]);
>>>>>>> b01021c5905c0e1b3ac3a7f0b7e4c69dfbf394c3
            }
            System.out.println();
        }
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> b01021c5905c0e1b3ac3a7f0b7e4c69dfbf394c3
