package monopoly;

import javax.swing.JButton;

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	public void testInitialise() {
		Board board = new Board();
		board.main(null);
	}
}
