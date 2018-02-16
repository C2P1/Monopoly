package monopoly;

public class GameLogic {
 
	private Square[] squares = 
		{
				new Square("GO", 200, 658, 658),  
				new Square(new Property("Mediterranean Avenue", 60, 2, 50, 50, 0, 2), 569, 658), 
				new Square("Community Chest", 0, 511, 658), 
				new Square(new Property("Baltic Avenue", 60, 4, 50, 50, 0, 2), 453, 658), 
				new Square("Income Tax", -200, 395, 658), 
				new Square(new Property("Reading Railroad", 200, 25, 0, 0, 8,4), 337, 658),
				new Square(new Property("Oriental Avenue", 100, 6, 50, 50, 1, 3), 279, 658), 
				new Square("Chance", 0, 221, 658), 
				new Square(new Property("Vermont Avenue", 100, 6, 50, 50, 1,3),163, 658), 
				new Square(new Property("Connecticut Avenue", 120, 8, 50, 50, 1, 3), 105, 658), 
				new Square("Just Visiting", 0, 31, 658),
				new Square(new Property("St. Charles Place", 140, 10, 100, 100, 2,3), 31, 569), 
				new Square(new Property("Electric Company", 150, 4, 0, 0, 9,2), 31, 511), 
				new Square(new Property("States Avenue", 140, 10, 100, 100, 2,3), 31, 453), 
				new Square(new Property("Virginia Avenue", 160, 12, 100, 100, 2,3), 31, 395), 
				new Square(new Property("Pennsylvania Railroad", 200, 25,  0, 0, 8,4),31, 337),
				new Square(new Property("St. James Place", 180, 14, 100, 100, 3,3),31, 279), 
				new Square("Community Chest", 0, 31, 221), 
				new Square(new Property("Tennesse Avenue", 180, 14, 100, 100, 3, 3), 31, 163), 
				new Square(new Property("New York Avenue", 200, 16, 100, 100, 3, 3), 31, 105), 
				new Square("Free Parking", 0, 31, 31),
				new Square(new Property("Kentucky Avenue", 220, 18, 150, 150, 4, 3), 105, 31), 
				new Square("Chance", 0, 163, 31), 
				new Square(new Property("Indiana Avenue", 220, 18, 150, 150, 4, 3),221, 31), 
				new Square(new Property("Illinois Avenue", 240, 20, 150, 150, 4, 3),279, 31), 
				new Square(new Property("B & O Railroad", 200, 25, 0, 0, 8, 4),337, 31),
				new Square(new Property("Atlantic Avenue", 260, 22, 150, 150, 5, 3),395, 31), 
				new Square(new Property("Ventnor Avenue", 260, 22, 150, 150, 5, 3), 453, 31), 
				new Square(new Property("Water Works", 150, 4, 0, 0, 9, 2), 511, 31), 
				new Square(new Property("Marvin Gardens", 280, 24, 150, 150, 5, 3), 569, 31), 
				new Square("Go To Jail", 0, 658,31),
				new Square(new Property("Pacific Avenue", 300, 28, 200, 200, 6, 3), 658, 105), 
				new Square(new Property("North Carolina Avenue", 300, 26, 200, 200, 6, 3), 658, 163), 
				new Square("Community Chest", 0, 658, 221), 
				new Square(new Property("Pennsylvania Avenue", 320, 28, 200, 200, 6, 3), 658, 279), 
				new Square(new Property("Short Line Railroad", 200, 25, 0, 0, 8, 4),658, 337), 
				new Square("Chance", 0, 658, 395), 
				new Square(new Property("Park Place", 350, 35, 200, 200, 7, 2), 658, 453), 
				new Square("Luxury Tax", -100, 658, 511), 
				new Square(new Property("Boardwalk", 400, 50, 200, 200, 7, 2), 658, 569)
		};

	private Player[] players;

	private Player turn;
	private int whoseturn;
	private int playerLoc; //the current Player's location on the board
	private int xCoord, yCoord;
	private double paidValue;
	private boolean compPlayer;
	
	public GameLogic(Player[] p) {
		whoseturn=0;
		players=p;
		paidValue=0;
	}
	
	//method to determine if the current Player is the computer
	public boolean isComputer() {
		if (turn.getOrder() == 0 && Prompt.isCompPlayer()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//method to set the compPlayer
	public void setCompPlayer(boolean x) {
		compPlayer = x;
	}
		
	//method to determine if there is a compPlayer
	public boolean isCompPlayer() {
		return compPlayer;
	}
	
	//method to determine if the Player has been in the jail for 3 turns
	public boolean clearToLeave() {
		if (turn.getAttempts() < 2) {
			turn.updateAttempts();
			return false;
		}
		else {
			return true;
		}		
	}
	
	//method to determine if the Player has sufficient funds for a Property
	public boolean hasFunds() {
		if (turn.getBalance() < squares[playerLoc].getProperty().getPrice()) {
			return false;
		}
		else {
			return true;
		}
	}

	public void nextTurn() {
//		turn=players[whoseturn];
//		whoseturn = (whoseturn + 1)%(players.length);	
		
		turn=players[whoseturn];
			whoseturn++;
			if(whoseturn>3||players[whoseturn]==null) {
	 			whoseturn=0;
	 		}
	}
	public boolean buyproperty(){
		//get the current Property, buy it, make the button invisible, change the text
		Property toBuy = squares[playerLoc].getProperty(); 
		return turn.buyDeed(toBuy); 

	}
	public boolean isUtilorRail(Property p) {
		if(p.getSetNum()==8||p.getSetNum()==9) {
			return true;
		}
		else {
			return false; 
		}
	}
	public boolean isFullSet(Player player, Property myproperty) {
		if (myproperty != null)
		{
			int numInSet=myproperty.getNumInSet();
			int count=player.getProperties().get(myproperty.getSetNum()).size();
			if(isUtilorRail(myproperty)) {
				return false;
			}
			if(count==numInSet) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
		
//		int numInSet=myproperty.getNumInSet();
//		int count=player.getProperties().get(myproperty.getSetNum()).size();
//		if(isUtilorRail(myproperty)) {
//			return false;
//		}
//		if(count==numInSet) {
//			return true;
//		}
//		else {
//			return false;
//		}
	}
 
	public boolean passedGo(int sum) {
		if((turn.getLocation()+sum)>=40) {
			return true;
		}
		else {
			return false;
		}
	}

	public void moveplayer(int sum) {
		playerLoc= (sum+turn.getLocation())%40;
		turn.setLocation(playerLoc);
		xCoord=squares[playerLoc].getX(); //CHANGED FROM i
		yCoord=squares[playerLoc].getY(); //CHANGED FROM i
	}
	public int getX() {
		return xCoord;
	}
	public int getY() {
		return yCoord;
	}
	public Player getTurn() {
		return turn;
	}

	public int getTurnIndex() {
		return whoseturn;
	}

	public boolean isValidProperty() {
		if(squares[playerLoc].getProperty()!=null && !squares[playerLoc].getProperty().getIsOwned()) {
			return true;
		}
		else {
			return false;
		}
	}
	
	//method to determine if the current Player is also the owner of the current Property
	public boolean isOwner() {
		if (turn == squares[playerLoc].getProperty().getOwner()) {
			return true;
		}
		else {
			return false;
		}
			
	}

	public int handleSquare(int sum) {
		Square current=squares[playerLoc];
		if(playerLoc==4||playerLoc==38) {
			handleTax();
			return 0;
		}
		else if (playerLoc==30) {
			handleJail();
			return 1;
		}
		else if(isValidProperty()) {
			return 2;
		}
		else if(current.getProperty()!=null) {
			if(playerLoc==12||playerLoc==28) {
				handleUtil(sum);
			}
			else if(current.getProperty().getNumInSet()==4) {
				handleRail();
			}
			else{
				handleProp();
			}
			
			//check if the current Player is also the owner
			if (isOwner())
			{
				//System.out.println("Player has landed on their own property.");
				return 4;
			}
			else //can only return 3 i.e. an owned Property, if the current Player is not the owner 
			{
				return 3;
			}
		}
		else {
			return 4;
		}
	}

	public void handleProp() {
		//the current Player is also the owner of the current Property
		if (isOwner())
		{
			//System.out.println("Player has landed on their own property.");
		}
		
		else
		{
			//handle the player owning the entire set; rent is doubled
			if (isFullSet(squares[playerLoc].getProperty().getOwner(), squares[playerLoc].getProperty()))
			{
				paidValue = squares[playerLoc].getProperty().getRent() * 2;
				turn.updateBalance(-paidValue);
				squares[playerLoc].getProperty().getOwner().updateBalance(paidValue);
			}
			
			else //handle the player not owning the entire set	
			{		
				paidValue=squares[playerLoc].getProperty().getRent();
				turn.updateBalance(-paidValue);
				squares[playerLoc].getProperty().getOwner().updateBalance(paidValue);
			}
		}
	}


	public void handleRail(){
		Player owner=squares[playerLoc].getProperty().getOwner();
		int count=owner.getProperties().get(squares[playerLoc].getProperty().getSetNum()).size();
		switch (count) {
		case 1: paidValue=25;
		break;
		case 2: paidValue=50;
		break;
		case 3: paidValue=100;
		break;
		case 4: paidValue=200;
		break;
		}
		
		//the current Player is also the owner of the current Property
		if (isOwner())
		{
			System.out.println("Player has landed on their own property.");
		}
				
		else
		{
			turn.updateBalance(-paidValue);
			owner.updateBalance(paidValue);
		}
	}

	public void handleUtil(int sum) {
		Player owner=squares[playerLoc].getProperty().getOwner(); 
		if(isFullSet(squares[playerLoc].getProperty().getOwner(), squares[playerLoc].getProperty())) {
			paidValue=sum*10;
		}
		else {
			paidValue=sum*4;
		}
		
		//the current Player is also the owner of the current Property
		if (isOwner())
		{
			System.out.println("Player has landed on their own property.");
		}
				
		else
		{
			turn.updateBalance(-paidValue);
			owner.updateBalance(paidValue);
		}
	}
	public void handleJail() {
		turn.setJailed(true);
		turn.setLocation(10);
		playerLoc=10;
		xCoord=squares[playerLoc].getX(); //CHANGED FROM i
		yCoord=squares[playerLoc].getY(); //CHANGED FROM i
	}
	public void handleTax() {
		switch(playerLoc) {
		case 4:turn.updateBalance((-200));
		paidValue=200;
		break;
		default:turn.updateBalance((-100));
		paidValue=100;
		break;
		}
	}
	public Square getCurrentSquare() {
		return squares[playerLoc];
	}

	public double getPaidValue() {
		return paidValue;
	}
}

