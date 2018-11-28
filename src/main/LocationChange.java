package main;

/**
 * A class to store a change in location in both number of steps and degrees to turn. Although it is generally expected
 * to either take steps or to turn in a given move, this isn't restricted, so move and turn could both be set.
 */
public class LocationChange {
    /** The number of steps to take for this LocationChange */
    private final int MOVE;
    /** The number of degrees to turn */
    private final int TURN;

    /**
     * Create a new LocationChange object
     * @param move Number of steps to take
     * @param turn Number of degrees to turn
     */
    public LocationChange(int move, int turn){
        this.MOVE = move;
        this.TURN = turn;
    }

    /**
     * Return the number of steps
     * @return the number of steps
     */
    public int getMove(){
        return MOVE;
    }

    /**
     * Return the number of degrees to turn
     * @return the number of degrees to turn
     */
    public int getTurn(){
        return TURN;
    }
}
