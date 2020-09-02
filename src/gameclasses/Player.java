package gameclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * class that holds information about the players and allows the to make moves and play the game
 * @author hamza
 *
 */
public class Player implements Constants{
	/**
	 * Name of player
	 */
	protected String name;
	/**
	 * The board the game is played on
	 */
	protected Board board;
	/**
	 * The other player
	 */
	 Player opponent;
	/**
	 * the players mark(X or O)
	 */
	protected char mark;
	
	private Socket aSocket;
	private BufferedReader socketIn;
	private PrintWriter socketOut;
	private ObjectOutputStream objectOut;
	/**		
	 * Sets a particular mark to a particular player
	 * @param name name of player that mark needs to be assigned to
	 * @param mark mark that needs to be assigned
	 */
	public Player(Socket s,char mark) {
		this.mark=mark;
		aSocket = s;
		try {
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter(aSocket.getOutputStream());
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * sets the name of the player
	 * @param name the name that needs to be set
	 */
	public void setPlayerName(String name) {
		
		this.name=name;
		return;
		
	}
	
	private void sendString(String toSend) {
		socketOut.println(toSend);
		socketOut.flush();
	}
	
	/**
	 * gets the name of the player
	 * @return the players name
	 */
	public void getPlayerName() {
		
		try {
			sendString("Enter name of "+ mark +" Player: \0");
			name = socketIn.readLine();
			while(name==null) {
				sendString ("Please try again: \0");
				name = socketIn.readLine();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	/**
	 * initiates the game and allows the game to continue till a player wins or game ends in a draw
	 * @throws IOException 
	 */
	public void play() throws IOException {
		
		while(board.xWins()==false && board.oWins()==false) {
			
			if(board.isFull()==true) {
				System.out.print(" Game ends in a draw " );
				return;
			}
			showBoard();
			
			makeMove();
			showBoard();
			opponent.play();
		}
		
		return;
	}
	/**
	 * makes the move the player wants depending on the row and column chosen
	 * @throws IOException 
	 */
	public void makeMove() throws IOException {
		int row,column;
		String line;
		socketOut.println(this.name + " what row your next " + this.mark +" be placed in? \0");
		socketOut.flush();
		line = socketIn.readLine();
		row= Integer.parseInt(line);
		socketOut.println(this.name + " what column your next " + this.mark +" be placed in? \0");
		socketOut.flush();
		line = socketIn.readLine();
		column= Integer.parseInt(line);
		board.addMark(row, column, this.mark);	
		
	}
	/**
	 * sets the opponent player
	 * @param foe the opponent player
	 */
	public void setOpponent(Player foe) {
		
		this.opponent=foe;
		return;
		
	}
	/**
	 * sets the board the game needs to be played on
	 * @param theBoard the game needs to be played on this board
	 */
	public void setBoard(Board theBoard) {
		this.board=theBoard;
		return;

	}
	/**
	 * shows the board to the user 
	 */
	public void showBoard() {
		//displaycolumnheaders();
		socketOut.print(board.addBlankSpace());
		for (int j = 0; j < 3; j++) {
			socketOut.print(board.displayColumnHeaders(j));
		}
		socketOut.println("");
		//
		//addhyphens();
		socketOut.print(board.addBlankSpace());
		for (int j = 0; j < 3; j++) {
			socketOut.print(board.addHyphens());
		}
		socketOut.println("+");
		//
		for (int row = 0; row < 3; row++) {
			//addspaces();
			socketOut.print(board.addBlankSpace());
			for (int j = 0; j < 3; j++) {
				socketOut.print(board.addSpaces());
			}
			socketOut.println("|");
			//
			socketOut.print("    row " + row + " ");
			for (int col = 0; col < 3; col++) {
				socketOut.print("|  " + board.getMark(row, col) + "  ");
			}
			socketOut.println("|");
			//addspaces();
			socketOut.print(board.addBlankSpace());
			for (int j = 0; j < 3; j++) {
				socketOut.print(board.addSpaces());
			}
			socketOut.println("|");
			//
			//addhyphens();
			socketOut.print(board.addBlankSpace());
			for (int j = 0; j < 3; j++) {
				socketOut.print(board.addHyphens());
			}
			socketOut.println("+/0");
			//
			
		}
		
	}
}
