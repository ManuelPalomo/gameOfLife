package game;

import utils.Cons;

/*
 * Class Cell
 * 
 *@author Manuel Palomo <manuel_palomo@hotmail.es>
 */
public class Cell {
	private boolean state; // Alive(true) or Dead(false)
	private int row;
	private int column;

	public Cell(int row, int column) {
		this.row = row;
		this.column = column;
		state = Cons.DEAD;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
	
	public boolean getState(){
		return state;
		
	}
	public void setState(boolean state){
		this.state=state;
		
	}
}
