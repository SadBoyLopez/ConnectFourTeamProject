// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI Project #1, Iteration #1
// Connect4PlayerTest.java
// 02-12-19
//
// This program tests the Connect4Player class.

import static org.junit.Assert.*;
import org.junit.*;

public class Connect4PlayerTest
{
    @BeforeClass
    public static void testConnect4Player()
    {
        System.out.println("This program performs unit testing on "
            + "the Connect4Player class.");
    }

    @Test
    public void testPlayerConstructor()
    {
        String name = "";
        char color = 'O';
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        assertEquals("name = Bob", "Bob", thePlayer.getName());
        assertEquals("color = Y", 'Y', thePlayer.getColor());
    }

    @Test
    public void testPlayerGetName()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        assertEquals("name = Bob", "Bob", thePlayer.getName());
        assertNotEquals("name != Dan", "Dan", thePlayer.getName());
    }

    @Test
    public void testPlayerGetColor()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        assertEquals("color = Y", 'Y', thePlayer.getColor());
        assertNotEquals("color != R", 'R', thePlayer.getColor());
    }

    @Test
    public void testPlayerGetWins()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        thePlayer.addWin();
        thePlayer.addWin();
        thePlayer.addWin();
        assertEquals("wins = 3", 3, thePlayer.getWins());
        assertNotEquals("wins != 2", 2, thePlayer.getWins());
    }

    @Test
    public void testPlayerGetLosses()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        thePlayer.addLoss();
        assertEquals("losses = 1", 1, thePlayer.getLosses());
        assertNotEquals("losses != 2", 2, thePlayer.getLosses());
    }

    @Test
    public void testAddWin()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        thePlayer.addWin();
        thePlayer.addWin();
        thePlayer.addWin();
        thePlayer.addWin();
        assertEquals("wins = 4", 4, thePlayer.getWins());
        assertNotEquals("wins != 3", 3, thePlayer.getWins());
    }

    @Test
    public void testAddLoss()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        thePlayer.addLoss();
        thePlayer.addLoss();
        assertEquals("losses = 2", 2, thePlayer.getLosses());
        assertNotEquals("losses != 1", 1, thePlayer.getLosses());
    }

    @Test
    public void testPlayerToString()
    {
        Connect4Player thePlayer = new Connect4Player("Bob", 'Y');
        thePlayer.addWin();
        thePlayer.addWin();
        thePlayer.addWin();
        thePlayer.addWin();
        thePlayer.addLoss();
        thePlayer.addLoss();
        assertEquals("Testing toString", "Name: Bob (Y)\nWins: 4\nLosses: 2",
            thePlayer.toString());
        assertNotEquals("Testing toString", "Name: Dan (R)\nWins: 3\nLosses: 1",
            thePlayer.toString());
    }

}
