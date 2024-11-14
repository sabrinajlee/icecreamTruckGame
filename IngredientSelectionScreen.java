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

/**
 * This is the screen for ingredient selection. It has all the selection options
 * for entering <br>
 * gameplay. It adjusts the player's inventory items (ingredients for icecream)
 * and their balance <b> as they purchase items and allows players to undo their
 * actions, returning their balance to the <b> before they purchased the last
 * item and restores the inventory to that state through a linked list. <b>
 * <p>
 * This class uses Java Swing to implement GUI elements. Music is original,
 * created by Ariana Feng using Soundtrap
 * <p>
 *
 * @author Lukas Bozinov
 * @author Sabrina Lee
 * @author Kevin Russel
 * @author Ariana Feng CS2212 Spring 2024 term Group 48 Prof. Servos Monday
 *         April 1, 2024
 */

@SuppressWarnings("serial")
public class IngredientSelectionScreen extends JFrame implements ActionListener {

	private JPanel panel = new JPanel();
	private JPanel ingredientPanel = new JPanel();

	private double cashDouble = 0;

	private JLabel title = new JLabel("Select Your Ingredients", SwingConstants.CENTER);

	// TODO: remember to change the cash.
	private JLabel cash = new JLabel("<html>Cash:&nbsp;" + String.format("%.1f", cashDouble) + "<html/>");
	private JLabel infoLabel = new JLabel(
			"<html><center>Pick how much of each ingredient you'd like to purchase for the day!<center/><html>");

	private Font startFont = new Font("Calibri", Font.BOLD, 36);
	private Font cashAndIngredientFont = new Font("Calibri", Font.BOLD, 48);
	private Font titleFont = new Font("Calibri", Font.BOLD, 96);

	private JButton makeRecipe = new JButton("<html><center>Make Recipe<center/><html/>");

	public Player currentPlayer;

	private int nCones = 0;
	private double nCream = 0, nVanilla = 0, nSugar = 0;
	private double conePrice = 0.10, creamPrice = 0.50, vanillaPrice = 1.0, sugarPrice = 0.20;

	private JLabel conesLabel = new JLabel("<html>Cones:&nbsp;<html/>" + String.format("%.1f", (double) nCones));
	private JLabel creamLabel = new JLabel("<html>Cream:&nbsp;<html/>" + String.format("%.1f", nCream));
	private JLabel vanillaLabel = new JLabel("<html>Vanilla:&nbsp;<html/>" + String.format("%.1f", nVanilla));
	private JLabel sugarLabel = new JLabel("<html>Sugar:&nbsp;<html/>" + String.format("%.1f", nSugar));

	private JLabel conesPriceLabel = new JLabel(
			"<html>($" + String.format("%.1f", conePrice) + "&nbsp;per cone)&nbsp;<html/>");
	private JLabel creamPriceLabel = new JLabel(
			"<html>($" + String.format("%.1f", creamPrice) + "&nbsp;per cup)&nbsp;<html/>");
	private JLabel vanillaPriceLabel = new JLabel(
			"<html>($" + String.format("%.1f", vanillaPrice) + "&nbsp;per tsp)&nbsp;<html/>");
	private JLabel sugarPriceLabel = new JLabel(
			"<html>($" + String.format("%.1f", sugarPrice) + "&nbsp;per tbsp)&nbsp;<html/>");

	private JButton buy10Cones = new JButton("<html><center>+10 Cones<center/><html/>");
	private JButton buy20Cones = new JButton("<html><center>+20 Cones<center/><html/>");
	private JButton buy40Cones = new JButton("<html><center>+40 Cones<center/><html/>");

	private JButton buy10Cream = new JButton("<html><center>+10 Cream<center/><html/>");
	private JButton buy20Cream = new JButton("<html><center>+20 Cream<center/><html/>");
	private JButton buy40Cream = new JButton("<html><center>+40 Cream<center/><html/>");

	private JButton buy10Vanilla = new JButton("<html><center>+10 Vanilla<center/><html/>");
	private JButton buy20Vanilla = new JButton("<html><center>+20 Vanilla<center/><html/>");
	private JButton buy40Vanilla = new JButton("<html><center>+40 Vanilla<center/><html/>");

	private JButton buy10Sugar = new JButton("<html><center>+10 Sugar<center/><html/>");
	private JButton buy20Sugar = new JButton("<html><center>+20 Sugar<center/><html/>");
	private JButton buy40Sugar = new JButton("<html><center>+40 Sugar<center/><html/>");

	private JButton undoButton = new JButton("<html><center>Undo<center/><html/>");

	private Clip sound1;

	/**
	 * This constructor runs everything required in the TitleScreen. This method
	 * runs the frameSetup and assembleWindow methods. This method also catches
	 * exceptions thrown by these other helper methods.
	 */
	public IngredientSelectionScreen(Player currentPlayer) {
		this.currentPlayer = currentPlayer;

		nCones = currentPlayer.inventory.getCones();
		conesLabel.setText("<html>Cones:&nbsp;<html/>" + String.format("%.1f", (double) nCones));

		nCream = currentPlayer.inventory.getCream();
		creamLabel.setText("<html>Cream:&nbsp;<html/>" + String.format("%.1f", nCream));

		nVanilla = currentPlayer.inventory.getVanilla();
		vanillaLabel.setText("<html>Vanilla:&nbsp;<html/>" + String.format("%.1f", nVanilla));

		nSugar = currentPlayer.inventory.getSugar();
		sugarLabel.setText("<html>Sugar:&nbsp;<html/>" + String.format("%.1f", nSugar));

		try {
			cashChange();
			frameSetup();
			assembleWindow();
			ingredientPanelSetup();
			addPurchaseButtons();
		} catch (IOException e) {
			System.out.println("Error: IOException, error code 4.1");
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Error: Unknown exception, error code 4.2");
			e.printStackTrace();
		}
	}

	private void cashChange() {
		cashDouble = currentPlayer.getBalance();
		cash.setText("<html>Cash:&nbsp;" + String.format("%.2f", cashDouble) + "<html/>");
	}

	private void changeCones() {
		nCones = currentPlayer.inventory.getCones();
		conesLabel.setText("<html>Cones:&nbsp;<html/>" + String.format("%.1f", (double) nCones));
	} 

	private void changeCreme() {
		nCream = currentPlayer.inventory.getCream();
		creamLabel.setText("<html>Cream:&nbsp;<html/>" + String.format("%.1f", nCream));
	}

	private void changeVanila() {
		nVanilla = currentPlayer.inventory.getVanilla();
		vanillaLabel.setText("<html>Vanilla:&nbsp;<html/>" + String.format("%.1f", nVanilla));
	}

	private void changeSugar() {
		nSugar = currentPlayer.inventory.getSugar();
		sugarLabel.setText("<html>Sugar:&nbsp;<html/>" + String.format("%.1f", nSugar));
	}

	private void playMusic() {
		try {
			// create a new input stream and grab the file from the sounds folder
			AudioInputStream audio = AudioSystem
					.getAudioInputStream(new File("files/MainGamePlayScreenMusic.wav").getAbsoluteFile());
			sound1 = AudioSystem.getClip();

			sound1.open(audio);
			sound1.start();
			sound1.loop(Clip.LOOP_CONTINUOUSLY);

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

		add(panel);

		title.setBounds(230, 31, 1440, 100);
		title.setForeground(Color.BLACK);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(titleFont);

		makeRecipe.setBounds(745, 800, 400, 100);
		makeRecipe.setFont(startFont);
		makeRecipe.setBackground(Color.decode("#9FDBFE"));
		makeRecipe.setForeground(Color.decode("#1D1128"));
		makeRecipe.setFocusPainted(false);
		makeRecipe.setHorizontalAlignment(SwingConstants.CENTER);
		makeRecipe.setVerticalAlignment(SwingConstants.CENTER);
		makeRecipe.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		makeRecipe.addActionListener(this);

		ingredientPanel.setBounds(150, 125, 1600, 650);
		ingredientPanel.setLayout(null);
		ingredientPanel.setBackground(Color.decode("#FDC6D8"));
		ingredientPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

		panel.add(ingredientPanel);
		panel.add(title);
		panel.add(makeRecipe);

		panel.setVisible(true);
	}

	private void ingredientPanelSetup() {

		cash.setBounds(0, 0, 300, 100);
		cash.setForeground(Color.BLACK);
		cash.setHorizontalAlignment(SwingConstants.CENTER);
		cash.setVerticalAlignment(SwingConstants.CENTER);
		cash.setFont(cashAndIngredientFont);
		cash.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		ingredientPanel.add(cash);

		infoLabel.setBounds(400, 7, 1100, 100);
		infoLabel.setForeground(Color.BLACK);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setVerticalAlignment(SwingConstants.CENTER);
		infoLabel.setFont(startFont);
		ingredientPanel.add(infoLabel);

		conesLabel.setBounds(0, 165, 300, 100);
		conesLabel.setFont(cashAndIngredientFont);
		conesLabel.setForeground(Color.BLACK);
		conesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		conesLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(conesLabel);

		creamLabel.setBounds(0, 265, 300, 100);
		creamLabel.setFont(cashAndIngredientFont);
		creamLabel.setForeground(Color.BLACK);
		creamLabel.setHorizontalAlignment(SwingConstants.CENTER);
		creamLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(creamLabel);

		vanillaLabel.setBounds(0, 365, 300, 100);
		vanillaLabel.setFont(cashAndIngredientFont);
		vanillaLabel.setForeground(Color.BLACK);
		vanillaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		vanillaLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(vanillaLabel);

		sugarLabel.setBounds(0, 465, 300, 100);
		sugarLabel.setFont(cashAndIngredientFont);
		sugarLabel.setForeground(Color.BLACK);
		sugarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sugarLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(sugarLabel);

		conesPriceLabel.setBounds(1185, 165, 300, 100);
		conesPriceLabel.setFont(startFont);
		conesPriceLabel.setForeground(Color.BLACK);
		conesPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		conesPriceLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(conesPriceLabel);

		creamPriceLabel.setBounds(1185, 265, 300, 100);
		creamPriceLabel.setFont(startFont);
		creamPriceLabel.setForeground(Color.BLACK);
		creamPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		creamPriceLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(creamPriceLabel);

		vanillaPriceLabel.setBounds(1185, 365, 300, 100);
		vanillaPriceLabel.setFont(startFont);
		vanillaPriceLabel.setForeground(Color.BLACK);
		vanillaPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		vanillaPriceLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(vanillaPriceLabel);

		sugarPriceLabel.setBounds(1185, 465, 300, 100);
		sugarPriceLabel.setFont(startFont);
		sugarPriceLabel.setForeground(Color.BLACK);
		sugarPriceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		sugarPriceLabel.setVerticalAlignment(SwingConstants.CENTER);
		ingredientPanel.add(sugarPriceLabel);

	}

	private void addPurchaseButtons() {

		buy10Cones.setBounds(350, 190, 175, 50);
		buy10Cones.setFont(startFont);
		buy10Cones.setBackground(Color.decode("#9FDBFE"));
		buy10Cones.setForeground(Color.decode("#1D1128"));
		buy10Cones.setFocusPainted(false);
		buy10Cones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy10Cones.addActionListener(this);
		ingredientPanel.add(buy10Cones);

		buy20Cones.setBounds(630, 190, 175, 50);
		buy20Cones.setFont(startFont);
		buy20Cones.setBackground(Color.decode("#9FDBFE"));
		buy20Cones.setForeground(Color.decode("#1D1128"));
		buy20Cones.setFocusPainted(false);
		buy20Cones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy20Cones.addActionListener(this);
		ingredientPanel.add(buy20Cones);

		buy40Cones.setBounds(910, 190, 175, 50);
		buy40Cones.setFont(startFont);
		buy40Cones.setBackground(Color.decode("#9FDBFE"));
		buy40Cones.setForeground(Color.decode("#1D1128"));
		buy40Cones.setFocusPainted(false);
		buy40Cones.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy40Cones.addActionListener(this);
		ingredientPanel.add(buy40Cones);

		buy10Cream.setBounds(350, 290, 175, 50);
		buy10Cream.setFont(startFont);
		buy10Cream.setBackground(Color.decode("#9FDBFE"));
		buy10Cream.setForeground(Color.decode("#1D1128"));
		buy10Cream.setFocusPainted(false);
		buy10Cream.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy10Cream.addActionListener(this);
		ingredientPanel.add(buy10Cream);

		buy20Cream.setBounds(630, 290, 175, 50);
		buy20Cream.setFont(startFont);
		buy20Cream.setBackground(Color.decode("#9FDBFE"));
		buy20Cream.setForeground(Color.decode("#1D1128"));
		buy20Cream.setFocusPainted(false);
		buy20Cream.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy20Cream.addActionListener(this);
		ingredientPanel.add(buy20Cream);

		buy40Cream.setBounds(910, 290, 175, 50);
		buy40Cream.setFont(startFont);
		buy40Cream.setBackground(Color.decode("#9FDBFE"));
		buy40Cream.setForeground(Color.decode("#1D1128"));
		buy40Cream.setFocusPainted(false);
		buy40Cream.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy40Cream.addActionListener(this);
		ingredientPanel.add(buy40Cream);

		buy10Vanilla.setBounds(350, 390, 175, 50);
		buy10Vanilla.setFont(startFont);
		buy10Vanilla.setBackground(Color.decode("#9FDBFE"));
		buy10Vanilla.setForeground(Color.decode("#1D1128"));
		buy10Vanilla.setFocusPainted(false);
		buy10Vanilla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy10Vanilla.addActionListener(this);
		ingredientPanel.add(buy10Vanilla);

		buy20Vanilla.setBounds(630, 390, 175, 50);
		buy20Vanilla.setFont(startFont);
		buy20Vanilla.setBackground(Color.decode("#9FDBFE"));
		buy20Vanilla.setForeground(Color.decode("#1D1128"));
		buy20Vanilla.setFocusPainted(false);
		buy20Vanilla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy20Vanilla.addActionListener(this);
		ingredientPanel.add(buy20Vanilla);

		buy40Vanilla.setBounds(910, 390, 175, 50);
		buy40Vanilla.setFont(startFont);
		buy40Vanilla.setBackground(Color.decode("#9FDBFE"));
		buy40Vanilla.setForeground(Color.decode("#1D1128"));
		buy40Vanilla.setFocusPainted(false);
		buy40Vanilla.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy40Vanilla.addActionListener(this);
		ingredientPanel.add(buy40Vanilla);

		buy10Sugar.setBounds(350, 490, 175, 50);
		buy10Sugar.setFont(startFont);
		buy10Sugar.setBackground(Color.decode("#9FDBFE"));
		buy10Sugar.setForeground(Color.decode("#1D1128"));
		buy10Sugar.setFocusPainted(false);
		buy10Sugar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy10Sugar.addActionListener(this);
		ingredientPanel.add(buy10Sugar);

		buy20Sugar.setBounds(630, 490, 175, 50);
		buy20Sugar.setFont(startFont);
		buy20Sugar.setBackground(Color.decode("#9FDBFE"));
		buy20Sugar.setForeground(Color.decode("#1D1128"));
		buy20Sugar.setFocusPainted(false);
		buy20Sugar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy20Sugar.addActionListener(this);
		ingredientPanel.add(buy20Sugar);

		buy40Sugar.setBounds(910, 490, 175, 50);
		buy40Sugar.setFont(startFont);
		buy40Sugar.setBackground(Color.decode("#9FDBFE"));
		buy40Sugar.setForeground(Color.decode("#1D1128"));
		buy40Sugar.setFocusPainted(false);
		buy40Sugar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		buy40Sugar.addActionListener(this);
		ingredientPanel.add(buy40Sugar);

		undoButton.setBounds(1425, 600, 175, 50);
		undoButton.setFont(startFont);
		undoButton.setBackground(Color.decode("#9FDBFE"));
		undoButton.setForeground(Color.decode("#1D1128"));
		undoButton.setFocusPainted(false);
		undoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		undoButton.addActionListener(this);
		ingredientPanel.add(undoButton);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// price for 10 cones $2.00
		if (e.getSource() == buy10Cones) {
			if (currentPlayer.inventory.changeConeQty(10, 1, "purchase") == 1) {
				cashChange();
				changeCones();
			}
			// price for 20 cones $3.00
		} else if (e.getSource() == buy20Cones) {
			if (currentPlayer.inventory.changeConeQty(20, 2, "purchase") == 1) {
				cashChange();
				changeCones();
			}
			// price for 40 cones $5.00
		} else if (e.getSource() == buy40Cones) {
			if (currentPlayer.inventory.changeConeQty(40, 4, "purchase") == 1) {
				cashChange();
				changeCones();
			}

			// price for 10 cream $5.00
		} else if (e.getSource() == buy10Cream) {
			if (currentPlayer.inventory.changeCreamQty(10, 5, "purchase") == 1) {
				;
				cashChange();
				changeCreme();
			}
			// price for 20 cream $8.00
		} else if (e.getSource() == buy20Cream) {
			if (currentPlayer.inventory.changeCreamQty(20, 10, "purchase") == 1) {
				;
				cashChange();
				changeCreme();
			}
			// price for 40 cream $15.00
		} else if (e.getSource() == buy40Cream) {
			if (currentPlayer.inventory.changeCreamQty(40, 20, "purchase") == 1) {
				;
				cashChange();
				changeCreme();
			}
			// price for 10 sugar $3.00
		} else if (e.getSource() == buy10Sugar) {
			if (currentPlayer.inventory.changeSugarQty(10, 2, "purchase") == 1) {
				;
				cashChange();
				changeSugar();
			}
			// price for 20 sugar $5.00
		} else if (e.getSource() == buy20Sugar) {
			if (currentPlayer.inventory.changeSugarQty(20, 4, "purchase") == 1) {
				;
				cashChange();
				changeSugar();
			}
			// price for 40 sugar $8.00
		} else if (e.getSource() == buy40Sugar) {
			if (currentPlayer.inventory.changeSugarQty(40, 8, "purchase") == 1) {
				cashChange();
				changeSugar();
			}
			// price 10 vanilla $2.00
		} else if (e.getSource() == buy10Vanilla) {
			if (currentPlayer.inventory.changeVanillaQty(10, 10, "purchase") == 1) {
				cashChange();
				changeVanila();
			}
			// price 20 vanilla $3.00
		} else if (e.getSource() == buy20Vanilla) {
			if (currentPlayer.inventory.changeVanillaQty(20, 20, "purchase") == 1) {
				;
				cashChange();
				changeVanila();
			}
			// price 40 vanilla $5.00
		} else if (e.getSource() == buy40Vanilla) {
			if (currentPlayer.inventory.changeVanillaQty(40, 40, "purchase") == 1) {
				;
				cashChange();
				changeVanila();
			}
		} else if (e.getSource() == undoButton) {
			String refundItem = currentPlayer.inventory.undoPurchase();
			switch (refundItem) {
			case "Cone":
				changeCones();
				break;
			case "Cream":
				changeCreme();
				break;
			case "Sugar":
				changeSugar();
				break;
			case "Vanilla":
				changeVanila();
				break;
			default:
				System.out.print("Default");
				break;
			}
			cashChange();
			// System.out.print(currentPlayer.getBalance());

		} else if (e.getSource() == makeRecipe) {
			setVisible(false);
			sound1.stop();
			sound1.close();
			new RecipeCreationScreen(currentPlayer);
		}

	}
}