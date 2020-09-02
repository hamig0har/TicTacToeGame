package gameclasses;

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

/**
 * The class responsible for displaying, and editing the board the game is being played on.
 * @author hamza
 *
 */
public class Board implements Constants {
	/**
	 * A multi-dimensional Array for making the board of the game and recording the marks
	 */
	private char theBoard[][];
	/**
	 * counts the number of marks on the board
	 */
	private int markCount;
/**
 * Forms an empty board on which the game is to be played
 * Sets markCount(the counter of marks on the board) to 0
 */
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
/**
 * Allows to get a mark at a specific point on the board
 * @param row the row in which the mark is
 * @param col the column in which the mark is
 * @return return the mark (either x or o)
 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
/**
 * Tells if the board is full or not
 * @return true if the markCount has reached 9(total cells on the board)
 */
	public boolean isFull() {
		return markCount == 9;
	}
/**
 * checks if the winner is player using 'X'
 * @return true if the winner is 'X'
 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
/**
 * checks if the winner is player using 'O'
 * @return true if winner is 'O'
 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

/**
 * adds the mark the player enters in its specific position
 * @param row the row in which the mark needs to be placed
 * @param col the column in which the mark needs to be placed
 * @param mark the mark the player has entered (either 'X' or'O')
 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}
/**
 * Clears the Board for a new game
 * Sets markCount to 0
 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
/**
 * Checks if a specific mark has repeated itself 3 times in a row, column or diagonally
 * @param mark the mark (X or O) that needs to be checked
 * @return 1 if the entered mark has been repeated three times back to back and won the game
 * 
 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	
	String addBlankSpace() {
		return "          ";
	}
	
/**
 * Displays the Column numbers on the board
 */
	String displayColumnHeaders(int j) {
			return "|col " + j;
	}
/**
 * Makes the borders and gives the board a table view by adding hyphens
 */
	String addHyphens() {
			return "+-----";
	}
/**
 * Makes spaces in the cells of the board for the marks to be entered
 */
	String addSpaces() {
			return "|     ";
	}
}