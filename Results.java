package application;

/**
* This class is used in MainGamePlayClass to determine the result of the game 
*<p>
*
* @author Kevin Russel
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
public class Results {
	/** stores the cash as it updates */
	public static double dayCash[];
	/** stores the number of items in the cash array */
	private int cashcounter;

	/** stores the reputation as it updates */
	public static double repArray[];
	/** stores the number of items in the reputation array */
	private int repcounter;

	/** stores the amount of sugar as it updates*/
	public static double SugarArray[];
	/**stores the number of items in the sugar array */
	private int sugarCounter;
	
	/** stores the amount of cream as it updates*/
	public static double CremeArray[];
	/**stores the number of items in the cream array */
	private int CremeCounter;

	/** stores the number of cones as it updates*/
	public static double ConeArray[];
	/**stores the number of items in the cone array */
	private int ConeCounter;

	/** stores the amount of vanilla as it updates*/
	public static double VanArray[];
	/**stores the number of items in the vanilla array */
	private int VanCounter;

	/**
	 * Constructor setting reputation and cash counters to 0
	 */
	Results() {
		repcounter = 0;
		cashcounter = 0;
	}

	/**
	 * Constructor for results object
	 * @param startDayrep reputation for the first day
	 * @param startDayCash balance for the first day
	 * @param sugar 
	 * @param creme
	 * @param cones
	 * @param van
	 */
	public Results(double startDayrep, double startDayCash,double sugar,double creme,double cones,double van) {

		repcounter = 0;
		cashcounter = 0;
		sugarCounter = 0;
		CremeCounter = 0;
		ConeCounter = 0;
		VanCounter = 0;


		dayCash = new double[11];
		dayCash[0] = startDayCash;
		cashcounter = cashcounter + 1;


		repcounter = repcounter + 1;
		repArray = new double[11];
		repArray[0] = startDayrep;

		sugarCounter ++;
		SugarArray = new double[11];
		SugarArray[0] = sugar;

		CremeCounter ++;
		CremeArray= new double[11];
		CremeArray[0] = creme;

		ConeCounter ++;
		ConeArray= new double[11];
		ConeArray[0] = cones;

		VanCounter ++;
		VanArray= new double[11];
		VanArray[0] = van;


	}

	/**
	 * Adds cash to array and increment counter
	 * @param cash
	 */
	public void addCash(double cash) {
		dayCash[cashcounter] = cash;
		cashcounter++;
	}

	/**
	 * Adds reputation to array and increment counter
	 * @param rep
	 */
	public void addRep(double rep) {
		repArray[repcounter] = rep;
		repcounter++;
	}

	/**
	 * Decreases sugar from array and increment counter
	 * @param sugar
	 */
	public void decreaseSugar(double sugar){
		SugarArray[sugarCounter] = sugar;
		sugarCounter++;
	}

	/**
	 * Decreases cream from array and increment counter
	 * @param cream
	 */
	public void decreaseCreme(double creme){
		CremeArray[CremeCounter] = creme;
		CremeCounter++;
	}

	/**
	 * Decreases vanilla from array and increment counter
	 * @param vanilla
	 */
	public void decreaseVan(double van){
		VanArray[VanCounter] = van;
		VanCounter++;
	}

	/**
	 * Decreases cones from array and increment counter
	 * @param cones
	 */
	public void decreaseCones(double cones){
		ConeArray[ConeCounter] = cones;
		ConeCounter++;
	}

	/**
	 * Returns dayCash
	 * @return dayCash
	 */
	public double[] getDayCash() {
		return dayCash;
	}

	/**
	 * Returns reputation array
	 * @return repArray
	 */
	public double[] getRepArray() {
		return repArray;
	}

	/**
	 * Prints results for the day
	 */
	public void printResults() {
		System.out.println("Here are the Cash");
		for (int i = 0; i < 11; i++) {
			System.out.print(dayCash[i] + " ");
		}

		System.out.println("Here are the rep");
		for (int i = 0; i < 11; i++) {
			System.out.print(repArray[i] + " ");
		}
	}
}