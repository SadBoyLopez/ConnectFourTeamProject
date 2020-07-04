// Group 4: Matthew Davitt, Albert Lopez, Jesus Cruz
// CSCI 434, Project 1, Iteration 1
// 2/11/2019
// ConnectFourBoard.java
// 
// Creates a connect four board

public class ConnectFourBoard
{
    String[][] gameBoard;
    int[] numPieces;
    int turnCount;
    int lastColumn;

    public ConnectFourBoard()
    {
        gameBoard = new String[6][7];
        numPieces = new int[7];
        lastColumn = 0;
        clearBoard();
    }

    public boolean addPiece(char color, int column)
    {
        int row = 5 - numPieces[column];

        if (numPieces[column] == 6)
        {
            return false;
        }
        else 
        {
            gameBoard[row][column] = "[" + color + "]";
            numPieces[column]++;
            turnCount++;
            lastColumn = column;
            return true;
        }
        
    }

    public boolean isValidMove(int column)
    {

        if(column >= 0 && column <= 6 && numPieces[column] < 6)
            return true;
        return false;
    }

    public int getTurnCount()
    {
        return turnCount;
    }

    public boolean isWinningTurn()
    {

        if (turnCount < 7)
            return false;

        if (turnCount == 42)
            return true;

// All of our errors are coming from here. 
// If we include this the board won't even display,
// and the program won't function properly

        for (int i = 0; i < gameBoard.length - 3; i++)
            for (int j = 0; j < gameBoard[i].length - 3; j++)
            {
                if (!gameBoard[i][j].equals("[ ]")
                        && gameBoard[i + 1][j + 1].equals(gameBoard[i][j])
                        && gameBoard[i + 2][j + 2].equals(gameBoard[i][j])
                        && gameBoard[i + 3][j + 3].equals(gameBoard[i][j]))
                    return true;
            }

        for (int i = gameBoard.length -1; i > gameBoard.length - 4; i--)
            for (int j = 0; j < gameBoard[i].length - 3; j++)
            {
                if (!gameBoard[i][j].equals("[ ]")
                        && gameBoard[i - 1][j + 1].equals(gameBoard[i][j])
                        && gameBoard[i - 2][j + 2].equals(gameBoard[i][j])
                        && gameBoard[i - 3][j + 3].equals(gameBoard[i][j]))
                    return true;
            }


            int row = 6 - numPieces[lastColumn];
/*            int downRun = 0;
            if (numPieces[lastColumn] >= 4)
            {
                String previous = gameBoard[row][lastColumn];
                for (int i = row; i <= 5; i++)
                 {
                    if (gameBoard[i][lastColumn].equals(previous))
                        downRun++;
                    else
                        downRun = 0;
                    previous = gameBoard[i][lastColumn];;
                 }
                if (downRun >= 4)
                    return true;

            }
*/
        for (int i = 0; i < gameBoard.length - 3; i++)
            for (int j = 0; j < gameBoard[i].length; j++)
            {
                if (!gameBoard[i][j].equals("[ ]")
                        && gameBoard[i + 1][j].equals(gameBoard[i][j])
                        && gameBoard[i + 2][j].equals(gameBoard[i][j])
                        && gameBoard[i + 3][j].equals(gameBoard[i][j]))
                    return true;
            }

            int horizontalRun = 0;
            String playerColor = gameBoard[row][lastColumn];
            for (int i = 0; i < gameBoard.length; i++)
            {
                if(gameBoard[row][i].equals(playerColor))
                    horizontalRun++;
                else
                    horizontalRun = 0;
              
                if (horizontalRun >=4)
                    return true;
            }

        return false;
    }

    public String toString()
    {
        String boardString = "";

        for (int i = 1; i <= 7; i++)
            boardString += "[" + i + "]";
        boardString += "\n";

        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
                boardString += gameBoard[i][j];
            boardString += "\n";
        }
        
        return boardString;
    }

    public void clearBoard()
    {
        turnCount = 0;
        for (int i = 0; i < gameBoard.length; i++)
        {
            for (int j = 0; j < gameBoard[i].length; j++)
            {
                gameBoard[i][j] = "[ ]";
            }
        }
        for (int i = 0; i < numPieces.length; i++)
        {
            numPieces[i] = 0;
        }

    }
}
