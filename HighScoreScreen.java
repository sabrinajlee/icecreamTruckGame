package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
* This is the screen for high scores. It displays the top 5 highest scores of 
* all profiles that have played the game (locally)
* <p>
* This class uses Java Swing to implement GUI elements.
*<p>
*
* @author Lukas Bozinov
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
@SuppressWarnings("serial")
public class HighScoreScreen extends JFrame implements ActionListener {

	private JPanel panel = new JPanel();
	private JPanel scorePanel = new JPanel();
	private JLabel iceCreamConePhotoR = new JLabel(new ImageIcon("files/icecreamcone.png"));
	private JLabel iceCreamConePhotoL = new JLabel(new ImageIcon("files/icecreamcone.png"));
	private JLabel title = new JLabel("High Scores", SwingConstants.CENTER);

	private Font backFont = new Font("Calibri", Font.BOLD, 48);
	private Font scoreFont = new Font("Calibri", Font.BOLD, 36);
	private Font titleFont = new Font("Calibri", Font.BOLD, 96);

	private JButton back = new JButton("<html><center>Go Back<center/><html/>");

	private static JLabel highScoreName1 = new JLabel();
	private static JLabel highScoreMoney1 = new JLabel();

	private static JLabel highScoreName2 = new JLabel();
	private static JLabel highScoreMoney2 = new JLabel();

	private static JLabel highScoreName3 = new JLabel();
	private static JLabel highScoreMoney3 = new JLabel();

	/**
	 * This constructor runs everything required in the HighScoreScreen. This method
	 * runs the frameSetup, assembleWindow, and addScoresToWindow methods. This
	 * method also catches exceptions thrown by these other helper methods.
	 */
	public HighScoreScreen() {
		try {
			frameSetup();
			assembleWindow();
			addScoresToWindow();
		} catch (IOException e) {
			System.out.println("Error: IOException, error code 2.1");
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Error: Unknown exception, error code 2.2");
			e.printStackTrace();
		}
	}

	/**
	 * This helper method sets up the basics of the JFrame that this class extends.
	 * Sets the size of the window, makes it unresizable, and sets titles as well as
	 * layout/decorations.
	 * 
	 * @throws IOException
	 */
	private void frameSetup() throws IOException {

		setSize(1920, 1080); // set size of the window to 1920x1080
		setLayout(null); // set to default layout (flow layout)
		setTitle("Ice Cream Truck Tycoon - Lukas, Sabrina, Kevin, Matthew, & Ariana"); // set the title of the window
		setResizable(false); // disallow resizing the window

		setIconImage(ImageIO.read(new File("files/icecreamcone.png")));

		setDefaultCloseOperation(HighScoreScreen.EXIT_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * This method assemble the larger background components of the window. This
	 * includes: - The main background panel - The photos used for decoration -
	 * JButtons for user interaction
	 */
	private void assembleWindow() {

		panel.setBounds(0, 0, 1920, 1080);
		panel.setLayout(null);
		panel.setBackground(Color.decode("#7CF3A0"));

		add(panel);

		iceCreamConePhotoR.setBounds(1540, 140, 300, 768);
		panel.add(iceCreamConePhotoR);

		iceCreamConePhotoL.setBounds(40, 140, 300, 768);
		panel.add(iceCreamConePhotoL);

		back.setBounds(860, 800, 200, 100);
		back.setFont(backFont);
		back.setBackground(Color.decode("#7CC6FE"));
		back.setForeground(Color.decode("#1D1128"));
		back.setFocusPainted(false);
		back.setHorizontalAlignment(SwingConstants.CENTER);
		back.setVerticalAlignment(SwingConstants.CENTER);
		back.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		back.addActionListener(this);

		title.setBounds(230, 31, 1440, 100);
		title.setForeground(Color.BLACK);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(titleFont);

		panel.add(back);
		panel.add(title);

		scorePanel.setBounds(345, 150, 1200, 560);
		scorePanel.setLayout(null);
		scorePanel.setBackground(Color.decode("#FDC6D8"));
		scorePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		panel.add(scorePanel);

		panel.setVisible(true);
	}

	/**
	 * This method will add the top scores to the highscore window. This method will
	 * receive the top 5 earners as input ///(to be implemented)/// and rank them
	 * accordingly. After ranking, this method adds the scorers to the respective
	 * panel.
	 */
	private void addScoresToWindow() {

		Player p1 = GameLauncher.player1;
		Player p2 = GameLauncher.player2;
		Player p3 = GameLauncher.player3;

		HashMap<String, Double> dictionary = new HashMap<>();
		String[] sortedInitials = new String[3];
		double[] sortedBalances = new double[3];
		int i = 0;

		dictionary.put(p1.getPlayerInitials(), p1.getBalance());
		dictionary.put(p2.getPlayerInitials(), p2.getBalance());
		dictionary.put(p3.getPlayerInitials(), p3.getBalance());

		ArrayList<Map.Entry<String, Double>> list = new ArrayList<>(dictionary.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
			public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
				return o1.getValue().compareTo(o2.getValue());
			}
		});

		for (Map.Entry<String, Double> entry : list) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
			sortedInitials[i] = entry.getKey();
			sortedBalances[i] = entry.getValue();
			i++;
		}

		// limit money to 8 characters (7 characters excluding $)
		// limit names to 30 characters
		highScoreName1.setText(sortedInitials[2]);
		highScoreMoney1.setText(Double.toString(sortedBalances[2]));

		highScoreName2.setText(sortedInitials[1]);
		highScoreMoney2.setText(Double.toString(sortedBalances[1]));

		highScoreName3.setText(sortedInitials[0]);
		highScoreMoney3.setText(Double.toString(sortedBalances[0]));

		highScoreName1.setBounds(15, 5, 400, 150);
		highScoreName1.setForeground(Color.BLACK);
		highScoreName1.setFont(scoreFont);

		highScoreMoney1.setBounds(1040, 5, 400, 150);
		highScoreMoney1.setForeground(Color.BLACK);
		highScoreMoney1.setFont(scoreFont);

		highScoreName2.setBounds(15, 105, 400, 150);
		highScoreName2.setForeground(Color.BLACK);
		highScoreName2.setFont(scoreFont);

		highScoreMoney2.setBounds(1040, 105, 400, 150);
		highScoreMoney2.setForeground(Color.BLACK);
		highScoreMoney2.setFont(scoreFont);

		highScoreName3.setBounds(15, 205, 400, 150);
		highScoreName3.setForeground(Color.BLACK);
		highScoreName3.setFont(scoreFont);

		highScoreMoney3.setBounds(1040, 205, 400, 150);
		highScoreMoney3.setForeground(Color.BLACK);
		highScoreMoney3.setFont(scoreFont);

		scorePanel.add(highScoreName1);
		scorePanel.add(highScoreMoney1);
		scorePanel.add(highScoreName2);
		scorePanel.add(highScoreMoney2);
		scorePanel.add(highScoreName3);
		scorePanel.add(highScoreMoney3);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == back) {
			setVisible(false);
			TitleScreen.panel.requestFocus();
		}

	}

}