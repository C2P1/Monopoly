package monopoly;

import java.awt.Color;
import java.util.*;

public class Player {

	/*
	 * instance fields
	 */
	private double balance; 
	private boolean inJail;
	//private ArrayList<Property> properties = new ArrayList<Property>(); //MODIFIED BELOW
	private ArrayList<ArrayList<Property>> properties = new ArrayList<ArrayList<Property>>(10); //MODIFIED FROM ABOVE
	//if we decide have each board position as an index in an array or something
	private int boardLocation, order; 	
	//if we decide to not make a Token class and instead make this a color for example
	private Color token;
	private int attempts;


	//constructor
	public Player(Color color, int order) {
		token=color;
		balance = 1500;
		boardLocation = 0;
		this.order=order;
		attempts = 0;
		
		//initialize the arrayList to be 2-dimensional
		for (int i=0; i<10; i++)
		{
			properties.add(new ArrayList<Property>());
		}
	}
	
	//method to get the number of attempts 
	public int getAttempts() {
		return attempts;
	}
		
	//method to update the number of attempts
	public void updateAttempts() {
		attempts += 1;
	}
		
	//method to set the number of attempts
	public void setAttempts(int x) {
		attempts = x;
	}

	//method to buy a house
	public boolean buyHouse(Property prop) {
		if(properties.get(prop.getSetNum()).contains(prop) && prop.getNumHouses() < 4 && balance >= prop.getHouseCost()) {
			balance -= prop.getHousePrice();
			prop.buyHouse();
			return true;
		} else {
			System.out.println("You cannot buy houses on that property");
			return false;
		}
	}

	//method to buy a hotel
	public boolean buyHotel(Property prop) {
		if(properties.get(prop.getSetNum()).contains(prop) && prop.getNumHotels() < 1 && prop.getNumHouses() == 4) {
			if (balance >= prop.getHotelCost()) {
				balance -= prop.getHotelPrice();
				prop.buyHotel();
				return true;
			} else {
				System.out.println("You do not have enough money to be able to buy a hotel");
				return false;
			}
		} else {
			System.out.println("You cannot buy a hotel on that property");
			return false;
		}
	}

	//method to pay rent to another player
	public boolean payRent(Property prop) {
		double price = prop.getRent();
		prop.getOwner().updateBalance(price);
		balance -= price;
		return true;
	}
	
	//***additional method to pay rent, but with an updated rent value (used for paying rent on a Utility & when entire set is owned)
	public boolean payNewRent(Property prop, int rentPrice)
	{
		prop.getOwner().updateBalance(rentPrice);
		balance -= rentPrice;
		return true;
	}

	//method to buy a Deed from the Bank 
	public boolean buyDeed(Property prop) { 
		double price = prop.getPrice();
		if(balance > price) {
			balance -= price;
			prop.buy(this);
			//properties.add(prop); //MODIFIED BELOW
			properties.get(prop.getSetNum()).add(prop); //adds the property to its specific row
			return true;
		} else {
			System.out.println("You don't have enough money to buy this Property");
			return false;
		}
	}

	//method to update the balance of the Player
	public void updateBalance(double change) {
		balance += change;
	}

	public int getOrder() {
		return order;
	}
	public int getLocation() {
		return boardLocation; 
	}
	public void setLocation(int i) {
		boardLocation=i;
	}
	public Color getColor() {
		return token;
	}

	public boolean mortgage(Property prop) {
		if(properties.get(prop.getSetNum()).contains(prop) && !prop.getIsMortgaged()) {
			balance += prop.getMortgagePrice();
			prop.mortgage();
			return true;
		} else {
			System.out.println("You cannot mortgage this property");
			return false;
		}
	}

	public boolean unmortgage(Property prop) {
		double price = prop.getMortgagePrice();
		if(properties.get(prop.getSetNum()).contains(prop) && prop.getIsMortgaged() && balance > price) {
			balance -= price;
			prop.unmortgage();
			return true;
		} else {
			System.out.println("You cannot unmortgage this property");
			return false;
		}
	}

	//method to sell a hotel
	public boolean sellHotel(Property prop) {
		if(properties.get(prop.getSetNum()).contains(prop) && prop.getNumHotels() > 0) {
			balance += prop.getHotelPrice();
			prop.sellHotel();
			return true;
		} else {
			System.out.println("You cannot sell a hotel on that property");
			return false;
		}
	}

	//method to sell a house
	public boolean sellHouse(Property prop) {
		if(properties.get(prop.getSetNum()).contains(prop) && prop.getNumHouses() > 0) {
			balance += prop.getHousePrice();
			prop.sellHouse();
			return true;
		} else {
			System.out.println("You cannot sell houses on that property");
			return false;
		}
	}

	// method to get all the properties currently owned by the player
	public ArrayList<ArrayList<Property>> getProperties() { //MODIFIED
		return properties;
	}

	// method to return the players balance
	public double getBalance() {
		return balance;
	}
	
	public boolean isInJail() {
		return inJail;
	}
	
	public void setJailed(boolean j) {
		inJail=j;
	}
}
