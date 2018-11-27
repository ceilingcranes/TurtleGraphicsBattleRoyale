package main;

/**
 * An abstract class for creating objects within the game, like Turtle or Line objects. Contains location and color data.
 */
public abstract class GameObject {
    /** The current location of the object */
    protected Location currentLocation;
    /** The color of the object as a hex string (e.g. 0xFFFFFF) */
    protected String objectColor;

    /**
     * Get the color of the object
     * @return a hex string color, such as 0xFFFFFF
     */
    public String getObjectColor(){ return objectColor;}

    /**
     * Return the current location
     * @return Location object of the current location
     */
    public Location getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Set the current location of the object
     * @param l Location object that is the new location.
     */
    public void setCurrentLocation(Location l){ currentLocation = l;}
}
