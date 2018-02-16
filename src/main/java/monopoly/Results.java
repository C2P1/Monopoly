package monopoly;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import java.awt.Font;

public class Results extends JDialog { 

	private final JPanel contentPanel = new JPanel();

	//	/**
	//	 * Launch the application.
	//	 */
	//	public static void main(String[] args) {
	//		Results dialog = new Results();
	//		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	//		dialog.setVisible(true);
	//	}

	/**
	 * Create the dialog.
	 */
	public Results(Player[] players) {
		setBounds(100, 100, 325, 390);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				JLabel lblP1Bal = new JLabel("Player" + (i+1) + " = $" + players[i].getBalance());
				lblP1Bal.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
				lblP1Bal.setOpaque(true);
				lblP1Bal.setHorizontalTextPosition(SwingConstants.CENTER);
				lblP1Bal.setHorizontalAlignment(SwingConstants.CENTER);
				lblP1Bal.setBounds(25, 60+(60*i), 150, 80);
				contentPanel.add(lblP1Bal);
			}
		}
		//		JLabel lblP1Bal = new JLabel("P1 = $0");
		//		lblP1Bal.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		lblP1Bal.setOpaque(true);
		//		lblP1Bal.setHorizontalTextPosition(SwingConstants.CENTER);
		//		lblP1Bal.setHorizontalAlignment(SwingConstants.CENTER);
		//		lblP1Bal.setBounds(25, 65, 95, 45);
		//		contentPanel.add(lblP1Bal);
		//
		//
		//		JLabel lblP2 = new JLabel("P2 = $0");
		//		lblP2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		lblP2.setOpaque(true);
		//		lblP2.setHorizontalTextPosition(SwingConstants.CENTER);
		//		lblP2.setHorizontalAlignment(SwingConstants.CENTER);
		//		lblP2.setBounds(25, 120, 95, 45);
		//		contentPanel.add(lblP2);
		//
		//		JLabel lblP3 = new JLabel("P3 = $0");
		//		lblP3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		lblP3.setOpaque(true);
		//		lblP3.setHorizontalTextPosition(SwingConstants.CENTER);
		//		lblP3.setHorizontalAlignment(SwingConstants.CENTER);
		//		lblP3.setBounds(25, 180, 95, 45);
		//		contentPanel.add(lblP3);
		//
		//		JLabel lblP4 = new JLabel("P4 = $0");
		//		lblP4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		//		lblP4.setOpaque(true);
		//		lblP4.setHorizontalTextPosition(SwingConstants.CENTER);
		//		lblP4.setHorizontalAlignment(SwingConstants.CENTER);
		//		lblP4.setBounds(25, 240, 95, 45);
		//		contentPanel.add(lblP4);

		double max = players[0].getBalance();
		int playerNum = 1;
		for (int i = 0; i < players.length; i++) {
			if (players[i] != null) {
				if (players[i].getBalance() > max) {
					max = players[i].getBalance();
					playerNum = (i+1);

				}
			}
		}

		JLabel lblPlayerXWins = new JLabel("Player " + playerNum + " Wins!");
		lblPlayerXWins.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblPlayerXWins.setOpaque(true);
		lblPlayerXWins.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayerXWins.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayerXWins.setBounds(65, 315, 197, 42);
		contentPanel.add(lblPlayerXWins);

		JLabel lblNewLabel = new JLabel("Player Balances");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(5, 17, 152, 40);
		contentPanel.add(lblNewLabel);
	}

}

