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

    // Step 1 unit in the given direction 
    public void takeStep(){
        // 0 == right
        // 90 == up
        // 180 == left
        // 270 == down
        int change = orientation/90;
        switch (change){
            case 0: this.xLocation++;
            case 1: this.yLocation++;
            case 3: this.xLocation--;
            case 4: this.yLocation--;
        }
    }
    public String toString(){
        return "["+xLocation+", "+yLocation + "], "+orientation+" degrees.";
    }
}
