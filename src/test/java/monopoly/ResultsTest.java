package monopoly;

import java.awt.Color;
import junit.framework.TestCase;

public class ResultsTest extends TestCase {
	public void testResultsInitialisation() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		Results endGame = new Results(players);
	}
}
