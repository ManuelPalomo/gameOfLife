package game;

import java.util.Random;

import utils.Cons;

/*
 * Class Board
 * This class contains all the functions that interact and create the logic board needed for the game
 * Implemented using the Singleton pattern
 * @author Manuel Palomo <manuel_palomo@hotmail.es>
 */
public class Board {
	private static Board BoardInstance = null;
	private Cell[][] cells;
	private int rows;
	private int columns;

	private Board() {

	}

	private synchronized static void createInstance(){
		if(BoardInstance==null){
			BoardInstance=new Board();
		}
	}
	/**
	 * Default "constructor" according to the Singleton Pattern, it instantiates
	 * a object of the class Board only if there isn't any instance
	 * 
	 * @return
	 */
	public static Board getInstance() {
		if (BoardInstance == null) {
			BoardInstance = new Board();
		}
		return BoardInstance;
	}

	/**
	 * Method for passing the initial parameters (class is a Singleton) to
	 * initialize the cell matrix with all the cells dead
	 * 
	 * @param rows
	 * @param columns
	 */
	public void parametrizeBoard(int rows, int columns) {
		cells = new Cell[rows][columns];
		this.rows = rows;
		this.columns = columns;
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				cells[row][col] = new Cell(row, col); // By default, cells are
														// created dead

			}
		}
	}

	public Cell getCell(int row, int column) {
		return cells[row][column];
	}

	public void setValue(int row, int column, boolean value) {
		cells[row][column].setState(value);
	}

	public Cell[][] getCellMatrix() {
		return cells;
	}

	/**
	 * This method calculates one tick of the game and change the values. The
	 * rules of the tick are the following:
	 * 
	 * 1-Any live cell with fewer than two live neighbours dies, as if caused by
	 * under-population. 2-Any live cell with two or three live neighbours lives
	 * on to the next generation. 3-Any live cell with more than three live
	 * neighbours dies, as if by over-population. 4-Any dead cell with exactly
	 * three live neighbours becomes a live cell, as if by reproduction.
	 * 
	 */
	public void nextTick() {
		Cell[][] nextCells = new Cell[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int cols = 0; cols < columns; cols++) {
				Cell actualCell = new Cell(row, cols);
				actualCell.setState(getCell(row, cols).getState());
				checkRules(actualCell, getAliveNeighbours(getCell(row, cols)));
				nextCells[row][cols] = actualCell;

			}
		}
		cells = nextCells;
	}

	/**
	 * Auxiliary method for nextTick. It checks the living neighbours around a
	 * particular cell
	 * 
	 * 
	 * @see nextTick
	 * @param cell
	 * @return number of alive neighbours
	 */
	private int getAliveNeighbours(Cell cell) {
		int cellCol = cell.getColumn();
		int cellRow = cell.getRow();
		int aliveNeighbours = 0;
		for (int row = cellRow - 1; row < cellRow + 2; row++) {
			for (int cols = cellCol - 1; cols < cellCol + 2; cols++) {
				if (((row >= 0) && (row < rows)) && ((cols >= 0) && (cols < columns))) {
					// Origin cell, do not check
					if (!((row == cellRow) && (cols == cellCol))) {
						if (getCell(row, cols).getState()) {
							aliveNeighbours++;

						}
					}
				}

			}

		}
		return aliveNeighbours;
	}

	/**
	 * Auxiliary method for nextTick, it applies the rules and decides whether a
	 * cell should live or not
	 * 
	 * @see nextTick
	 * @param cell
	 * @param aliveNeighbours
	 *            Living neighbours around a particular cell
	 * @return true if alive, false if not
	 */
	private void checkRules(Cell cell, int aliveNeighbours) {
		if (cell.getState()) { // Cell is alive, so only rules 1,2,3 apply
			if (aliveNeighbours < 2 || aliveNeighbours > 3) { // Rule 1 and 2
				cell.setState(Cons.DEAD);

			}
		} else { // Cell is dead, so only rule 4 apply
			if (aliveNeighbours == 3) {
				cell.setState(Cons.ALIVE);

			}
		}

	}

	/**
	 * Fills the board with random cells using a seed
	 */
	public void randomizeBoard(Long seed) {
		Random random = new Random();
		random.setSeed(seed);
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				if (random.nextDouble() > 0.5D) {
					cells[row][col].setState(Cons.ALIVE);
				}

			}
		}
	}

	/**
	 * Sets all the cells to false
	 */
	public void clearBoard() {
		for (int row = 0; row < rows; row++) {
			for (int cols = 0; cols < columns; cols++) {
				cells[row][cols].setState(false);
			}
		}
	}

}
