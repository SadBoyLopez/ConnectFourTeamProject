// Albert Lopez, Matthew Davitt, Jesus Cruz
// CSCI 434, Project 1, Iteration 3
// ConnectFourServer.java
// 2/26/2019
// 
// This is the server for the client-server iteration of the connect four game

import java.io.*;
import java.net.*;


/**
 * Server for the connect four client-server game
 * @author Albert J. Lopez Jr.
 * @author Matthew J. Davitt
 * @author Jesus Cruz
 * @version Iteration 3
 */

public class ConnectFourServer implements ConnectFourConstants
{
    
    
    public static void main(String[] args)
    {
        new ConnectFourServer();
    }

    /**
     * Establishes server 
     */
    public ConnectFourServer()
    {
        try
        {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server is Started");

            int sessionNum = 1;

            while (true)
            {
                // Listen for a new connection request

                Socket player1 = serverSocket.accept();
                System.out.println("Player 1 has joined " + sessionNum
                    + " from IP address " + player1.getInetAddress());

                // Shows that player is player 1

                DataOutputStream player1Stream = new DataOutputStream(
                    player1.getOutputStream());
                player1Stream.writeInt(PLAYER1);

                Socket player2 = serverSocket.accept();
                System.out.println("Player 2 has joined " + sessionNum
                    + " from IP address " + player2.getInetAddress());

                // Shows that player is player 2

                DataOutputStream player2Stream = new DataOutputStream(
                    player2.getOutputStream());
                player2Stream.writeInt(PLAYER2);

                //Creates thread for current session

                ConnectFourSession task = new ConnectFourSession(player1, player2);

                new Thread(task).start();
                sessionNum++;
            }
        }
        catch(IOException e)
        {
                e.printStackTrace();
        }
        
    }
}
