
package application;

/**
 * This class represents the player's reputation to customers. 
*<p>
*
* @author Kevin Russel
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/

public class Reputation {
	/** the reputation of the icecream truck */
    private double currReputation;

    /**
     * Constructor that sets reputation to for a new profile
     */
    public Reputation(){
        currReputation = 0;
    }
    
    /**
     * Constructor that sets the reputation from the csv
     * @param rep the reputation of the last day of the profile
     */
    public Reputation(double rep){
        this.currReputation = rep;
    }

    /**
     * this class sets the reputation of the shop
     * @param Repthis is how much the currReputation is going to change by.
     */
    public void setReputation (double Rep){
        this.currReputation =  currReputation+ Rep;
    }
   
    /**
     * Method that returns the reputation
     * @return the reputation
     */
    public double getReputation(){
    	return currReputation;
    }

}