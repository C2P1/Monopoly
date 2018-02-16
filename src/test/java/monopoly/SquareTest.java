package monopoly;

import junit.framework.TestCase;

public class SquareTest extends TestCase {
	public void testConstructor_property() throws Exception {
		Property mediteraneanAvenue = new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2);
		Square square = new Square(mediteraneanAvenue, 569, 658);
		assertEquals(mediteraneanAvenue, square.getProperty());
	}
	
	public void testGetValue() {
		Property mediteraneanAvenue = new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2);
		Square square = new Square(mediteraneanAvenue, 569, 658);
		assertEquals("testing value", 0, square.getValue());
	}
	
	public void testGetX() {
		Property mediteraneanAvenue = new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2);
		Square square = new Square(mediteraneanAvenue, 569, 658);
		assertEquals("testing get x value", 569, square.getX());
	}
	
	public void testGetY() {
		Property mediteraneanAvenue = new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2);
		Square square = new Square(mediteraneanAvenue, 569, 658);
		assertEquals("testing get x value", 658, square.getY());
	}
	
	public void testGetName() {
		Property mediteraneanAvenue = new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2);
		Square square = new Square(mediteraneanAvenue, 569, 658);
		assertEquals("Getting name", "Mediterranean Avenue", square.getName());
	}
	
	
	
	public void testConstructor_name() {
		Square square = new Square("Mediteranean Avenue",100, 569, 658);
		assertEquals("Getting name", "Mediteranean Avenue", square.getName());
	}
}
