package main;

public class Location implements Comparable<Location> {
    private double xLocation;
    private double yLocation;
    private double orientation;

    public Location(double startX, double startY){
        this.xLocation = startX;
        this.yLocation = startY;
        this.orientation = 0;
    }

    public Location(Location l){
        this.xLocation = l.getXLocation();
        this.yLocation = l.getYLocation();
        this.orientation = l.getOrientation();
    }
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

    public double getXLocation() {
        return xLocation;
    }

    public void setXLocation(int xLocation) {
        this.xLocation = xLocation;
    }

    public double getYLocation() {
        return yLocation;
    }

    public void setYLocation(int yLocation) {
        this.yLocation = yLocation;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
        if (this.orientation < 0){
            this.orientation += 360;
        }
        if (this.orientation >= 360){
            this.orientation -= 360;
        }
    }

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

    public int compareTo(Location l2){
        if (l2.getXLocation() == xLocation && l2.getYLocation() == yLocation){
            return 0;
        }
        else return -1;
    }
    public String toString(){
        return "["+xLocation+", "+yLocation + "], "+orientation+" degrees.";
    }
}
