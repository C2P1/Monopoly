package monopoly;

public class Dice {
private int d1;
private int d2;
	public Dice(){}
	
	public void rollDice() {
		d1=(int)((Math.random()*6)+1);
		d2=(int)((Math.random()*6)+1);
	}
	
	public int getDice1() {
		return d1;
	}
	public int getDice2() {
		return d2;
	}
}
