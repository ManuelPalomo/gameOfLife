package graphics;
/*
 * Class CanvasPanel
 * 
 * This class will handle the direct paint and display
 * 
 *@author Manuel Palomo <manuel_palomo@hotmail.es>
 */

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel {
	private int height;
	private int length;
	private boolean[][] cells;
	private boolean drawGrid = true;
	

	public CanvasPanel(int height, int length) {
		this.height = height;
		this.length = length;
		cells = new boolean[height][length];
		

		// Initialize all cells to false
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < length; y++) {
				cells[x][y] = false;
			}
		}
	}

	public int getHeightInCells() {
		return height;
	}

	public int getLengthInCells() {
		return length;
	}

	public void setValue(int x, int y, boolean value) {
		cells[x][y] = value;
	}

	public boolean getValue(int x, int y) {
		return cells[x][y];
	}

	/**
	 * This methods autostarts when the CanvasPanel is created and draws into
	 * the canvas
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGrid(g);
		drawCells(g);
	

	}

	/**
	 * This functions draw the grid that contains all the cells. it can be
	 * toggled changing the boolean drawGrid or by using toggleGrid method
	 * 
	 * @see toggleGrid
	 */
	public void drawGrid(Graphics g) {
		int cellSize = this.getWidth() / length;
		if (drawGrid) {
			for (int i = 0; i < length; i++) {
				g.drawLine(i * cellSize, 0, i * cellSize, this.getHeight());
			}
			for (int i = 0; i < height; i++) {
				g.drawLine(0, i * cellSize, this.getWidth(), i * cellSize);
			}
		}
	}

	/**
	 * Paints the cells blue/white acording to the state of the cells in the
	 * matrix
	 * 
	 * @param g
	 */
	public void drawCells(Graphics g) {
		int cellSize = this.getWidth() / length;
		
		for (int x = 0; x < height; x++) {
			for (int y = 0; y < length; y++) {
				g.drawRect(x * cellSize, y * cellSize, cellSize, cellSize);
				if (cells[x][y] == true) { // live
					g.setColor(Color.BLACK);
					g.fillRect(x * cellSize, y * cellSize, cellSize-1, cellSize-1);

				} else {
					g.setColor(Color.WHITE);
					g.fillRect(x * cellSize, y * cellSize, cellSize-1, cellSize-1);
				}

			}
		}

	}

	/**
	 * Changes the state of the current value of drawGrid to it's opposite
	 */
	public void toggleGrid() {
		drawGrid = !drawGrid;
	}

}
