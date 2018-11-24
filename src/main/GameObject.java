package main;


public abstract class GameObject {
    protected Location currentLocation;
    protected String objectColor;
    public String getObjectColor(){ return objectColor;}
    public Location getCurrentLocation() {
        return currentLocation;
    }
    public void setCurrentLocation(Location l){ currentLocation = l;}
}
