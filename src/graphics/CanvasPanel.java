package graphics;
/*
 * Class CanvasPanel
 * 
 * This class will handle the direct paint and display
 * 
 *@author Manuel Palomo <manuel_palomo@hotmail.es>
 */


import java.awt.Graphics;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel{
	private int height;
	private int length;
	private boolean drawGrid=true;
	
	public CanvasPanel(int height,int length){
		this.height=height;
		this.length=length;
		
		
	}
	
	/**
	 * This methods autostarts when the CanvasPanel is created and draws into the canvas
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		drawGrid(g); 
		
	}
	
	/**
	 * This functions draw the grid that contains all the cells.
	 * it can be toggled changing the boolean drawGrid or by using toggleGrid method
	 * @see toggleGrid
	 */
	public void drawGrid(Graphics g){
		if(drawGrid){
			int cellSize = this.getWidth() / length;
			for(int i=0;i<length+1;i++){
				g.drawLine(i*cellSize, 0, i*cellSize, this.getHeight());				
			}
			for(int i=0;i<height;i++){
				g.drawLine(0,i*cellSize , this.getWidth(), i*cellSize);
			}
		}
	}
	/**
	 * Changes the state of the current value of drawGrid to it's opposite
	 */
	public void toggleGrid(){
		drawGrid=!drawGrid;
	}

}
