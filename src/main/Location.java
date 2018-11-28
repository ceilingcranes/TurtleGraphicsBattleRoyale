package main;

/**
 * Location object corresponding to a pair of coordinates and an orientation. This object is used by all game elements
 * to determine location, distance, equality, and other attributes related to physical location.
 *
 * The orientation attribute is set such that 0 is straight up, and it assumes the inputs are in degrees, with a maximum
 * of 360. Any inputs greater than 360 or less than 0 will loop around, keeping the range between 0 and 360. Positive
 * angles go around clockwise, while negative angles are counterclockwise.
 */
public class Location implements Comparable<Location> {
    /** X coordinate of location */
    private double xLocation;
    /** Y coordinate of location */
    private double yLocation;
    /** The angle of a given object, with 0 being straight up, positive numbers going clockwise, and negative
     * numbers going counter-clockwise.
     */
    private double orientation;

    /** Create a new location with the given coordinates and an orientation of 0.
     * @param startX Starting X coordinate
     * @param startY Starting Y coordinate
     */
    public Location(double startX, double startY){
        this.xLocation = startX;
        this.yLocation = startY;
        this.orientation = 0;
    }

    /** Create a new location based off the information of the given Location object, creating a copy of the
     * data from a location object.
     * @param l Location to copy
     */
    public Location(Location l){
        this.xLocation = l.getXLocation();
        this.yLocation = l.getYLocation();
        this.orientation = l.getOrientation();
    }

    /**
     * Create a new location with the given coordinates and orientation.
     * @param startX Starting X coordinate
     * @param startY Starting Y coordinate
     * @param startOrientation Starting orientation, with zero being straight up, and positive numbers going clockwise.
     */
    public Location(double startX, double startY, double startOrientation){
        this.xLocation = startX;
        this.yLocation = startY;
        this.orientation = startOrientation;
    }

    /**
     * Return the distance between location and another location
     * @param l Location object to find distance to
     * @return Distance from l -> current Location
     */
    public double distance(Location l){
        return Math.sqrt(Math.pow(l.getXLocation() - this.xLocation,2)+
                Math.pow(l.getYLocation() - this.yLocation, 2));
    }

    /**
     * Get the X coordinate of the location
     * @return The X coordinate of the location as a double.
     */
    public double getXLocation() {
        return xLocation;
    }

    /**
     * Update the X coordinate of the location
     * @param xLocation The new X coordinate
     */
    public void setXLocation(double xLocation) {
        this.xLocation = xLocation;
    }

    /**
     * Get the Y coordinate of the location
     * @return The Y coordinate of the location as a double
     */
    public double getYLocation() {
        return yLocation;
    }

    /**
     * Set the Y coordinate of the location
     * @param yLocation The new Y coordinate
     */
    public void setYLocation(double yLocation) {
        this.yLocation = yLocation;
    }

    /**
     * Get the orientation of the location.
     * @return The orientation
     */
    public double getOrientation() {
        return orientation;
    }

    /**
     * Update the orientation. Will set orientation to the given value, and will loop around when given a number that is
     * less than 0 or greater than 360. For example, if given 365, the orientation will be set to 5.
     * @param orientation The new orientation
     */
    public void setOrientation(double orientation) {
        this.orientation = orientation;
        if (this.orientation < 0){
            this.orientation += 360;
        }
        if (this.orientation >= 360){
            this.orientation -= 360;
        }
    }

    /**
     * Add turn degrees to the orientation. If the turn puts the value above 360 or below 0, the number will wrap
     * around to keep it in that range.
     * @param turn The number of degrees to add to the orientation, with positive numbers being a clockwise turn and
     *             negative being a counter clockwise turn.
     */
    public void updateOrientation(double turn){
        this.setOrientation(orientation + turn);
    }

    /**
     * Step 1 stepsize in the given direction, calculated by finding the X and Y changes that will sum to stepSize
     * while maintaining the correct angle (i.e., tan(angle) = Y/X)
      * @param stepSize The number of units to move in a step
     */
    public void takeStep(int stepSize){
        // X+Y = stepSize
        // Y/X = tan(orientation)
        // X = stepSize - Y

        double yChange =(stepSize*Math.cos(Math.toRadians(orientation)));
        double xChange = (stepSize * Math.sin(Math.toRadians(orientation)));

        this.xLocation += xChange;
        this.yLocation -= yChange;
    }

    /** Compare the equality of two locations.
     * Will only check to see if the coordinates of this Location and the given Location L2 match, without comparing
     * orientation. If they are equal, this will return 0, and otherwise, this will return -1.
     * @param l2 The second Location to compare coordinates
     * @return 0 if the two locations have the same X and Y values, -1 otherwise.
     */
    public int compareTo(Location l2){
        if (l2.getXLocation() == xLocation && l2.getYLocation() == yLocation){
            return 0;
        }
        else return -1;
    }

    /**
     * Convert the location to a string.
     * @return String containing Location data.
     */
    public String toString(){
        return "["+xLocation+", "+yLocation + "], "+orientation+" degrees.";
    }
}
