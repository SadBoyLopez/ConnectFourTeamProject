// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI 434 Project #1, Iteration #1
// Connect4Player.java
// 02-11-19 
//
// This program defines the Connect 4 Player class

public class Connect4Player
{
    private char color;
    private int wins;
    private int losses;
    private String name;
    private boolean isWinner;

    public Connect4Player(String name, char color)
    {
        this.name = name;
        this.color = color;
        wins = 0;
        losses = 0;
        isWinner = false;
    }

    public String getName()
    {
        return name;
    }

    public char getColor()
    {
        return color;
    }

    public int getWins()
    {
        return wins;
    }

    public int getLosses()
    {
        return losses;
    }

    public boolean getIsWinner()
    {
        return isWinner;
    }

    public void addWin()
    {
        wins++;
    }

    public void addLoss()
    {
        losses++;
    }

    public void setIsWinner(boolean bool)
    {
        isWinner = bool;
    }

    public boolean isValidColChoice(Connect4Board board, int colChoice)
    {
        if (colChoice < 1 || colChoice > board.COLUMNS 
            || board.columnIsFull(colChoice))
                return false;
        else
            return true;
    }

    public String toString()
    {
        return "Name: " + getName() + " ("
            + getColor() + ")\nWins: "
            + getWins() + "\nLosses: "
            + getLosses();
    }
}
