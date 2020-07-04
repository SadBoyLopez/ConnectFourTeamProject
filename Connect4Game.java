// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI 434 Project #1, Iteration #1
// Connect4Game.java
// 02-11-19
//
// This program plays the game of Connect 4.

import java.util.Scanner;

public class Connect4Game
{
    private static Scanner scan = new Scanner(System.in);

    private static Connect4Player player1;
    private static Connect4Player player2;
    
    public static void main(String[] arg)
    {
        char playAgain;

        printInstructions();
        playerInfo();

        do
        {
            System.out.println(player1 + "\n");
            System.out.println(player2 + "\n");
            playGame();
            System.out.print("\nWould you like to play again? "
                + "(Y/N): ");
            playAgain = scan.nextLine().charAt(0);
        } while (playAgain == 'y' || playAgain == 'Y');
    
        System.out.println(player1);
        System.out.println(player2);
    }

    public static void printInstructions()
    {
        System.out.println("This program represents a text-based "
            + "game of Connect 4,");
        System.out.println("where two players alternate placing chips "
            + "into a");
        System.out.println("board until four of the player's colored "
            + "checkers");
        System.out.println("line up either horizontally, vertically, "
            + "or diagonally.\n");
    }

    public static void playerInfo()
    {
        System.out.print("Player 1, please enter your name: ");
        String player1Name = scan.nextLine();
        player1 = new Connect4Player(player1Name, 'Y');

        System.out.print("Player 2, please enter your name: ");
        String player2Name = scan.nextLine();
        player2 = new Connect4Player(player2Name, 'R');
        System.out.println();
    }

    public static void playGame()
    {
        Connect4Board gameBoard = new Connect4Board();
        boolean player1Won = false;
        boolean player2Won = false;
        
        System.out.println("\n" + gameBoard + "\n");

        do
        {
            player1Won = player1.takeTurn(gameBoard);
            System.out.println("\n" + gameBoard + "\n");

            if (!player1Won && !gameBoard.boardIsFull())
            {
                player2Won = player2.takeTurn(gameBoard);
                System.out.println("\n" + gameBoard + "\n");
            }
        } while (!player1Won && !player2Won && 
            !gameBoard.boardIsFull());
    
        if (player1Won)
        {
            System.out.println(player1.getName() + " won!!!");
            player1.addWin();
            player2.addLoss();
        }
        else
        {
            System.out.println(player2.getName() + " won!!!");
            player1.addLoss();
            player2.addWin();
        }
    }

}
