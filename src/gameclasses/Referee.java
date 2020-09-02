package gameclasses;

import java.io.IOException;

/**
 * class that allows players and the game to be set up 
 * @author hamza
 *
 */
public class Referee implements Constants{
	/**
	 * player with X mark
	 */
	private Player xPlayer;
	/**
	 * player with 'O' mark
	 */
	private Player oPlayer;
	/**
	 * the board the game is played on
	 */
	private Board board;
	/**
	 * constructs the referee of the game
	 */
	public Referee() {
		return;
	}
	/**
	 * sets players to their marks, displays the board and basically starts the game
	 */
	public void runTheGame() {
		
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		
		xPlayer.getPlayerName();
		oPlayer.getPlayerName();
		
		//board.display();
		try {
			xPlayer.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return;
		
	}
	/**
	 * sets the board the game needs to be played on 
	 * @param board the board the game is played on
	 */
	public void setBoard(Board board) {
		this.board=board;
		return;
		
	}
	/**
	 * sets the player with the 'O' mark
	 * @param oPlayer the player with the O mark
	 */
	public void setoPlayer(Player oPlayer){
		this.oPlayer=oPlayer;
		return;
	}
	/**
	 * sets the player wit the 'X'mark 
	 * @param xPlayer the player with the X mark
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer=xPlayer;
		return;
	}
	/**
	 * return the x player
	 * @return
	 */
	public Player getxPlayer() {
		return this.xPlayer;
	}
	/**
	 * return the o player
	 * @return
	 */
	public Player getoPlayer() {
		return this.oPlayer;
	}

}
