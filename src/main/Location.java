package main;

public class Location implements Comparable<Location> {
    // TODO: Make this a float? Allow for angles beyond 90? Depends on system for drawing gameboard.
    private int xLocation;
    private int yLocation;
    private int orientation;

    public Location(int startX, int startY){
        this.xLocation = startX;
        this.yLocation = startY;
        this.orientation = 0;
    }

    public Location(Location l){
        this.xLocation = l.getXLocation();
        this.yLocation = l.getYLocation();
        this.orientation = l.getOrientation();
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
        // Y/X = tan(orientation)
        // X = stepSize - Y

        int yChange = 0;
        int xChange = 0;

        // orientation corresponds to unit circle
        switch(orientation){
            case 0:
                xChange = stepSize;
                break;
            case 90:
                xChange = 0;
                break;
            case 180:
                xChange = -stepSize;
                break;
            case 270:
                xChange = 0;
                break;
            default:
//                yChange =(int)((Math.tan(orientation)*stepSize)/(1+Math.tan(orientation)));
//                xChange = stepSize - yChange;
                xChange = (int)(stepSize/(Math.tan(Math.toRadians(orientation))+1));
        }

        if(orientation >= 0 && orientation<90){
            System.out.println("Between 0 and 90!");
            yChange = stepSize - Math.abs(xChange);
        }
        if(orientation >= 90 && orientation < 180){
            yChange = stepSize + Math.abs(xChange);
        }
        if (orientation >=180 && orientation < 270){
            yChange = -stepSize - Math.abs(xChange);
        }
        if (orientation >= 270 && orientation < 360){
            yChange = Math.abs(xChange) - stepSize;
        }
//        int yChange = or * stepSize/(or+1);

        System.out.println("("+orientation+") Moving "+xChange+" units by "+yChange+" units.");
        this.xLocation += xChange;
        this.yLocation += yChange;

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
