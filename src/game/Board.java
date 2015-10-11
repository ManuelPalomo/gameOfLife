package game;

import java.util.Random;

/*
 * Class Board
 * This class contains all the functions that interact and create the logic board needed for the game
 * @author Manuel Palomo <manuel_palomo@hotmail.es>
 */
public class Board {
	private Cell[][] cells;
	private int rows;
	private int columns;

	/**
	 * Default constructor for the class cell Initializes the cell matrix with
	 * all the cells dead
	 * 
	 * @param rows
	 * @param columns
	 */
	public Board(int rows, int columns) {
		cells = new Cell[rows][columns];
		this.rows = rows;
		this.columns = columns;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < columns; c++) {
				cells[r][c] = new Cell(r, c); // By default, cells are created
												// dead

			}
		}
	}

	public Cell getCell(int row, int column) {
		return cells[row][column];
	}

	public void setValue(int x, int y, boolean value) {
		cells[x][y].setState(value);
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
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				Cell actualCell = new Cell(x, y);
				actualCell.setState(getCell(x, y).getState());
				checkRules(actualCell, getAliveNeighbours(getCell(x, y)));
				nextCells[x][y] = actualCell;

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
		for (int x = cellRow - 1; x < cellRow + 2; x++) {
			for (int y = cellCol - 1; y < cellCol + 2; y++) {
				if (((x >= 0) && (x < rows)) && ((y >= 0) && (y < columns))) {
					// Origin cell, do not check
					if (!((x == cellRow) && (y == cellCol))) {
						if (getCell(x, y).getState()) {
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
			if (aliveNeighbours < 2 || aliveNeighbours > 3) { // Rule 1
				cell.setState(false);

			}
		} else { // Cell is dead, so only rule 4 apply
			if (aliveNeighbours == 3) {
				cell.setState(true);

			}
		}

	}

	/**
	 * Fills the board with random cells using a seed
	 */
	public void randomize(Long seed) {
		Random random = new Random();
		random.setSeed(seed);
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				if (random.nextDouble() > 0.5D) {
					cells[x][y].setState(true);
				}

			}
		}
	}

	/**
	 * Sets all the cells to false
	 */
	public void clear() {
		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < columns; y++) {
				cells[x][y].setState(false);
			}
		}
	}

}
