// Mary Eby, Matthew Macasadia, and David Wederstrandt
// CSCI 434 Project #1, Iteration #1
// Connect4Board.java
// 02-11-19
// 
// This program defines the board class

import java.util.Arrays;

public class Connect4Board
{
    public static final int WINNING_NUM = 4;
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;
    public static final int BOARD_FULL = ROWS * COLUMNS;

    private char[][] board;
    private int spacesFilled;
    
    public Connect4Board()
    {
        spacesFilled = 0;
        board = new char[ROWS][COLUMNS];

        for (int row = 0; row < board.length; row++)
            for (int col = 0; col < board[row].length; col++)
                board[row][col] = 'O';
    }

    public char getIndex(int row, int col)
    {
        return board[row][col];
    }

    public void setIndex(int row, int col, char c)
    {
        board[row][col] = c;
        spacesFilled++;
    }

    public boolean columnIsFull(int column)
    {
        int chipsInCol = 0;

        for (int row = 0; row < board.length; row++)
        {
            if (board[row][column] != 'O')
                chipsInCol++;
        }

        return (chipsInCol == ROWS);
    }

    public boolean boardIsFull()
    {
        return (spacesFilled == BOARD_FULL);
    }

    public int addChip(char playerColor, int column)
    {
        int row = board.length - 1;
        
        while (getIndex(row, column) != 'O')
            row--;
        
        setIndex(row, column, playerColor);
        spacesFilled++;

        return row;
    }

    public boolean hasConnect4(char playerColor, int row, int column)
    {
        if (hasVertical(playerColor, column))
            return true;

        if (hasHorizontal(playerColor, row))
            return true;
        
        if (hasDiagonal(playerColor, row, column))
            return true;

        return false;
    }

    private boolean hasVertical(char playerColor, int column)
    {
        int chipRun = 0;
        
        for (int row = 0; row < board.length; row++)
        {
            if (board[row][column] == playerColor)
            {
                chipRun++;

                if (chipRun == WINNING_NUM)
                    return true;
            }
            else
                chipRun = 0;
        }

        return false;
    }

    private boolean hasHorizontal(char playerColor, int row)
    {
        int chipRun = 0;
        
        for (int column = 0; column < board[row].length; column++)
        {
            if (board[row][column] == playerColor)
            {
                chipRun++;

                if (chipRun == WINNING_NUM)
                    return true;
            }

            else
                chipRun = 0;
        }
        return false;
    }



    private boolean hasDiagonal(char playerColor, int row, int column)
    {
        int chipRun = 0;
        int leftRowIndex = row;
        int leftColIndex = column;
        int rightRowIndex = row;
        int rightColIndex = column;

        // looking for left starting point
        int i = row;
        int j = column;
        
        while (i != 0 && j != 0)
        {
            i--;
            j--;
            leftRowIndex = i;
            leftColIndex = j;
        }

        // looking for right starting point
        i = row;
        j = column;
        
        while (i != 0 && j != COLUMNS - 1)
        {
            i--;
            j++;
            rightRowIndex = i;
            rightColIndex = j;
        }

        // iterating through diagonal from left starting point
        while (leftRowIndex < ROWS - 1 && leftColIndex < COLUMNS - 1)
        {
            leftRowIndex++;
            leftColIndex++;

            if (board[leftRowIndex][leftColIndex] == playerColor)
                chipRun++;
            else
                chipRun = 0;

            if (chipRun == WINNING_NUM)
                return true;
        }

        // iterating through diagonal from right starting point
        while (rightRowIndex < ROWS - 1 && rightColIndex > 0)
        {
            rightRowIndex++;
            rightColIndex--;

            if (board[rightRowIndex][rightColIndex] == playerColor)
                chipRun++;
            else
                chipRun = 0;

            if (chipRun == WINNING_NUM)
                return true;
        }

        return false;
    }

    public String toString()
    {
        String result = "";

        for (int i = 1; i <= COLUMNS; i++)
            result += i + " ";
        
        for (int row = 0; row < board.length; row++)
        {
            result += "\n";
            for (int col = 0; col < board[row].length; col++)
                result += board[row][col] + " ";
        }

        return result;
    }
}

