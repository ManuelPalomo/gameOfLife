package graphics;
/*
 * Class Screen
 * 
 * This class contains all the functions needed to draw the Board class
 * 
 *@author Manuel Palomo <manuel_palomo@hotmail.es>
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import listeners.ScreenListener;
import listeners.StartListener;

public class Screen {
	private int height;
	private int length;
	private JFrame frame;
	private CanvasPanel panel;
	private ScreenListener listener;
	private JButton start, pause, step;
	private JPanel container;
	private StartListener buttonListener;

	public Screen(int height, int length) {
		this.height = height;
		this.length = length;
		frame = new JFrame("Conway's Game of Life");
		container = new JPanel();
		panel = new CanvasPanel(height, length);
		start=new JButton("Start");
		pause=new JButton("Pause");
		step=new JButton("Stop");
		listener = new ScreenListener(panel);
		buttonListener = new StartListener(panel);
		
		start.addActionListener(buttonListener);
		
		frame.setLayout(new BorderLayout());
		frame.add(container);

		
		panel.addMouseListener(listener);

		GroupLayout layout = new GroupLayout(container);
		container.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup()
				.addComponent(panel)
				.addGroup(layout.createSequentialGroup()
						.addComponent(start)
						.addComponent(pause)
						.addComponent(step)					
						));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addComponent(panel)
				.addGroup(layout.createParallelGroup()
						.addComponent(start)
						.addComponent(pause)
						.addComponent(step)	
						));

		frame.setSize(600, 600);// Initialize the window to a fixed width/height
		panel.setMinimumSize(new Dimension(600, 600));
		panel.setMaximumSize(new Dimension(600, 600));
		frame.setResizable(false);
		
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
