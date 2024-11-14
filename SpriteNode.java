package application;

/**
 * This class creates the single linked list for the MainGamePlayClass.java class. Each node <b>
 * represents a sprite and the sprites are the customers who will buy the icecream. <b>
 * "I cooked doing this class" - Kevin Russel <b>
 * <p>
 * Sprite.java class can set the sprite nodes in order using {@link setNext} <b>
 * and can set all the sprite attributes through this class. <b>
 * <p>
 * @param <Color> String param that gives the color of the sprite- This color will determine the difficulty
 *               and can be used in the GUI.
 * @param <range> - gives the range of the sprite (Before Range additives).
 * <p>
 * @author Kevin Russel
 * CS2212 Spring 2024 term
 * Group 48
 * Prof. Servos
 * Monday April 5, 2024
 */

public class SpriteNode<Color,range> {

    /** var that gets the next node in the single linked list */
    private SpriteNode<Color,range> next;
    /** color to name the sprite */
    private Color color;
    /** this will determine if the sprite buys or does not buy */
    private  boolean buy;
    /** determines the range of price the new sprite is willing to buy */
    private range range;
    /** the range of an existing sprite whos range has been changed */
    private int newrange;

    /** Constructor for a null sprite node
     */
    public SpriteNode(){
        range = null;
        color = null;
        next = null;
    }

    /**
     * Constructor for a sprite node with known traits 
     * @param col 
     * @param range 
     */
    public SpriteNode(Color col,range range){
        this.color=col;
        this.range = range;
        next = null;
    }

    /**
     * Sets the range of a new sprite for the first time
     * @param range the new range of the sprite
     */
    public void setRange(range range) {
        this.range = range;
    }

    /**
     * Returns the range of this sprite
     * @return range 
     */
    public range getRange() {
        return range;
    }

    /**
     * Returns the color of this sprite
     * @return color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this sprite
     * @param color
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Sets the next SpriteNode object in the linked list in MainGamePlayClass.java
     * @param next the SpriteNode object following this object
     */
    public void setNext(SpriteNode<Color, range> next) {
        this.next = next;
    }

    /**
     * Returns the next sprite node in the linked lsit in MainGamePlayClass.java
     * @return next the SpriteNode object following this object
     */
    public SpriteNode<Color, range> getNext() {
        return next;
    }
    
    /**
     * Sets a new range for an existing sprite, ensuring it is within bounds
     * @param num the added value for the sprite's range
     */
    public void setNewRange(int num){
        // this condition is so that we don't get negative numbers in the buying power
        if(((int) range)+num <= 0){
            newrange = 0;
        }
        else {
            newrange= (int) range + num;
        }
    }

    /**
     * Returns the new range of the sprite
     * @return newrange
     */
    public int getNewrange(){
        return newrange;
    }

    /**
     * Returns a boolean representing whether or not the sprite will buy an icecream
     * @return true if the sprite buys and false if they don't
     */
    public boolean getbuy(){
        return this.buy;
    }

    /**
     * Sets the value of the buy variable to true or false
     * @param val
     */
    public void setBuy(boolean val){
        this.buy = val;
    }

}