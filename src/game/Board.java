package game;

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
				cells[r][c] = new Cell(r, c);

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
	 * Picks a cell and changes his status to alive
	 * 
	 * @param row
	 * @param column
	 * @return cell changed
	 */
	public Cell activateCell(int row, int column) {
		Cell activatedCell = getCell(row, column);
		activatedCell.setState(true);
		return activatedCell;
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
				Cell actualCell = getCell(x, y);
				actualCell.setState(checkRules(actualCell, getAliveNeighbours(actualCell)));
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
		//
		// // First we check if the cell is in the external walls
		// if (cellRow == 0 || cellCol == 0 || cellRow == rows - 1 || cellCol ==
		// columns - 1) {
		// int startX = 0;
		// int startY = 0;
		// int finishX = 0;
		// int finishY = 0;
		//
		// if (cellRow == 0 && cellCol == 0) { // NorthWest
		// startX = startY = cellRow - 1;
		// finishX = finishY = cellCol + 1;
		//
		// } else if (cellRow == rows - 1 && cellCol == 0) { // NorthEast
		// startX = cellRow - 2;
		// finishX = cellRow;
		// startY = cellCol - 1;
		// finishY = cellCol + 1;
		//
		// } else if (cellRow == 0 && cellCol == columns - 1) { // SouthWest
		// startX = cellRow - 1;
		// finishX = cellRow + 1;
		// startY = cellCol - 2;
		// finishY = cellCol + 1;
		//
		// } else if (cellRow == rows - 1 && cellCol == columns - 1) {
		// //SouthEast
		// startX = cellRow - 2;
		// finishX = cellRow + 1;
		// startY = cellCol - 2;
		// finishY = cellCol + 1;
		//
		// } else if(cellRow==0 && cellCol>0){ //North
		// startX = cellRow - 2;
		// finishX = cellRow + 1;
		// startY = cellCol - 1;
		// finishY = cellCol;
		// }
		//
		// // General bucle for all the cases
		// for (int x = startX; x < finishX; x++) {
		// for (int y = startY; y < finishY; y++) {
		// if (x != cellRow - 1 && y != cellCol - 1) {
		// if (getCell(x, y).getState()) {
		// aliveNeighbours++;
		// }
		// }
		// }
		//
		// }
		//
		// } else {
		// // If not, we perform the standard operation
		//
		// for (int x = cellRow - 2; x < cellRow; x++) {
		// for (int y = cellCol - 2; y < cellCol; y++) {
		//
		// // Origin cell, do not check
		// if (x != cellRow - 1 && y != cellCol - 1) {
		// if (getCell(x, y).getState()) {
		// aliveNeighbours++;
		// }
		//
		// }
		//
		// }
		//
		// }
		// }
		// return aliveNeighbours;

		for (int x = cellRow - 2; x <= cellRow; x++) {
			for (int y = cellCol - 2; y <= cellCol; y++) {

				// Origin cell, do not check
				if (x != cellRow - 1 && y != cellCol - 1) {
					try {
						if (getCell(x, y).getState()) {
							aliveNeighbours++;
						}
					} catch (IndexOutOfBoundsException e) {}

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
	private boolean checkRules(Cell cell, int aliveNeighbours) {
		if (cell.getState()) { // Cell is alive, so only rules 1,2,3 apply
			if (aliveNeighbours < 2) { // Rule 1
				System.out.println("Cambiando");
				return false;
			}
			if (aliveNeighbours == 2 || aliveNeighbours == 3) { // Rule 2
				System.out.println("Cambiando");
				return true;
			}
			if (aliveNeighbours > 3) { // Rule 3
				System.out.println("Cambiando");
				return false;
			}
		} else { // Cell is dead, so only rule 4 apply
			if (aliveNeighbours == 3) {
				System.out.println("Cambiando");
				return true;
			}
		}
		return cell.getState();

	}

}
