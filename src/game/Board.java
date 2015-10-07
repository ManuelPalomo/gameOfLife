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

	}

	/**
	 * Auxiliary method for nextTick. It checks the neighbours and change the
	 * state as per the rules of one cell.
	 * 
	 * It does so by getting the neighbours and evaluating the number of alive
	 * cells and then applying the rules.
	 * 
	 * @see nextTick
	 * @param cell
	 * @return alive or dead
	 */
	private boolean checkRules(Cell cell) {
		int liveNeighbors = 0;
		int cellRow = cell.getRow();
		int cellColumn = cell.getColumn();

		// First, we check if the cell is inside the boundaries of the external
		// walls
		if (cellRow == 0 || cellRow == rows - 1 || cellColumn == 0 || cellColumn == columns - 1) {
			int startX=0;
			int finishX=0;
			int startY=0;
			int finishY=0;

			// check if the cell is in one of the four corners
			// 1-(0,0)
			if (cellRow == 0 && cellColumn == 0) {
				startX=startY=0;
				finishX=finishY=2;
			}
			// 2-(row-1,0)
			if (cellRow == rows - 1 && cellColumn == 0) {

			}
			// 3-(0,columns-1)
			if (cellRow == 0 && cellColumn == columns - 1) {

			}
			// 4-(rows-1,columns-1)
			if (cellRow == rows - 1 && cellColumn == columns - 1) {

			}
			
			//We perform the default operation once we have the start and finish
			for(int x=startX;x<finishX;x++){
				for(int y=startY;y<finishY;y++){
					if (getCell(x, y).getState()) {
						liveNeighbors++;
					}
				}
			}
			
		} else {
			// The cell is inside the boundaries and we may perform the standard
			// operation

			for (int x = cellRow - 1; x < cellRow + 2; x++) {
				for (int y = cellColumn - 1; y < cellColumn + 2; y++) {
					if (getCell(x, y).getState()) {
						liveNeighbors++;
					}
				}

			}
		}
		
		

	}

}
