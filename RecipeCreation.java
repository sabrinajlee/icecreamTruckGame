package application;
/**
 * RecipeCreation class handles the ratios of ingredients required to make an ice cream cone.
 * <p>
 * It will incorporate signals from the sliders in the GUI to incr/decr the amount of <br>
 * cream, sugar, and vanilla will be in each ice cream cone. The class will additionally <br>
 * verify whether a recipe surpasses the quantity of any ingredient present in the player's <br>
 * inventory and consequently limit any further increment.
 * <p>
 * 
 * @author Matthew Molloy
 * @author Sabrina Lee
 * CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
 */
public class RecipeCreation {
    /** Stores the measurement of cream to be used in one cone */
    private double creamMes;
    /** Stores the measurement of sugar to be used in one cone */
    private double sugarMes;
    /** Stores the measurement of vanilla to be used in one cone */
    private double vanillaMes;

    /** the max price of a cone that a user can select */
    final double maxConePrice = 10;
    /** the min price of a cone that a user can select */
    final double minConePrice = 0;
    /** the price of a cone of icecream */
    private double conePrice;

    
    /**
     * Constructor initializes measurements of all ingredients
     */
    public RecipeCreation() {
    	creamMes = 0;
    	sugarMes = 0;
    	vanillaMes = 0;
    	conePrice = 0;
    }
    
    /**
     * Used to set cream measurement for recipe from RecipeCreationScreen.java
     * @param cream the measurement of cream to set the recipe to
     */
    public void setCreamMes(double cream) {
        creamMes = cream;
    }

    /**
     * Returns the measurement of cream in the recipe
     * @return Cream in inventory
     */
    public double getCreamMes() {
        return creamMes;
    }

    /**
     * Used to set sugar measurement for recipe from RecipeCreationScreen.java
     * @param sugar the measurement of sugar to set the recipe to
     */
    public void setSugarMes(double sugar) {
        sugarMes = sugar;
    }

    /**
     * Returns the measurement of sugar in the recipe
     * @return sugarMes Sugar in inventory
     */
    public double getSugarMes() {
        return sugarMes;
    }

    /**
     * Used to set vanilla measurement for recipe from RecipeCreationScreen.java
     * @param vanilla is the measurement of vanilla to set the recipe of an icecream cone to
     */
    public void setVanillaMes(double vanilla) {
        vanillaMes = vanilla;
    }

    /**
     * Returns the measurement of vanilla in the recipe
     * @return Vanillames is the measurement of vanilla to be used in recipe
     */
    public double getVanillaMes() {
        return vanillaMes;
    }

    /**
     * Used to set price for a cone of ice cream from RecipeCreationScreen.java
     * @param price the price of a cone of icecream
     */
    public void setConePrice(double price) {
        conePrice = price;
    }

    /**
     * Returns the price for a cone of ice cream
     * @return Price for one cone of ice cream
     */
    public double getConePrice() {
        return conePrice;
    }


}