// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI 434 Project #1, Iteration #2
// Connect4Model.java
// 02-16-19
//
// This program is the model for our Connect4 Game.

import java.util.Observable;

public class Connect4Model extends Observable
{
    private Connect4Player player1;
    private Connect4Player player2;
    private Connect4Board gameBoard;
    private Object data;
    private boolean player1HasWon;
    private boolean player2HasWon;
    private int colChoice;

    public Connect4Model()
    {
        gameBoard = new Connect4Board();
    }

    public void createPlayers(String name1, String name2)    
    {
        player1 = new Connect4Player(name1, 'Y');
        player2 = new Connect4Player(name2, 'R');
    }

    public void chooseColumn(int colChoice)
    {
        this.colChoice = colChoice;
    }

    public void playGame()
    {
        int row;

        do
        {
            data = (Connect4Player) player1;
            updateObservers();

            while (!player1.isValidColChoice(gameBoard, colChoice))
            {
                data = (String) "invalid column";
                updateObservers();
            }

            row = gameBoard.addChip(player1.getColor(), colChoice);
            data = (Integer) row;
            updateObservers();
            player1HasWon = gameBoard.hasConnect4(player1.getColor(), row,
                colChoice);

            if (!player1HasWon && !gameBoard.boardIsFull())
            {
                data = (Connect4Player) player2;
                updateObservers();

                while (!player2.isValidColChoice(gameBoard, colChoice))
                {
                    data = (String) "invalid column";
                    updateObservers();
                }

                row = gameBoard.addChip(player2.getColor(), colChoice);
                data = (Integer) row;
                updateObservers();
                player2HasWon = gameBoard.hasConnect4(player2.getColor(), row,
                    colChoice);
            }
        } while (!player2HasWon && !gameBoard.boardIsFull());

        if (player1HasWon)
        {
            player1.setIsWinner(true);
            player1.addWin();
            player2.addLoss();
            data = (Connect4Player) player1;
            updateObservers();
        }
        else
        {
            player2.setIsWinner(true);
            player1.addLoss();
            player2.addWin();
            data = (Connect4Player) player2;
            updateObservers();
        }

        data = (String) player1.toString() 
            + "\n" + player2.toString();
        updateObservers();
    }

    private void updateObservers()
    {
        setChanged();
        notifyObservers(data);
    }
}
