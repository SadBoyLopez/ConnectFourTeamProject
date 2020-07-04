//  Group 4: Jesus Cruz, Matthew Davitt, Albert Lopez
// CSCI 434, Project 1, Iteration 1
// 2/18/19
// ConnectFourPlayerTest.java
//
// This program test the ConnectFourPlayer class.

import static org.junit.Assert.*;
import org.junit.*;

public class ConnectFourPlayerTest
{
    private ConnectFourPlayer testPlayer1;
    private ConnectFourPlayer testPlayer2;
    
    @Before
    public void testConnectFourPlayer()
    {
        testPlayer1 = new ConnectFourPlayer("Richard",'G');
        testPlayer2 = new ConnectFourPlayer("Gilbert",'R');

        for (int i = 0; i<=9; i++)
            testPlayer1.addWin();

        for (int i = 0; i<=17; i++)
            testPlayer2.addWin();
    }

    @Test
    public void testGetName()
    {
        assertEquals("Should be Richard","Richard",testPlayer1.getName());
        assertEquals("Should be Gilbert", "Gilbert",testPlayer2.getName());
    }

    @Test
    public void testGetColor()
    {
        assertEquals("Should be Green",'G',testPlayer1.getColor());
        assertEquals("Should be Red", 'R',testPlayer2.getColor());
    }

    @Test
    public void testGetWinCount()
    {
        assertEquals("Wins should be 10", 10, testPlayer1.getWinCount());
        assertEquals("Wins should be 18", 18, testPlayer2.getWinCount());
    }

    @Test
    public void testToString()
    {
        assertEquals("toString displaying improperly", "Richard wins: 10", 
            testPlayer1.toString());
        assertEquals("toString displaying improperly", "Gilbert wins: 18",
            testPlayer2.toString());
    }
}
