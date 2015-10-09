package game;

import graphics.Screen;

public class Main {
	
	/**
	 * This constants represent how many cells are going to be in the program
	 */
	final static int HEIGHT=10;
	final static int LENGTH=10;
	public static Board board = new Board(HEIGHT, LENGTH);
	public static void main(String[] args) {
		Screen screen = new Screen(HEIGHT, LENGTH);
	
	}
}
