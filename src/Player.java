// -----------------------------------------------------
// Assignment 1-SnakesAndLadders
// Part-3 
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
 * Creates player object
 */
public class Player {
	//attributes
    private String name;
    private int boardPosition;

/**
 * Constructor that takes the name of the player object and sets the boardPostion to 0
 * @param name represents the name of the player object
 */
    public Player(String name) {
    	this.name = name;
    	this.boardPosition = 0;

    }

/**
* Accessor method to get the name
* @return a string
*/
    public String getName() {
    	return name;
    }

/**
 * Mutator method to change the name
 * @param name represents the name of the player object
 */
    public void setName(String name) {
    	this.name = name;
    }
    
/**
 * Accessor method to get the boardPosition of the player object
 * @return an integer
 */
    public int getBoardPosition() {
    	return boardPosition;
    }

/**
 * Mutator method to change the position of the player
 * @param newBoardPosition represents the newBoardPosition of the player
 */
    public void setBoardPosition(int newBoardPosition) {
    	this.boardPosition = newBoardPosition;
    }
    
/**
 * override's the default toString method
 */
    public String toString() {
    	return (name);
    }
    
}
