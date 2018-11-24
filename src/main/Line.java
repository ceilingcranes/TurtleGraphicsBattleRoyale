package main;

public class Line extends GameObject{
    private Location startLocation;
    private Location endLocation;
    private double length=0;

    /**
     * Initialize a line that goes from (xStart, yStart) to (xEnd, yEnd)
     * @param xStart x coordinate of start of line
     * @param yStart y coordinate of start of line
     * @param xEnd x coordinate of end of line
     * @param yEnd y coordinate of end of line
     */
    public Line(int xStart, int yStart, int xEnd, int yEnd, String color){
        startLocation = new Location(xStart, yStart);
        endLocation = new Location(xEnd, yEnd);
        length = startLocation.distance(endLocation);
        currentLocation = startLocation;
        objectColor = color;
    }

    public Line(Location startLocation, Location endLocation, String color){
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        currentLocation = startLocation;
        objectColor = color;
    }

    /**
     * Given a Location checkLocation, check to see if the location falls on the Line (within STEPSIZE units)
     * @param checkLocation Location to test
     * @return True if Location checkLocation falls on the line, False if Location checkLocation
     */
    public boolean checkLocation(Location checkLocation){
        // If dist(A, C) + dist(B, C) == dist(A, B), C lies on the line between A and B
        double onLine = startLocation.distance(checkLocation) + endLocation.distance(checkLocation);
        // dist(A, C) or dist(B, C) will be greater
        if(onLine - length < GameController.STEPSIZE){
            return true;
        }
        return false;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
        length = startLocation.distance(endLocation);
    }

    public double getLength() {
        return length;
    }

}
