package main;

/**
 * Tracks parameters and methods related to the size and shape of the gameboard in use.
 */
public class GameBoard {
    /** The width of the board */
    private int width;
    /** The height of the board */
    private int height;

    /** Create a new game board
     * @param width The width of the board
     * @param height the height of the board
     */
    public GameBoard(int width, int height){
        this.height = height;
        this.width = width;
    }

    /**
     * Get the board width
     * @return integer board width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Update the width of the board
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Return the board height
     * @return The integer height of the board
     */
    public int getHeight() {
        return height;
    }

    /** Set the board height
     * @param height The new height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Check to see if the location given is valid on the board
     * @param l: Location to check
     * @return: True if a valid location, False if invalid
     */
    public boolean checkLocationValidity(Location l){
        boolean valid = true;
        if (l.getXLocation() >= width || l.getXLocation() < 0){
            valid = false;
        }
        if (l.getYLocation() >= height || l.getYLocation() < 0){
            valid = false;
        }
        return valid;
    }
}
