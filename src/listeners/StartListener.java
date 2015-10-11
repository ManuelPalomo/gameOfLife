package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import game.Main;
import graphics.CanvasPanel;

public class StartListener implements ActionListener {
	private CanvasPanel panel;
	
	public StartListener(CanvasPanel panel) {
		this.panel=panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Timer timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.board.nextTick();
				panel.setCells(Main.board.getCellMatrix());
				panel.repaint();
				

			}
		});
		timer.setInitialDelay(0);
		timer.start();

	}

}
