// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI Project #1, Iteration #1
// Connect4BoardTest.java
// 02-11-19
//
// This program tests the Connect4Board class.

import static org.junit.Assert.*;
import org.junit.*;

public class Connect4BoardTest
{
    @BeforeClass
    public static void testConnect4Board()
    {
        System.out.println("This program performs unit testing on "
            + "the Connect4Board class.");
    }

    @Test
    public void testBoardConstructor()
    {
        Connect4Board theBoard = new Connect4Board();
        assertEquals("Board size = 42", 42, theBoard.COLUMNS * theBoard.ROWS);
        assertNotEquals("Board size != 0", 0, theBoard.ROWS
            * theBoard.COLUMNS);
    }

    @Test
    public void testIsColumnFull()
    {
        Connect4Board theBoard = new Connect4Board();
        int column = 2;

        for (int i = theBoard.ROWS - 1; i > theBoard.ROWS / 2; i--)
            theBoard.setIndex(i, column, 'Y');

        assertFalse("The column is not full", theBoard.columnIsFull(column));
        
        for (int i = theBoard.ROWS / 2; i >= 0; i--)
            theBoard.setIndex(i, column, 'R');

        assertTrue("The column is full", theBoard.columnIsFull(column));
    }

    @Test 
    public void testIsBoardFull()
    {
        Connect4Board theBoard = new Connect4Board();

        assertFalse("The board is empty.", theBoard.boardIsFull());

        for (int i = theBoard.ROWS - 1; i > theBoard.ROWS / 2; i--)
            for (int j = theBoard.COLUMNS - 1; j >= 0; j--)
                theBoard.setIndex(i, j, 'Y');

        assertFalse("The board is not full", theBoard.boardIsFull());
        
        for (int i = theBoard.ROWS / 2; i >= 0; i--)
            for (int j = theBoard.COLUMNS - 1; j >= 0; j--)
                theBoard.setIndex(i, j, 'R');

        assertTrue("The board is full", theBoard.boardIsFull());
    }

    @Test
    public void testAddChip()
    {
        Connect4Board theBoard = new Connect4Board();
        int row = theBoard.addChip('R', 4);
        assertEquals("R at [0,4]", 'R', theBoard.getIndex(row, 4));
        assertNotEquals("No chip at [0,3]", 'R',
            theBoard.getIndex(row, 3));
    }

    @Test
    public void testHasConnect4()
    {
        Connect4Board theBoard = new Connect4Board();
        assertFalse("The board does not have a Connect 4.",
            theBoard.hasConnect4('R', 3, 3));

        int row = 0;
        int col; 
        for (col = theBoard.COLUMNS - 1; col >= 0; col--)
            theBoard.setIndex(row, col, 'Y');

        assertTrue("The board has a horizontal Connect 4",
            theBoard.hasConnect4('Y', row, 2));

        theBoard = new Connect4Board();

        col = 2;
        for (row = theBoard.ROWS - 1; row >= 0; row--)
            theBoard.setIndex(row, col, 'R');

        assertTrue("The board has a vertical Connect 4",
            theBoard.hasConnect4('R', 4, col));

        theBoard = new Connect4Board();
        theBoard.setIndex(5, 0, 'R');
        theBoard.setIndex(4, 1, 'R');
        theBoard.setIndex(3, 2, 'R');
        theBoard.setIndex(2, 3, 'R');

        assertTrue("The board has a diagonal Connect 4",
            theBoard.hasConnect4('R', 3, 2));

        theBoard = new Connect4Board();
        theBoard.setIndex(2, 2, 'Y');
        theBoard.setIndex(3, 3, 'Y');
        theBoard.setIndex(4, 4, 'Y');
        theBoard.setIndex(5, 5, 'Y');

        assertTrue("The board has a diagonal Connect 4",
            theBoard.hasConnect4('Y', 4, 4));
    }

    @Test
    public void testBoardToString()
    {
        Connect4Board theBoard = new Connect4Board();

        String testString = "";

        for (int i = 1; i <= theBoard.COLUMNS; i++)
            testString += i + " ";

        for (int i = 1; i <= theBoard.ROWS; i++)
        {
            testString += "\n";
            testString += "O O O O O O O ";
        }
        
        assertEquals("theBoard and testString are equal",
            theBoard.toString(), testString);
    }
}
