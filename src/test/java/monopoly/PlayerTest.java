package monopoly;

import junit.framework.TestCase;
import java.awt.Color;
import org.junit.*;
import java.util.*;

public class PlayerTest extends TestCase {

	/* 
	 * Buy a deed that is owned by the bank and check the success of the purchase by the boolean 
	 * returned by the buyDeed method
	 */
	public void testBuyUnownedDeed_checkSucessfulPurcahse() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		boolean success = player1.buyDeed(mediterraneanAve);
		assertTrue("Sucessful purchase of deed", success);
	}

	/* 
	 * Attempt to buy a deed without having the balance to do so
	 */
	public void testBuyUnownedDeed_noBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.updateBalance(-1500);
		boolean success = player1.buyDeed(mediterraneanAve);
		assertFalse("Balance too low", success);
	}

	/* 
	 * Buy a deed that is owned by the bank and check the success of the purchase by making sure the
	 * property is now contained in the list of the players owned properties
	 */
	public void testBuyUnownedDeed_checkPropertyList() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		ArrayList<Property> properties = player1.getProperties().get(0);
		assertTrue("Player owns the deed", properties.contains(mediterraneanAve));
	}
	
	/*
	 * Attempt to buy house on a property not owned by the player
	 */
	public void testBuyHouse_unownedProperty() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		boolean success = player1.buyHouse(mediterraneanAve);
		assertFalse("Do not currently own this property", success);
	}
	
	/*
	 * Attempt to buy house when lacking enough money to do so
	 */
	public void testBuyHouse_insufficientFunds() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.updateBalance(-1455);
		player1.buyDeed(mediterraneanAve);
		boolean success = player1.buyHouse(mediterraneanAve);
		assertFalse("Not enough cash to buy house", success);
	}

	/* 
	 * attempt to buy a house on a property where the current number of houses is < 4 whilst not having
	 * the balance to do so
	 */
	public void testBuyHouse_noBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.updateBalance(-1500);
		player1.buyDeed(mediterraneanAve);
		boolean success = player1.buyHouse(mediterraneanAve);
		assertFalse("Not enough cash to buy house", success);
	}	

	/* 
	 * attempt to buy a house on a property where the current number of houses is < 4 whilst not having
	 * the balance to do so
	 */
	public void testBuyHouse_negativeBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.updateBalance(-1600);
		player1.buyDeed(mediterraneanAve);
		boolean success = player1.buyHouse(mediterraneanAve);
		assertFalse("Not enough cash to buy house", success);
	}	

	/* buy a house on a property where the current number of houses is < 4 and check the 
	 * success of the purchase by using the boolean returned by the buyHouse method
	 */
	public void testBuyHouse_success() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);	
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		boolean success = player1.buyHouse(mediterraneanAve);
		assertTrue("House has been bought", success);
	}	


	/*
	 * buy a house on a property where the current number of houses is < 4
	 */
	public void testBuyHouse_underMaximumNum() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		int numHouses = mediterraneanAve.getNumHouses();
		assertEquals("House has been bought", 1, numHouses);
	}	

	/*
	 * attempt to buy a house on a property where the current number of houses is 4
	 */
	public void testBuyHouse_atMaximumNum() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		// player1.updateBalance(...)
		player1.buyDeed(mediterraneanAve);

		// buy 4 houses on the property
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);

		// try and buy a fifth house
		player1.buyHouse(mediterraneanAve);
		assertEquals("Cannot buy a property that is already owned", 4, mediterraneanAve.getNumHouses());
	}

	/* 
	 * attempt to buy a hotel on a property where the current number of hotel is 0 whilst not having
	 * the balance to do so
	 */
	public void testBuyHotel_noBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.updateBalance(-1500);
		boolean success = player1.buyHotel(mediterraneanAve);
		assertFalse("Not enough cash to buy hotel", success);
	}	

	/* 
	 * attempt to buy a hotel on a property where the current number of hotel is 0 whilst not having
	 * the balance to do so
	 */
	public void testBuyHotel_negativeBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.updateBalance(-1600);
		boolean success = player1.buyHotel(mediterraneanAve);
		assertFalse("Not enough cash to buy hotel", success);
	}

	/* 
	 * buy a hotel on a property where the current number of houses is < 4 and check the 
	 * success of the purchase by using the boolean returned by the buyHouse method
	 */
	public void testBuyHotel_lessThan4Houses() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		boolean success = player1.buyHotel(mediterraneanAve);
		assertFalse("Hotel has not been bought", success);
	}	

	/* 
	 * buy a hotel on a property where the current number of houses is 4 and check the 
	 * success of the purchase by using the boolean returned by the buyHouse method
	 */
	public void testBuyHotel_4Houses() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		boolean success = player1.buyHotel(mediterraneanAve);
		assertTrue("Hotel has been bought", success);
	}


	/*
	 * attempt to buy a hotel on a property which already has 1 hotel on it
	 */
	public void testBuyHotel_atMaximumNum() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);

		// buy 4 houses on the property
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		// buy one hotel
		player1.buyHotel(mediterraneanAve);
		// try buy another hotel
		player1.buyHotel(mediterraneanAve);
		assertEquals("Already own the maximum number of hotels", 1, mediterraneanAve.getNumHotels());
	}

	/*
	 * Test paying rent when currently have zero balance
	 */
	public void testPayRent_zeroBalance() {
		System.out.println("Start");
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		player2.buyDeed(mediterraneanAve);
		double initialBalance = player1.getBalance();

		player1.payRent(mediterraneanAve);
		double finalBalance = player1.getBalance();
		double rentPrice = mediterraneanAve.getRent();
		double rentPaid = initialBalance - finalBalance;
		System.out.println("end");
		assertEquals("Correct amount rent paid", rentPrice, rentPaid);
	}

	/*
	 * Test paying rent when currently have a negative balance
	 */
	public void testPayRent_negativeBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);	
		player1.updateBalance(-1600);
		Player player2 = new Player(Color.blue, 2);
		player2.buyDeed(mediterraneanAve);
		double initialBalance = player1.getBalance();
		player1.payRent(mediterraneanAve);
		double finalBalance = player1.getBalance();
		double rentPrice = mediterraneanAve.getRent();
		double rentPaid = Math.abs(finalBalance) - Math.abs(initialBalance);
		assertEquals("Correct amount rent paid", rentPrice, rentPaid);
	}

	/*
	 * Test paying rent when current balance is not enough
	 */
	public void testPayRent_notEnoughBalance() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);	
		Player player2 = new Player(Color.blue, 2);
		player2.buyDeed(mediterraneanAve);
		double initialBalance = player1.getBalance(); // positive number
		player1.payRent(mediterraneanAve);
		double finalBalance = player1.getBalance(); // negative number
		double rentPrice = mediterraneanAve.getRent();
		double rentPaid = initialBalance - finalBalance;
		assertEquals("Correct amount rent paid", rentPrice, rentPaid);
	}


	/*
	 * Test paying rent to another player
	 */
	public void testPayRent() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.blue, 2);
		player2.buyDeed(mediterraneanAve);
		double initialBalance = player1.getBalance();
		player1.payRent(mediterraneanAve);
		double finalBalance = player1.getBalance();
		double rentPrice = mediterraneanAve.getRent();
		double rentPaid = initialBalance - finalBalance;
		assertEquals("Correct amount rent paid", rentPrice, rentPaid);

	}

	/*
	 * Test selling a house when at least 1 house is owned on the property
	 */
	public void testSellHouse_atLeastOneOwned() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.sellHouse(mediterraneanAve);
		assertEquals("House sold", 1, mediterraneanAve.getNumHouses());
	}

	/*
	 * Test selling a house when no houses are currently owned on the property
	 */
	public void testSellHouse_noHousesOwned() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		boolean success = player1.sellHouse(mediterraneanAve);

		assertFalse("No houses to sell on the property", success);
	}

	/*
	 * Test that when trying to sell a house when no houses are currently owned on the property that the 
	 * count of houses on the property does not decrease to -1 .
	 */
	public void testSellHouse_countNotChanged() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.sellHouse(mediterraneanAve);

		assertEquals("No houses on the property to sell", 0, mediterraneanAve.getNumHouses());
	}

	/*
	 * Test selling a hotel when at least 1 hotel is owned on the property
	 */
	public void testSellHotel_atLeastOneOwned() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);

		player1.buyDeed(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);
		player1.buyHouse(mediterraneanAve);

		player1.buyHotel(mediterraneanAve);
		player1.sellHotel(mediterraneanAve);

		assertEquals("House sold", 0, mediterraneanAve.getNumHotels());
	}

	/*
	 * Test selling a hotel when no hotels are currently owned on the property
	 */
	public void testSellHotel_noHotelsOwned() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		boolean success = player1.sellHotel(mediterraneanAve);

		assertFalse("No hotels to sell on the property", success);
	}

	/*
	 * Test that when trying to sell a hotel when no hotels are currently owned on the property that the 
	 * count of hotels on the property does not decrease to -1 .
	 */
	public void testSellHotel_countNotChanged() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.sellHotel(mediterraneanAve);

		assertEquals("No hotels on the property to sell", 0, mediterraneanAve.getNumHotels());
	}

	/*
	 * Test mortgage an owned property
	 */
	public void testMortgage_ownedProperty() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.mortgage(mediterraneanAve);
		assertTrue("property is mortgaged", mediterraneanAve.getIsMortgaged());
	}

	/*
	 * Test mortgage an unowned property
	 */
	public void testMortgage_unownedProperty() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.mortgage(mediterraneanAve);
		assertFalse("property is mortgaged", mediterraneanAve.getIsMortgaged());
	}
	
	/*
	 * Test unmortgage on an owned property
	 */
	public void testUnmortgage_ownedProperty() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.buyDeed(mediterraneanAve);
		player1.mortgage(mediterraneanAve);
		boolean success = player1.unmortgage(mediterraneanAve);
		assertTrue("Property is now unmortgaged", success);
	}
	
	/*
	 * Test unmortgage on an unowned property
	 */
	public void testUnmortgage_unownedProperty() {
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		Player player1 = new Player(Color.red, 1);
		player1.mortgage(mediterraneanAve);
		boolean success = player1.unmortgage(mediterraneanAve);
		assertFalse("Property is now unmortgaged", success);
	}
	
	/*
	 * Test getting the order
	 */
	public void testGetOrder() {
		Player player1 = new Player(Color.red, 1);
		assertEquals(1, player1.getOrder());
	}
	
	/*
	 * Test getting the order
	 */
	public void testGetColor() {
		Player player1 = new Player(Color.red, 1);
		assertEquals(Color.red, player1.getColor());
	}
	
	/*
	 * Test get board location
	 */
	public void testGetBoardLocation() {
		Player player1 = new Player(Color.red, 1);
		assertEquals(0, player1.getLocation());
	}
	
	/*
	 * Test move player
	 */
	public void testMovePlayer() {
		Player player1 = new Player(Color.red, 1);
		player1.setLocation(5);
		assertEquals(5, player1.getLocation());
	}
	
	/*
	 * Test pay new rent
	 */
	public void testPayNewRent() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.blue, 1);
		Property mediterraneanAve = new Property("Mediterranean Avenue", 60, 2, 50, 1, 0, 2);
		player2.buyDeed(mediterraneanAve);
		double startBalance = player1.getBalance();
		player1.payNewRent(mediterraneanAve, 10);
		double endBalance = player1.getBalance();
		assertEquals(10.0, startBalance - endBalance);
	}
	
	/*
	 * Test if player is in jail
	 */
	public void testInJail() {
		Player player1 = new Player(Color.red, 1);
		assertFalse("Player not in jail", player1.isInJail());
	}
	
	/*
	 * Test if player is in jail
	 */
	public void testSetInJail() {
		Player player1 = new Player(Color.red, 1);
		player1.setJailed(true);
		assertTrue("Player not in jail", player1.isInJail());
	}
	
	/*
	 * Test if player is in jail
	 */
	public void testSetGetOutOfJail() {
		Player player1 = new Player(Color.red, 1);
		player1.setJailed(true);
		player1.setJailed(false);
		assertFalse("Player not in jail", player1.isInJail());
	}
	
	/*
	 * Test getting attempts
	 */
	public void testGetAttemps() {
		Player player1 = new Player(Color.red, 1);
		assertEquals("No attempts yet", 0, player1.getAttempts());
	}
	
	/*
	 * Test setting attempts
	 */
	public void testSetAttemps() {
		Player player1 = new Player(Color.red, 1);
		player1.setAttempts(5);
		assertEquals("No attempts yet", 5, player1.getAttempts());
	}
	
	/*
	 * Test setting attempts
	 */
	public void testUpdateAttemps() {
		Player player1 = new Player(Color.red, 1);
		player1.setAttempts(5);
		player1.updateAttempts();
		assertEquals("No attempts yet", 6, player1.getAttempts());
	}	
}
