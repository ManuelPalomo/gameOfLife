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
import javax.swing.JTextField;

import listeners.ScreenListener;
import listeners.ButtonListener;

public class Screen {
	private JFrame frame;
	private CanvasPanel panel;
	private ScreenListener listener;
	private JButton start, pause, step, random, clear;
	private JTextField seed;
	private JPanel container;
	private ButtonListener buttonListener;

	public Screen(int height, int length) {

		frame = new JFrame("Conway's Game of Life");
		container = new JPanel();
		panel = new CanvasPanel(height, length);
		start = new JButton("Start");
		pause = new JButton("Pause");
		step = new JButton("Step");
		random = new JButton("Random");
		clear = new JButton("Clear");
		seed = new JTextField("0");

		start.setActionCommand("Start");
		pause.setActionCommand("Pause");
		step.setActionCommand("Step");
		random.setActionCommand("Random");
		clear.setActionCommand("Clear");

		listener = new ScreenListener(panel);
		buttonListener = new ButtonListener(panel);

		start.addActionListener(buttonListener);
		pause.addActionListener(buttonListener);
		step.addActionListener(buttonListener);
		random.addActionListener(buttonListener);
		clear.addActionListener(buttonListener);

		frame.setLayout(new BorderLayout());
		frame.add(container);
		

		panel.addMouseListener(listener);

		GroupLayout layout = new GroupLayout(container);
		container.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup().addComponent(panel)
				.addGroup(layout.createSequentialGroup().addComponent(start).addComponent(pause).addComponent(step)
						.addComponent(clear).addComponent(random).addComponent(seed)));

		layout.setVerticalGroup(layout.createSequentialGroup().addComponent(panel)
				.addGroup(layout.createParallelGroup().addComponent(start).addComponent(pause).addComponent(step)
						.addComponent(clear).addComponent(random).addComponent(seed)

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
