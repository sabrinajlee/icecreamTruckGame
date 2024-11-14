package application;

/**
* This class is used for administrators to play the game in debug mode for ease of access <b>
* and level skipping. It takes the parameters selected on the debug screen (accessible from <b>
* the main screen by inputting code "qwertyuiop" and takes the administrator into gameplay. <b>
*<p>
*
* @author Kevin Russel
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
public class Debug {
	/** the administrator */
	Player currentPlayer;
	/** the name of the file saving debug data */
	CSVFile newfile;

	/**
	 * Constructor used to write to the debug csv and take administrator to the first gameplay screen
	 * @param diff 		game difficulty level
	 * @param cash		administrator's balance
	 * @param weather	weather of the day in game
	 * @param rep		reputation of administrator
	 * @param cones		administrator's number of cones
	 * @param sugar		administrator's number of sugar
	 * @param vanilla	administrator's number of vanilla
	 * @param milk		administrator's number of cream
	 */
	public Debug(int diff, double cash, int weather, double rep, int cones, double sugar, double vanilla, double milk) {
		System.out.println("coming in here");
		newfile = new CSVFile("Debug.csv");
		currentPlayer = new Player(newfile, "Debug", diff, diff, weather, rep, cash, cones, sugar, vanilla, milk);
		newfile.CSVWriter("debug", diff, diff, weather, rep, cash, cones, sugar, vanilla, milk);
		new IngredientSelectionScreen(currentPlayer);
	}

}