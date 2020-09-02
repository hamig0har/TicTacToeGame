package gameclasses;
import java.io.*;
import java.net.Socket;

/**
 * This Class is responsible for setting up and conducting the game 
 * as it oversees everything that goes on in the game
 * @author hamza
 *
 */
public class Game implements Constants,Runnable {
/**
 * The field responsible for creating and managing the game to be played on 
 */
	private Board theBoard;
	/**
	 * The field responsible for running and overseeing game operation
	 */
	private Referee theRef;
	/**
	 * Assigns 'theBoard field' with space in memory in which a new table for the game is created
	 */
    public Game() {
        theBoard  = new Board();
	}
    /**
     * Appoints a specific referee variable to the game
     * @param r the referee that needs to be assigned to the game
     * @throws IOException
     */
    public void appointReferee(Referee r) throws IOException {
        theRef = r;
        theRef.setBoard(theBoard);
        theRef.getxPlayer().setBoard(theBoard);
        theRef.getoPlayer().setBoard(theBoard);
    	return;
    }
    
	@Override
	public void run() {
		try {
			theRef.runTheGame();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
