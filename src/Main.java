package Connect4.src;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] argc) {

        SwingUtilities.invokeLater(() -> {
            Connect4 game = new Connect4();
            CheckWinner winner = new CheckWinner();
            GUI ui = new GUI(game, winner); // Pass logic to GUI
        });
    }
}