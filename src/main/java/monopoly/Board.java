package monopoly;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JTextArea;

public class Board{
	Timer countdown; 
	private long time_limit = 3600000; //the game's time limit
	//one hour in milliseconds = 3600000
	//one minute in milliseconds = 60000;
	//5 seconds in milliseconds = 5000;
	private GameLogic game;
	//
	private JButton btnBeginTurn;
	private JButton btnEndTurn;
	private JButton btnPurchaseHouse;
	private JButton btnPurchaseHotel;
	private JButton btnRollDice;

	private JLabel players1cash, players2cash, players3cash, players4cash;
	private Panel player1, player2, player3, player4;

	private Player[] players;
	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		


		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Board window = new Board();
				window.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Board() {
		initializeboard();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeboard() {		
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Panel buttonbox = new Panel();
		buttonbox.setBounds(748, 411, 420, 314);
		buttonbox.setBackground(Color.WHITE);
		frame.getContentPane().add(buttonbox);
		buttonbox.setLayout(null);

		player1 = new Panel();
		player1.setBounds(648, 658, 30, 30);
		frame.getContentPane().add(player1);
		player1.setVisible(false);


		player2 = new Panel();
		player2.setBounds(653, 658, 30, 30);
		frame.getContentPane().add(player2);
		player2.setVisible(false);


		player3 = new Panel();
		player3.setBounds(658, 658, 30, 30);
		frame.getContentPane().add(player3);
		player3.setVisible(false);


		player4 = new Panel();
		player4.setBounds(663, 658, 30, 30);
		frame.getContentPane().add(player4);
		player4.setVisible(false);

		Panel playerturnbox = new Panel();
		playerturnbox.setBounds(764, 297, 388, 94);
		playerturnbox.setBackground(new Color(255, 102, 0));
		frame.getContentPane().add(playerturnbox);
		playerturnbox.setVisible(false); //invisible until game is started

		Panel playermoneybox = new Panel();
		playermoneybox.setBounds(764, 22, 168, 270);
		frame.getContentPane().add(playermoneybox);
		playermoneybox.setLayout(null);
		playermoneybox.setVisible(false); //invisible until game is started
		
		/*
		 * pop-up text-area to signify it is the computer's turn
		 */
		JTextArea txtrCompPopUp = new JTextArea();
		txtrCompPopUp.setLineWrap(true);
		txtrCompPopUp.setWrapStyleWord(true);
		txtrCompPopUp.setEditable(false);
		txtrCompPopUp.setEnabled(false);
		txtrCompPopUp.setText("THE COMPUTER IS PLAYING...");
		txtrCompPopUp.setBounds(254, 157, 197, 16);
		frame.getContentPane().add(txtrCompPopUp);
		txtrCompPopUp.setVisible(false); //invisible unless the computer is playing

		JTextArea txtrGOPopUp = new JTextArea();
		txtrGOPopUp.setEnabled(false);
		txtrGOPopUp.setEditable(false);
		txtrGOPopUp.setWrapStyleWord(true);
		txtrGOPopUp.setLineWrap(true);
		txtrGOPopUp.setText("You have gone around the board!\nYour balance will be updated 200 dollars.");
		txtrGOPopUp.setBounds(254, 201, 197, 72);
		frame.getContentPane().add(txtrGOPopUp);
		txtrGOPopUp.setVisible(false); //invisible until GO has been passed

		JTextArea txtrPopUp = new JTextArea();
		txtrPopUp.setEnabled(false);
		txtrPopUp.setWrapStyleWord(true);
		txtrPopUp.setLineWrap(true);
		txtrPopUp.setEditable(false);
		txtrPopUp.setText("You have landed on unowned property. \nSelect 'Purchase Property' to the right if you want to buy it!");
		txtrPopUp.setBounds(252, 285, 199, 158);
		frame.getContentPane().add(txtrPopUp);
		txtrPopUp.setVisible(false); //invisible until Square has been landed on

		Panel timerbox = new Panel();
		timerbox.setBounds(938, 21, 214, 106);
		timerbox.setBackground(new Color(0, 255, 204));
		frame.getContentPane().add(timerbox);
		timerbox.setLayout(null);

		Panel dicebox = new Panel();
		dicebox.setBounds(938, 133, 214, 158);
		dicebox.setBackground(Color.WHITE);
		frame.getContentPane().add(dicebox);
		dicebox.setLayout(null);

		JLabel lblDice1 = new JLabel("");
		lblDice1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice1.setIcon(new ImageIcon("src/main/resources/img/0dice.jpg"));
		lblDice1.setBackground(Color.WHITE);
		lblDice1.setOpaque(true);
		lblDice1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice1.setBounds(6, 6, 90, 90);
		dicebox.add(lblDice1);
		lblDice1.setVisible(false); //set to be invisible until game is started

		JLabel lblDice2 = new JLabel("");
		lblDice2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDice2.setIcon(new ImageIcon("src/main/resources/img/0dice.jpg"));
		lblDice2.setOpaque(true);
		lblDice2.setBackground(Color.WHITE);
		lblDice2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDice2.setBounds(118, 6, 90, 90);
		dicebox.add(lblDice2);
		lblDice2.setVisible(false); //set to be invisible until game is started

		JLabel playersTurn = new JLabel("___ Player's Turn");
		playersTurn.setHorizontalTextPosition(SwingConstants.CENTER);
		playersTurn.setHorizontalAlignment(SwingConstants.CENTER);
		playersTurn.setFont(new Font("Lucida Grande", Font.PLAIN, 24));
		playersTurn.setBackground(Color.GREEN);
		playersTurn.setBounds(6, 101, 100, 51);
		playerturnbox.add(playersTurn);

		playersTurn.setVisible(false); //set to be invisible until game is started

		players1cash = new JLabel("Player 1:");
		players1cash.setHorizontalTextPosition(SwingConstants.CENTER);
		players1cash.setHorizontalAlignment(SwingConstants.LEFT);
		players1cash.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		players1cash.setOpaque(true);
		players1cash.setBounds(0, 0, 168, 68);
		players1cash.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		playermoneybox.add(players1cash);

		players1cash.setVisible(false);

		players2cash = new JLabel("Player 2:");
		players2cash.setHorizontalTextPosition(SwingConstants.CENTER);
		players2cash.setHorizontalAlignment(SwingConstants.LEFT);
		players2cash.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		players2cash.setOpaque(true);
		players2cash.setBounds(0, 68, 168, 67);
		players2cash.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		playermoneybox.add(players2cash);

		players2cash.setVisible(false);

		players3cash = new JLabel("Player 3:");
		players3cash.setHorizontalTextPosition(SwingConstants.CENTER);
		players3cash.setHorizontalAlignment(SwingConstants.LEFT);
		players3cash.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		players3cash.setOpaque(true);
		players3cash.setBounds(0, 135, 168, 67);
		players3cash.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		playermoneybox.add(players3cash);

		players3cash.setVisible(false);

		players4cash = new JLabel("PLayer 4:");
		players4cash.setHorizontalTextPosition(SwingConstants.CENTER);
		players4cash.setHorizontalAlignment(SwingConstants.LEFT);
		players4cash.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		players4cash.setOpaque(true);
		players4cash.setBounds(0, 202, 168, 68);
		players4cash.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
		playermoneybox.add(players4cash);

		players4cash.setVisible(false);

		/*
		 * PURCHASE HOTEL BUTTON
		 */
		JButton btnPurchaseHotel = new JButton("Purchase Hotel");
		btnPurchaseHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			}
		});
		btnPurchaseHotel.setBounds(6, 195, 127, 51);
		buttonbox.add(btnPurchaseHotel);
		btnPurchaseHotel.setVisible(false); //make the button invisible until an 4 houses are on each Property
		buttonbox.setVisible(false);

		/*
		 * PURCHASE HOUSE BUTTON
		 */
		JButton btnPurchaseHouse = new JButton("Purchase House");
		btnPurchaseHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//get the current Property, buy a house for it, make the button invisible, change the text
				Property toUpgrade = game.getCurrentSquare().getProperty();
				game.getTurn().buyHouse(toUpgrade);
				btnPurchaseHouse.setVisible(false);
				txtrPopUp.setText("You now have a house on " + game.getCurrentSquare().getName() + ".");
				//check if they have 4 houses on the *current property
				if (game.getCurrentSquare().getProperty().getNumHouses() == 4) {
					//inform them they can buy a hotel
					txtrPopUp.setText("You have 4 houses on " + game.getCurrentSquare().getName() + ", you "
							+ "can now buy a hotel");
					btnPurchaseHotel.setVisible(false);
				}
				
				updateCash(game.getTurn().getOrder());
				switch(game.getTurn().getOrder()) {
				case 0:players1cash.setText("Player 1:	$"+ game.getTurn().getBalance());
				break;
				case 1:players2cash.setText("Player 2:	$"+ game.getTurn().getBalance());
				break;
				case 2:players3cash.setText("Player 3:	$"+ game.getTurn().getBalance());
				break;
				case 3:players4cash.setText("Player 4:	$"+ game.getTurn().getBalance());
				break;
				}
			}
		});
		btnPurchaseHouse.setBounds(6, 132, 127, 51);
		buttonbox.add(btnPurchaseHouse);
		btnPurchaseHouse.setVisible(false); //make the button invisible until an entire set is owned

		/*
		 * PURCHASE PROPERTY BUTTON
		 */
		JButton btnPurchaseProperty = new JButton("Purchase Property");
		btnPurchaseProperty.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				game.buyproperty();
				btnPurchaseProperty.setVisible(false);
				btnPurchaseProperty.setEnabled(false);
				
				//check if the current Player is the computer i.e. Player 1
				if (game.isComputer()) {
					txtrPopUp.setText("The computer now owns " + game.getCurrentSquare().getName() + "!");
					System.out.println("The computer now owns " + game.getCurrentSquare().getName() + "!");
					//wait for 1 second and click End Turn
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//btnEndTurn.doClick(); //clicking the button with a 1 second delay
				}
				else {
					txtrPopUp.setText("You now own " + game.getCurrentSquare().getName() + "!");
				}
				//txtrPopUp.setText("You now own " + game.getCurrentSquare().getName() + "!");
				if(game.isFullSet(game.getTurn(), game.getCurrentSquare().getProperty())) {
					txtrPopUp.setText("You now own " + game.getCurrentSquare().getName() + ".\nYou own all properties "
							+ "in this set! Select 'Purchase House' to the right if you want a house on one of your properties.");
					btnPurchaseHouse.setVisible(true);
				}
				switch(game.getTurn().getOrder()) {
				case 0:players1cash.setText("Player 1:	$"+ game.getTurn().getBalance());
				break;
				case 1:players2cash.setText("Player 2:	$"+ game.getTurn().getBalance());
				break;
				case 2:players3cash.setText("Player 3:	$"+ game.getTurn().getBalance());
				break;
				case 3:players4cash.setText("Player 4:	$"+ game.getTurn().getBalance());
				break;
				}
			}
		});

		btnPurchaseProperty.setBounds(6, 69, 127, 51);
		buttonbox.add(btnPurchaseProperty);
		btnPurchaseProperty.setVisible(false); //make the button invisible until an un-owned Property is landed on


		/*
		 * DICE IMPLEMENTATION
		 */
		JLabel lblTotal = new JLabel("Total = 0");
		lblTotal.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setFont(new Font("Lucida Grande", Font.PLAIN, 19));
		lblTotal.setBackground(Color.GREEN);
		lblTotal.setBounds(6, 101, 100, 51);
		dicebox.add(lblTotal);
		lblTotal.setVisible(false); //set to be invisible until game is started

		/*
		 * ROLL DICE BUTTON
		 */
		btnRollDice = new JButton("Roll Dice");
		btnRollDice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//create a new Dice instance and roll both dice
				Dice diceRoll = new Dice();
				diceRoll.rollDice();
				int x = diceRoll.getDice1(); //first die
				int y = diceRoll.getDice2(); //second die

				//display the total
				int sum = x + y;
				lblTotal.setText("Total = " + sum);


				//logic to set the first dice picture accordingly
				switch (x)
				{
				//case 1: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/1dice.jpg")));
				case 1: lblDice1.setIcon(new ImageIcon("src/main/resources/img/1dice.jpg"));
				break;
				//case 2: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/2dice.jpg")));
				case 2: lblDice1.setIcon(new ImageIcon("src/main/resources/img/2dice.jpg"));
				break;
				//case 3: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/3dice.jpg")));
				case 3: lblDice1.setIcon(new ImageIcon("src/main/resources/img/3dice.jpg"));
				break;
				//case 4: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/4dice.jpg")));
				case 4: lblDice1.setIcon(new ImageIcon("src/main/resources/img/4dice.jpg"));
				break;
				//case 5: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/5dice.jpg")));
				case 5: lblDice1.setIcon(new ImageIcon("src/main/resources/img/5dice.jpg"));
				break;
				//case 6: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/6dice.jpg")));
				case 6: lblDice1.setIcon(new ImageIcon("src/main/resources/img/6dice.jpg"));
				break;
				//default: lblDice1.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/0dice.jpg")));
				default: lblDice1.setIcon(new ImageIcon("src/main/resources/img/0dice.jpg"));
				break;
				}

				//logic to set the second dice picture accordingly
				switch (y)
				{
				//case 1: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/1dice.jpg")));
				case 1: lblDice2.setIcon(new ImageIcon("src/main/resources/img/1dice.jpg"));
				break;
				//case 2: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/2dice.jpg")));
				case 2: lblDice2.setIcon(new ImageIcon("src/main/resources/img/2dice.jpg"));
				break;
				//case 3: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/3dice.jpg")));
				case 3: lblDice2.setIcon(new ImageIcon("src/main/resources/img/3dice.jpg"));
				break;
				//case 4: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/4dice.jpg")));
				case 4: lblDice2.setIcon(new ImageIcon("src/main/resources/img/4dice.jpg"));
				break;
				//case 5: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/5dice.jpg")));
				case 5: lblDice2.setIcon(new ImageIcon("src/main/resources/img/5dice.jpg"));
				break;
				//case 6: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/6dice.jpg")));
				case 6: lblDice2.setIcon(new ImageIcon("src/main/resources/img/6dice.jpg"));
				break;
				//default: lblDice2.setIcon(new ImageIcon(getClass().getResource("/src/main/resources/img/0dice.jpg")));
				default: lblDice2.setIcon(new ImageIcon("src/main/resources/img/6dice.jpg"));
				break;
				}

				//move the Player's token 'sum' amount of Squares
				if(game.getTurn().isInJail()) {
					if(x==y) {
						game.getTurn().setJailed(false); //PLAYER IS OUT OF JAIL IF THEY ROLL DOUBLES
						game.getTurn().setAttempts(0);
						game.moveplayer(sum);
						int xCoord=game.getX();
						int yCoord=game.getY();
						updatePosition(game.getTurn().getOrder(), xCoord, yCoord);

						switch(game.handleSquare(sum)) {
						case 0: //taxed Square
							
							//check if the current Player is the computer i.e. Player 1
							if (game.isComputer()) {
								//inform the Player they have landed on a taxed Square and call updateBalance()
								System.out.println("The computer landed on " + game.getCurrentSquare().getName() + ", which requires a tax! "
										+ " \nTheir balance will be deducted " + game.getPaidValue() + " dollars.");
								updateCash(game.getTurn().getOrder());
								//txtrPopUp.setVisible(true);
								//wait for 1 second and click End Turn
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								//btnEndTurn.doClick();
							}
							else {
								//inform the Player they have landed on a taxed Square and call updateBalance()
								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which requires a tax! "
										+ " \nYour balance will be deducted " + game.getPaidValue() + " dollars.");
								updateCash(game.getTurn().getOrder());
								txtrPopUp.setVisible(true);
							}
//							//inform the Player they have landed on a taxed Square and call updateBalance()
//							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which requires a tax! "
//									+ " \nYour balance will be deducted " + game.getPaidValue() + " dollars.");
//							updateCash(game.getTurn().getOrder());
//							txtrPopUp.setVisible(true);
							break;
							
						case 1: //Jail
							//need to check if the Square is Go To Jail
							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + "You do not pass go, Go straight to jail.");
							txtrPopUp.setVisible(true);
							int xCoordj=game.getX();
							int yCoordj=game.getY();
							updatePosition(game.getTurn().getOrder(), xCoordj, yCoordj);
							break;
							
						case 2:	//Un-owned Property
							//check if the Player has enough money
							if (!game.hasFunds())
							{
								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", but you "
										+ "have insufficient funds...");
								txtrPopUp.setVisible(true);
							}
							else
							{
								//check if the current Player is the computer i.e. Player 1
								if (game.isComputer()) {
									//'click' the purchase property button
									//txtrPopUp.setVisible(true);
									btnPurchaseProperty.setVisible(true);
									btnPurchaseProperty.setEnabled(true);
									btnPurchaseProperty.doClick(500); //clicking the button with a 1 second delay
								}
								else {
									//make text field & button visible to inform the Player they landed on a Property for sale
									txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is unowned. Select 'Purchase Property' "
											+ "to the right if you want to buy it!\nThe purchase price is $" + game.getCurrentSquare().getProperty().getPrice());
									txtrPopUp.setVisible(true);
									btnPurchaseProperty.setVisible(true);
									btnPurchaseProperty.setEnabled(true);
								}
//								//make text field & button visible to inform the Player they landed on a Property for sale
//								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is unowned. Select 'Purchase Property' "
//										+ "to the right if you want to buy it!\nThe purchase price is $" + game.getCurrentSquare().getProperty().getPrice());
//								txtrPopUp.setVisible(true);
//								btnPurchaseProperty.setVisible(true);
//								btnPurchaseProperty.setEnabled(true);
							}
							break;
							
						case 3: //Owned Property
							//check if the current Player is the computer i.e. Player 1
							if (game.isComputer()) {
								//inform the Player they have landed on an owned Property and call payNewRent()
								System.out.println("The computer landed on " + game.getCurrentSquare().getName() + ", which is owned! \nTheir balance "
										+ "will be deducted " + game.getPaidValue() + " dollars.");
								//txtrPopUp.setVisible(true);
								updateCash(game.getTurn().getOrder());
								updateCash(game.getCurrentSquare().getProperty().getOwner().getOrder());
								//wait for 1 second and click End Turn
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								//btnEndTurn.doClick();
							}
							else {
								//inform the Player they have landed on an owned Property and call payNewRent()
								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is owned! \nYour balance "
										+ "will be deducted " + game.getPaidValue() + " dollars.");
								updateCash(game.getTurn().getOrder());
								updateCash(game.getCurrentSquare().getProperty().getOwner().getOrder());
								txtrPopUp.setVisible(true);
							}
							
//							//inform the Player they have landed on an owned Property and call payNewRent()
//							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is owned! \nYour balance "
//									+ "will be deducted " + game.getPaidValue() + " dollars.");
//							updateCash(game.getTurn().getOrder());
//							updateCash(game.getCurrentSquare().getProperty().getOwner().getOrder());
//							txtrPopUp.setVisible(true);
							break;
							
//						case 4: 
//							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName()+". A property you own");
//							break;
						default: 
							//check if the current Player is the computer i.e. Player 1
							if (game.isComputer()) {
								System.out.println("The computer landed on " + game.getCurrentSquare().getName());
								//txtrPopUp.setVisible(true);
								//wait for 1 second and click End Turn
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
								//btnEndTurn.doClick(500);
							}
							else {
								//check if they own the full set
								if (game.isFullSet(game.getTurn(), game.getCurrentSquare().getProperty())) {
									//give them the option to buy a house
									txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", a property"
											+ " you own a full set of! Select 'Purchase House' to the right if you want a house "
											+ "on one of your properties. ");
									btnPurchaseHouse.setVisible(true);
								}
								else {
									txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName());
									txtrPopUp.setVisible(true);
								}
//								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName());
//								txtrPopUp.setVisible(true);
							}
//							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName());
//							txtrPopUp.setVisible(true);
							break;
						}
						//make the button invisible until the next Player's turn
						btnRollDice.setVisible(false);
						btnEndTurn.setVisible(true);
					}

					//doubles were not rolled
					else {
						//need to check if the player has been in jail for two turns already
						if (game.clearToLeave()) {
							txtrPopUp.setText("You did not roll doubles on your last attempt.\nYou are no longer In Jail.");
							txtrPopUp.setVisible(true);
							game.getTurn().setJailed(false);
							game.getTurn().setAttempts(0);
							btnRollDice.setVisible(false);
							btnEndTurn.setVisible(true);
						}
						
						else {
							txtrPopUp.setText("You did not roll doubles. You remain In Jail.\n(Attempt # " + 
									game.getTurn().getAttempts() + " of 3)");
							txtrPopUp.setVisible(true);
							btnRollDice.setVisible(false);
							btnEndTurn.setVisible(true);
						}
					}
				}
				
				//Player is not in Jail
				else {
					if(game.passedGo(sum)) { //CHANGED FROM i
						//the Player is on GO. make the pop-up visible and update the balance
						txtrGOPopUp.setText("You have passed GO!\nYour balance will be updated 200 dollars.");
						txtrGOPopUp.setVisible(true);
						game.getTurn().updateBalance(200);
						updateCash(game.getTurn().getOrder());
					}
					game.moveplayer(sum);
					int xCoord=game.getX();
					int yCoord=game.getY();
					updatePosition(game.getTurn().getOrder(), xCoord, yCoord);

					switch(game.handleSquare(sum)) {
					case 0: //taxed Square
						
						//check if the current Player is the computer i.e. Player 1
						if (game.isComputer()) {
							//inform the Player they have landed on a taxed Square and call updateBalance()
							System.out.println("The computer landed on " + game.getCurrentSquare().getName() + ", which requires a tax! "
									+ " \nTheir balance will be deducted " + game.getPaidValue() + " dollars.");
							updateCash(game.getTurn().getOrder());
							//txtrPopUp.setVisible(true);
							//wait for 1 second and click End Turn
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//btnEndTurn.doClick(); 
						}
						else {
							//inform the Player they have landed on a taxed Square and call updateBalance()
							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which requires a tax! "
									+ " \nYour balance will be deducted " + game.getPaidValue() + " dollars.");
							updateCash(game.getTurn().getOrder());
							txtrPopUp.setVisible(true);
						}
//						//inform the Player they have landed on a taxed Square and call updateBalance()
//						txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which requires a tax! "
//								+ " \nYour balance will be deducted " + game.getPaidValue() + " dollars.");
//						updateCash(game.getTurn().getOrder());
//						txtrPopUp.setVisible(true);
						break;
						
						
						
					case 1: //Jail
						//need to check if the Square is Go To Jail
						txtrPopUp.setText("You have landed on GO TO Jail! You do not pass go, Go straight to jail.");
						txtrPopUp.setVisible(true);
						int xCoordj=game.getX();
						int yCoordj=game.getY();
						updatePosition(game.getTurn().getOrder(), xCoordj, yCoordj);
						break;
						
					case 2: //Un-owned Property			
						//check if the Player has enough money
						if (!game.hasFunds())
						{
							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", but you "
									+ "have insufficient funds...");
							txtrPopUp.setVisible(true);
						}
						else //Player has enough money
						{
							//check if the current Player is the computer i.e. Player 1
							if (game.isComputer()) {
								//'click' the purchase property button
								//txtrPopUp.setVisible(true);
								btnPurchaseProperty.setVisible(true);
								btnPurchaseProperty.setEnabled(true);
								btnPurchaseProperty.doClick(500); //click the button 
							}
							else {
								//make text field & button visible to inform the Player they landed on a Property for sale
								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is unowned. Select 'Purchase Property' "
										+ "to the right if you want to buy it!\nThe purchase price is $" + game.getCurrentSquare().getProperty().getPrice());
								txtrPopUp.setVisible(true);
								btnPurchaseProperty.setVisible(true);
								btnPurchaseProperty.setEnabled(true);
							}
//							//make text field & button visible to inform the Player they landed on a Property for sale
//							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is unowned. Select 'Purchase Property' "
//									+ "to the right if you want to buy it!\nThe purchase price is $" + game.getCurrentSquare().getProperty().getPrice());
//							txtrPopUp.setVisible(true);
//							btnPurchaseProperty.setVisible(true);
//							btnPurchaseProperty.setEnabled(true);
						}
						break;			
						
					case 3: //Owned Property
						
						//check if the current Player is the computer i.e. Player 1
						if (game.isComputer()) {
							//inform the Player they have landed on an owned Property and call payNewRent()
							System.out.println("The computer landed on " + game.getCurrentSquare().getName() + ", which is owned! \nYour balance "
									+ "will be deducted " + game.getPaidValue() + " dollars.");
							updateCash(game.getTurn().getOrder());
							updateCash(game.getCurrentSquare().getProperty().getOwner().getOrder());
							//txtrPopUp.setVisible(true);
							//wait for 1 second and click End Turn
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//btnEndTurn.doClick();
						}
						else {
							//inform the Player they have landed on an owned Property and call payNewRent()
							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is owned! \nYour balance "
									+ "will be deducted " + game.getPaidValue() + " dollars.");
							updateCash(game.getTurn().getOrder());
							updateCash(game.getCurrentSquare().getProperty().getOwner().getOrder());
							txtrPopUp.setVisible(true);
						}
						
//						//inform the Player they have landed on an owned Property and call payNewRent()
//						txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", which is owned! \nYour balance "
//								+ "will be deducted " + game.getPaidValue() + " dollars.");
//						updateCash(game.getTurn().getOrder());
//						updateCash(game.getCurrentSquare().getProperty().getOwner().getOrder());
//						txtrPopUp.setVisible(true);
						break;

//					case 4: txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName()+". A property you own");
//					break;
					default: 		
						//check if the current Player is the computer i.e. Player 1
						if (game.isComputer()) {
							System.out.println("The computer landed on " + game.getCurrentSquare().getName());
							//txtrPopUp.setVisible(true);
							//wait for 1 second and click End Turn
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							//btnEndTurn.doClick();
						}
						else {
							//check if they own the full set
							if (game.isFullSet(game.getTurn(), game.getCurrentSquare().getProperty())) {
								//give them the option to buy a house
								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName() + ", a property"
										+ " you own a full set of! Select 'Purchase House' to the right if you want a house "
										+ "on one of your properties. ");
								btnPurchaseHouse.setVisible(true);
							}
							else {
								txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName());
								txtrPopUp.setVisible(true);
							}
//							txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName());
//							txtrPopUp.setVisible(true);
						}
//						txtrPopUp.setText("You have landed on " + game.getCurrentSquare().getName());
//						txtrPopUp.setVisible(true);
						break;
					}
					//make the button invisible until the next Player's turn
					btnRollDice.setVisible(false);
					btnEndTurn.setVisible(true);
				}
			}});
		btnRollDice.setBounds(108, 101, 100, 51);
		dicebox.add(btnRollDice);
		btnRollDice.setVisible(false); //set to be invisible until game is started

		/*
		 * GAME TIMER IMPLEMENTATION
		 */
		JLabel lblTimer = new JLabel("60 : 00");
		lblTimer.setFont(new Font("Lucida Grande", Font.PLAIN, 33));
		lblTimer.setHorizontalAlignment(SwingConstants.CENTER);
		ActionListener action = new ActionListener()
		{ public void actionPerformed(ActionEvent e)
		{
			SimpleDateFormat sdf = new SimpleDateFormat("mm : ss");					
			time_limit -= 1000; //set the display to be = (currentTime) - (1 second)
			lblTimer.setText(sdf.format(time_limit));

			//check if the countdown has reached zero
			if (time_limit < 0)
			{
				//stop the Timer from listening for events, close the game window and open a new one
				countdown.stop();
				frame.setVisible(false);
				Results endGame = new Results(players);
				endGame.setVisible(true);
			}
		}
		};
		int delay = 1000;
		countdown = new Timer(delay, action); //probably a better way to deal with this Timer instance	
		lblTimer.setBounds(6, 6, 202, 77);
		timerbox.add(lblTimer);

		Canvas canvas_1 = new Canvas();
		canvas_1.setBounds(748, 10, 415, 392);
		canvas_1.setBackground(Color.WHITE);
		frame.getContentPane().add(canvas_1);
		//JLabel Gameboard = new JLabel(new ImageIcon(getClass().getResource("/src/main/resources/img/Boardimg.jpg")));
		Image img = Toolkit.getDefaultToolkit().createImage("src/main/resources/img/Boardimg.jpg");
		JLabel Gameboard = new JLabel(new ImageIcon(img));
		Gameboard.setBounds(10, 10, 715, 715);
		frame.getContentPane().add(Gameboard);

		/*
		 * ENDING THE GAME BUTTON
		 */
		JButton btnEndGame = new JButton("End Game");
		btnEndGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stop the Timer from listening for events, close the game window and open a new one
				countdown.stop();
				frame.setVisible(false);
				Results endGame = new Results(players);
				endGame.setVisible(true);
			}
		});
		btnEndGame.setBounds(109, 71, 99, 29);
		timerbox.add(btnEndGame);
		btnEndGame.setVisible(false); //invisible until turn is started


		JButton btnSetupGame = new JButton("Setup Game");
		JButton btnStartGame = new JButton("Start Game");

		btnSetupGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//open up an additional window to prompt the user for pre-game info
				players=new Player[4];
				Prompt prompt = new Prompt(players);
				prompt.setVisible(true);

				//make the button invisible
				btnSetupGame.setVisible(false);

				//start the countdown and set make necessary elements visible to the user
				lblDice1.setVisible(true);
				lblDice2.setVisible(true);
				lblTotal.setVisible(true);
				buttonbox.setVisible(true); 
				playermoneybox.setVisible(true);
				playerturnbox.setVisible(true);
				btnStartGame.setEnabled(true);
				btnStartGame.setVisible(true);

			}
		});
		btnSetupGame.setBounds(6, 71, 200, 29);
		timerbox.add(btnSetupGame);
		btnStartGame.setBounds(6, 71, 200, 29);
		timerbox.add(btnStartGame);


		/*
		 * STARTING THE GAME BUTTON
		 */
		btnStartGame.setVisible(false);
		btnStartGame.setEnabled(false);
		btnEndGame.setEnabled(false);
		btnStartGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRollDice.setVisible(true);
				btnStartGame.setVisible(false);
				btnStartGame.setEnabled(false);
				game=new GameLogic(players);
				btnEndGame.setVisible(true);
				btnEndGame.setEnabled(true);
				countdown.start();
				int i=0;
				while(i<4&&players[i]!=null) {
					switch(i) {
					case 0:
						player1.setBackground(players[i].getColor());
						System.out.println(players[i].getOrder());
						player1.setVisible(true); 
						game.nextTurn();
						
						//check if there's a computer player
						if (Prompt.isCompPlayer()) {
							players1cash.setText("NPC:	$"+ players[i].getBalance());
							playersTurn.setText("Computer's Turn");							
						}
						else {
							players1cash.setText("Player 1:	$"+ players[i].getBalance());
							playersTurn.setText("Player "+(1+game.getTurn().getOrder())+"'s Turn");
						}
						//players1cash.setText("Player 1:	$"+ players[i].getBalance());
						players1cash.setBackground(players[i].getColor());
						playerturnbox.setBackground(game.getTurn().getColor());
						//playersTurn.setText("Player "+(1+game.getTurn().getOrder())+"'s Turn");
						playersTurn.setVisible(true);
						players1cash.setVisible(true);
						break;
					case 1:
						players2cash.setText("Player 2:	$"+ players[i].getBalance());
						players2cash.setBackground(players[i].getColor());
						player2.setBackground(players[i].getColor());
						player2.setVisible(true);
						players2cash.setVisible(true);
						break;
					case 2:
						players3cash.setText("Player 3:	$"+ players[i].getBalance());
						players3cash.setBackground(players[i].getColor());
						player3.setBackground(players[i].getColor());
						player3.setVisible(true);
						players3cash.setVisible(true);
						break;
					case 3:
						players4cash.setText("Player 4:	$"+ players[i].getBalance());
						players4cash.setBackground(players[i].getColor());
						player4.setBackground(players[i].getColor());
						player4.setVisible(true);
						players4cash.setVisible(true);
						break;
					}
					i++;
				}

				//check if the computer is playing
				if (game.isComputer())
				{
					txtrCompPopUp.setVisible(true);
					btnRollDice.doClick(500); //clicking the button 
				}
			}
		});
		btnStartGame.setBounds(6, 71, 200, 29);
		timerbox.add(btnSetupGame);

		/*
		 * END TURN BUTTON
		 */
		btnEndTurn = new JButton("End Turn");
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//make the Roll Dice button visible and the End Turn button invisible
				btnEndTurn.setVisible(false);		
				btnRollDice.setVisible(true);
				btnPurchaseProperty.setVisible(false);
				//change the current Player to be the next one in line
				game.nextTurn(); 
				playerturnbox.setBackground(game.getTurn().getColor());

				//check if the current Player is the computer i.e. Player 1
				if (game.isComputer()) {
					playersTurn.setText("Computer's Turn");
					txtrCompPopUp.setVisible(true);
					btnEndTurn.setVisible(false);
					btnRollDice.doClick(500); //clicking the button
				}
				else {
					playersTurn.setText("Player "+(1+game.getTurn().getOrder())+"'s Turn");
					txtrCompPopUp.setVisible(false);
					
					btnEndTurn.setVisible(false);		
					btnRollDice.setVisible(true);
				}
				//playersTurn.setText("Player "+(1+game.getTurn().getOrder())+"'s Turn");
				//make the pop-up windows invisible
				txtrPopUp.setVisible(false);
				txtrGOPopUp.setVisible(false);
				btnPurchaseHouse.setVisible(false);
				btnPurchaseHotel.setVisible(false);
			}
		});
		btnEndTurn.setBounds(6, 6, 127, 51);
		buttonbox.add(btnEndTurn);
		btnEndTurn.setVisible(false);
	}

	public void updateCash(int order) {
		switch(order) {
		case 0:
			//check if there is a computer player
			if (Prompt.isCompPlayer()) {
				players1cash.setText("NPC: $"+ players[order].getBalance());
			}
			else {
				players1cash.setText("Player 1:	$"+ players[order].getBalance());
			}
			break;
		case 1:
			players2cash.setText("Player 2:	$"+ players[order].getBalance());
			break;
		case 2:
			players3cash.setText("Player 3:	$"+ players[order].getBalance());
			break;
		case 3:
			players4cash.setText("Player 4:	$"+ players[order].getBalance());
			break;
		}
	}

	public void updatePosition(int order, int x, int y) {
		switch(order) {
		case 0: 
			player1.setBounds((x+5), y+5, 25, 25);
			break;
		case 1: 
			player2.setBounds((x+10), y+5, 25, 25);
			break;
		case 2: 
			player3.setBounds((x+15), y+5, 25, 25);
			break;
		case 3: 
			player4.setBounds((x+20), y+5, 25, 25);
			break;
		}
	}
	
	public JButton getBtnBeginTurn() {
		return btnBeginTurn;
	}

	public JButton getBtnEndTurn() {
		return btnEndTurn;
	}

	public JButton getBtnPurchaseHouse() {
		return btnPurchaseHouse;
	}

	public JButton getBtnPurchaseHotel() {
		return btnPurchaseHotel;
	}

	public JButton getBtnRollDice() {
		return btnRollDice;
	}

	public JButton getBtnSetupGame() {
		return getBtnSetupGame();
	}

	public JButton getBtnStartGame() {
		return getBtnStartGame();
	}
}
