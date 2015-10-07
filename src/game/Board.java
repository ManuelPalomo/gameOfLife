package game;

/*
 * Class Board
 * This class contains all the functions that interact and create the logical board needed for the game
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
		for(int x=0;x<rows;x++){
			for(int y=0;y<columns;y++){
				Cell cell=getCell(x, y);
				cells[x][y].setState(checkRules(cell, getAliveNeighbours(cell)));
				
					
				
			}
		}

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
		int aliveNeighbors = 0;
		int cellRow = cell.getRow();
		int cellColumn = cell.getColumn();

		// First, we check if the cell is inside the boundaries of the external
		// walls
		if (cellRow == 0 || cellRow == rows - 1 || cellColumn == 0 || cellColumn == columns - 1) {
			int startX = 0;
			int finishX = 0;
			int startY = 0;
			int finishY = 0;

			// check if the cell is in one of the four corners
			// 1-(0,0)
			if (cellRow == 0 && cellColumn == 0) {
				startX = startY = 0;
				finishX = finishY = 2;
			}
			// 2-(row-1,0)
			else if (cellRow == rows - 1 && cellColumn == 0) {
				startX = cellRow - 2;
				finishX = cellRow;
				startY = 0;
				finishY = 2;

			}
			// 3-(0,columns-1)
			else if (cellRow == 0 && cellColumn == columns - 1) {
				startX = 0;
				finishX = 2;
				startY = cellColumn - 2;
				finishY = cellColumn;

			}
			// 4-(rows-1,columns-1)
			else if (cellRow == rows - 1 && cellColumn == columns - 1) {
				startX = cellRow - 2;
				finishX = cellRow;
				startY = cellColumn - 2;
				finishY = cellColumn;

			}

			// We perform the default operation once we have the start and
			// finish
			for (int x = startX; x < finishX; x++) {
				for (int y = startY; y < finishY; y++) {
					if (getCell(x, y).getState()) {
						aliveNeighbors++;
					}
				}
			}

		} else {
			// The cell is inside the boundaries and we may perform the standard
			// operation

			for (int x = cellRow - 2; x < cellRow + 1; x++) {
				for (int y = cellColumn - 2; y < cellColumn + 1; y++) {
					if (getCell(x, y).getState()) {
						aliveNeighbors++;
					}
				}

			}
		}
		return aliveNeighbors;
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
				return false;
			}
			if (aliveNeighbours == 2 || aliveNeighbours == 3) { // Rule 2
				return true;
			}
			if (aliveNeighbours > 3) { // Rule 3
				return false;
			}
		} else { //Cell is dead, so only rule 4 apply
			if(aliveNeighbours==3){
				return true;
			}
		}
		return cell.getState();

	}

}
