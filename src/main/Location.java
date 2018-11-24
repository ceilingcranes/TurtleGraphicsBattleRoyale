package main;

public class Location {
    // TODO: Make this a float? Allow for angles beyond 90? Depends on system for drawing gameboard.
    private int xLocation;
    private int yLocation;
    private int orientation;

    public Location(int startX, int startY){
        this.xLocation = startX;
        this.yLocation = startY;
        this.orientation = 0;
    }

    public Location(int startX, int startY, int startOrientation){
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

    public int getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
        if (this.orientation < 0){
            this.orientation += 360;
        }
        if (this.orientation > 360){
            this.orientation -= 360;
        }
    }

    public void updateOrientation(int turn){
        orientation += turn;
    }

    /**
     * Step 1 stepsize in the given direction
      * @param stepSize The number of units to move in a step
     */
    public void takeStep(int stepSize){
        // X+Y = stepSize
        // Y/X = orientation
        // X = stepSize - Y
        // Y/(stepSize - Y) = orientation

        int yChange = orientation * stepSize/(orientation+1);
        int xChange = stepSize - yChange;

        this.xLocation += xChange;
        this.yLocation += yChange;

    }

    public String toString(){
        return "["+xLocation+", "+yLocation + "], "+orientation+" degrees.";
    }
}
