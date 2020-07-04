// Jesus Cruz, Matthew Davitt, Albert Lopez
// Still testing

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observer;
import java.util.Observable;


/**
 * GUI for the connect four client-server game
 * @author Albert J. Lopez Jr.
 * @author Matthew J. Davitt
 * @author Jesus Cruz
 * @version Iteration 3
 */

public class ConnectFourClientGUI extends JFrame implements
ConnectFourConstants, Observer
{
    public static final int WIDTH = 700;
    public static final int HEIGHT = 650;

    private JLabel[][] board;
    private JLabel winnerLabel;
    private JLabel playerStatus;
    private JPanel gamePanel;
    private JPanel endPanel;
    private JButton selectButton;
    private JTextField player1Name;
    private JTextField player2Name;
    
    private static final ImageIcon EMPTY = new ImageIcon("chip_image/blankboard.png");
    private static final ImageIcon REDCHIP = new ImageIcon("chip_image/redchip.png");
    private static final ImageIcon YELLOWCHIP = new ImageIcon("chip_image/yellowchip.png");
    
    public int col;
    public int colSelected;
    private int colWidth;
    private int xPosition;
    private int[] numPieces;

    private JLabel player1ScoreLabel;
    private JLabel player2ScoreLabel;

    private JPanel playerPanel;

    private String playerName;
    private JLabel playerLabel;

    private ConnectFourClient client;

    /**
     * Sets up the GUI for connect four
     */
    public ConnectFourClientGUI()
    {
        super("Connect 4");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //colSelected = -1;
        numPieces = new int[7];

        playerStatus = new JLabel("");

        // start panel
        playerLabel = new JLabel("You have not been assigned to a game yet");
        playerPanel = new JPanel(new GridLayout(3,1));
        player1ScoreLabel = new JLabel("Player 1\'s score : 0");
        player2ScoreLabel = new JLabel("Player 2\'s score : 0");


        playerPanel.add(player1ScoreLabel);
        playerPanel.add(player2ScoreLabel);
        playerPanel.add(playerStatus);

        add(playerPanel, BorderLayout.NORTH);

        // game panel

        board = new JLabel[Connect4Board.ROWS][Connect4Board.COLUMNS];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                board[i][j] = new JLabel(EMPTY);

        gamePanel = new JPanel(new GridLayout(Connect4Board.ROWS,
            Connect4Board.COLUMNS));

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[i].length; j++)
                gamePanel.add(board[i][j]);

        for (int i = 0; i < numPieces.lenth; i++)
        {
            numPieces[i] = 5;
        }

        MouseListener listener = new MyMouseListener();
        gamePanel.addMouseListener(new MyMouseListener());

        selectButton = new JButton("Select");
        selectButton.addActionListener(new SelectButtonListener());

        selectButton.setEnabled(false);
	    add(gamePanel, BorderLayout.CENTER);
        add(selectButton, BorderLayout.SOUTH);

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
        pack();
        
        client = new ConnectFourClient();
        client.addObserver(this);
    }

    
    /**
     * Recvieves information back from the connect four client
     * @param o  Observable class recieving information from
     * @param obj  Object being passed from observable class
     */
    public void update(Observable o, Object obj)
    {
        
        
        if (obj instanceof String)
        {
            String text = (String) obj;
            if (text.contains("won"))
            {
                playerStatus.setText(text);
                selectButton.setEnabled(false);
            }
            else
                playerStatus.setText((String) obj);
        }
        else if (obj instanceof Integer)
        {
            col = (Integer) obj;
            
            System.out.println("Recieved column: " + col);
        }
        else if (obj instanceof Character)
        {
            
            char currentColor = (char) obj;
            System.out.println("Recieved color: " + currentColor);
            if (currentColor == RED)
            {
                System.out.println("Board Col: " + col);
                JLabel currentPiece = board[numPieces[col]][col];
                currentPiece.setIcon(REDCHIP);
                
            }
            else
            {
                JLabel currentPiece = board[numPieces[col]][col];
                currentPiece.setIcon(YELLOWCHIP);
            }
        }
    }
    

    /**
     * Determines which column was clicked by the mouse
     */ 
    private class MyMouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            xPosition = e.getX();
            colWidth = gamePanel.getWidth() / Connect4Board.COLUMNS;
            colSelected = xPosition / colWidth;
            System.out.println("Col is " + colSelected);
            selectButton.setEnabled(true);
        }
    }

    
    /**
     * Passes selected column to client
     */
    private class SelectButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            colSelected = xPosition / colWidth;
            System.out.println(colSelected);
            
            if (numPieces[colSelected] <= FULL_COL)
            {
                selectButton.setEnabled(false);
                client.readyForTurn(colSelected);
                numPieces[colSelected]--;
            }
            else
            {
                System.out.println(numPieces[colSelected] + "Pieces");
                playerStatus.setText("Column " + (colSelected + 1 )+ " is full.");
            } 
        }
    }

    /**
     * allows user to play once again
     */
    private class PlayAgainButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {

            removeAll();
            add(gamePanel, BorderLayout.CENTER);
            add(selectButton, BorderLayout.SOUTH); 
        }
    }
    /**
     * closes GUI
     */
    private class QuitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e) 
        {
            System.exit(0);
        }
    }
}

