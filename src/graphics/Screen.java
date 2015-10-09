package graphics;
/*
 * Class Screen
 * 
 * This class contains all the functions needed to draw the Board class
 * 
 *@author Manuel Palomo <manuel_palomo@hotmail.es>
 */

import java.awt.BorderLayout;

import javax.swing.JFrame;

import listeners.ScreenListener;

public class Screen {
	private int height;
	private int length;
	private JFrame frame;
	private CanvasPanel panel;
	private ScreenListener listener;

	public Screen(int height, int length) {
		this.height = height;
		this.length = length;
		frame = new JFrame("Conway's Game of Life");
		frame.setSize(600, 600);// Initialize the window to a fixed width/height
		frame.setLayout(new BorderLayout());

		panel = new CanvasPanel(height, length);
		panel.addMouseListener(listener);

		frame.add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
