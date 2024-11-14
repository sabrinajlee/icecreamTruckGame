package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
* This is the screen for debug mode. It has all the selection options for entering gameplay  <br>
* with administrator settings. It also displays the 3 player profiles' last day data. <br>
* <p>
* This class uses Java Swing to implement GUI elements.
*<p>
*
* @author Lukas Bozinov
* @author Sabrina Lee
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
@SuppressWarnings("serial")
public class DebugScreen extends JFrame implements ActionListener {
	
	private JPanel panel = new JPanel();
	private JPanel commandPanel = new JPanel();

	private JLabel title = new JLabel("Administrator Console", SwingConstants.CENTER);

	private final String[] weatherOptions = { "Freezing (0 deg)", "Cold (1-5 deg)", "Cool (6-10 deg)",
			"Warm (11-15 deg)", "Hot (16-20 deg)", "Scalding (>20 deg)" };
	private final Double[] moneyOptions = { 0.0, 10.0, 50.0, 100.0, 1000.0 }; // 0.10.50.100.1000
	private final Integer[] vanillaOptions = { 0, 10, 50, 100, 1000 }; 
	private final Integer[] creamOptions = { 0, 10, 50, 100, 1000 }; 
	private final Integer[] sugarOptions = { 0, 10, 50, 100, 1000 }; 
	private final Integer[] coneOptions = { 0, 10, 50, 100, 1000 }; 
	private final Integer[] reputationOptions = { -10, -5, 0, 5, 10 }; // -10/-5/0/5/10
	private final String[] difficultyOptions = { "Easy", "Medium", "Hard" }; // easy/med/hard

	
	private JComboBox<String> weatherDropdown = new JComboBox<String>(weatherOptions);
	private JComboBox<Double> moneyDropdown = new JComboBox<Double>(moneyOptions);
	private JComboBox<Integer> vanillaDropdown = new JComboBox<Integer>(vanillaOptions);
	private JComboBox<Integer> creamDropdown = new JComboBox<Integer>(creamOptions);
	private JComboBox<Integer> sugarDropdown = new JComboBox<Integer>(sugarOptions);
	private JComboBox<Integer> conesDropdown = new JComboBox<Integer>(coneOptions);
	private JComboBox<Integer> reputationDropdown = new JComboBox<Integer>(reputationOptions);
	private JComboBox<String> difficultyDropdown = new JComboBox<String>(difficultyOptions);

	private Font startFont = new Font("Calibri", Font.BOLD, 36);
	private Font dropDownFont = new Font("Calibri", Font.BOLD, 24);
	private Font dropDownLabelFont = new Font("Calibri", Font.BOLD, 28);
	private Font titleFont = new Font("Calibri", Font.BOLD, 96);

	private JLabel weatherLabel = new JLabel("Select Weather", SwingConstants.CENTER);
	private JLabel moneyLabel = new JLabel("Set Money ($)", SwingConstants.CENTER);
	private JLabel vanillaLabel = new JLabel("Add Vanilla", SwingConstants.CENTER);
	private JLabel creamLabel = new JLabel("Add Cream", SwingConstants.CENTER);
	private JLabel sugarLabel = new JLabel("Add Sugar", SwingConstants.CENTER);
	private JLabel conesLabel = new JLabel("Add Cones", SwingConstants.CENTER);
	private JLabel reputationLabel = new JLabel("Select Reputation", SwingConstants.CENTER);
	private JLabel difficultyLabel = new JLabel("Select Difficulty", SwingConstants.CENTER);
	private JLabel p1Label = new JLabel("Profile 1: ", SwingConstants.CENTER);
	private JLabel p2Label = new JLabel("Profile 2: ", SwingConstants.CENTER);
	private JLabel p3Label = new JLabel("Profile 3: ", SwingConstants.CENTER);
	private JLabel p1Data = new JLabel();
	private JLabel p2Data = new JLabel();
	private JLabel p3Data = new JLabel();
	
	private JButton execute = new JButton("Execute");
	
	/** profile 1 player object */
	private Player player1 = GameLauncher.player1;
	/** profile 2 player object */
	private Player player2 = GameLauncher.player2;
	/** profile 3 player object */
	private Player player3 = GameLauncher.player3;

	/**
	 * This constructor runs everything required in the TitleScreen. This method
	 * runs the frameSetup and assembleWindow methods. This method also catches
	 * exceptions thrown by these other helper methods.
	 */
	public DebugScreen() {
		try {
			frameSetup();
			assembleWindow();
			addDropDownLabels();
			addDropDowns();
		} catch (IOException e) {
			System.out.println("Error: IOException, error code 9.1");
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Error: Unknown exception, error code 9.2");
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

		setDefaultCloseOperation(TitleScreen.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// assembles basic parts of the window
	private void assembleWindow() {

		panel.setBounds(0, 0, 1920, 1080);
		panel.setLayout(null);
		panel.setBackground(Color.decode("#70FFB3"));

		add(panel);

		title.setBounds(230, 31, 1440, 100); 
		title.setForeground(Color.BLACK);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(titleFont);

		execute.setBounds(745, 800, 400, 100);
		execute.setFont(startFont);
		execute.setBackground(Color.decode("#7CC6FE"));
		execute.setForeground(Color.decode("#1D1128"));
		execute.setFocusPainted(false);
		execute.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		execute.addActionListener(this);

		commandPanel.setBounds(125, 125, 1625, 650);
		commandPanel.setLayout(null);
		commandPanel.setBackground(Color.decode("#FFADAD"));
		commandPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		panel.add(commandPanel);
		panel.add(title);
		panel.add(execute);

		panel.setVisible(true);
	}

	/**
	 * This method implements the dropdown option names for the debug settings
	 */
	private void addDropDownLabels() {
		weatherLabel.setBounds(137, 4, 250, 60);
		weatherLabel.setForeground(Color.BLACK);
		weatherLabel.setHorizontalAlignment(SwingConstants.CENTER);
		weatherLabel.setVerticalAlignment(SwingConstants.CENTER);
		weatherLabel.setFont(dropDownLabelFont);
		commandPanel.add(weatherLabel);
		
		moneyLabel.setBounds(412, 4, 250, 60);
		moneyLabel.setForeground(Color.BLACK);
		moneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		moneyLabel.setVerticalAlignment(SwingConstants.CENTER);
		moneyLabel.setFont(dropDownLabelFont);
		commandPanel.add(moneyLabel);

		vanillaLabel.setBounds(687, 4, 250, 60);
		vanillaLabel.setForeground(Color.BLACK);
		vanillaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vanillaLabel.setVerticalAlignment(SwingConstants.CENTER);
		vanillaLabel.setFont(dropDownLabelFont);
		commandPanel.add(vanillaLabel);

		creamLabel.setBounds(687, 174, 250, 60);
		creamLabel.setForeground(Color.BLACK);
		creamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creamLabel.setVerticalAlignment(SwingConstants.CENTER);
		creamLabel.setFont(dropDownLabelFont);
		commandPanel.add(creamLabel);
		
		sugarLabel.setBounds(412, 174, 250, 60);
		sugarLabel.setForeground(Color.BLACK);
		sugarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sugarLabel.setVerticalAlignment(SwingConstants.CENTER);
		sugarLabel.setFont(dropDownLabelFont);
		commandPanel.add(sugarLabel);

		conesLabel.setBounds(962, 174, 250, 60);
		conesLabel.setForeground(Color.BLACK);
		conesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		conesLabel.setVerticalAlignment(SwingConstants.CENTER);
		conesLabel.setFont(dropDownLabelFont);
		commandPanel.add(conesLabel);

		reputationLabel.setBounds(962, 4, 250, 60);
		reputationLabel.setForeground(Color.BLACK);
		reputationLabel.setHorizontalAlignment(SwingConstants.CENTER);
		reputationLabel.setVerticalAlignment(SwingConstants.CENTER);
		reputationLabel.setFont(dropDownLabelFont);
		commandPanel.add(reputationLabel);

		difficultyLabel.setBounds(1237, 4, 250, 60);
		difficultyLabel.setForeground(Color.BLACK);
		difficultyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		difficultyLabel.setVerticalAlignment(SwingConstants.CENTER);
		difficultyLabel.setFont(dropDownLabelFont);
		commandPanel.add(difficultyLabel);

		p1Label.setBounds(137, 400, 250, 60);
		p1Label.setForeground(Color.BLACK);
		p1Label.setVerticalAlignment(SwingConstants.CENTER);
		p1Label.setHorizontalAlignment(SwingConstants.CENTER);
		p1Label.setFont(dropDownLabelFont);
		commandPanel.add(p1Label);

		p2Label.setBounds(137, 475, 250, 60);
		p2Label.setForeground(Color.BLACK);
		p2Label.setVerticalAlignment(SwingConstants.CENTER);
		p2Label.setHorizontalAlignment(SwingConstants.CENTER);
		p2Label.setFont(dropDownLabelFont);
		commandPanel.add(p2Label);

		p3Label.setBounds(137, 550, 250, 60);
		p3Label.setForeground(Color.BLACK);
		p3Label.setVerticalAlignment(SwingConstants.CENTER);
		p3Label.setHorizontalAlignment(SwingConstants.CENTER);
		p3Label.setFont(dropDownLabelFont);
		commandPanel.add(p3Label);
	} 

	/**
	 * This method implements the dropdown options for the debug settings
	 */
	private void addDropDowns() {
		weatherDropdown.setBounds(137, 60, 250, 60);
		weatherDropdown.setFont(dropDownFont);
		weatherDropdown.setBackground(Color.decode("#7CC6FE"));
		weatherDropdown.setForeground(Color.decode("#1D1128"));
		weatherDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) weatherDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) weatherDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(weatherDropdown);
		weatherDropdown.revalidate();
		weatherDropdown.repaint();

		moneyDropdown.setBounds(412, 60, 250, 60);
		moneyDropdown.setFont(dropDownFont);
		moneyDropdown.setBackground(Color.decode("#7CC6FE"));
		moneyDropdown.setForeground(Color.decode("#1D1128"));
		moneyDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) moneyDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) moneyDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(moneyDropdown);
		moneyDropdown.revalidate();
		moneyDropdown.repaint();

		vanillaDropdown.setBounds(687, 60, 250, 60);
		vanillaDropdown.setFont(dropDownFont);
		vanillaDropdown.setBackground(Color.decode("#7CC6FE"));
		vanillaDropdown.setForeground(Color.decode("#1D1128"));
		vanillaDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) vanillaDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) vanillaDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(vanillaDropdown);
		vanillaDropdown.revalidate();
		vanillaDropdown.repaint();

		creamDropdown.setBounds(687, 230, 250, 60);
		creamDropdown.setFont(dropDownFont);
		creamDropdown.setBackground(Color.decode("#7CC6FE"));
		creamDropdown.setForeground(Color.decode("#1D1128"));
		creamDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) creamDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) creamDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(creamDropdown);
		creamDropdown.revalidate();
		creamDropdown.repaint();

		sugarDropdown.setBounds(412, 230, 250, 60);
		sugarDropdown.setFont(dropDownFont);
		sugarDropdown.setBackground(Color.decode("#7CC6FE"));
		sugarDropdown.setForeground(Color.decode("#1D1128"));
		sugarDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) sugarDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) sugarDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(sugarDropdown);
		sugarDropdown.revalidate();
		sugarDropdown.repaint();

		conesDropdown.setBounds(962, 230, 250, 60);
		conesDropdown.setFont(dropDownFont);
		conesDropdown.setBackground(Color.decode("#7CC6FE"));
		conesDropdown.setForeground(Color.decode("#1D1128"));
		conesDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) conesDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) conesDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(conesDropdown);
		conesDropdown.revalidate();
		conesDropdown.repaint();
		
		reputationDropdown.setBounds(962, 60, 250, 60);
		reputationDropdown.setFont(dropDownFont);
		reputationDropdown.setBackground(Color.decode("#7CC6FE"));
		reputationDropdown.setForeground(Color.decode("#1D1128"));
		reputationDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) reputationDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) reputationDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(reputationDropdown);
		reputationDropdown.revalidate();
		reputationDropdown.repaint();
		
		difficultyDropdown.setBounds(1237, 60, 250, 60);
		difficultyDropdown.setFont(dropDownFont);
		difficultyDropdown.setBackground(Color.decode("#7CC6FE"));
		difficultyDropdown.setForeground(Color.decode("#1D1128"));
		difficultyDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) difficultyDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) difficultyDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);

		commandPanel.add(difficultyDropdown);
		difficultyDropdown.revalidate();
		difficultyDropdown.repaint();
		
		// printing player profile data
		
		if (player1.getPlayerInitials().startsWith("user")) {
			p1Data.setText("No Data");
		}
		else {
			p1Data.setText("ID: " + player1.getPlayerInitials() + ",    Diff: " + player1.getDifficultyAsString() + ",    Day: " + player1.getDay() + ",    Wethr: "
							+ player1.getWeather() + ",    Rep: " + player1.getReputation() + ",    $: " + player1.getBalance() + ",    Cones: " + player1.inventory.getCones()
							+ ",    Sug: " + player1.inventory.getSugar() + ",    Van: " + player1.inventory.getVanilla() + ",    Crm: " + player1.inventory.getCream());
		}
		p1Data.setBounds(325, 400, 1110, 60);
		p1Data.setFont(dropDownFont);
		p1Data.setForeground(Color.BLACK);
		
		commandPanel.add(p1Data);
		p1Data.revalidate();
		p1Data.repaint();

		if (player2.getPlayerInitials().startsWith("user")) {
			p2Data.setText("No Data");
		}
		else {
			p2Data.setText("ID: " + player2.getPlayerInitials() + ",    Diff: " + player2.getDifficultyAsString() + ",    Day: " + player2.getDay() + ",    Wethr: "
							+ player2.getWeather() + ",    Rep: " + player2.getReputation() + ",    $: " + player2.getBalance() + ",    Cones: " + player2.inventory.getCones()
							+ ",    Sug: " + player2.inventory.getSugar() + ",    Van: " + player2.inventory.getVanilla() + ",    Crm: " + player2.inventory.getCream());
		}
		p2Data.setBounds(325, 475, 1110, 60);
		p2Data.setFont(dropDownFont);
		p2Data.setForeground(Color.BLACK);
		
		commandPanel.add(p2Data);
		p2Data.revalidate();
		p2Data.repaint();
		
		if (player3.getPlayerInitials().startsWith("user")) {
			p3Data.setText("No Data");	}
		else {
			p3Data.setText("ID: " + player3.getPlayerInitials() + ",    Diff: " + player3.getDifficultyAsString() + ",    Day: " + player3.getDay() + ",    Wethr: "
							+ player3.getWeather() + ",    Rep: " + player3.getReputation() + ",    $: " + player3.getBalance() + ",    Cones: " + player3.inventory.getCones()
							+ ",    Sug: " + player3.inventory.getSugar() + ",    Van: " + player3.inventory.getVanilla() + ",    Crm: " + player3.inventory.getCream());
		}
		p3Data.setBounds(325, 550, 1110, 60);
		p3Data.setFont(dropDownFont);
		p3Data.setForeground(Color.BLACK);
		
		commandPanel.add(p3Data);
		p3Data.revalidate();
		p3Data.repaint();
	}

	/**
	 * Method implements actions for each action event on the screen,
	 * including buttons, dropdowns.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		int diff = 0;
		double cash = 0;
		int weather= 0;
		double rep= 0;
		int cones= 0;
		double sugar = 0;
		double vanilla= 0;
		double creme= 0;

		if (e.getSource() == execute) {
			if(difficultyDropdown.getSelectedIndex()==0){
				diff = 1;
			}
			else if(difficultyDropdown.getSelectedIndex() == 1){
			diff = 2;
			}
			else if (difficultyDropdown.getSelectedIndex() == 2){
			diff = 3;
			}

			if(moneyDropdown.getSelectedIndex() == 0){
				cash = 0.0;
			}
			else if(moneyDropdown.getSelectedIndex() == 1){
				cash = 10.0;
			}
			else if(moneyDropdown.getSelectedIndex() == 2){
				cash = 50.0;
			}
			else if(moneyDropdown.getSelectedIndex() == 3){
				cash = 100.0;
			}
			else if(moneyDropdown.getSelectedIndex() == 4){
				cash = 1000.0;
			}

			if(vanillaDropdown.getSelectedIndex() == 0){
					vanilla = 0;
			}
			else if(vanillaDropdown.getSelectedIndex() == 1){
				vanilla = 10;
			}
			else if(vanillaDropdown.getSelectedIndex() == 2){
				vanilla = 50;
			}
			else if(vanillaDropdown.getSelectedIndex() == 3){
				vanilla = 100;
			}
			else if(vanillaDropdown.getSelectedIndex() == 4){
				vanilla = 100;
			}

			if(reputationDropdown.getSelectedIndex() == 0){
				rep = -10;
			}
			else if(reputationDropdown.getSelectedIndex() == 1){
				rep = -5;
			}
			else if(reputationDropdown.getSelectedIndex() == 2){
				rep = 0;
			}
			else if(reputationDropdown.getSelectedIndex() == 3){
				rep = 5;
			}
			else if(reputationDropdown.getSelectedIndex() == 4){
				rep = 10;
			}

			if(weatherDropdown.getSelectedIndex() == 0){
				weather = 0;
			}
			else if(weatherDropdown.getSelectedIndex() == 1){
				weather = 4;
			}
			else if(weatherDropdown.getSelectedIndex() == 2){
				weather = 7;
			}
			else if(weatherDropdown.getSelectedIndex() == 3){
				weather = 13;
			}
			else if(weatherDropdown.getSelectedIndex() == 4){
				weather = 17;
			}
			else if(weatherDropdown.getSelectedIndex() == 5){
				weather = 20;
			}

			if(sugarDropdown.getSelectedIndex() == 0){
				sugar = 0;
			}
			else if (sugarDropdown.getSelectedIndex() == 1){
				sugar = 10;
			}
			else if (sugarDropdown.getSelectedIndex() == 2){
				sugar = 50;
			}
			else if (sugarDropdown.getSelectedIndex() == 3){
				sugar = 100;
			}
			else if (sugarDropdown.getSelectedIndex() == 4){
				sugar = 1000;
			}


			if(creamDropdown.getSelectedIndex() == 0){
				creme = 0;
			}
			else if (creamDropdown.getSelectedIndex() == 1){
				creme = 10;
			}
			else if (creamDropdown.getSelectedIndex() == 2){
				creme = 50;
			}
			else if (creamDropdown.getSelectedIndex() == 3){
				creme = 100;
			}
			else if (creamDropdown.getSelectedIndex() == 4){
				creme = 1000;
			}


			if(conesDropdown.getSelectedIndex() == 0){
				cones = 0;
			}
			else if (conesDropdown.getSelectedIndex() == 1){
				cones = 10;
			}
			else if (conesDropdown.getSelectedIndex() == 2){
				cones = 50;
			}
			else if (conesDropdown.getSelectedIndex() == 3){
				cones = 100;
			}
			else if (conesDropdown.getSelectedIndex() == 4){
				cones = 1000;
			}


			setVisible(false);
			new Debug(diff,cash,weather,rep,cones,sugar,vanilla,creme);
				}





	}
}