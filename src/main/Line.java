package main;

/**
 * Object containing methods and data related to the Lines controlled by a player. Since it extends GameObject,
 * all Line objects also include currentLocation and color attributes. The currentLocation is set to the endpoint of
 * the line.
 */
public class Line extends GameObject{
    /** The start point of the line */
    private Location startLocation;
    /** The end point of the line */
    private Location endLocation;
    /** The length of the line */
    private double length=0;

    /**
     * Initialize a line that goes from (xStart, yStart) to (xEnd, yEnd)
     * @param xStart x coordinate of start of line
     * @param yStart y coordinate of start of line
     * @param xEnd x coordinate of end of line
     * @param yEnd y coordinate of end of line
     * @param color The color of the line, a String hex value (e.g. "0xFFFFFF")
     */
    public Line(int xStart, int yStart, int xEnd, int yEnd, String color){
        startLocation = new Location(xStart, yStart);
        endLocation = new Location(xEnd, yEnd);
        length = startLocation.distance(endLocation);
        currentLocation = endLocation;
        objectColor = color;
    }

    /**
     * Initialize a line that goes from startLocation to endLocation
     * @param startLocation The starting Location of the line
     * @param endLocation The initial ending Location of the line
     * @param color The color of the line, a String hex value (e.g. "0xFFFFFF")
     */
    public Line(Location startLocation, Location endLocation, String color){
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        currentLocation = endLocation;
        objectColor = color;
    }

    /**
     * Given a Location checkLocation, check to see if the location falls on the Line (within STEPSIZE/2 units)
     * @param checkLocation Location to test
     * @return True if Location checkLocation falls on the line, False if Location checkLocation
     */
    public boolean checkLocation(Location checkLocation){
        // If dist(A, C) + dist(B, C) == dist(A, B), C lies on the line between A and B
        double onLine = startLocation.distance(checkLocation) + endLocation.distance(checkLocation);
        // dist(A, C) or dist(B, C) will be greater
        if(onLine - length < GameController.STEPSIZE/2){
            return true;
        }
        return false;
    }

    /**
     * Return the start location
     * @return Location of the start of the line
     */
    public Location getStartLocation() {
        return startLocation;
    }

    /**
     * Return the end location
     * @return Location of the end of the line
     */
    public Location getEndLocation() {
        return endLocation;
    }

    /**
     * Set the end location of the line.
     * @param endLocation Location object of new end.
     */
    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
        length = startLocation.distance(endLocation);
        this.currentLocation = endLocation;
    }

    /**
     * Get the total length of the line.
     * @return double with the distance from startLocation to endLocation
     */
    public double getLength() {
        return length;
    }

}
