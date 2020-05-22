# Mancala-AI
Problem Statement: Mancala is an ancient family of board games, and there are numerous variants. The object of the game is to capture more stones than your opponent. The Mancala board is made up of two rows of six holes, or pits, each. Four pieces—marbles, chips, or stones—are placed in each of the 12 holes. The colour of the pieces is irrelevant. Each player has a store (called a Mancala) to the right side of the Mancala board. Design an AI agent for this game to play against human.

# Algorithm: 	Minimax Algorithm 

# Rules:

1.	The game begins with one player picking up all the pieces in any one of the pockets on his/her side. 
2.	Moving counter clockwise, the player deposits one of the stones in each pocket until the stones run out. 
3.	If you run into your own Mancala (store), deposit one piece in it. If you run into your opponent's Mancala, skip it and continue moving to the next pocket. 
4.	If the last piece you drop is in an empty pocket on your side, you capture that piece and any pieces in the pocket directly opposite.
5.	Always place all captured pieces in your Mancala (store). 
6.	The game ends when all six pockets on one side of the Mancala board are empty. 
7.	The player who still has pieces on his/her side of the board when the game ends captures all of those pieces. 
8.	Count all the pieces in each Mancala. The winner is the player with the most pieces.

# Code Summary:

The game has a Pit class to illustrate a pit in the game as well as a Stones class to illustrate the mancala. 

The Pit class has following functionalities: add stones to a pit, set the no of stones in a pit, take all the stones from the pit and get the no. of stones in the pit.

The Stones class has the following functionalities: add stones to mancala, get no of stones in mancala, set the no of stones in mancala.

The State class is used to represent the current state of the board. It has the following functions: setPlayer() which sets the active player of the current state, getPlayer() to get the active player of the current state, getEmptied() to empty the other side of the board when one side of the board is empty, constructors to initialize the state, avaialbleMoves() returns an arraylist of all the possible moves from the current state, getChildren() returns an arraylist of all the possible states that we get when we make all possible moves and the Turn() function which makes turn on the game,

The mancalaModel class has a doTurn() function that takes in the current player and the pit number as arguments and performs a turn on the board. At the end of the turn, the current player is switched. The game takes a human as player A and AI as player B. When player A’s turn gets over, the current player is switched to player B and the pit number is calculated for the AI using the bestPitFinder() function defined in the minimax class. 

The bestPitFinder() takes the initial state of the game as argument, performs turn using the Turn function for every possible move for AI and initiates the minimax search corresponding to each possible turn using minimax() function with AI as the maximizing agent and human as the minimizing agent.

The minimax() function takes three arguments: the state after performing the move, depth and isMaximizing which denote the depth of the tree and whether the current player is for human or AI. The base case for this recursive function checks if the game has ended or the maximum depth has been reached through the isTerminal() function or checking if depth equals 0 respectively. If the game has not ended, every possible turn is made on the corresponding player’s pits and score is calculated for it by calling minimax() function on it recursively. Each time, we pass depth-1 while calling the minimax() function. The score that is highest for maximizing agent is stored in bestScore and lowest for minimizing agent is stored in bestScore and then returned.

The Turn() function works like the doTurn() function defined in the mancalaModel class following the rules of the game.

The isTerminal() function checks if all the pits on one side of the board are empty, which indicates that the game has ended. If so, the function calls getEmptied() function to clear the other side of the pits and store in the corresponding mancala. The isTerminal() function then returns true or if the all the pits on either side of the board are not empty it returns false.

The Evaluate() function evaluates and returns the score of the game on reaching the terminal state or max depth by returning the difference in the number of stones in AI’s(player B) mancala and human’s(player A) mancala.

# Development Process:

To Design GUI we create multiple java files to understand code clearly. We create an interface where we define function for mancala shape, pit shape, stones shape in pits, and stone shape in mancala. we also used getcolor() function for defining the outline color of shapes and fillcolor() function to fill color in stones that we added in pits. 

Then we create class MancalaDesign where we define these functions to set the shape of mancala, pits, shape of stone in pits and shape of stone in mancala. We create an arraylist to store the stones in pits and mancala and mathematical function to define the size of stones in pits and mancala on basis of pits and mancala’s size which we have defined in another java file GameBoard in which we make a panel in which we added mancala and pits. We define the size of gameboard, added mancalapanel which contains mancalas and pitpanel which contains pits that we defined in another class pitpanel.

In pit panel we also included mouselistener because the game will played on pits by using mouseclick. We also add toppanel class in game board which contains a text box and reset button. The text box will display turn of players when game is playing and it also shows who won the game. The reset button will reset the game in its original form. 

In the gameboard class we add stones in pits using array.

We created a mainpanel which contains all the panel mancalapanel, pitpanel, toppanel.

We create a Mancalagame class which contains the main function in which we use init function to start the game. We declare the no of stones in a pit will be 4. We also create a dialogue box which will display after the game end to ask the user want to play again or not. When mancalaModel class is created for backend then we create its object into the classes which is created for gui design to attach gui to its backend.

The mancalaModel class makes use of newMinimax class which implements the minimax algorithm and makes use of the State class to keep track of the state of the board.
