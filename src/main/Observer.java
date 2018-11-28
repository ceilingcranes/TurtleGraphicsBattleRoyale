package main;

/** Interface for use in Observer pattern, requiring all observers (here Players) to have the "update" method. */
public interface Observer {
    /** Require an update method that takes a step when the updateObservers method is called by the Subject. */
    public void update();
}
