package listeners;

/**
 * Class ScreenListener
 * This class receives the clicks the user performs on the screen and processes them
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ScreenListener extends MouseAdapter {
	/**
	 * @return direction of the cell in the array in correlation with the
	 *         position of the cell in the screen
	 */
	public void mouseReleased(MouseEvent e) {
		System.out.println(e.getX() + " " + e.getY());

	}

}
