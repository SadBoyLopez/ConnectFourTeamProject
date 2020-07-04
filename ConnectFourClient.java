// Chuy Cruz, Albert Lopez, Matthew Davitt
// CSCI 434, Project 1, Iteration 3
// 3/2/19 
// ConnectFourClient.java
//
// This is the client to the client-server relation for connect four

import java.io.*;
import java.net.*;
import java.util.Observable;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Client for the connect four client-server game
 * @author Albert J. Lopez Jr.
 * @author Matthew J. Davitt
 * @author Jesus Cruz
 * @version Iteration 3
 */

public class ConnectFourClient extends Observable 
    implements ConnectFourConstants, Runnable
{
    private Connect4Player player;
    private boolean playerWon;
    
    
    private static final String SERVER = "localhost";

    private DataInputStream fromServer;
    private DataOutputStream toServer;
    private int playerNum;
    private boolean waiting;
    private boolean continueToPlay;
    private int colSelected;

    
    /**
     * Starts thread for game of connect four for the connect
     */
    public ConnectFourClient()
    {
        Thread myThread = new Thread(this);
        myThread.start();
    }

    public void run()
    {
        waiting = true;
        continueToPlay = true;
        try
        {
            Socket server = new Socket(SERVER, PORT);
            fromServer = new DataInputStream(server.getInputStream());
            toServer = new DataOutputStream(server.getOutputStream());

            playerNum = fromServer.readInt();
            setChanged();
            notifyObservers("Player " + playerNum);

            fromServer.readInt();

            do
            {
                if(playerNum == PLAYER1)
                {
                    waitForPlayerAction();
                    takeTurn();
                    if(!continueToPlay)
                        break;
                    receiveInformationFromServer();
                }
                else
                {
                    receiveInformationFromServer();
                    if(!continueToPlay)
                        break;
                    waitForPlayerAction();
                    takeTurn();
                }
            }
            while(continueToPlay);
            server.close();
        }
        catch(Exception e)
        {
            System.err.println(e);
            e.printStackTrace();
        }

    }

    
    /**
     * waits for action from player
     * @throws InteruptedException
     */
    private void waitForPlayerAction() throws InterruptedException
    {
        while(waiting)
        {
            Thread.sleep(100);
        }
        waiting = true;
    }

    
    /**
     * Notifies that the player is ready for their turn
     * @param col  column player has chosen
     */
    public void readyForTurn(int col)
    {
        waiting = false;
        colSelected = col;
    }

    
    /**
     * Allows player to take their turn on the Connect Four game
     * @throws IOException
     */
    private void takeTurn() throws IOException
    {
        toServer.writeInt(colSelected);
        int col = (Integer) fromServer.readInt();
        setChanged();
        notifyObservers(new Integer(col));
        String color = fromServer.readUTF(); 

        if (color.equals(Y_STRING))
        {
            setChanged();
            notifyObservers(new Character(YELLOW));
        }
        else
        {
            setChanged();
            notifyObservers(new Character(RED));
        }
        
        int status = fromServer.readInt();
        System.out.println(status);
        if (status == PLAYER1_WON)
        {
            System.out.println("Player One Won!");
            setChanged();
            notifyObservers(new String("END OF GAME: Player 1 won!"));
            continueToPlay = false;
        }
        else if (status == PLAYER2_WON)
        {

            System.out.println("Player Two Won!");
            setChanged();
            notifyObservers(new String("END OF GAME: Player 2 won!"));
            continueToPlay = false;
        }

    }
    
    /**
     * Recieve information of other player's turn
     * @throws IOException
     */
    private void receiveInformationFromServer() throws IOException
    {
        int status = fromServer.readInt();

        if (status == PLAYER1_WON)
        {
            if (playerNum == 2)
            { 
                int col = (Integer) fromServer.readInt();
                setChanged();
                notifyObservers(new Integer(col));
        
                
                String color = fromServer.readUTF(); 

                if (color.equals(Y_STRING))
                {
                    setChanged();
                    notifyObservers(new Character(YELLOW));
                }
                else
                {
                    setChanged();
                    notifyObservers(new Character(RED));
                }
            }
            System.out.println("Player 1 won!");
            setChanged();
            notifyObservers(new String("Game Ended: Player 1 won!"));
            continueToPlay = false;
        }
        else if (status == PLAYER2_WON)
        {
            if (playerNum == 1)
            {
                
                int col = (Integer) fromServer.readInt();
                setChanged();
                notifyObservers(new Integer(col));
                

                String color = fromServer.readUTF(); 
                
                if (color.equals(Y_STRING))
                {
                    setChanged();
                    notifyObservers(new Character(YELLOW));
                }
                else
                {
                    setChanged();
                    notifyObservers(new Character(RED));
                }
            }
            System.out.println("Player 2 won!");
            setChanged();
            notifyObservers(new String("Game Ended: Player 2 won!"));
            continueToPlay = false;
        }
        else
        {
            
            int col = (Integer) fromServer.readInt();
            setChanged();
            notifyObservers(new Integer(col));
            System.out.println("Col passed: " + col);

            String color = (String) fromServer.readUTF(); 
            
            System.out.println("Color Passed = " + color);
            if (color.equals(Y_STRING))
            {
                setChanged();
                notifyObservers(new Character(YELLOW));
            }
            else if (color.equals(R_STRING))
            {
                setChanged();                    
                notifyObservers(new Character(RED));
            }
        }
    }
}
