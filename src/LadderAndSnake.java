// Assignment 1-SnakesAndLadders
// Part-4 
// Written by: Eric Tan 40208502 & Kevin Yang 40214231
// -----------------------------------------------------
/**
 * @author Eric Tan 40208502 & Kevin Yang 40214231
 * COMP249
 * Assignment # 1
 * Due Date: Friday February 4 2022
 * 
 * The LadderAndSnake class implements methods to
 * roll dices in order to determine the player's playing order
*/

//imports
import java.util.Random;

/**
 * Main body of the program, assigns player order after dice roll
 */
public class LadderAndSnake {
	//Attributes
    private Board playingBoard;
    private static int numberOfPlayers;
    private int valueDice;
    private boolean repeatingValue = true;
   
/**
 * Default constructor
 * @param playingBoard represent the board
 * @param numberOfPlayers represents the number of players
 */
    public LadderAndSnake(Board playingBoard, int numberOfPlayers) {
        this.playingBoard = playingBoard;
        this.numberOfPlayers = numberOfPlayers;
    }

/**
 * flipDice method to roll a dice
 * @return the value of the dice rolled
 */
    public int flipDice(){
        Random rand = new Random(); 
        valueDice = rand.nextInt((6-1)+1)+1; //generate a random number between 1 to 6
             return valueDice;
    }

/**
 * startDice method to determine the dice values rolled by players
 * @param listOfPlayers array of all the players
 */
    public void startDice(Player[] listOfPlayers) {
    	//Attributes
        int count=1;
        int temp;
        boolean repeating[] = new boolean [numberOfPlayers]; //create a 1-D boolean array to identify the repeated dice values
        double StartingDiceRoll[] = new double[numberOfPlayers]; //create a 1-D array for the players' dice value
        
        System.out.println("All the players will now roll a dice to determine the play order.");

        for(int i=0;i<numberOfPlayers;i++) { //flip a starting dice value for each player
                temp = flipDice();
                StartingDiceRoll[i] = temp;
                System.out.println(listOfPlayers[i]+" has rolled a dice value of "+ temp); //show the dice value of each player
        }
        
        orderingList(listOfPlayers, StartingDiceRoll,repeating); //order the dice values with corresponding players

        do { //continuous loop until there are no more repeated values
        	this.repeatingList(StartingDiceRoll,repeating,listOfPlayers); //
 	        for(int i=0;i<numberOfPlayers;i++) { //display all the players that need to re-roll a dice to break a tie
	        	if(repeating[i]) { //if repeating[i]==true then the player at index i has a repeated dice
	            	System.out.println(listOfPlayers[i]+" must roll again to break a tie.");
	        	}
	        }
        	for(int i=0;i<numberOfPlayers;i++) {
        		if(repeating[i]) { //roll a new dice and add that value as a decimal to the previous rolled dice for a player
        			temp = flipDice();
        			System.out.println(listOfPlayers[i]+" has rolled a dice value of "+temp); //display the new rolled dice
        			StartingDiceRoll[i]= StartingDiceRoll[i]+(temp*(1.0/Math.pow(10.0,count))); //formula to add the new dice as decimal to starting dice
        			

        		}
        		orderingList(listOfPlayers,StartingDiceRoll,repeating); //order dice values with their corresponding player
        	}
        	this.repeatingList(StartingDiceRoll,repeating,listOfPlayers); //check for repeated dice values
        	count++; //increment the count to add the next dice value of the repeated dice value onto the next decimal

        }while(repeatingValue);
        orderingList(listOfPlayers,StartingDiceRoll,repeating); //order the list after rolling a dice to break the tie
        System.out.println("The new order of play is: ");
        for(int i=0;i<numberOfPlayers;i++) {
        	System.out.print(listOfPlayers[i]+", ");
        }
        System.out.println();
    }
    
    /**
     * check if there are any repeated dice values
     * @param StartingDiceRoll array of player's starting dice values
     * @param repeating array of booleans representing the repeated and different dice values
     * @param listOfPlayers array of all the players
     * @return repeatingList array of booleans checking if there are repeated dice values
     */
    public boolean[] repeatingList(double[] StartingDiceRoll,boolean[] repeating,Player[] listOfPlayers){
    	int counter = 0;
        for(int i=0;i<numberOfPlayers;i++) { //comparing the dice values of each player with all the other players
    		for(int j=i+1;j<numberOfPlayers;j++) {
    			if(StartingDiceRoll[i]==StartingDiceRoll[j]) { //if two player has the same dice value, then boolean repeating==true
    				repeating[i]=true;
    				repeating[j]=true;
    				counter++; //increment counter to not add the next dice value onto a occupied decimal
    			}
    			else if(StartingDiceRoll[i]!=StartingDiceRoll[j]) { //if two consecutive players do not share the same dice value
    				//repeating of both players become false
    				repeating[i] = false;
    				repeating[j]=false;
    				
    				//check the dice value of index i<0 with index i-0 or i+1
    				if((i>0 && i<numberOfPlayers-1) && ((StartingDiceRoll[i] == StartingDiceRoll[i+1])||StartingDiceRoll[i] == StartingDiceRoll[i-1])) {
    					repeating[i] = true; //
    					counter++; //increment counter to not add the next dice value onto a occupied decimal
    				}
    				if(i==0 && StartingDiceRoll[i] == StartingDiceRoll[i+1]){ //check the dice value of the index 0 and 1
    					repeating[i] = true;
    					counter++; //increment counter to not add the next dice value onto a occupied decimal
    				}
    			}
    		}
        }
        if(counter>0) { //if counter>0, then there are some repeated dice values
        	this.repeatingValue = true;
        }
        else { //that means counter=0, hence there are no repeated dice values
        	this.repeatingValue = false;
        }

        return repeating; //return the boolean true if the dice value is repeated, and false if it is not repeated
    }
    
    /**
     * order from highest to lowest the dice values as well as the corresponding player   
     * @param listOfPlayers array of all players
     * @param StartingDiceRoll array of dice values
     * @param repeating array of repeated dice values
     */
    public void orderingList(Player[] listOfPlayers,double[] StartingDiceRoll,boolean[] repeating) {
        //Attributes
    	double tempStartingDice;
        Player tempPlayer;    
        boolean tempRepeating;
        
        for(int i=0;i<numberOfPlayers;i++) { //compare the dice value of each player with all the other players
            for(int j=i;j<numberOfPlayers;j++) {
                if(StartingDiceRoll[i]<StartingDiceRoll[j]) { //if the dice value of player is lesser than the next player, then change positions
                    //store the player, dice value, and repeating boolean into variables
                	tempPlayer = listOfPlayers[j];
                    tempStartingDice = StartingDiceRoll[j];
                    tempRepeating = repeating[j];
                    //switch the values from i to j, and from j to i
                    repeating[j] = repeating[i];
                    repeating[i] = tempRepeating;
                    StartingDiceRoll[j] = StartingDiceRoll[i];
                    StartingDiceRoll[i] = tempStartingDice;
                    listOfPlayers[j] = listOfPlayers[i];
                    listOfPlayers[i] = tempPlayer;
                }    
            }
        }
    }

	/**
	 * play the game: everyone flips a dice and moves on the board
	 * @param listOfPlayers array of all the players
	 * @param playingBoard board of the game
	 */
	public void play(Player[] listOfPlayers,Board playingBoard){		
	//Attributes
	this.startDice(listOfPlayers);
	int turn = 1;
	
	while(true) { //continue the game until a player has the position 100
		for(int i=0;i<numberOfPlayers;i++) { //all players executes the action
			playingBoard.movePlayer(listOfPlayers[i],flipDice()); //player flip a dice and move according to it
		}
		turn++; //increment the turn number to keep track of the turns
		System.out.println("\nIt is now turn "+turn+".\n"); //display turn number
	}
}
}