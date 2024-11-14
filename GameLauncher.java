package application;

import java.util.Arrays;

/**
* This class launches the game and sets up Player objects.
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
public class GameLauncher {
	/** Initialize the three player/profiles objects that will be used */

	// DO NOT CHANGE THESE CONSTRUCTORS. IT WILL BREAK THE SUBSEQUENT SCREENS.
	public static Player player1;
	public static Player player2;
	public static Player player3;
	/** can be used as a bases for method calls instead of player1,2,3 */
	public static Player currentPlayer;

	public static CSVFile player1csv = new CSVFile("Player1.csv");
	public static CSVFile player2csv = new CSVFile("Player2.csv");
	public static CSVFile player3csv = new CSVFile("Player3.csv");

	/**
	 * This method is the main method of the program. This is what needs to be
	 * compiled and run for the program to execute.
	 */
	public static void main(String[] args) {

		if (player1csv.returnLastDay().length == 10 && player1csv.returnLastDay()[0].equals("UserID")) {
			System.out.println("player1csv was empty, made new player");
			player1 = new Player(player1csv, "user1", 1, 1);
		} else {
			String[] params = player1csv.returnLastDay();
			String playerInitials = params[0];
			int diff = Integer.parseInt(params[1]);
			int currentDay = Integer.parseInt(params[2]);
			int weather = Integer.parseInt(params[3]);
			double reputation = Double.parseDouble(params[4]);
			double balance = Double.parseDouble(params[5]);

			int numCones = (int) (Double.parseDouble(params[6]));
			double numCream = Double.parseDouble(params[9]);
			double numSugar = Double.parseDouble(params[7]);
			double numVanilla = Double.parseDouble(params[8]);

			player1 = new Player(player1csv, playerInitials, diff, currentDay, weather, reputation, balance, numCones,
					numSugar, numVanilla, numCream);
		}
		if (player2csv.returnLastDay().length == 10 && player2csv.returnLastDay()[0].equals("UserID")) {
			System.out.println("player2csv was empty, made new player");
			player2 = new Player(player2csv, "user2", 1, 1);
		} else {
			String[] params = player2csv.returnLastDay();
			String playerInitials = params[0];
			int diff = Integer.parseInt(params[1]);
			int currentDay = Integer.parseInt(params[2]);
			int weather = Integer.parseInt(params[3]);
			double reputation = Double.parseDouble(params[4]);
			double balance = Double.parseDouble(params[5]);

			int numCones = (int) (Double.parseDouble(params[6]));
			double numCream = Double.parseDouble(params[9]);
			double numSugar = Double.parseDouble(params[7]);
			double numVanilla = Double.parseDouble(params[8]);
			player2 = new Player(player2csv, playerInitials, diff, currentDay, weather, reputation, balance, numCones,
					numSugar, numVanilla, numCream);
		}
		if (player3csv.returnLastDay().length == 10 && player3csv.returnLastDay()[0].equals("UserID")) {
			System.out.println("player3csv was empty, made new player");
			player3 = new Player(player3csv, "user3", 1, 1);
		} else {
			String[] params = player3csv.returnLastDay();
			String playerInitials = params[0];
			int diff = Integer.parseInt(params[1]);
			int currentDay = Integer.parseInt(params[2]);
			int weather = Integer.parseInt(params[3]);
			double reputation = Double.parseDouble(params[4]);
			double balance = Double.parseDouble(params[5]);

			int numCones = (int) (Double.parseDouble(params[6]));
			double numCream = Double.parseDouble(params[9]);
			double numSugar = Double.parseDouble(params[7]);
			double numVanilla = Double.parseDouble(params[8]);

			player3 = new Player(player3csv, playerInitials, diff, currentDay, weather, reputation, balance, numCones,
					numSugar, numVanilla, numCream);
		}

		new TitleScreen();
	}

}