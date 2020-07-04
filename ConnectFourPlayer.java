// Group 4: Matthew Davitt, Albert Lopez, Jesus Cruz
// CSCI 434, Project 1, Iteration 1
// 2/11/2019
// ConnectFourPlayer.java
// 
// Class for connect four player

public class ConnectFourPlayer
{
    String name;
    char color;
    int winCount;

    public ConnectFourPlayer(String name, char color)
    {
        this.name = name;
        this.color = color;
        winCount = 0;
    }

    public char getColor()
    {
        return color;
    }

    public String getName()
    {
        return name;
    }

    public int getWinCount()
    {
        return winCount;
    }

    public void addWin()
    {
        winCount++;
    }

    public String toString()
    {
        return name + " wins: " + winCount;
    }
}
