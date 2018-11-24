package main;

/**
 * Tracks parameters and methods related to the size and shape of the gameboard in use.
 */
public class GameBoard {
    private int width;
    private int height;

    public GameBoard(int width, int height){
        this.height = height;
        this.width = width;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

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
