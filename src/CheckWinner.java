package src;

public class CheckWinner {
    private static final int[][] direction = {{0, 1}, {1, 0}, {1, 1}, {-1, 1}}; //right, down, right down, right up
    private static final int rowNumber = 6;
    private static final int columnNumber = 7;

    public boolean checkBoard(int[][] board, int x, int y) {
        int row = y - 1;
        int column = x - 1;
        
        int turns = board[row][column];

        for (int[] a : direction) {
            int count = 1 + counter(board, row, column, a[0], a[1], turns) + counter(board, row, column, -a[0], -a[1], turns);
            if (count >= 4) {
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
}
