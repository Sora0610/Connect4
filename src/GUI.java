package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private final CellPanel[][] cells = new CellPanel[ROWS][COLS];
    private final JLabel statusLabel = new JLabel("Red's turn");
    private final JLabel scoreLabel = new JLabel("Red: 0 | Yellow: 0");
    private int redScore = 0, yellowScore = 0;

    public GUI() {
        setTitle("Connect 4 Game - Group 2B");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeader("Connect 4 Game - Group 2B"), BorderLayout.NORTH);
        add(createBoard(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);

        setSize(700, 700);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    //UI Creation
    private JLabel createHeader(String title) {
        JLabel header = new JLabel(title, SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        return header;
    }

    private JPanel createBoard() {
        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS, 5, 5));
        boardPanel.setBackground(Color.BLUE);

        for (int r = 0; r < ROWS; r++)
            for (int c = 0; c < COLS; c++) {
                cells[r][c] = new CellPanel(r, c);
                boardPanel.add(cells[r][c]);
            }

        return boardPanel;
    }

    private JPanel createControlPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(2, 1));
        controlPanel.setBackground(Color.WHITE);

        JPanel statusPanel = new JPanel();
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        statusPanel.add(statusLabel);
        statusPanel.add(Box.createHorizontalStrut(20));
        statusPanel.add(scoreLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(createButton("Reset Game", e -> resetBoard(false)));
        buttonPanel.add(createButton("New Match (Reset Scores)", e -> resetBoard(true)));

        controlPanel.add(statusPanel);
        controlPanel.add(buttonPanel);
        return controlPanel;
    }

    private JButton createButton(String text, ActionListener action) {
        JButton btn = new JButton(text);
        btn.addActionListener(action);
        return btn;
    }
    //cellpanels
    private class CellPanel extends JPanel {
        private final int row, col;
        private Color color = Color.WHITE;

        public CellPanel(int row, int col) {
            this.row = row;
            this.col = col;
            setBackground(Color.WHITE);

            addMouseListener(new MouseAdapter() {
                public void mouseEntered(MouseEvent e) {
                    if (!gameOver && board[0][col] == 0)
                        setBackground(new Color(230, 230, 230));
                }

                public void mouseExited(MouseEvent e) {
                    if (board[row][col] == 0)
                        setBackground(Color.WHITE);
                }

                public void mouseClicked(MouseEvent e) {
                    if (!gameOver)
                        dropPiece(col);
                }
            });
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(color);
            g.fillOval(10, 10, getWidth() - 20, getHeight() - 20);
        }

        public void setColor(Color c) {
            color = c;
            repaint();
        }
    }
    //Victory Pop Out
        private void showVictoryPopup(String winner) {
        JDialog dialog = new JDialog(this, "Victory!", true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        JPanel main = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1 = winner.equals("Red") ? new Color(255, 200, 200) : new Color(255, 255, 200);
                Color c2 = winner.equals("Red") ? new Color(255, 100, 100) : new Color(255, 255, 150);
                g2d.setPaint(new GradientPaint(0, 0, c1, 0, getHeight(), c2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        main.setPreferredSize(new Dimension(400, 300));

        main.add(Box.createVerticalStrut(40));
        main.add(makeCenteredLabel("🎉🎉🎉", 48));
        main.add(Box.createVerticalStrut(20));
        main.add(makeCenteredLabel(winner + " Wins!", 42,
        winner.equals("Red") ? new Color(180, 0, 0) : new Color(180, 150, 0)));
        main.add(Box.createVerticalStrut(30));
        main.add(makeCenteredLabel("Score: Red " + redScore + " - " + yellowScore + " Yellow", 20));
        main.add(Box.createVerticalStrut(30));

        JButton close = createButton("Continue", e -> dialog.dispose());
        close.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel bp = new JPanel();
        bp.setOpaque(false);
        bp.add(close);
        main.add(bp);

        dialog.add(main);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }

    private JLabel makeCenteredLabel(String text, int size) {
        return makeCenteredLabel(text, size, Color.BLACK);
    }

    private JLabel makeCenteredLabel(String text, int size, Color color) {
        JLabel lbl = new JLabel(text, SwingConstants.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, size));
        lbl.setForeground(color);
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        return lbl;
    }
}
