package monopoly;

import static org.junit.Assert.*;

import junit.framework.TestCase;

public class DiceTest extends TestCase {
	
	Dice dice = new Dice();
	
	public void testRollDiceD1() throws Exception {
			dice.rollDice();
			Integer d1 = dice.getDice1();
			assertNotNull("dice roll returns an int", d1);
	}
	

	public void testRollDiceD2() throws Exception {
			dice.rollDice();
			Integer d2 = dice.getDice2();
			assertNotNull("Dice roll returns an int", d2);
	}
}

