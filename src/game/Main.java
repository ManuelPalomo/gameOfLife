package game;

import graphics.Screen;

public class Main {

	/**
	 * This constants represent how many cells are going to be in the program
	 */
	final static int HEIGHT = 50;
	final static int LENGTH = 50;
	


	public static void main(String[] args) {
		Board board = Board.getInstance();
		board.parametrizeBoard(HEIGHT, LENGTH);
		Screen screen = new Screen(HEIGHT, LENGTH);

	}
}
