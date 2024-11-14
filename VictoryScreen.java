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
* This is the screen displayed when a play successfully finishes the game. 
* <p>
* This class uses Java Swing to implement GUI elements.
* Music is original, created by Lukas Bozinov using Soundtrap
*<p>
*
* @author Lukas Bozinov
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
@SuppressWarnings("serial")
public class VictoryScreen extends JFrame implements ActionListener {

	private JPanel panel = new JPanel();
	private JLabel truckPhoto = new JLabel(new ImageIcon("files/icecreamtruckballoon.png"));
	private JLabel iceCreamConePhotoR = new JLabel(new ImageIcon("files/moneycone.png"));
	private JLabel iceCreamConePhotoL = new JLabel(new ImageIcon("files/moneycone.png"));
	private JLabel title = new JLabel("You Win!");

	private Font startFont = new Font("Calibri", Font.BOLD, 72);
	private Font titleFont = new Font("Calibri", Font.BOLD, 96);

	private JButton quit = new JButton("Quit Game");

	
	/**
	 * This constructor runs everything required in the TitleScreen.
	 * This method runs the frameSetup and assembleWindow methods.
	 * This method also catches exceptions thrown by these other helper methods.
	 */
	public VictoryScreen() {
		try {
			frameSetup();
			assembleWindow();
			playDefeatNoise();
		} catch (IOException e) {
			System.out.println("Error: IOException, error code 7.1");
			e.printStackTrace();

		} catch (Exception e) {
			System.out.println("Error: Unknown exception, error code 7.2");
			e.printStackTrace();
		}
	}

	/**
	 * This helper method sets up the basics of the JFrame that this class extends.
	 * Sets the size of the window, makes it unresizable, and sets titles as well as layout/decorations.
	 * @throws IOException
	 */
	private void frameSetup() throws IOException {

		setSize(1920, 1080); // set size of the window to 1920x1080
		setLayout(null); // set to default layout (flow layout)
		setTitle("Ice Cream Truck Tycoon - Lukas, Sabrina, Kevin, Matthew, & Ariana"); // set the title of the window
		setResizable(false); // disallow resizing the window

		setIconImage(ImageIO.read(new File("files/icecreamcone.png")));

		setDefaultCloseOperation(VictoryScreen.EXIT_ON_CLOSE);
		setVisible(true);
	}

	// assembles basic parts of the window
	private void assembleWindow() {

		panel.setBounds(0, 0, 1920, 1080);
		panel.setLayout(null);
		panel.setBackground(Color.decode("#7CF3A0"));

		add(panel);

		truckPhoto.setBounds(550, 100, 800, 800);
		panel.add(truckPhoto);

		iceCreamConePhotoR.setBounds(1440, 140, 300, 768);
		panel.add(iceCreamConePhotoR);

		iceCreamConePhotoL.setBounds(140, 140, 300, 768);
		panel.add(iceCreamConePhotoL);

		quit.setBounds(745, 800, 400, 145);
		quit.setFont(startFont);
		quit.setForeground(Color.decode("#1D1128"));
		quit.setFocusPainted(false);
		quit.setHorizontalAlignment(SwingConstants.CENTER);
		quit.setVerticalAlignment(SwingConstants.CENTER);
		quit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
		quit.addActionListener(this);

		title.setBounds(230, 31, 1440, 100);
		title.setForeground(Color.BLACK);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setVerticalAlignment(SwingConstants.CENTER);
		title.setFont(titleFont);

		panel.add(quit);
		panel.add(title);

		panel.setVisible(true);
	}
	
	private void playDefeatNoise() {
		try {
			// create a new input stream and grab the file from the sounds folder
			AudioInputStream audio = AudioSystem
					.getAudioInputStream(new File("files/victory music.wav").getAbsoluteFile());
			Clip sound1 = AudioSystem.getClip(); // create a clip called startGame and get the clip from the
													// "audio

			sound1.open(audio);
			sound1.start(); // play the clip/sound
		} catch (Exception ex) { // print in console if the clip doesn't work for whatever reason
			System.out.println("Error playing sound.");
			ex.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == quit) {
			System.exit(0);
		} 

	}

}