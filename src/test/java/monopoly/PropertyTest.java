package monopoly;

import junit.framework.TestCase;
import java.awt.Color;

public class PropertyTest extends TestCase {

	/*
	 * Test a player buying and unowned property
	 */
	public void testBuy_unownedProperty() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player = new Player(Color.red, 1);
		mediteraneanAve.buy(player);
		assertTrue("Property is now owned", mediteraneanAve.getIsOwned());
	}

	/*
	 *  Test that the new owner is the player
	 */
	public void testBuy_unownedPropertyOwner() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player = new Player(Color.red, 1);
		mediteraneanAve.buy(player);
		assertEquals("Property is now owned", player, mediteraneanAve.getPlayer());
	}


	/*
	 * Test a player buying an already owned property. Buy attempt from second player should fail.
	 */
	public void testBuy_ownedProperty() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.blue, 2);
		mediteraneanAve.buy(player1);
		boolean success = mediteraneanAve.buy(player2);
		assertFalse("Property already owned", success);
	}

	/*
	 * Test a player buying an already owned property. Check that the owner does not change.
	 */
	public void testBuy_ownedPropertyOwner() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.blue, 2);
		mediteraneanAve.buy(player1);
		mediteraneanAve.buy(player2);
		assertEquals("Property already owned", player1, mediteraneanAve.getPlayer());
	}

	/*
	 * Test selling a property when there are no houses or hotels on it
	 */
	public void testSell_noHouseHotel() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		mediteraneanAve.buy(player1);
		mediteraneanAve.sell();
		assertFalse("Property is unowned", mediteraneanAve.getIsOwned());
	}

	/*
	 * Test selling a property when there are no houses or hotels on it. Check if there is an owner.
	 */
	public void testSell_unownedPropertyOwner() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		mediteraneanAve.buy(player1);
		mediteraneanAve.sell();
		assertNull("Property is unowned", mediteraneanAve.getPlayer());
	}

	/*
	 * Test selling a property which is unowned
	 */
	public void testSell_unownedProperty() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		boolean success = mediteraneanAve.sell();
		assertFalse("Property is unowned", success);
	}

	/*
	 * Test selling a property which has houses or hotels on it
	 */
	public void testSell_househotel() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		boolean success = mediteraneanAve.sell();
		assertFalse("Property has houses on it", success);
	}
	
	/*
	 * Test selling house on a property with no houses on it
	 */
	public void testSell_noHouse() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		boolean success = mediteraneanAve.sellHouse();
		assertFalse("No houses to sell", success);
	}
	
	/*
	 * Test selling hotel on a property with no hotels on it
	 */
	public void testSell_noHotel() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		boolean success = mediteraneanAve.sellHotel();
		assertFalse("No houses to sell", success);
	}
	
	/*
	 * 
	 */
	public void testBuyHouse_atMaximum() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		
		// buy the houses
		player1.buyHouse(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		
		boolean success = player1.buyHouse(mediteraneanAve);
		assertFalse("Cannot buy any more houses", success);
	}
	
	public void testBuyHouse_atMaximum2() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		
		// buy the houses
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		
		boolean success = mediteraneanAve.buyHouse();
		assertFalse("Cannot buy any more houses", success);
	}
	
	
	/*
	 * Test buying hotel on a property with a hotel on it
	 */
	public void testBuyHotel_alreadyHotel() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		
		// buy the houses
		player1.buyHouse(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		player1.buyHouse(mediteraneanAve);
		
		// buy hotels
		player1.buyHotel(mediteraneanAve);
		boolean success = player1.buyHotel(mediteraneanAve);
		assertFalse("No houses to sell", success);
	}
	
	/*
	 * Test buying hotel on a property with a hotel on it
	 */
	public void testBuyHotel_alreadyHotel2() {
		System.out.println("=======");
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		
		// buy the houses
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		
		// buy hotel
		System.out.println("Buying hotel: ");
		mediteraneanAve.buyHotel();
		
		// buy the houses
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHotel();
		System.out.println("Another hotel. try different buy hotel method");
		player1.buyHotel(mediteraneanAve);
		System.out.println("boolean check");
		boolean success = mediteraneanAve.buyHotel();
		System.out.println("=======");
		assertFalse("No houses to sell", success);
	}
	
	/*
	 * Test buying hotel on a property with a hotel on it
	 */
	public void testBuyHotel_tooFewHouses() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		
		// buy the houses
		mediteraneanAve.buyHouse();
		mediteraneanAve.buyHouse();
		
		// buy hotels
		boolean success = mediteraneanAve.buyHotel();
		assertFalse("No houses to sell", success);
	}
	
	/*
	 * Test get name
	 */
	public void testGetName() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		assertEquals("Mediterranean Avenue", mediteraneanAve.getName());
	}
	
	/*
	 * Test mortgaging an unowned property
	 */
	public void testMortgage_unowned() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		boolean success = mediteraneanAve.mortgage();
		assertFalse("Property unowned", success);
	}
	
	/*
	 * Test mortgaging an owned property that is already mortgaged
	 */
	public void testMortgage_alreadyMortgaged() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediteraneanAve);
		player1.mortgage(mediteraneanAve);
		
		boolean success = player1.mortgage(mediteraneanAve);
		assertFalse("Property already mortaged", success);
		
	}
	
	/*
	 * Test mortgaging an unowned property
	 */
	public void testUnMortgage_unowned() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		boolean success = mediteraneanAve.unmortgage();
		assertFalse("Property unowned", success);
	}
	
	/*
	 * Test getNumInSet
	 */
	public void testGetNumInSet() {
		Property mediteraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		assertEquals("Propery position in set", 2, mediteraneanAve.getNumInSet());
	}

}

