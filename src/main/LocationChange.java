package main;

public class LocationChange {
    private final int MOVE;
    private final int TURN;

    public LocationChange(int move, int turn){
        this.MOVE = move;
        this.TURN = turn;
    }

    public int getMove(){
        return MOVE;
    }

    public int getTurn(){
        return TURN;
    }
}
