// Assignment 1-SnakesAndLadders
// Part-1 
// Written by: Eric Tan 40208502 & Kevin Yang 40214231
// -----------------------------------------------------
//This class serves as the driver where the program utilizes the user's input and calls method for
// the game to function.

/**
 * 
 * @author Eric Tan 40208502 and Kevin Yang 40214231
 * COMP249
 * Assignment # 1
 * Due Date: Friday February 4 2022
 * 
 */

//imports 
import java.util.Scanner;
/**
 * This is the class that calls in all the necessary methods to run the snakes and ladders program
 */
public class PlayLadderAndSnake {
	

	public static void main(String[] args) {
		//initialize scanner Object
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to Ladder and Snake"); //Welcome message
		
		int counter=0; //counts the number of attempts made
		int numberOfPlayers; //number of players
		
		
		do {
			System.out.println("Enter the # of players for your game -Number must be between 2 and 4 inclusively: ");
			numberOfPlayers = scanner.nextInt(); //stores the user's input
				
			if(numberOfPlayers>4||numberOfPlayers<2) {
				counter++;
				if(counter==4) {
					System.out.println("Bad Attempt "+counter+"! You have exhausted all your chances. Program will terminate!");
					System.exit(0); //if counter reaches 4 tries, then the program is terminated
				}
				System.out.println("Bad Attempt "+counter+"- Invalid # of players. Please enter a # between 2 and 4 inclusively."); //displays the number of attempts
			}
		}while(numberOfPlayers>4||numberOfPlayers<2);
			
		Board playingBoard = new Board(); //nitialize the playingBoard object
		LadderAndSnake LadderAndSnake = new LadderAndSnake(playingBoard,numberOfPlayers); //initialize the LadderAndSnake object
		
		Player listOfPlayers[] = new Player[numberOfPlayers];	 //creates 1-D array of players
		for(int i=0;i<numberOfPlayers;i++) { 					
			 listOfPlayers[i] = new Player("P"+Integer.toString(i));	//default name of players
		}
		System.out.println("Would you like to enter player name?(yes/no)");
		String answer = scanner.next(); //asks if the user wants to rename the players
		if(answer.equals("yes")) {
			for(int i=0;i<numberOfPlayers;i++) {
				System.out.println("Enter a name for player "+((i+1))+ " " );
				String name = scanner.next(); //stores name
				listOfPlayers[i].setName(name); //sets the name of the player object
			}
		}
		else {
			System.out.println("Players will take default name.");
		}
		
		LadderAndSnake.play(listOfPlayers,playingBoard); //commencing the game

	}
}
