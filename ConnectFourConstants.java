// ALbert Lopez, Jesus Cruz, Matthew Davitt
// CSCI 434, Project 1, iteration 3
// 2.26.2019
// ConnectFourConstants.java
//
// Constants for the connect four client server game

public interface ConnectFourConstants
{
    public static final int START = 0;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    public static final int PLAYER1_WON = 3;
    public static final int PLAYER2_WON = 4;
    public static final int CONTINUE = 5;
    public static final int WAIT_FOR_PLAYER = 6;
    public static final int PLAYER_TURN = 7;
    public static final char YELLOW = 'Y';
    public static final char RED = 'R';
    public static final String Y_STRING = "Yellow";
    public static final String R_STRING = "Red";

    // end of game constants

    public static final int FULL_BOARD = 42;
    public static final int FULL_COL = 6;
    

    // port number for server
    public static final int PORT = 8123;

}
