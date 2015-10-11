package listeners;

/**
 * Class ButtonListener
 * This class receives the events and handles the buttons
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.Timer;

import game.Main;
import graphics.CanvasPanel;

public class ButtonListener implements ActionListener {
	private CanvasPanel panel;
	Timer timer;

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
					Main.board.nextTick();
					panel.setCells(Main.board.getCellMatrix());
					panel.repaint();

				}
			});
			timer.setInitialDelay(0);
			timer.start();
			break;

		case "Step":
			timer.stop();
			Main.board.nextTick();
			panel.setCells(Main.board.getCellMatrix());
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
			Main.board.randomize(seed);
			panel.setCells(Main.board.getCellMatrix());
			panel.repaint();
			break;

		case "Pause":
			timer.stop();
			break;

		case "Clear":
			Main.board.clear();
			panel.setCells(Main.board.getCellMatrix());
			panel.repaint();
			timer.stop();
			break;
		}

	}

}
