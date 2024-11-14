package application;
/**
 * This class stores each purchase that the player transacts from the Ingredient Selection screen <br>
 * Each transaction will be put on a stack so that players can undo their purchases<br><br>
 *<p>
 * The stack's contents should be cleared once the player starts the daytime cycle
 *
 * @author Matthew Molloy
 * CS2212 Spring 2024 term
 * Group 48
 * Prof. Servos
 * Monday April 1, 2024
 */
public class Transaction {
	/** represents the name of the ingredient being bought/refunded */
    private String ingredient;
    /** the number of ingredients involved in the transaction */
    private int quantity;
    /** the cost of the total amount of ingredients in the transaction */
    private int price;

    /**
     * Constructor initializes instance variables
     * @param ingredient
     * @param quantity
     * @param price
     */
    public Transaction(String ingredient, int quantity, int price) {
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the ingredient that was bought/refunded
     * @return a String representing the ingredient
     */
    public String getIngredient() {
        return ingredient;
    }

    /**
     * Returns the number of ingredients in the transaction
     * @return quantity an integer amount 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Returns the cost of the amount of the ingredients in the transaction
     * @return price an integer representing the cost of a number of ingredients
     */
    public int getPrice() {
        return price;
    }
}