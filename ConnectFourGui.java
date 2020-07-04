// Group 4: Jesus Cruz, Matthew Davitt, Albert Lopez
// 2/18/19
// CSCI 434, Project 1, Iteration 2
// ConnectFourGui.java
//
// This class creates a GUI in which users can play connect four.

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ConnectFourGui extends JFrame
{
    public static final int WIDTH = 850;
    public static final int HEIGHT = 650;
    public static final int ROWS = 6;
    public static final int COLS = 7;
    private static final int MAX_CHIPS = 6;

    private ImageIcon blankSpace = new ImageIcon("blankboard.png");
    private ImageIcon redChip = new ImageIcon("redchip.png");
    private ImageIcon yellowChip = new ImageIcon("yellowchip.png");

    private JLabel playerOneLabel = new JLabel();
    private JLabel playerTwoLabel = new JLabel();
    private JButton[] colButton;
    private JLabel[][] labelArr;
    
    private ConnectFourPlayer playerOne;
    private ConnectFourPlayer playerTwo;
    private ConnectFourBoard theBoard;
    private int[] numPieces;

    private JLabel statusLabel = new JLabel();

    public static void main(String[] args)
    {
        ConnectFourGui gui = new ConnectFourGui();
        gui.setVisible(true);
    }

    public ConnectFourGui()
    {

        super("Connect Four");
	    setSize(WIDTH,HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        gameStartUp();

        colButton = new JButton[COLS];    
        for (int i = 0; i < colButton.length; i++)
        {
            colButton[i] = new JButton("Column " + (i + 1));
        }

        ButtonListener buttonListener = new ButtonListener();

        for (int i = 0; i < colButton.length; i++)
        {
            colButton[i].addActionListener(buttonListener);
        }

        JPanel boardPanel = new JPanel(new GridLayout(ROWS, COLS));
        labelArr = new JLabel[ROWS][COLS];
        for (int i = 0; i < 6; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                labelArr[i][j] = new JLabel(blankSpace);
            }
        }

        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLS; j++)
            {
                boardPanel.add(labelArr[i][j]);
            }
        }

        JPanel playerOnePanel = new JPanel();
        playerOnePanel.add(playerOneLabel);

        JPanel playerTwoPanel = new JPanel();
        playerTwoPanel.add(playerTwoLabel);

        JPanel statusPanel = new JPanel();
        statusLabel.setText("Player one\'s turn");
        statusPanel.add(statusLabel);

        JPanel buttonPanel = new JPanel(new GridLayout(ROWS, COLS));
        for (int i = 0; i < colButton.length; i++)
        {
            buttonPanel.add(colButton[i]);
        }

        add(playerOnePanel, BorderLayout.WEST);
        add(playerTwoPanel, BorderLayout.EAST);
        add(buttonPanel, BorderLayout.NORTH);
        add(boardPanel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.SOUTH);
        pack();
    }
    
    private class ButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Object buttonPushed = event.getSource();
            
            int selectedCol =0;
            for (int i = 0; i < colButton.length; i++)
            {
                if (buttonPushed == colButton[i])
                    selectedCol = i;
            }

            int currentTurn = theBoard.getTurnCount() % 2;
            if (currentTurn == 0)
            {
                statusLabel.setText("Player two\'s turn");
                theBoard.addPiece('R', selectedCol);
                JLabel currentPiece = labelArr[numPieces[selectedCol]][selectedCol];
                currentPiece.setIcon(redChip);
                numPieces[selectedCol]--;
            }
            else
            {
                theBoard.addPiece('Y', selectedCol);
                JLabel currentPiece = labelArr[numPieces[selectedCol]][selectedCol];
                statusLabel.setText("Player one\'s turn");
                currentPiece.setIcon(yellowChip);
                numPieces[selectedCol]--;
            }

            if (theBoard.isWinningTurn())
            {
                if (currentTurn == 0)
                {
                    JOptionPane.showMessageDialog(null, "Player 1 is the" 
                        +" winner");
                    playerOne.addWin();
                    restartGame();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Player 2 is the" 
                        +" winner");
                    playerTwo.addWin();
                    restartGame();
                }
            }

            if(theBoard.getTurnCount() == 42)
            {
                statusLabel.setText("Board full, game tied");
            }
            
        }
    }
    
    public void restartGame()
    {
        int answer = JOptionPane.showConfirmDialog(null, "Play again?");
        if (answer == JOptionPane.YES_OPTION)
        {
            theBoard.clearBoard();
            for (int i = 0; i < 6; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    labelArr[i][j].setIcon(blankSpace);
                }
            }

            for (int i = 0; i < numPieces.length; i++)
            {
                numPieces[i] = 5;
            }

            playerOneLabel.setText(playerOne.toString());
            playerTwoLabel.setText(playerTwo.toString());
        }
        else
            System.exit(0);
    }
    
    public void gameStartUp()
    {
        theBoard = new ConnectFourBoard();
        numPieces = new int[7];
        for (int i = 0; i < numPieces.length; i++)
        {
            numPieces[i] = 5;
        }
        playerOne = new ConnectFourPlayer("Player One", 'R');
        playerTwo = new ConnectFourPlayer("Player Two", 'Y');
        
        playerOneLabel.setText(playerOne.toString());
        playerTwoLabel.setText(playerTwo.toString());
    }

}
