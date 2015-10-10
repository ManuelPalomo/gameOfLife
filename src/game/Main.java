package game;

import graphics.Screen;

public class Main {
	
	/**
	 * This constants represent how many cells are going to be in the program
	 */
	final static int HEIGHT=50;
	final static int LENGTH=50;
	public static Board board = new Board(HEIGHT, LENGTH);
	public static void main(String[] args) {
		Screen screen = new Screen(HEIGHT, LENGTH);
	
	}
}
