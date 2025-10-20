package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    private final int ROWS = 6;
    private final int COLS = 7;
    private final CellPanel[][] cells = new CellPanel[ROWS][COLS];
    private final JLabel statusLabel = new JLabel("Red's turn");
    private final JLabel scoreLabel = new JLabel("Red: 0 | Yellow: 0");
    private Connect4 game;
    private CheckWinner wincheck;
    private boolean isAnimating = false;

    public GUI(Connect4 game, CheckWinner wincheck) {
        this.game = game;
        this.wincheck = wincheck;
        setTitle("Connect 4 Game - Group 2B");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        add(createHeader("Connect 4 Game - Group 2B"), BorderLayout.NORTH);
        add(createBoard(), BorderLayout.CENTER);
        add(createControlPanel(), BorderLayout.SOUTH);

        setSize(700, 700);
        setResizable(false);
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
        // maybe because the resetboard function cannot reset the color on the board since it is handled in gui? (maybe reset colors in gui too)
        // buttonPanel.add(createButton("Reset Game", e -> {
        //     game.resetBoard(false); 
        //     refreshBoardFromModel(false);
        // }));
        buttonPanel.add(createButton("New Match (Reset Scores)", e -> {
            game.resetBoard(true); 
            refreshBoardFromModel(true);
        }));

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
                    if (!wincheck.getGameOver() && game.returnBoard()[row][col] == 0) {
                        int turn = game.getTurns();
                        if (turn == 1) {
                            setBackground(new Color(255, 150, 150));
                        } else {
                            setBackground(new Color(255, 240, 120));
                        }
                    }
                }

                public void mouseExited(MouseEvent e) {
                    if (game.returnBoard()[row][col] == 0)
                        setBackground(Color.WHITE);
                }

                public void mouseClicked(MouseEvent e) {
                    int turn = game.getTurns();
                    int column = col;
                    int processedRows = game.calculate(column);
                
                    // Check if the column is full
                    if (processedRows == -1 || wincheck.getGameOver()) {
                        return;
                    }

                    //System.out.println("Column: " + column + " Rows: " + processedRows);
                    game.play(column, processedRows);

                    if (!wincheck.getGameOver()) {
                        if (turn == 1) {
                            cells[processedRows][column].setColor(Color.RED);                           
                        } else {
                            cells[processedRows][column].setColor(Color.YELLOW);
                        }
                        cells[processedRows][column].setBackground(Color.WHITE);
                        repaint();
                    }

                    if(!game.hasZero()){
                        updateScoreAndDisplay();
                        return;
                    }
                    
                    int win = wincheck.returnWinnerNo(game.returnBoard(), processedRows, column);
                    
                    if (win != 0) {
                        updateScoreAndDisplay(win);
                        return;
                    }
                
                    if (game.getTurns() == 1) {
                        statusLabel.setText("Red's turn");
                    } else {
                        statusLabel.setText("Yellow's turn");
                    }
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
        private void showVictoryPopup(int winner) {
        JDialog dialog = new JDialog(this, "Victory!", true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        JPanel main = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1, c2;
                if(winner == 1){
                    c1 = new Color(255, 200, 200);
                    c2 = new Color(255, 100, 100);
                }else{
                    c1 = new Color(255, 255, 200);
                    c2 = new Color(255, 255, 150);
                }
                g2d.setPaint(new GradientPaint(0, 0, c1, 0, getHeight(), c2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        main.setPreferredSize(new Dimension(400, 300));

        main.add(Box.createVerticalStrut(40));
        main.add(makeCenteredLabel("Congratulations!", 20));
        main.add(Box.createVerticalStrut(20));
        main.add(makeCenteredLabel(wincheck.returnWinner(winner) + " Wins!", 42, new Color(180, winnerColor(winner), 0)));
        main.add(Box.createVerticalStrut(30));
        main.add(makeCenteredLabel("Score: Red " + game.getRed() + " - " + game.getYellow() + " Yellow", 20));
        main.add(Box.createVerticalStrut(30));

        JButton close = createButton("Continue", e -> {dialog.dispose(); game.resetBoard(false);refreshBoardFromModel(false);});
        close.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel bp = new JPanel();
        bp.setOpaque(false);
        bp.add(close);
        main.add(bp);

        dialog.add(main);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private void showDrawPopup() {
        JDialog dialog = new JDialog(this, "Draw!", true);
        dialog.setUndecorated(true);
        dialog.setLayout(new BorderLayout());

        JPanel main = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color c1, c2;
                c1 = new Color(0, 128, 0);
                c2 = new Color(0, 255, 0);
                g2d.setPaint(new GradientPaint(0, 0, c1, 0, getHeight(), c2));
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        main.setPreferredSize(new Dimension(400, 300));

        main.add(Box.createVerticalStrut(40));
        main.add(Box.createVerticalStrut(20));
        main.add(makeCenteredLabel(" Draw!", 42, new Color(0, 0, 0)));
        main.add(Box.createVerticalStrut(30));
        main.add(makeCenteredLabel("Score: Red " + game.getRed() + " - " + game.getYellow() + " Yellow", 20));
        main.add(Box.createVerticalStrut(30));

        JButton close = createButton("Continue", e -> {dialog.dispose(); game.resetBoard(false);refreshBoardFromModel(false);});
        close.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel bp = new JPanel();
        bp.setOpaque(false);
        bp.add(close);
        main.add(bp);

        dialog.add(main);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
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

    private void updateScoreAndDisplay(int winner) {
        if (winner == 1) {
            game.addRed();
        } else {
            game.addYellow();
        }
        scoreLabel.setText("Red: " + game.getRed() + " | Yellow: " + game.getYellow());
        statusLabel.setText(wincheck.returnWinner(winner) + " wins!");
        wincheck.gameOverSwitch();
        showVictoryPopup(winner);
    }
    //drawUpdate
    private void updateScoreAndDisplay() {
        scoreLabel.setText("Red: " + game.getRed() + " | Yellow: " + game.getYellow());
        statusLabel.setText("Draw!");
        wincheck.gameOverSwitch();
        showDrawPopup();
    }

    private void refreshBoardFromModel(boolean condition) {
        int[][] board = game.returnBoard();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (board[r][c] == 0) {
                    cells[r][c].setColor(Color.WHITE);
                } else if (board[r][c] == 1) {
                    cells[r][c].setColor(Color.RED);
                } else {
                    cells[r][c].setColor(Color.YELLOW);
                }
            }
        }
        if (condition) {
            scoreLabel.setText("Red: 0 | Yellow: 0");
        }
        statusLabel.setText("Red's turn");
        repaint();
    }

    private int winnerColor(int winner){
        if(winner == 1){
            return 0;
        }
        return 150;
    }

    private boolean isEmpty(int r, int c) {
        return game.returnBoard()[r][c] == 0;
    }
}
