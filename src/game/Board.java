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
	 * Default constructor for the class cell
	 * Initializes the cell matrix with all the cells dead
	 * @param rows
	 * @param columns
	 */
	public Board(int rows, int columns) {
		cells = new Cell[rows][columns];
		this.rows=rows;
		this.columns=columns;
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
	 * This method calculates one tick of the game and change the values.
	 * The rules of the tick are the following:
	 * 
	 * 1-Any live cell with fewer than two live neighbours dies, as if caused by under-population.
	 * 2-Any live cell with two or three live neighbours lives on to the next generation.
	 * 3-Any live cell with more than three live neighbours dies, as if by over-population.
	 * 4-Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
	 * 
	 */
	public void nextTick() {
		

	}
	
	/**
	 * Auxiliary method for nextTick.
	 * It checks the neighbours and change the state as per the rules of one cell
	 * @see nextTick
	 * @param cell
	 * @return alive or dead
	 */
	private boolean checkRules(Cell cell){
		//First, check if the cell is in one of the four corners
		//1-(0,0)
		if(cell.getRow()==0 && cell.getColumn()==0){
			
		}
		//2-(row-1,0)
		if(cell.getRow()==rows-1 && cell.getColumn()==0){
			
		}
		//3-(0,columns-1)
		if(cell.getRow()==0 && cell.getColumn()==columns-1){
			
		}
		//4-(rows-1,columns-1)
		if(cell.getRow()==rows-1 && cell.getColumn()==columns-1){
			
		}
		
	}

}
