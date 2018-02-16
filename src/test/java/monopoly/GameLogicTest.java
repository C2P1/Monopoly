package monopoly;

import java.awt.Color;

import junit.framework.TestCase;

public class GameLogicTest extends TestCase {

	public void testGetTurn() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		assertEquals("Next turn", 0, game.getTurnIndex());
	}

	public void testNextTurn() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		assertEquals("Next turn", 1, game.getTurnIndex());
	}

	public void testNextTurn_backToFirstPlayer() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.nextTurn();
		assertEquals("Next turn", 0, game.getTurnIndex());
	}

	public void testMovePlayer() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		assertEquals("Player moved 5", 5, game.getTurn().getLocation());
	}

	public void testMovePlayer_completeCycleOfBoard() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(41);
		assertEquals("Player moved to square 1", 1, game.getTurn().getLocation());
	}

	public void testGetXLocation() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		assertEquals("At starting position", 0, game.getX());
	} 

	public void testGetYLocation() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		assertEquals("At starting position", 0, game.getY());
	}

	public void testBuyProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		boolean success = game.buyproperty();
		assertTrue("Property bought", success);
	}

	public void testUtilOrRail_rail() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		Property railroad = new Property("Short Line Railroad", 200, 25, 0, 0, 8, 4);
		assertTrue("Property bought", game.isUtilorRail(railroad));
	}

	public void testUtilOrRail_util() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		Property utility = new Property("Electric Company", 150, 4, 0, 0, 9,2);
		assertTrue("Property bought", game.isUtilorRail(utility));
	}

	public void testUtilOrRail_neither() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		Property orientalAvenue = new Property("Oriental Avenue", 100, 6, 50, 50, 1, 3);
		assertFalse("Property bought", game.isUtilorRail(orientalAvenue));
	}

	public void testPassGo_yes() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		boolean passedGo = game.passedGo(52);
		assertTrue("Passed Go", passedGo);
	}

	public void testPassGo_no() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		boolean passedGo = game.passedGo(4);
		assertFalse("Passed Go", passedGo);
	}

	/*
	 * Testing the paying of rent on property
	 */
	public void testHandleProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(6);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(6);
		double balance = game.getTurn().getBalance();
		game.handleProp();
		double newBalance = game.getTurn().getBalance();
		assertEquals("Rent paid", balance - 6, newBalance);
	}

	/*
	 * Testing the paying of rent on railroad; only one owned
	 */
	public void testHandleRailroad_oneOwned() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(5);
		double balance = game.getTurn().getBalance();
		game.handleRail();
		double newBalance = game.getTurn().getBalance();
		assertEquals("Rent paid", balance - 25, newBalance);
	}

	/*
	 * Testing the paying of rent on railroad; two owned
	 */
	public void testHandleRailroad_twoOwned() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn(); 
		game.nextTurn();
		game.moveplayer(10);
		game.buyproperty();
		game.nextTurn();
		double balance = game.getTurn().getBalance();
		game.handleRail(); 
		double newBalance = game.getTurn().getBalance();
		assertEquals("Rent paid", balance - 50, newBalance);
	}

	/*
	 * Testing the paying of rent on railroad; three owned
	 */
	public void testHandleRailroad_threeOwned() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn(); 
		game.nextTurn();
		game.moveplayer(10);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(10);
		game.buyproperty();
		game.nextTurn();
		double balance = game.getTurn().getBalance();
		game.handleRail(); 
		double newBalance = game.getTurn().getBalance();
		assertEquals("Rent paid", balance - 100, newBalance);
	}
	
	/*
	 * Testing the paying of rent on railroad; four owned
	 */
	public void testHandleRailroad_fourOwned() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn(); 
		game.nextTurn();
		game.moveplayer(10);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(10);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(10);
		game.buyproperty();
		game.nextTurn();
		double balance = game.getTurn().getBalance();
		game.handleRail(); 
		double newBalance = game.getTurn().getBalance();
		assertEquals("Rent paid", balance - 200, newBalance);
	}

	/*
	 * check full set for railroad
	 */
	public void testFullSet_threeRailroads() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		boolean isFull = game.isFullSet(game.getTurn(), game.getCurrentSquare().getProperty());
		assertFalse("Not a full set", isFull);
	}
	
	/*
	 * Testing the handling of landing of a railroad that the player whos turn it is (and who landed on it)
	 * current owns
	 */
	public void testHandleRailroad_PlayersOwn() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(40);
		double balance = game.getTurn().getBalance();
		game.handleRail();
		double newBalance = game.getTurn().getBalance();
		assertEquals("Rent paid", balance, newBalance);
	}

	/*
	 * check full set for single property
	 */
	public void testFullSet_oneProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(6);
		game.buyproperty();
		boolean isFull = game.isFullSet(game.getTurn(), game.getCurrentSquare().getProperty());
		assertFalse("Not a full set", isFull);
	}

	/*
	 * check full set for full property set
	 */
	public void testFullSet_fullPropertySet() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(2);
		game.buyproperty();
		boolean isFull = game.isFullSet(game.getTurn(), game.getCurrentSquare().getProperty());
		assertTrue("Not a full set", isFull);
	}

	/*
	 * test if the current unowned square can be bought
	 */
	public void testIsValidProperty_validUnownedProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		boolean isValid = game.isValidProperty();
		assertTrue("Property can be bought", isValid);
	}

	/*
	 * test if the current square which is not a valid property to buy can be owned
	 */
	public void testIsValidProperty_invalidUnownedProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(0);
		boolean isValid = game.isValidProperty();
		assertFalse("Not a valid property", isValid);
	}

	/*
	 * test if the current square can be bought
	 */
	public void testIsValidProperty_OwnedProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(1);
		boolean isValid = game.isValidProperty();
		assertFalse("Property is already owned", isValid);
	}

	/*
	 * Test sending a player to jail
	 */
	public void testSendJail() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.handleJail(); // send player to jail
		assertTrue("Player in jail", game.getTurn().isInJail());
	}

	/*
	 * Test the taxation of a player on the tax square
	 */
	public void testHandleTax_TaxSquare() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(4);
		double initBalance = game.getTurn().getBalance();
		game.handleTax();
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Tax paid", initBalance - 200, finalBalance);
	}
	
	/*
	 * Test the taxation of a player on any square
	 */
	public void testHandleTax_anytime() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(6);
		double initBalance = game.getTurn().getBalance();
		game.handleTax();
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Tax paid", initBalance - 100, finalBalance);
	}
	
	/*
	 * Test the handling of a player landing on a utility when another player
	 * owns only one utility
	 */
	public void testHandleUtil_oneOwned() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(12);
		game.buyproperty();
		game.nextTurn();	
		game.moveplayer(12);
		double initBalance = game.getTurn().getBalance();
		game.handleUtil(10);
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Balance paid", initBalance - 40, finalBalance);
	}
	
	/*
	 * Test the handling of a player landing on a utility when another player
	 * owns the whole set
	 */
	public void testHandleUtil_allOwned() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(1);
		game.nextTurn();
		game.moveplayer(2);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(2);
		double initBalance = game.getTurn().getBalance();
		game.handleUtil(10);
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Balance paid", initBalance - 100, finalBalance);
	}
	
	/*
	 * Test the handling of a player landing on a utility that the currently player already owns
	 */
	public void testHandleUtil_playerOwns() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(12);
		game.buyproperty();
		game.nextTurn();	
		game.nextTurn();
		game.moveplayer(40);
		double initBalance = game.getTurn().getBalance();
		game.handleUtil(10);
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Balance paid", initBalance, finalBalance);
	}
	
	/*
	 * Test the getting of the paid value
	 */
	public void testGetPaidValue() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(6);
		game.handleTax();
		assertEquals("Tax paid", 100.0, game.getPaidValue());
	}
	
	/*
	 * Test the handling of landing on first utility
	 */
	public void testSquareHandle_firstUtil() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(4);
		double initBalance = game.getTurn().getBalance();
		game.handleSquare(0);	
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Tax paid", initBalance - 200, finalBalance);
	}
	
	/*
	 * Test the handling of landing on second utility
	 */
	public void testSquareHandle_secondUtil() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(38);
		double initBalance = game.getTurn().getBalance();
		game.handleSquare(0);	
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Tax paid", initBalance - 100, finalBalance);
	}
	
	/*
	 * Test handling of player land on go to jail
	 */
	public void testSquareHandle_goJail() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(30);
		game.handleSquare(0);
		assertTrue("Player in jail", game.getTurn().isInJail());
	}
	
	/*
	 * Test handling of player landing on valid property
	 */
	public void testSquareHandle_validProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 2, result);
	}
	
	/*
	 * Test handling of player landing on GO
	 */
	public void testSquareHandle_NULLProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(40);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 4, result);
	}
	
	/*
	 * Test handling of player landing on a valid utility 
	 */
	public void testSquareHandle_FirstUtilProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2; 
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(12);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(12);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 3, result);
	}
	
	/*
	 * Test handling of player landing on a valid utility 
	 */
	public void testSquareHandle_SecondUtilProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(28);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(28);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 3, result);
	}
	
	/*
	 * Test handling of player landing on a valid utility 
	 */
	public void testSquareHandle_OwnedProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(9);
		game.buyproperty();
		game.nextTurn();                         
		game.moveplayer(9);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 3, result);
	}
	
	/*
	 * Test handling of player landing on a valid railroad
	 */
	public void testSquareHandle_railroad() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(5);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 3, result);
	}
	
	
	
	/*
	 * Test that the system recognizes the correct owner of a property
	 */
	public void testIsOwner() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(5);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(40);
		assertTrue("Current player is the owner", game.isOwner());
	}
	
	/*
	 * Test handling of player landing on a valid utility 
	 */
	public void testSquareHandle_isOnCurrentlyOwnedProperty() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(9);
		game.buyproperty();
		game.nextTurn();                         
		game.nextTurn();
		game.moveplayer(40);
		int result = game.handleSquare(0);
		assertEquals("Player on valid property", 4, result);
	}
	
	/*
	 * check full set for full property set
	 */
	public void testHandleProp_fullPropertySet() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		game.buyproperty();
		game.nextTurn();
		game.nextTurn();
		game.moveplayer(2);
		game.buyproperty();
		game.nextTurn();
		game.moveplayer(3);
		double initBalance = game.getTurn().getBalance();
		game.handleProp();
		double finalBalance = game.getTurn().getBalance();
		assertEquals("Full set rent fee charged", initBalance - 8, finalBalance);
	}
	
	/*
	 * Testing the nextTurn method
	 */
	public void testNextTurn_whoseTurn4() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player player3 = new Player(Color.green, 3);
		Player player4 = new Player(Color.yellow, 4);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;  
		players[2] = player3;
		players[3] = player4;
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Next turn", 0, game.getTurnIndex());
	}
	
	
	/*
	 * Testing the player being able to leave jail
	 */
	public void testClearToLeave_cantleave() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		boolean success = game.clearToLeave();
		assertFalse("Unable to leave jail", success);
	}
	
	/*
	 * Testing the player being able to leave jail
	 */
	public void testClearToLeave_leaveAtMax() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.getTurn().setAttempts(3);
		boolean success = game.clearToLeave();
		assertTrue("Able to leave jail", success);
	}
	
	/*
	 * Testing the player being able to leave jail
	 */
	public void testClearToLeave_leaveAboveMax() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.getTurn().setAttempts(7);
		boolean success = game.clearToLeave();
		assertTrue("Able to leave jail", success);
	}
	
	/*
	 * Test to make sure player has funds to buy property
	 */
	public void testHasFunds_success() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		boolean success = game.hasFunds();
		assertTrue("Player has funds to buy property", success);
	}
	
	/*
	 * Test to make sure player has funds to buy property
	 */
	public void testHasFunds_unsuccessful() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.moveplayer(1);
		game.getTurn().updateBalance(-1600);
		boolean success = game.hasFunds();
		assertFalse("Player does not have enough funds to buy property", success);
	}
	
	
	public void testSetPlayerComputer() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.setCompPlayer(true);
		assertTrue("Computer player", game.isCompPlayer());
	}
	
	public void testIfPlayerIsComputer_no() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		assertFalse("No Computer player", game.isCompPlayer());
	}
	
	public void testIfComputerPlayerPresent_no() {
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		game.nextTurn();
		game.setCompPlayer(true);
		assertFalse("Computer player present", game.isComputer());
	}
	
	public void testIfComputerPlayer_yes() {
		Player player1 = new Player(Color.red, 0);	
		Player player2 = new Player(Color.blue, 1);
		Player[] players = new Player[4];
		players[0] = player1;
		players[1] = player2;   
		GameLogic game = new GameLogic(players);
		Prompt prompt = new Prompt(players);
		prompt.setCompPlayer(true);
		game.nextTurn();
		game.setCompPlayer(true);
		assertTrue("Computer player present", game.isComputer());
	}
}
