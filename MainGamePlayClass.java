package application;

/**
* This class implements the gameplay through interaction with sprites <b>
* and player choices with recipe creation. The player's inventory and balance <b>
* are updated when a sale is made. Sprite decisions on whether to purchase <b>
* icecreams are also made here.
*<p>
*
* @author Kevin Russel
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/


public class MainGamePlayClass {
	/** this is for the sprite linkedlist head. */
	private SpriteNode head;
	/** this is for the sprite linked list tail */
	private SpriteNode tail;

	/** the static method is for the order in which the sprites buy */
	public static boolean SpriteOrder[] = new boolean[10];

	/** simply a placeholder for the currentPlayer variable */
	private Player currentPlayer;

	/**
	 * Constructor that does the calculations in the back-end to see if the sprite buys or not.
	 * @param player the player object with all the values from the excel sheet.
	 */
	public MainGamePlayClass(Player player) {

		// sets the parameter to this player object.
		this.currentPlayer = player;
		// this sets the current day using the excel sheet.
		currentPlayer.setDay(Integer.parseInt(currentPlayer.getNewFile().returnLastDay()[2]));
		// this gets a number of the max ice cremes we can sell without ingredients going into negatives.
		int iceCreameCounter = currentPlayer.inventory.getMaxSellableProduct();

		// this gets the weather and how much the weather is going to affect the sprite range.
		Weather weather = new Weather();

		// this is to get the actual degrees that the weather is.
		double weatherNum = (double) weather.getWeatherInt()[2];

		// to set the weather of the current game.
		currentPlayer.setWeather((double)weather.getWeatherInt()[0]);

		// this is for the reputation. As reputation also has an affect on the sprite buying or not.
		Reputation rep = new Reputation(Double.parseDouble(currentPlayer.getNewFile().returnLastDay()[4]));

		// this method is to change the values on screen in real time when the sprite buys.
		Results results = new Results(rep.getReputation(), currentPlayer.getBalance(), currentPlayer.inventory.getSugar(),currentPlayer.inventory.getCream(),currentPlayer.inventory.getCones(),currentPlayer.inventory.getVanilla());
		// getting the cone price that the user set it to
		double iceCremePrice = currentPlayer.recipe.getConePrice();
		// getting the creme quantity the user set it to.
		double currCreme = currentPlayer.inventory.getCream();
		// getting the vanilla amount the user set it to
		double currVan = currentPlayer.inventory.getVanilla();
		// getting the sugar amount the user set it to.
		double currSugar = currentPlayer.inventory.getSugar();



		// this is going to be a customer counter for the sprite, can't let it go over
		// 20.
		int customerCounter;

		head = null;
		tail = null;

		// this gets the Sprites hardness, then creates a new application.Sprite Linked
		// list.
		for (customerCounter = 0; customerCounter < 10; customerCounter++) {
			int range;
			String hard;

			// this is to get a range back for each sprite.
			int test = (int) SpriteHardnessMaker();

			// if else statment that will return a specific range "starting" value for each sprite.
			if (test == 1) {
				range = -1;
				hard = " red ";
			} else if (test == 2) {
				range = 1;
				hard = " blue ";
			} else if (test == 3) {
				range = 2;
				hard = " green ";
			} else if (test == 4) {
				range = 3;
				hard = " black ";
			} else {
				range = 4;
				hard = " gold ";

			}


			// this part is to put that into a node.
			SpriteNode newNode = new SpriteNode(hard, range);

			// first case when there is no nodes in the list.
			if (head == null) {
				head = newNode;
				tail = newNode;
				newNode.setNewRange((int) weatherNum);
			}
			// second case when there is a head node
			// we first have to traverse the linked list
			else {
				// setting this to the head node
				SpriteNode currNode = head;
				while (currNode.getNext() != null) {
					currNode = currNode.getNext();
				}
				currNode.setNext(newNode);
				tail = newNode;
				// This is setting up the difficulty.
				newNode.setNewRange((int) (weatherNum + rep.getReputation())-(Integer.parseInt(currentPlayer.getNewFile().returnLastDay()[1])));
			}
		}

		SpriteNode currNode = head;
		while (currNode != null) {


			// this is an if statement to stimulate if they will buy or not.
			if (iceCreameCounter > 0 && 0 < currNode.getNewrange() && currNode.getNewrange() >= iceCremePrice) {

				// if they buy, the number of available ice cremes goes down by 1.
				iceCreameCounter = iceCreameCounter - 1;
				// everytime they buy, the store reputation goes up by 0.5
				rep.setReputation(0.5);

				// adding this reputation to the array for the graphs to be created.
				results.addRep(rep.getReputation());

				// the number of cones are going down by 1.
				currentPlayer.inventory.setCones(currentPlayer.inventory.getCones() -1);
				// this is for the real time decrease on screen of cones
				results.decreaseCones(currentPlayer.inventory.getCones());

				// the number of creme is going down by the value the user set it by
				currentPlayer.inventory.setCream(currCreme - currentPlayer.recipe.getCreamMes());
				// putting this new value into the number of cremes that are left.
				currCreme = currentPlayer.inventory.getCream();
				// this is for the real time decrease of creme on screen.
				results.decreaseCreme(currCreme);

				//  decreaseing the number of vanilla by the value the user set it by.
				currentPlayer.inventory.setVanilla(currVan - currentPlayer.recipe.getVanillaMes());
				// putting the new value into the number of vanilla that are left.
				currVan = currentPlayer.inventory.getVanilla();
				results.decreaseVan(currVan);
				// this is for the real time decrease of vanilla on screen.
				currentPlayer.inventory.setSugar(currSugar - currentPlayer.recipe.getSugarMes());
				// this is for the current sugar in the inventory.
				currSugar = currentPlayer.inventory.getSugar();

				// decrease the sugar for the real time analysis.
				results.decreaseSugar(currSugar);

				// this sets the currentplayer balance.
				currentPlayer.setBalance(currentPlayer.getBalance() + currentPlayer.recipe.getConePrice());
				results.addCash(currentPlayer.getBalance());
				// setting true if they buy for the array for the animation.
				currNode.setBuy(true);
			} else {


				// if they don't buy, the reputation goes down by 0.25
				rep.setReputation(-0.25);
				// deoesn't change the cash/reputation/#of icecreme/vanilla/sugar/creme for real time/
				results.addCash(currentPlayer.getBalance());
				results.addRep(rep.getReputation());
				results.decreaseCones(iceCreameCounter);
				results.decreaseVan(currentPlayer.inventory.getVanilla());
				results.decreaseSugar(currentPlayer.inventory.getSugar());
				results.decreaseCreme(currentPlayer.inventory.getCream());
				currNode.setBuy(false);

			}
			// points to the next node.
			currNode = currNode.getNext();
		}

		// this is to traverse the linked list to create an array for the animation.
		SpriteNode BooleanNode = head;
		int arrayCount = 0;
		while (BooleanNode != null) {
			// this is an if statement to stimulate if they will buy or not.
			if (!BooleanNode.getbuy()) {
				// if the sprite does not buy, the arrya at that index gets set to false.
				SpriteOrder[arrayCount] = false;
			} else {
				// if the sprite does buy, the array at that index gets set to true.
				SpriteOrder[arrayCount] = true;
			}
			// increments the array
			arrayCount = arrayCount + 1;

			// traverses the linked list.
			BooleanNode = BooleanNode.getNext();
		}

		// this just writes to the excel files.
		currentPlayer.getNewFile().CSVWriter(currentPlayer.getPlayerInitials(),
				Integer.parseInt(currentPlayer.getNewFile().returnLastDay()[1]),
				Integer.valueOf(currentPlayer.getNewFile().returnLastDay()[2]) + 1,(int) currentPlayer.getWeather(),
				rep.getReputation(), currentPlayer.getBalance(), currentPlayer.inventory.getCones(),
				currentPlayer.inventory.getSugar(), currentPlayer.inventory.getVanilla(),
				currentPlayer.inventory.getCream());

	}

	/**
	 * Function that figures out the sprite hardness
	 * 
	 * @return a number between 1 and 6 to indicate how small the range of buying
	 *         is.
	 */
	public double SpriteHardnessMaker() {
		return ((Math.random() * (5)) + 1);
	}
}