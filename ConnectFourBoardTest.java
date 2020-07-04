// Group 4: Jesus Cruz, Matthew Davitt, Albert Lopez
// CSCI 434, Project 1, Iteration 2
// 2/18/19
// ConnectFourBoardTest.java
//
// This class test the ConnnectFourBoard class

import static org.junit.Assert.*;
import org.junit.*;

public class ConnectFourBoardTest
{
    private ConnectFourBoard testBoard1;
    private ConnectFourBoard testBoard2;
    private ConnectFourBoard testBoard3;

    private String testString1;
    private String testString2;

    @Before
    public void testConnectFourBoard()
    {
        testBoard1 = new ConnectFourBoard();
        testBoard2 = new ConnectFourBoard();
        testBoard3 = new ConnectFourBoard();
        
        for (int i = 0; i < 6; i++)
            testBoard1.addPiece('Y', 1);
        
        for (int i = 0; i < 4; i++)
            testBoard1.addPiece('R', 2);

        for (int i = 3; i < 6; i++)
            testBoard1.addPiece('R', i);
        
        for (int i = 0; i < 3; i++)
            for (int j = 3 - i; j > 0; j--)
            {
                testBoard2.addPiece('R', i);
                testBoard2.addPiece('R', 6 - i);
            }

        for (int i = 0; i < 7; i++)
            testBoard2.addPiece('Y', i);
        
    
        testString1 = "[1][2][3][4][5][6][7]\n"
            + "[ ][Y][ ][ ][ ][ ][ ]\n"
            + "[ ][Y][ ][ ][ ][ ][ ]\n"
            + "[ ][Y][R][ ][ ][ ][ ]\n"
            + "[ ][Y][R][ ][ ][ ][ ]\n"
            + "[ ][Y][R][ ][ ][ ][ ]\n"
            + "[ ][Y][R][R][R][R][ ]\n";

        testString2 = "[1][2][3][4][5][6][7]\n"
            + "[ ][ ][ ][ ][ ][ ][ ]\n"
            + "[ ][ ][ ][ ][ ][ ][ ]\n"
            + "[Y][ ][ ][ ][ ][ ][Y]\n"
            + "[R][Y][ ][ ][ ][Y][R]\n"
            + "[R][R][Y][ ][Y][R][R]\n"
            + "[R][R][R][Y][R][R][R]\n";

    }

    @Test
    public void testAddPiece()
    {
        assertFalse("Unable to add to Column One", testBoard1.addPiece('Y', 1));
        assertTrue("Added to Column Two", testBoard1.addPiece('R', 2));
    }

    @Test
    public void testIsValidMove()
    {
        assertFalse("That is not a valid move", testBoard1.isValidMove(8));
        assertTrue("That is a valid move", testBoard1.isValidMove(5));
    }

    @Test
    public void testGetTurnCount()
    {
        assertEquals("The turn count is 13", 13, testBoard1.getTurnCount());
        assertEquals("The turn count is 19", 19, testBoard2.getTurnCount());
    }

    @Test
    public void testIsWinningTurn()
    {
        assertTrue("There is a win condition in the board",
            testBoard1.isWinningTurn());
        assertTrue("There is a win condition in the board", 
            testBoard2.isWinningTurn());
        assertFalse("There is no win condition", testBoard3.isWinningTurn());
    }

    @Test
    public void testToString()
    {
        assertEquals("The strings do not match", testString1,
            testBoard1.toString());
        assertEquals("The strings do not match", testString2,
            testBoard2.toString());
    }
}
