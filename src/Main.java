package src;
import java.util.Scanner;
import java.lang.System;

public class Main {
    public static void main(String[] argc) {
        Scanner scan = new Scanner(System.in);
        Connect4 game = new Connect4();
        CheckWinner winner = new CheckWinner();
        GUI UI = new GUI();

        // Get input
        while (true) {
            //SwingUtilities.invokeLater(connect4::new);
            System.out.print("X: ");
            int x = scan.nextInt();
            System.out.print("Y: ");
            int y = scan.nextInt();
            game.play(x,y);
            game.printBoard();
            if (winner.checkBoard(game.returnBoard(), x, game.calculate(x))) {
                int win = winner.returnWinnerNo(game.returnBoard(), x, game.calculate(x));
                UI.updateScoreAndDisplay(win);
                break;
            }
        }

        scan.close();
    }
}