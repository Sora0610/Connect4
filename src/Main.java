package src;
import java.util.Scanner;
import java.lang.System;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] argc) {
        // Scanner scan = new Scanner(System.in);
        // Connect4 game = new Connect4();
        // CheckWinner winner = new CheckWinner();
        // GUI UI = new GUI();

        // // Get input
     
        // SwingUtilities.invokeLater(connect4::new);
        // game.play(UI.MouseXCoordinates(), game.calculate(UI.MouseXCoordinates()));
        // game.printBoard();
        // if (winner.checkBoard(game.returnBoard(), x, game.calculate(x))) {
        //     int win = winner.returnWinnerNo(game.returnBoard(), x, game.calculate(x));
        //     UI.updateScoreAndDisplay(win);
        // }

        SwingUtilities.invokeLater(() -> {
            Connect4 game = new Connect4();
            CheckWinner winner = new CheckWinner();
            GUI ui = new GUI(game, winner); // Pass logic to GUI
        });

    }
}