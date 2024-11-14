package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

/**
* This is the screen for the results screen after a day in main gameplay. It shows <b>
* a graph for the change in reputation as each customer visited the icecream truck and <b>
* a graph for the change in profit. It also displays information on how to improve profit <b>
* and reputation in the game <b>
* <p>
* This class uses Java Swing to implement GUI elements.
* This class uses JavaFX to implement graphs.
* Music is original, created by Ariana Feng using Soundtrap
*
* @author Lukas Bozinov
* @author Ariana Feng
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
@SuppressWarnings("serial")
public class ResultsScreen extends JFrame implements ActionListener {

	private JPanel panel = new JPanel();
	private JPanel resultsPanel = new JPanel();
	private JPanel infoPanel = new JPanel();
	private JPanel separator = new JPanel();

	private JFXPanel fxpanel = new JFXPanel();

	private JLabel title = new JLabel("Results", SwingConstants.CENTER);

	private final String[] graphOptions = { "Reputation", "Profit" }; // rep v days (days x, profit y) and profit v days
																		// (proft y, days x)
	private JComboBox<String> graphDropdown = new JComboBox<String>(graphOptions);
	private JComboBox<String> infoDropdown = new JComboBox<String>(graphOptions);

	private Font startFont = new Font("Calibri", 1, 36);
	private Font dropDownLabelFont = new Font("Calibri", 1, 28);
	private Font titleFont = new Font("Calibri", 1, 96);

	private JLabel selectionLabel = new JLabel("Generate Graph");
	private JLabel infoSelectionLabel = new JLabel("Information");
	private JButton startNextDayButton = new JButton("<html><center>Start Next Day<center/><html/>");
	private Player currentPlayer;
	private Clip sound1;

	/**
	 * This constructor runs everything required in the TitleScreen. This method
	 * runs the frameSetup and assembleWindow methods. This method also catches
	 * exceptions thrown by these other helper methods.
	 * 
	 * @param currentPlayer
	 */
	public ResultsScreen(Player currentPlayer) {
		try {

			this.currentPlayer = currentPlayer;

			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					try {
						frameSetup();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					assembleWindow();
					addLabelsAndDropDown();
				}
			});

		} catch (Exception e) {
			System.out.println("Error: Unknown exception, error code 4.2");
			e.printStackTrace();
		}
	}

	private void playMusic() {
		try {
			// create a new input stream and grab the file from the sounds folder
			AudioInputStream audio = AudioSystem
					.getAudioInputStream(new File("files/MainGamePlayScreenMusic.wav").getAbsoluteFile());
			sound1 = AudioSystem.getClip(); // create a clip called startGame and get the clip from the
			// "audio

			sound1.open(audio);
			sound1.start(); // play the clip/sound
		} catch (Exception ex) { // print in console if the clip doesn't work for whatever reason
			System.out.println("Error playing sound.");
			ex.printStackTrace();
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
		playMusic();
		panel.setBounds(0, 0, 1920, 1080);
		panel.setLayout(null);
		panel.setBackground(Color.decode("#7CF3A0"));

		fxpanel.setBounds(549, 125, 1100, 650);
		fxpanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		infoPanel.setBounds(549, 125, 1100, 650);
		infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		add(fxpanel);
		add(infoPanel);
		add(panel);
		infoPanel.setVisible(false);

		title.setBounds(230, 31, 1440, 100);
		title.setForeground(Color.BLACK);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(titleFont);

		startNextDayButton.setBounds(745, 800, 400, 100);
		startNextDayButton.setFont(startFont);
		startNextDayButton.setBackground(Color.decode("#9FDBFE"));
		startNextDayButton.setForeground(Color.decode("#1D1128"));
		startNextDayButton.setFocusPainted(false);
		startNextDayButton.setHorizontalAlignment(SwingConstants.CENTER);
		startNextDayButton.setVerticalAlignment(SwingConstants.CENTER);
		startNextDayButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		startNextDayButton.addActionListener(this);

		resultsPanel.setBounds(250, 125, 300, 650);
		resultsPanel.setLayout(null);
		resultsPanel.setBackground(Color.decode("#FDC6D8"));
		resultsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		panel.add(title);
		panel.add(resultsPanel);
		panel.add(startNextDayButton);

		panel.setVisible(true);

	}

	private void addLabelsAndDropDown() {

		selectionLabel.setBounds(0, 4, 300, 60);
		selectionLabel.setForeground(Color.BLACK);
		selectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectionLabel.setVerticalAlignment(SwingConstants.CENTER);
		selectionLabel.setFont(startFont);
		resultsPanel.add(selectionLabel);

		graphDropdown.setBounds(25, 60, 250, 60);
		graphDropdown.setFont(dropDownLabelFont);
		graphDropdown.setBackground(Color.decode("#9FDBFE"));
		graphDropdown.setForeground(Color.decode("#1D1128"));
		graphDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) graphDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) graphDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);
		graphDropdown.setSelectedIndex(-1);
		graphDropdown.addActionListener(this);

		infoSelectionLabel.setBounds(0, 194, 300, 60);
		infoSelectionLabel.setForeground(Color.BLACK);
		infoSelectionLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoSelectionLabel.setVerticalAlignment(SwingConstants.CENTER);
		infoSelectionLabel.setFont(startFont);
		resultsPanel.add(infoSelectionLabel);

		infoDropdown.setBounds(25, 240, 250, 60);
		infoDropdown.setFont(dropDownLabelFont);
		infoDropdown.setBackground(Color.decode("#9FDBFE"));
		infoDropdown.setForeground(Color.decode("#1D1128"));
		infoDropdown.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		((JLabel) infoDropdown.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JLabel) infoDropdown.getRenderer()).setVerticalAlignment(SwingConstants.CENTER);
		infoDropdown.setSelectedIndex(-1);
		infoDropdown.addActionListener(this);

		separator.setBounds(300, 0, 2, 650);
		separator.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		resultsPanel.add(separator);
		resultsPanel.add(graphDropdown);
		resultsPanel.add(infoDropdown);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		sound1.stop();
		sound1.close();;
		if (e.getSource() == graphDropdown && graphDropdown.getSelectedIndex() == 0) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					initFXIndex1(fxpanel);

				}
			});

			panel.repaint();
		} else if (e.getSource() == graphDropdown && graphDropdown.getSelectedIndex() == 1) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					initFXIndex2(fxpanel);

				}
			});

			panel.repaint();
		}

		else if (e.getSource() == startNextDayButton) {
			setVisible(false);

			if(currentPlayer.getDay() == 3){
				if(currentPlayer.getBalance()>= 30){
					new VictoryScreen();
				}
				else{
					new DefeatScreen();
				}
			}
			else{
			Player newDay = new Player(currentPlayer.getNewFile(), currentPlayer.getPlayerInitials(),
					currentPlayer.getDay(), currentPlayer.getDay(), currentPlayer.getWeather(),
					currentPlayer.getReputation(), currentPlayer.getBalance(), currentPlayer.inventory.getCones(),
					currentPlayer.inventory.getSugar(), currentPlayer.inventory.getVanilla(),
					currentPlayer.inventory.getCream());
			new IngredientSelectionScreen(newDay);}

		} else if (e.getSource() == infoDropdown && infoDropdown.getSelectedIndex() == 0) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					fxpanel.setVisible(false);
					infoPanel.removeAll();
					infoPanel.setBackground(Color.decode("#9FDBFE"));
					
					JLabel infoLabel = new JLabel("<html>To increase reputation, you need to sell more ice cream!<br>"
							+ "Try the following strategies:&nbsp;<br>"
							+ "&nbsp;- Lower your prices, so more people buy the ice cream. <br>"
							+ "&nbsp;&nbsp;&nbsp;Word-of-mouth goes a long way!<br>"
							+ "&nbsp;- Increase the amount of ingredients you use in your recipe! <br>"
							+ "&nbsp;&nbsp;&nbsp;People want tasty ice cream.</html>");

					infoLabel.setBounds(0, 0, 1095, 650);
					infoLabel.setForeground(Color.BLACK);
					infoLabel.setFont(new Font("Calibri", Font.BOLD, 36));
					infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
					infoLabel.setVerticalAlignment(SwingConstants.TOP);
					infoPanel.add(infoLabel);
					infoPanel.setVisible(true);
					infoPanel.repaint();
					infoLabel.repaint();
				}
			});
		} else if (e.getSource() == infoDropdown && infoDropdown.getSelectedIndex() == 1) {
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					fxpanel.setVisible(false);
					infoPanel.removeAll();
					infoPanel.setBackground(Color.decode("#9FDBFE"));

					JLabel infoLabel = new JLabel("<html>To increase profits, you need to increase prices!<br>"
							+ "Try the following other strategies, too:&nbsp;<br>"
							+ "&nbsp;- Decrease the amount of ingredients you use in your recipe! <br>"
							+ "&nbsp;&nbsp;&nbsp;This saves on cost.<br>"
							+ "&nbsp;- Try not to spend all your money buying more ingredients! <br>"
							+ "&nbsp;&nbsp;&nbsp;This helps you save money, too.</html>");

					infoLabel.setBounds(0, 0, 1095, 650);
					infoLabel.setForeground(Color.BLACK);
					infoLabel.setFont(new Font("Calibri", Font.BOLD, 36));
					infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
					infoLabel.setVerticalAlignment(SwingConstants.TOP);
					infoPanel.add(infoLabel);
					infoPanel.setVisible(true);
					infoPanel.repaint();
					infoLabel.repaint();
				}
			});
		}

	}

	private static void initFXIndex1(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread
		Scene scene = createSceneIndex1();
		fxPanel.setScene(scene);
		fxPanel.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Scene createSceneIndex1() {

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		Scene scene = new Scene(lineChart, 800, 600);

		xAxis.setLabel("Sprite #");
		yAxis.setLabel("Reputation");
		// creating the chart

		lineChart.setTitle("Reputation over time");
		// defining a series
		XYChart.Series series = new XYChart.Series();

		series.setName("Reputation Marker");
		// populating the series with data

		for (int i = 0; i < 11; i++) {

			series.getData().add(new XYChart.Data(i, Results.repArray[i]));
		}
		lineChart.getData().add(series);

		return (scene);
	}

	private static void initFXIndex2(JFXPanel fxPanel) {
		// This method is invoked on the JavaFX thread
		Scene scene = createSceneIndex2();
		fxPanel.setScene(scene);
		fxPanel.setVisible(true);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static Scene createSceneIndex2() {

		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

		Scene scene = new Scene(lineChart, 800, 600);

		xAxis.setLabel("Sprite #");
		yAxis.setLabel("Profit ($)");
		// creating the chart

		lineChart.setTitle("Profit over time");
		// defining a series
		XYChart.Series series = new XYChart.Series();

		series.setName("Profit Marker");
		// populating the series with data
		for (int i = 0; i < 11; i++) {

			series.getData().add(new XYChart.Data(i, Results.dayCash[i]));

		}
		lineChart.getData().add(series);

		return (scene);
	}

}