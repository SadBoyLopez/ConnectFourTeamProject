// Matthew Davitt, ALbert Lopez, Jesus Cruz
// CSCI 434, Project 1, Iteration 3
// 2/26/19
// ConnectFourSession.java
// 
// Session for connect four client server game

import java.io.*;
import java.net.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

/**
 * Session for the connect four client-server game
 * @author Albert J. Lopez Jr.
 * @author Matthew J. Davitt
 * @author Jesus Cruz
 * @version Iteration 3
 */

public class ConnectFourSession implements ConnectFourConstants, Runnable
{
    private Socket player1;
    private Socket player2;

    

    /**
     * Constructor of ConnectFourSession class
     * @param player1  Establishes socket for player one in game
     * @param player2  Establishes socket for player two in game
     */
    public ConnectFourSession(Socket player1, Socket player2)
    {
        this.player1 = player1;
        this.player2 = player2;
    }

    
    /**
     * Run method to create the game of connect four
     */
    
    public void run()
    {
        try
        {
            DataInputStream fromPlayer1
                = new DataInputStream(player1.getInputStream());
            DataOutputStream toPlayer1
                = new DataOutputStream(player1.getOutputStream());
            DataInputStream fromPlayer2
                = new DataInputStream(player2.getInputStream());
            DataOutputStream toPlayer2
                = new DataOutputStream(player2.getOutputStream());

            
            toPlayer1.writeInt(START);
            toPlayer2.writeInt(START);

            // Set up team DM^2's connect four board method


            Connect4Board theBoard = new Connect4Board();

            while (true)
            {
                // Handle Player One's turn

                int col = fromPlayer1.readInt();
                int row = theBoard.addChip(YELLOW, col);
                
                System.out.print("Session col: " + col);
                System.out.print("Session row: " + row);

                toPlayer1.writeInt(col);

                toPlayer1.writeUTF(Y_STRING);

                if (theBoard.hasConnect4(YELLOW, row, col))
                {
                    toPlayer1.writeInt(PLAYER1_WON);
                    toPlayer2.writeInt(PLAYER1_WON); 
                    
                    toPlayer2.writeInt(col);
                    toPlayer2.writeUTF(Y_STRING);
                    break;
                }
                else
                {
                    toPlayer2.writeInt(CONTINUE);
                    toPlayer1.writeInt(WAIT_FOR_PLAYER);
                    
                    // Send Player1's move
                
                    toPlayer2.writeInt(col);
                    toPlayer2.writeUTF(Y_STRING);
                }



                // Handle Player Two's turn

                col = fromPlayer2.readInt();
                row = theBoard.addChip(RED, col);
                
                toPlayer2.writeInt(col);
                toPlayer2.writeUTF(R_STRING);
                
                if (theBoard.hasConnect4(RED, row, col))
                {
                    toPlayer1.writeInt(PLAYER2_WON);
                    toPlayer2.writeInt(PLAYER2_WON); 
                    
                    toPlayer1.writeInt(col);
                    toPlayer1.writeUTF(R_STRING);
                    break;
                }
                else if (theBoard.boardIsFull())
                {
                    
                    toPlayer1.writeInt(FULL_BOARD);
                    toPlayer2.writeInt(FULL_BOARD); 
                    
                    toPlayer1.writeInt(col);
                    toPlayer1.writeUTF(R_STRING);
                    break;
                }
                else
                {
                    toPlayer1.writeInt(CONTINUE);
                    toPlayer2.writeInt(WAIT_FOR_PLAYER);
                    
                    // Send Player2's move
                
                    toPlayer1.writeInt(col);
                    toPlayer1.writeUTF(R_STRING);
                }

            
            }
            
            player1.close();
            player2.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
