package application;

/**
* This class represents a game for one player profile. It holds the aspects of gameplay <b>
* that must be used during the gameplay by the player.  <b>
*<p>
*
* @author Ariana Feng
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/

public class Player {
	/** the player's balance including starting money and revenue */
	private double balance;
	/** the player's ID */
	private String playerInitials;
	/** the difficulty of the player's game */
	private int difficulty;
	/** the weather of the last day of the player's game */
	private double weather;
	/** the reputation of the last day of the player's game */
	private double reputation;
	/** the difficulty of the last day of the player's game */
	private int diff;
	/** the last day of the player's game */
	private int currentDay;
	
	/** the IngredientList object belonging to the player, accessible to all other classes */
	public IngredientList inventory;
	/** the RecipeCreation object belonging to the player, accessible to all other classes */
	public RecipeCreation recipe;

	/** the file that will contain the player's data */
	private CSVFile newFile;

	/**
	 * This constructor is used for the very first creation of the player. They will have
	 * predetermined default values, as initialized. 
	 * 
	 * @param playercsv the .csv file containing the data for this player
	 * @param user the id of this player
	 * @param diff the difficulty level of the game for this player
	 * @param day the day of gameplay for this player
	 */
	public Player(CSVFile playercsv, String user, int diff, int day) {
		this.playerInitials = user;
		this.diff = diff;
		this.currentDay = day;
		this.weather = 0;
		this.reputation = 0;
		this.balance = 30;
		inventory = new IngredientList(this);

		this.recipe = new RecipeCreation();
		this.newFile = playercsv;

		newFile.CSVWriter(user, diff, 1, 0, 0, 30, 0, 0, 0, 0);

	}

	/**
	 * This constructor is used to initialize a new player object with the data of an
	 * existing profile. 
	 * 
	 * @param playercsv the .csv file containing the data for this player
	 * @param playerInitials the id of this player
	 * @param diff the difficulty level of the game for this player
	 * @param day the day of gameplay for this player
	 * @param weather the weather of the last day of gameplay for the player
	 * @param reputation the reputation of the last day of gameplay for the player
	 * @param balance the player's balance including starting money and revenue
	 * @param numCones the number of cones in the player's inventory
	 * @param numSugar the number of sugar in the player's inventory
	 * @param numVanilla the number of vanilla in the player's inventory
	 * @param numCream the number of cream in the player's inventory
	 */
	public Player(CSVFile playercsv, String playerInitials, int diff, int day, double weather, double reputation,
			double balance, int numCones, double numSugar, double numVanilla, double numCream) {
		this.balance = balance;
		this.playerInitials = playerInitials;
		this.diff = diff;
		this.currentDay = day;
		this.weather = weather;
		this.reputation = reputation;
		this.inventory = new IngredientList(numCones, numCream, numSugar, numVanilla,this);
		this.newFile = playercsv;
		this.recipe = new RecipeCreation();
	}

	/**
	 * Method gets the .csv file for the player.
	 * @return newFile the .csv file containing the data for the player
	 */
	public CSVFile getNewFile() {
		return newFile;
	}
	
	/**
	 * Method gets the ID for the player.
	 * @return ID the ID of the player
	 */
	public String getPlayerInitials() {
		return playerInitials;
	}

	/**
	 * Method gets the current day for the player.
	 * @return currentDay the current day of the game of the player
	 */

	public int getDay() {
		return currentDay;
	}
	
	/**
	 * Method gets the current weather for the player.
	 * @return weather the current weather of the game of the player
	 */
	public double getWeather() {
		return weather;
	}
	
	/**
	 * Method gets the difficulty of the game for the player
	 * @return "Easy" 	the easiest level of difficulty
	 * @return "Medium" between the easiest and hardest level of difficulty
	 * @return "Hard" 	the hardest level of difficulty
	 */
	public String getDifficultyAsString() {
		if (diff == 0) {
			return "Easy";
		} else if (diff == 1) {
			return "Medium";
		} else {
			return "Hard";
		}
	}
	
	/**
	 * Method gets the reputation for the player.
	 * @return newFile the reputation of the player
	 */
	public double getReputation() {
		return reputation;
	}
	
	/**
	 * Method gets the balance for the player.
	 * @return balance the balance of the player
	 */
	public double getBalance() {
		return balance;
	}

	
	/**
	 * Setter method is used to change the player's balance during a transaction, refund or sale
	 * during the day
	 *
	 * @param price is either a positive or negative value
	 * @param type  checks what the balance change is for
	 */
	public void changeBalance(int price, String type) {
		// If purchase, remove money from balance
		if (type == "purchase") {
			balance -= price;
		}
		// If refund or sale, increase money
		else if (type == "refund" || type == "sale") {
			balance += price;
		}
	}
	
	/**
	 * Method sets the balance of the player from the .csv file when initializing an existing profile
	 * @param balance the balance of the player from their last day of gameplay
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Method sets the day of the player from the .csv file when initializing an existing profile
	 * @param day sets the day of the game
	 */

	public void setDay(int day) {
		this.currentDay = day;
	}
	

	/**
	 * Method sets the weather of the player from the .csv file when initializing an existing profile
	 * @param weather the weather of the last day of gameplay of the player
	 */
	public void setWeather(double weather) {
		this.weather = weather;
	}

	/**
	 * Method sets the ID of the player from the .csv file when initializing an existing profile
	 * @param Initials the ID of the player
	 */
	public void setPlayerInitials(String Initials) {
		this.playerInitials = Initials;
		
	}

	/**
	 * Method returns a string containing all the player's data
	 * @return a string containing all the player's data
	 */
	public String toString() {
		return "Player [balance=" + balance + ", playerInitials=" + playerInitials + ", difficulty=" + difficulty
				+ ", day=" + currentDay + ", weather=" + weather + ", reputation=" + reputation + ", diff=" + diff
				+ ", currentDay=" + currentDay + ", newFile=" + newFile + "]";
	}

}