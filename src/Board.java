// -----------------------------------------------------
// Assignment 1-SnakesAndLadders
// Part-2 
// Written by: Eric Tan 40208502 & Kevin Yang 40214231
// -----------------------------------------------------

/**
 * 
 * @author Eric Tan 40208502 and Kevin Yang 40214231
 * COMP249
 * Assignment # 1
 * Due Date: Friday February 4 2022
 * 
 */

/**
 * Displays the board and assigns snakes and ladders to board positions
 */
public class Board {
	//attributes
	private int numberOfSnakes=8;
	private int numberOfLadders=9;
	private int[][] playBoard;
	private int[][] snakes;
	private int[][] ladders;
	
/**
* Default constructor that does not take any parameters
*/
	public Board() {
		playBoard = new int [10][10]; //creates a 2-D board
		int boardPosition = 100; //number of squares
		
		for(int i=0;i<10;i++) { 	//setting the squares value inside of the 2-D array
			for(int j=0;j<10;j++) {
				if(i % 2 == 0) {  //if row is even 
				playBoard[i][j] = boardPosition;
					if(j<9) {      //if column is less than 9 decrement boardPosition
						boardPosition--;
					}
				}
				else {
					if(j==0) { 		//if column is 0 then substract 10
						boardPosition = boardPosition - 10;
					}
					playBoard[i][j] = boardPosition;
					if(j==9) {    	//if column is 9 then substract 10
						boardPosition = boardPosition - 10;
					}
					else {
						boardPosition++; //increment board position
					}
				}
			}
		}
		setSnakes(); //set the position of the snakes
		setLadders(); //set the position of the ladders
	}
	
/**
 * 
 * @param player represents the player object that we wish to move
 * @param diceRoll represents the value of the dice that the player object rolled
 */
	public  void movePlayer(Player player, int diceRoll) {
		int position = player.getBoardPosition()+diceRoll; //set the new position of the player to a variable
		boolean encounter = true;
		
		if(position>100) { 			//if the new position is greater than 100, move back a certain amount of squares
			position = (100-(position-100));
		}
		
		for(int i=0;i<numberOfSnakes;i++) {  //check to see if the new position is on a snake's head
			if(snakes[i][0]==position) {
				System.out.println("\n"+player + " got dice value of " + diceRoll + "; gone to square " + position + " then down to square " + snakes[i][1]);
				position = snakes[i][1]; //if the new position is on a snake's head, change the position for the snake's tail
				encounter = false;
			}
		}
		
		for(int i=0;i<numberOfLadders;i++) {  //check to see if the new position is at the bottom of a ladder
			if(ladders[i][0]==position) {
				System.out.println("\n"+player + " got dice value of " + diceRoll + "; gone to square " + position + " then up to square " + ladders[i][1]);
				position = ladders[i][1];  //if the new position is at the bottom of a ladder, change the position for the top of the ladder
				encounter = false;
			}
		}
		if(encounter) {
			System.out.println("\n"+player+" got a dice value of " + diceRoll+"; now in square " + position); //text if the player did not land on snake or ladder
		}
		
		player.setBoardPosition(position); //set the new position of the player
		showBoardwithPlayer(player);  //show the board with the player
		if(position==100) {   //if the player reaches square 100. he wins, and the program terminates itself
			System.out.println("\n"+player+" won the game!\nSee you next time!");
			System.exit(0);
		}
		else {
			System.out.println("Game not over; flipping again!"); //text saying that the game continues
		}
	}
	
/**
 * Set the head and the tail of all the snakes on the board
 */
	public void setSnakes() {
		snakes = new int[numberOfSnakes][2];
		snakes[0][0]=17;  //[x][0] or [x][1] = position, where 0 represents the snakes head and 1 represents the snakes tail
		snakes[0][1]=6;
		snakes[1][0]=48;
		snakes[1][1]=30;
		snakes[2][0]=62;
		snakes[2][1]=19;
		snakes[3][0]=63;
		snakes[3][1]=60;
		snakes[4][0]=93;
		snakes[4][1]=68;
		snakes[5][0]=95;
		snakes[5][1]=24;
		snakes[6][0]=97;
		snakes[6][1]=76;
		snakes[7][0]=98;
		snakes[7][1]=78;
	}

/**
 * Set the bottom and top of all the ladders on the board
*/
	public void setLadders() {
		ladders = new int[numberOfLadders][2];
		ladders[0][0]=1;  ////[x][0] or [x][1] = position, where 0 represents the bottom of a ladder and 1 represents the top of a ladder
		ladders[0][1]=38;
		ladders[1][0]=4;
		ladders[1][1]=14;
		ladders[2][0]=9;
		ladders[2][1]=31;
		ladders[3][0]=21;
		ladders[3][1]=42;
		ladders[4][0]=28;
		ladders[4][1]=84;
		ladders[5][0]=36;
		ladders[5][1]=44;
		ladders[6][0]=51;
		ladders[6][1]=67;
		ladders[7][0]=71;
		ladders[7][1]=91;
		ladders[8][0]=80;
		ladders[8][1]=100;
	}
	
/**
 * Displays the board with a series of print statements
 * @param player represents the player object
 * @param positionRow represents the player's row position
 * @param positionCol represents the player's columns position
 */

	public void showBoard(Player player,int positionRow, int positionCol) {
		
		for(int i=0;i<10;i++) {
			for(int j=0;j<10;j++) {
				if(i==9 && j<9) {
					if(positionRow==i && positionCol==j) {
						System.out.print("|"+playBoard[i][j] + "   "+player.getName()+"     |");
					}
					else {
						System.out.print("|"+playBoard[i][j] + "         |");
					}
				}
				else if(i==0 && j == 0) {
					if(positionRow==i && positionCol==j) {
						System.out.print("|"+playBoard[i][j] + "   "+player.getName()+"     |");
					}
					else {
						System.out.print("|"+playBoard[i][j] + "       |"); 
					}
				}
				else {
					if(positionRow==i && positionCol==j) {
						System.out.print("|"+playBoard[i][j] + "   "+player.getName()+"     |");
					}
					else {
						System.out.print("|"+playBoard[i][j] + "        |");
					}
				}

			}
			System.out.print("\n________________________________________________________________________________________________________________________\n");

		}
	}

/**
 * Displays the game board with the last player that rolled a dice, by callin the showBoard() method
 * @param player represents the player that must be shown on the board
 */
	public void showBoardwithPlayer(Player player) {
		//variables
		int positionRow;
		int positionCol;		
		
		positionRow = 9-(player.getBoardPosition()/10); 
		if(player.getBoardPosition() % 10 ==0) {
			positionRow = 10-(player.getBoardPosition()/10); //calculates the player's row position on the game board
			if(positionRow % 2 ==0) {
				positionCol = 0; //calculate the player's column position on the game board
			}
			else {
				positionCol = 9; //calculate the player's column position on the game board
			}
		}
	
		else if(positionRow % 2 == 0) {
			positionCol = 10-(player.getBoardPosition() % 10); //calculate the player's column position on the game board
		}
		else {
			positionCol = (player.getBoardPosition() % 10)-1; //calculate the player's column position on the game board
		}

		showBoard(player,positionRow,positionCol); //calls the showBoard() method to display the game board with the player's name
		
	}
}