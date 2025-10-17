package src;


public class CheckWinner {
    private static final int[][] direction = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}}; //right, down, right down, right up
    private static final int rowNumber = 6;
    private static final int columnNumber = 7;
    private boolean gameOver = false;
    
    public boolean checkBoard(int[][] board, int row, int column) {
        int turns = board[row][column];
        if (turns == 0) {
            return false;
        }

        for (int[] a : direction) {
            int count = 1 + counter(board, row, column, a[0], a[1], turns) + counter(board, row, column, -a[0], -a[1], turns);
            //System.out.println(count);
            if (count >= 4) {
                gameOver = true;
                return true;
            }
        }
        return false;
    }

    // return the winner number
    public int returnWinnerNo(int[][] board, int lastRowMove, int lastColumnMove) {
        if (checkBoard(board, lastRowMove, lastColumnMove)) {
            return board[lastRowMove][lastColumnMove];
        }
        return 0;
    }
    
    public String returnWinner(int turns) {
        if (turns == 1) {
            return "Red";
        }
        return "Yellow";
    }

    public int counter(int[][] board, int row, int column, int rowDirection, int columnDirection, int turns) {
        int count = 0;
        int newRow = row + rowDirection;
        int newColumn = column + columnDirection;

        // while in the area of the board
        while (newRow >= 0 && newRow < rowNumber && newColumn >= 0 && newColumn < columnNumber && board[newRow][newColumn] == turns) {
            count++;
            newRow += rowDirection;
            newColumn += columnDirection;
        }

        return count;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void gameOverSwSitch(){
        if(gameOver == true){
            gameOver = false;
        }
        else{
            gameOver = true;
        }
    }
}
