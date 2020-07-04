Mary Eby, Matthew Macasadia, and David Wederstrandt
CSCI 434 Project #1, Iteration #1
02-18-19
README.txt

Classes:
    Connect4Model.java
    Connect4Player.java
    Connect4Board.java
    Connect4Game.java
    Connect4BoardTest.java
    Connect4PlayerTest.java
    Connect4GUI.java

Class Descriptions: 
    Connect4Player.java
        - Connect4Player(String name, char color)
            "initializes the Connect 4 player with name and color of their
             chip, as well as wins and losses totals"
	- getName()
            "returns the the name of the player"
        - getColor()
            "returns the color of the player's chip"
        - getWins()
            "returns the number of wins"
        - getLosses()
            "returns the number of losses"
        - addWin()
            "increments the number of wins"
        - addLoss()
            "increments the number of losses"
        - isValidColChoice(int colChoice)
            "returns whether a column chosen is valid or not"
        - toString()
            "prints player's name, wins, and losses"

    Connect4Board.java
        - Connect4Board()
            "creates 2-D array text-based representation of an empty Connect 4
             board"
        - getIndex(int row, int column)
            "returns the char present at the given position"
        - setIndex(int row, int col, char c)
            "assigns given char to specified positon"
        - columnIsFull(int column)
            "returns whether column is full"
        - boardIsFull()
            "returns whether the board is full"
        - addChip(char playerColor, int column)
            "if column or board is not full, adds chip to next available slot"
        - hasConnect4(char playerColor, int row, int column)
            "checks if a player has a connect 4"
        - toString()
            "displays 2-D array text-based representation of the current 
             Connect 4 board"

    // Connect4Model.java will replace this class
    Connect4Game.java
        - main(String[] args)
            "manages gameplay"
        - printInstructions()
            "prints the instructions of the game"
        - playerInfo()
            "takes user input names and creates Connect 4 players and assigns
             chip colors to each player"
        - playGame()
            "manages the turn of each player and asks if players want to
             continue playing"

     Connect4Model.java
	- Connect4Model()
	- createPlayers(String name1, String name2)
	    "creates the players and assigns their chip color"
        - chooseColumn(int colChoice)
            "creates local colChoice
	- playGame()
	    "sends signal to gui that it is player1's turn. Determines if player1's 
             choice is valid. If it is valid, adds chip, than determines if
             player1 has won. If player1 has one, sends win notification to gui, 
             if not sends that it is player2's turn. Repeats checks for 
             player2." 
        
        
     Connect4GUI.java    // extends Frame implements Observer
        - Connect4GUI(Connect4Model model)
	    "create an interactive graphical representation of the connect 4 board. 
        Creates
	     all of the buttons, text fields, and labels for interacting with the GUI.
	     Gets the players names and selected column then sends that data to Connect4Model.
	     Receives the row, playerTurn, and if player won back from the model. Updates 
             GUI with new data."
	- updateTurn(Observable model, Player player)
	    "set currentPlayer according to data from the model."
	- updateRow(Observable model, Object data)
	    "sets the current row according to data from the model."
	- updateGameEnded(Observable model, Player player)
	    "sets the winner according to data from the model."
 
             

JUnit Testing Classes
    Connect4PlayerTest.java
        - testConnect4()
        - testPlayerConstructor()
        - testPlayerGetName()
        - testPlayerGetWins()
        - testPlayerGetLosses()
        - testPlayerGetColor()
        - testPlayerTakeTurn()
        - testPlayerToString()
        
    Connect4BoardTest.java
        - testBoardConstructor()
        - testIsColumnFull()
        - testIsBoardFull()
        - testAddChip()
        - testHasConnect4()
        - testBoardToString()
 
