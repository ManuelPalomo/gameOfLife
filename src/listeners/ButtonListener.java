package listeners;

/**
 * Class ButtonListener
 * This class receives the events and handles the buttons
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import game.Board;
import graphics.CanvasPanel;

public class ButtonListener implements ActionListener {
	private CanvasPanel panel;
	Timer timer;
	Board board = Board.getInstance();
	JFrame frame; //Used to change the title to display the current generation
	public ButtonListener(CanvasPanel panel) {
		this.panel = panel;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Start":
			timer = new Timer(100, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					board.nextTick();
					panel.setCells(board.getCellMatrix());
					frame=(JFrame)SwingUtilities.getWindowAncestor(panel);
					frame.setTitle("Game of life. Generation: "+board.getGeneration());
					panel.repaint();

				}
			});
			timer.setInitialDelay(0);
			timer.start();
			break;

		case "Step":
			timer.stop();
			board.nextTick();
			frame.setTitle("Game of life. Generation: "+board.getGeneration());
			panel.setCells(board.getCellMatrix());
			panel.repaint();
			break;

		case "Random":
			JTextField seedPanel = (JTextField) panel.getParent().getComponent(6);
			long seed;
			if (seedPanel.getText() == "0") {
				Random random = new Random(); // By default, this constructor
												// generates a random seed
				seed = random.nextLong();
			} else {
				seed = Long.parseLong(seedPanel.getText());
			}
			board.randomizeBoard(seed);
			panel.setCells(board.getCellMatrix());
			panel.repaint();
			break;

		case "Pause":
			timer.stop();
			break;

		case "Clear":
			board.clearBoard();
			panel.setCells(board.getCellMatrix());
			panel.repaint();
			timer.stop();
			break;
		}

	}

}
