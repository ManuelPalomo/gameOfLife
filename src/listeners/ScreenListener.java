package listeners;

/**
 * Class ScreenListener
 * This class receives the clicks the user performs on the screen and processes them
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.Main;
import graphics.CanvasPanel;

public class ScreenListener extends MouseAdapter {
	private CanvasPanel panel; // Needed to obtain the height/width of the panel
								// as it can change during execution

	public ScreenListener(CanvasPanel panel) {
		this.panel = panel;
	}

	/**
	 * Gets events coming from the JFrame and translates the coordinates of the
	 * mouse to correlating coordenates in the cell grid
	 * 
	 * @return direction of the cell in the array in correlation with the
	 *         position of the cell in the screen
	 */
	public void mouseReleased(MouseEvent e) {
		try {
			int row = e.getX() / (panel.getHeight() / panel.getHeightInCells());
			int col = e.getY() / (panel.getWidth() / panel.getLengthInCells());
			panel.setValue(row, col, !panel.getValue(row, col));
			Main.board.setValue(row, col, panel.getValue(row, col));
			panel.repaint();
		} catch (IndexOutOfBoundsException banana) {

		}

	}

}
