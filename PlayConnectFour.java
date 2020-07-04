// Group 4: Matthew Davitt, Albert Lopez, Jesus Cruz
// CSCI 434, Project 1, Iteration 1
// 2/11/2019
// PlayConnectFour.java
// 
// Simulates a game of Connect four

import java.util.Scanner;

public class PlayConnectFour
{
    
    
    public static void main(String[] args)
    {
        printInstructions();
        ConnectFourPlayer[] playerArray = new ConnectFourPlayer[2];
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Player one enter your name: ");
        String playerOneName = scan.nextLine();

        System.out.print("Player two enter your name: ");
        String playerTwoName = scan.nextLine();

        playerArray[0] = new ConnectFourPlayer(playerOneName, 'Y');
        playerArray[1] = new ConnectFourPlayer(playerTwoName, 'R');

        ConnectFourBoard gameBoard = new ConnectFourBoard();
        
        char answer;
        do
        {
            int playerNum = 0;
            while(!gameBoard.isWinningTurn())
            {
                playerNum = gameBoard.getTurnCount() % 2;
                String currentPlayer = playerArray[playerNum].getName();
                System.out.println("It is player " + currentPlayer +
                    " turn");

                takeTurn(playerArray[playerNum], gameBoard);
            }
            System.out.println("The winner was: " + playerArray[playerNum]);
            playerArray[playerNum].addWin();
            playerArray[0].toString();
            playerArray[1].toString();

            System.out.print("Play Again? (Y/N):");
            String response = scan.nextLine();
            System.out.println();
            
            answer = response.toUpperCase().charAt(0);
            gameBoard.clearBoard();
        }while (answer == 'Y');
    }
    
    private static void takeTurn(ConnectFourPlayer player, ConnectFourBoard gameBoard)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println(gameBoard.toString());
        System.out.println("What column are you placing your piece: ");
        int column = scan.nextInt();

        while(!gameBoard.isValidMove(column-1))
        {
            System.out.println("That is not a valid move");
            System.out.println("What column are you placing your piece: ");
            column = scan.nextInt();
        }
        char playerColor = player.getColor();
        gameBoard.addPiece(playerColor, column - 1);
    }

    public static void printInstructions()
    {
        System.out.println("This is connect four.");
    }
}
