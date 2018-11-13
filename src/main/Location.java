package main;

public class Location {
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
    }

    public void updateOrientation(int turn){
        orientation += turn;
    }

    // Step 1 unit in the given direction 
    public void makeStep(){

    }
    public String toString(){
        return "["+xLocation+", "+yLocation + "], "+orientation+" degrees.";
    }
}
