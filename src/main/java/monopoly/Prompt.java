package monopoly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Font;

public class Prompt extends JDialog {

	private static boolean isCompPlayer; //variable to determine if there the computer is playing
	private JTextField textNumPlayers;
	private JButton buttonBlue = new JButton("Blue"); 
	private JButton buttonRed = new JButton("Red");
	private JButton buttonGreen = new JButton("Green");
	private JButton buttonYellow = new JButton("Yellow");
	JButton btnYes;
	private JButton btnNo; 
	
	JTextArea txtrColorPrompt;
	JTextArea txtrPlayerPrompt;
	private int numPlayers, currentlySelectedPlayers;
	private JTextArea txtrThankYouPlayer;
	private GameLogic game;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the dialog.
	 */
	public Prompt(Player[] players) {
		game = new GameLogic(players);
		setBounds(100, 100, 450, 450);

		JPanel contentpanel = new JPanel();
		contentpanel.setBackground(new Color(0, 128, 128));
		getContentPane().add(contentpanel, BorderLayout.CENTER);
		contentpanel.setLayout(null);

		/*
		 * TEXT PROMPT FOR THE NUMBER OF PLAYERS
		 */
		textNumPlayers = new JTextField();
		textNumPlayers.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		textNumPlayers.setBackground(new Color(255, 255, 255));
		textNumPlayers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//save the value the user entered
				String value = textNumPlayers.getText();
				numPlayers = Integer.valueOf(value);
				
				//there is a computer playing
				if (isCompPlayer())
				{
					if(numPlayers > 1 && numPlayers < 5) {
						
						//make the previous window invisible
						txtrPlayerPrompt.setVisible(false);
						textNumPlayers.setVisible(false);
						txtrColorPrompt.setVisible(true);
					
						currentlySelectedPlayers=0;
						buttonBlue.setVisible(true); 
						buttonRed.setVisible(true);
						buttonGreen.setVisible(true);
						buttonYellow.setVisible(true);
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e1) {
						}
						
						buttonBlue.doClick(500); //click the blue button
						}
					
						else {
							txtrPlayerPrompt.setText("Invalid choice! Please pick a value, BETWEEN 2 AND 4, and then press ENTER :");
						}
				}
				else
				{
					if(1<numPlayers&&numPlayers<5) {
				
					//make the previous window invisible
					txtrPlayerPrompt.setVisible(false);
					textNumPlayers.setVisible(false);
					txtrColorPrompt.setVisible(true);
				
					currentlySelectedPlayers=0;
					buttonBlue.setVisible(true);
					buttonRed.setVisible(true);
					buttonGreen.setVisible(true);
					buttonYellow.setVisible(true);
					}
				
					else {
						txtrPlayerPrompt.setText("Invalid choice! Please pick a value, BETWEEN 2 AND 4, and then press ENTER :");
					}
				}
			}
		});
		textNumPlayers.setBounds(301, 47, 83, 26);
		contentpanel.add(textNumPlayers);
		textNumPlayers.setColumns(10);
		textNumPlayers.setVisible(false); //invisible until computer option has been filled


		txtrPlayerPrompt = new JTextArea();
		txtrPlayerPrompt.setWrapStyleWord(true);
		txtrPlayerPrompt.setBackground(Color.WHITE);
		txtrPlayerPrompt.setEditable(false);
		txtrPlayerPrompt.setLineWrap(true);
		txtrPlayerPrompt.setText("Please pick a value, BETWEEN 2 AND 4, to represent the number of players, "
				+ "and then press ENTER : \n*IF APPLICABLE, include the computer player in your value*");
		txtrPlayerPrompt.setBounds(6, 6, 283, 80);
		contentpanel.add(txtrPlayerPrompt);
		txtrPlayerPrompt.setVisible(false); //invisible until computer option has been filled

		txtrColorPrompt = new JTextArea(); 
		txtrColorPrompt.setWrapStyleWord(true);
		txtrColorPrompt.setEditable(false);
		txtrColorPrompt.setLineWrap(true);
		txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 1 : ");
		txtrColorPrompt.setBounds(29, 98, 247, 50);
		contentpanel.add(txtrColorPrompt);
		txtrColorPrompt.setVisible(false); //invisible until number of players has been selected

		buttonBlue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players[currentlySelectedPlayers]=new Player(Color.decode("#2E5DE5"), currentlySelectedPlayers);
				buttonBlue.setVisible(false);
				currentlySelectedPlayers++;
				if(currentlySelectedPlayers==(numPlayers)) {
					buttonRed.setVisible(false);
					buttonGreen.setVisible(false);
					buttonYellow.setVisible(false);
					txtrThankYouPlayer.setVisible(true);



				}
				else {
					switch(currentlySelectedPlayers) {
					case 1: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 2 : ");
					break;
					case 2: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 3 : ");
					break;
					case 3: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 4 : ");
					break;

					}
				}
			}
		});

		buttonRed.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players[currentlySelectedPlayers]=new Player(Color.decode("#E53F2C"), currentlySelectedPlayers);
				buttonRed.setVisible(false);
				currentlySelectedPlayers++;
				if(currentlySelectedPlayers==(numPlayers)) {
					buttonBlue.setVisible(false);
					buttonGreen.setVisible(false);
					buttonYellow.setVisible(false);
					txtrThankYouPlayer.setVisible(true);

				}
				else {
					switch(currentlySelectedPlayers) {
					case 1: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 2 : ");
					break;
					case 2: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 3 : ");
					break;
					case 3: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 4 : ");
					break;

					}
				}
			}
		});

		buttonGreen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players[currentlySelectedPlayers]=new Player(Color.decode("#379C24"), currentlySelectedPlayers);
				buttonGreen.setVisible(false);
				currentlySelectedPlayers++;
				if(currentlySelectedPlayers==(numPlayers)) {
					buttonRed.setVisible(false);
					buttonBlue.setVisible(false);
					buttonYellow.setVisible(false);
					txtrThankYouPlayer.setVisible(true);


				}
				else {
					switch(currentlySelectedPlayers) {
					case 1: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 2 : ");
					break;
					case 2: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 3 : ");
					break;
					case 3: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 4 : ");
					break;

					}
				}
			}
		});

		buttonYellow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				players[currentlySelectedPlayers]=new Player(Color.decode("#E5E212"), currentlySelectedPlayers);
				buttonYellow.setVisible(false);
				currentlySelectedPlayers++;
				if(currentlySelectedPlayers==(numPlayers)) {
					buttonRed.setVisible(false);
					buttonGreen.setVisible(false);
					buttonBlue.setVisible(false);
					txtrThankYouPlayer.setVisible(true);

				}
				else {
					switch(currentlySelectedPlayers) {
					case 1: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 2 : ");
					break;
					case 2: txtrColorPrompt.setText("Choose a color, from the selection on the right, for Player 3 : ");
					break;

					}
				}
			}
		});


		txtrThankYouPlayer = new JTextArea();
		txtrThankYouPlayer.setWrapStyleWord(true);
		txtrThankYouPlayer.setEditable(false);
		txtrThankYouPlayer.setVisible(false);
		txtrThankYouPlayer.setLineWrap(true);

		txtrThankYouPlayer.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		txtrThankYouPlayer.setText("Thank You. Anyone can now press the 'Start Game' button to begin. "
				+ "Have fun!\n(you can close this window)");
		txtrThankYouPlayer.setBounds(18, 257, 366, 163);
		contentpanel.add(txtrThankYouPlayer);	

		buttonBlue.setBounds(330, 125, 100, 30);
		buttonRed.setBounds(330, 160, 100, 30);
		buttonGreen.setBounds(330, 198, 100, 30);
		buttonYellow.setBounds(330, 236, 100, 30);
		contentpanel.add(buttonBlue);
		contentpanel.add(buttonRed);
		contentpanel.add(buttonGreen);
		contentpanel.add(buttonYellow);
		buttonBlue.setVisible(false);
		buttonRed.setVisible(false);
		buttonGreen.setVisible(false);
		buttonYellow.setVisible(false);
		
		JTextArea txtrCompPrompt = new JTextArea();
		txtrCompPrompt.setEditable(false);
		txtrCompPrompt.setLineWrap(true);
		txtrCompPrompt.setWrapStyleWord(true);
		txtrCompPrompt.setText("Would you like to play with a computer player? \nTHEY WILL BE P1 AND BLUE");
		txtrCompPrompt.setBounds(16, 174, 302, 30);
		contentpanel.add(txtrCompPrompt);
		
		/*
		 * CHOOSING A COMPUTER PLAYER
		 */
		btnYes = new JButton("YES");
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setCompPlayer(true);
				System.out.println("computer player has been chosen");
				
				//make the necessary prompts visible
				txtrPlayerPrompt.setVisible(true);
				textNumPlayers.setVisible(true);
				txtrCompPrompt.setVisible(false);
				btnYes.setVisible(false);
				getBtnNo().setVisible(false);
				txtrPlayerPrompt.setVisible(true);
				textNumPlayers.setVisible(true);
			}
		});
		btnYes.setBounds(59, 216, 75, 26);
		contentpanel.add(btnYes);
		
		/*
		 * DECLINING THE COMPUTER PLAYER
		 */
		setBtnNo(new JButton("NO"));
		getBtnNo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				setCompPlayer(false);
				System.out.println("computer player has been declined");
				
				//make the necessary prompts visible 
				txtrPlayerPrompt.setVisible(true);
				textNumPlayers.setVisible(true);
				txtrCompPrompt.setVisible(false);
				btnYes.setVisible(false);
				getBtnNo().setVisible(false);
				txtrPlayerPrompt.setVisible(true);
				textNumPlayers.setVisible(true);
			}
		});
		getBtnNo().setBounds(146, 216, 83, 29);
		contentpanel.add(getBtnNo());
	}

	public JButton getButtonBlue() {
		return buttonBlue;
	}
	
	public JButton getButtonRed() {
		return buttonRed;
	}
	public JButton getButtonGreen() {
		return buttonGreen;
	}
	public JButton getButtonYellow() {
		return buttonYellow;
	}
	
	public JTextField gettextNumPlayers() {
		return textNumPlayers;
	}

	public JButton getBtnNo() {
		return btnNo;
	}

	public void setBtnNo(JButton btnNo) {
		this.btnNo = btnNo;
	}

	public static boolean isCompPlayer() {
		return isCompPlayer;
	}

	public static void setCompPlayer(boolean isCompPlayer) {
		Prompt.isCompPlayer = isCompPlayer;
	}	
}
