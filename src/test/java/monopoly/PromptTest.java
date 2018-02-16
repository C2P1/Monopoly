package monopoly;

import java.awt.Color;
import junit.framework.TestCase;

public class PromptTest extends TestCase {
	public void testPromptInitialisation() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
	}
	
	public void testBlueButtonPress() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.getButtonBlue().doClick();
	}
	
	public void testGreenButtonPress() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.getButtonGreen().doClick();
	}
	
	public void testRedButtonPress() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.getButtonRed().doClick();
	}
	
	public void testYellowButtonPress() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.getButtonYellow().doClick();
	}
	

	/*
	 * ============================================
	 * Testing the different cases of when a red button press occurs 
	 *  ============================================
	 */
	
	/*
	 * Test red button when 2 players are selected
	 */
	public void testRedButton_2SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonRed().doClick();
	}
	
	/*
	 * Test red button when 3 players are selected
	 */
	public void testRedButton_3SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonGreen().doClick();
		prompt.getButtonRed().doClick();
	}
	
	/*
	 * Test red button when 4 players are selected
	 */
	public void testRedButton_4SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonGreen().doClick();
		prompt.getButtonBlue().doClick();
		prompt.getButtonRed().doClick();
	}
	
	/*
	 * ============================================
	 * Testing the different cases of when a blue button press occurs 
	 *  ============================================
	 */
	
	/*
	 * Test red button when 2 players are selected
	 */
	public void testBlueButton_2SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonBlue().doClick();
	}
	
	/*
	 * Test red button when 3 players are selected
	 */
	public void testBlueButton_3SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonGreen().doClick();
		prompt.getButtonBlue().doClick();
	}
	
	/*
	 * Test red button when 4 players are selected
	 */
	public void testBlueButton_4SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonGreen().doClick();
		prompt.getButtonRed().doClick();
		prompt.getButtonBlue().doClick();
	}
	
	/*
	 * ============================================
	 * Testing the different cases of when a yellow button press occurs 
	 *  ============================================
	 */
	
	/*
	 * Test red button when 2 players are selected
	 */
	public void testYellowButton_2SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonRed().doClick();
		prompt.getButtonYellow().doClick();
	}
	
	/*
	 * Test red button when 3 players are selected
	 */
	public void testYellowButton_3SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonRed().doClick();
		prompt.getButtonGreen().doClick();
		prompt.getButtonYellow().doClick();
	}
	
	/*
	 * Test red button when 4 players are selected
	 */
	public void testYellowButton_4SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonRed().doClick();
		prompt.getButtonGreen().doClick();
		prompt.getButtonBlue().doClick();
		prompt.getButtonYellow().doClick();
	}
	
	/*
	 * ============================================
	 * Testing the different cases of when a green button press occurs 
	 *  ============================================
	 */
	
	/*
	 * Test red button when 2 players are selected
	 */
	public void testGreenButton_2SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonGreen().doClick();
	}
	
	/*
	 * Test red button when 3 players are selected
	 */
	public void testGreenButton_3SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonRed().doClick();
		prompt.getButtonGreen().doClick();
	}
	
	/*
	 * Test red button when 4 players are selected
	 */
	public void testGreenButton_4SelectedPlayers() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player player3 = new Player(Color.red, 3);
		Player player4 = new Player(Color.red, 4);
		Player[] players = {player1, player2, player3, player4};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
		prompt.getButtonRed().doClick();
		prompt.getButtonBlue().doClick();
		//prompt.getButtonGreen().doClick();
	}
	
	public void testButton_onePlayer_yellow() {
		Player player1 = new Player(Color.red, 1);
		Player[] players = {player1};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonYellow().doClick();
	}
	
	public void testButton_onePlayer_blue() {
		Player player1 = new Player(Color.red, 1);
		Player[] players = {player1};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonBlue().doClick();
	}
	
	public void testButton_onePlayer_red() {
		Player player1 = new Player(Color.red, 1);
		Player[] players = {player1};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonRed().doClick();
	}
	
	public void testButton_onePlayer_green() {
		Player player1 = new Player(Color.red, 1);
		Player[] players = {player1};
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("1");
		prompt.gettextNumPlayers().postActionEvent();
		prompt.getButtonGreen().doClick();
	}
	
	
	
	public void testPrompt_textactionPerformed() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("2");
		prompt.gettextNumPlayers().postActionEvent();
	}
	
	public void testPrompt_textactionPerformed2() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("0");
		prompt.gettextNumPlayers().postActionEvent();
	}
	
	public void testPrompt_textactionPerformed3() {
		Player[] players = new Player[1];
		Player player1 = new Player(Color.red, 1);
		players[0] = player1;
		Prompt prompt = new Prompt(players);
		prompt.gettextNumPlayers().setText("5");
		prompt.gettextNumPlayers().postActionEvent();
	}
	
	/*
	 * Test pressing no button
	 */
	public void testBtnNo() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player[] players = {player1, player2};
		Prompt prompt = new Prompt(players);
		prompt.getBtnNo().doClick();
	}
	
	/*
	 * Test pressing yes button
	 */
	public void testBtnYes() {
		Player player1 = new Player(Color.red, 1);
		Player player2 = new Player(Color.red, 2);
		Player[] players = {player1, player2};
		Prompt prompt = new Prompt(players);
		prompt.getBtnNo().doClick();
	}
	

}
