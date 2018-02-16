package monopoly;

public class Square 
{
	//instance variables
	private String name; //name of the Square
	private int value; //value of the Square (e.g. -200 for Income Tax; the value is deduced from the Player)
	private Property property; //not sure about this
	private int x, y; // coordinates of the squares based on pixels
	
	//constructor
	public Square(String name, int value, int x, int y) 
	{
		this.name = name;
		this.value = value;
		this.x=x;
		this.y=y;
		property=null;
	}
	
	//additional constructor
	public Square(Property property, int x, int y)
	{
		this.property = property;
		this.x=x;
		this.y=y;
	}
	
	//method to return the value
	public int getValue()
	{
		return value;
	}
	
	//method to return a property
	public Property getProperty() 
	{
		return property;
	}
	
	//returns the x coordinate
	public int getX() {
		return x;
	}
	//returns the y coordinate
	public int getY() {
		return y;
	}
	
	//return the name given its type
	public String getName() {
		//check if the Square contains a Property
		if (getProperty() != null)
		{
			return property.getName(); //return the name of the Property
		}
		else
		{
			return name; //return the name of the Square
		}
	}
}

