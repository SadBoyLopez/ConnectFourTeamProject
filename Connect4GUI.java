// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI 434 Project #1, Iteration #2
// Connect4GUI.java
// 2-17-2019
//
// This plays a graphical-based Connect 4 game between two
// players.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;

public class Connect4GUI extends JFrame implements Observer
{
    public static final int WIDTH = 700;
    public static final int HEIGHT = 650;

    private Connect4Model model;
    private JLabel[][] board;
    private JLabel winnerLabel;
    private JLabel playerPrompt;
    private JLabel playerStats;
    private JPanel gamePanel;
    private JPanel endPanel;
    private JButton selectButton;
    private JTextField player1Name;
    private JTextField player2Name;
    private Connect4Player currentPlayer;
    private ImageIcon emptyPosition;
    private ImageIcon redChip;
    private ImageIcon yellowChip;
    private int colSelected;
    private int colWidth;
    private String playerOneName;
    private String playerTwoName;
    private JLabel player1Label = new JLabel();
    private JLabel player2Label = new JLabel();

    public Connect4GUI(Connect4Model model)
    {
        super("Connect 4");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.model = model;
        model.addObserver(this);
        
        colSelected = -1;

        // start panel
        JTextArea instructions = new JTextArea("CONNECT 4 INSTRUCTIONS\n"
            + "Connect 4 is a two player game with the players racing "
            + "to be the first to connect 4 of their chips in a row.\n"
            + "The 4 chips can be up and down, side-to-side, or diagonally.\n"
            + "The first player has yellow chips and the second player has "
            + "red chips.\nEach player will choise a column and the chip will "
            + "fall to the lowest avalible position.\nThe game ends when "
            + "either player has a connect 4 or the board has no ramaining "
            + "empty positions.");

/*
        JLabel player1Label = new JLabel("Enter Player 1's name:");
        player1Name = new JTextField("");
        JLabel player2Label = new JLabel("Enter Player 2's name:");
        player2Name = new JTextField("");

        JPanel startPanel = new JPanel(new GridLayout(2, 2));
        startPanel.add(player1Label);
        startPanel.add(player1Name);
        startPanel.add(player2Label);
        startPanel.add(player2Name);
*/

        JPanel startPanel = new JPanel(new GridLayout(2,2));

        playerOneName = (String) JOptionPane.showInputDialog(null,
            "Player one enter your name:");
        playerTwoName = (String) JOptionPane.showInputDialog(null,
        "Player two enter your name:");

        player1Label.setText(playerOneName);
        player2Label.setText(playerTwoName);

        startPanel.add(player1Label);
        startPanel.add(player2Label);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new StartButtonListener());

        add(instructions, BorderLayout.NORTH);
        add(startPanel, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);


        // game panel
        emptyPosition = new ImageIcon("chip_image/C4White.gif");
        redChip = new ImageIcon("chip_image/C4Red.gif");
        yellowChip = new ImageIcon("chip_image/C4Yellow.gif");

        playerPrompt = new JLabel(" ");

        board = new JLabel[Connect4Board.ROWS][Connect4Board.COLUMNS];
        JLabel chipSpace = new JLabel(emptyPosition);

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = chipSpace;

        gamePanel = new JPanel(new GridLayout(Connect4Board.ROWS,
            Connect4Board.COLUMNS));

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                gamePanel.add(board[i][j]);

        gamePanel.addMouseListener(new MyMouseListener());

        selectButton = new JButton("Select");
        selectButton.addActionListener(new SelectButtonListener());

        // end panel
        JLabel winnerLabel = new JLabel();
        JLabel playerStatsLabel = new JLabel();
        JButton playAgainButton = new JButton("Play Again");
        JButton quitButton = new JButton("Quit");

        playAgainButton.addActionListener(new PlayAgainButtonListener());
        quitButton.addActionListener(new QuitButtonListener());
        
        JPanel endPanel = new JPanel(new GridLayout(4, 1));
        endPanel.add(playerStatsLabel);
        endPanel.add(playAgainButton);
        endPanel.add(quitButton);

    }

    public void update(Observable obj, Object data)
    {
       if (data instanceof Integer)
        {
            int row = (int) data;

            if (currentPlayer.getColor() == 'R')
                board[row][colSelected].setIcon(redChip);
            else
                board[row][colSelected].setIcon(yellowChip);
        }
        else if (data instanceof Connect4Player)
        {
            if (currentPlayer.getIsWinner())
            {
                removeAll();
                winnerLabel.setText(currentPlayer.getName() + " won!!!");
                add(winnerLabel, BorderLayout.NORTH);
                add(endPanel, BorderLayout.CENTER);
            }
            else
            {
                currentPlayer = (Connect4Player) data;
                playerPrompt.setText(currentPlayer.getName() + "'s turn.");
            }
        }
        else
        {
            String message = (String) data;
            
            if (message.equals("invalid column"))
            {
                JOptionPane.showMessageDialog(null, "Invalid column selection");
            }
            else
            {
                playerStats.setText(message);    
            }
        }

    }

    private class StartButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            //name1 = player1Name.getText();
            //name2 = player2Name.getText();
            model.createPlayers(playerOneName, playerTwoName);
            playerPrompt.setText("Welcome to Connect 4!");

            removeAll();
            add(playerPrompt, BorderLayout.NORTH);
            add(gamePanel, BorderLayout.CENTER);
            add(selectButton, BorderLayout.SOUTH); 
            model.playGame();
        }
    }

    private class MyMouseListener extends MouseAdapter
    {

        public void mouseclicked(MouseEvent e)
        {
            int xPosition = e.getX();
            colWidth = gamePanel.getWidth() / Connect4Board.COLUMNS;
            colSelected = xPosition / colWidth;
        }
    }

    private class SelectButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            model.chooseColumn(colSelected);
        }
    }

    private class PlayAgainButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            playerPrompt.setText("Welcome to Connect 4!");

            removeAll();
            add(playerPrompt, BorderLayout.NORTH);
            add(gamePanel, BorderLayout.CENTER);
            add(selectButton, BorderLayout.SOUTH); 
            model.playGame();
        }
    }

    private class QuitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }
}

