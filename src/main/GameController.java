package main;

import java.util.ArrayList;

/**
 * Provides access to the gameboard and the player list, as well as running general game settings. This object
 * will act as an observer which updates each of the Players with their next moves, and retrieves information from
 * the Players for use in the UI. This Object also resets the gameboard.
 * @Author Maxine Hartnett
 */
public class GameController {
    /** The PlayerList object controlling all the players for the game.*/
    private PlayerList players;
    /** The GameBoard containing attributes and methods relating to the board size. */
    private GameBoard board;
    /** The number of units moved by a turtle in each step. */
    public static final int STEPSIZE = 10;

    /**
     * Create a new GameController object with a GameBoard of the given width and height.
     * @param canvasWidth The width of the GameBoard
     * @param canvasHeight The height of the GameBoard
     */
    public GameController(int canvasWidth, int canvasHeight){
        players = new PlayerList();
        board = new GameBoard(canvasWidth,canvasHeight);
    }

    /**
     * @return PlayerList for game
     */
    public PlayerList getPlayers(){
        return this.players;
    }

    /**
     * Function that returns all objects following a step of the game. This function updates the turtle locations,
     * and lines, before getting all objects controlled by each player and returning them all.
     * @return ArrayList of GameObjects, corresponding to all the GameObjects in play.
     */
    public ArrayList<GameObject> getObjects(){
        // call player.update, get objects from each player, then return the full list. The
        // view controller then gets the location and color of each gameobject and uses this to
        // create a new "step" of the board.
        players.updatePlayers();
        boolean bounced;
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for (Player p: players.getPlayerList()){
            for (GameObject obj: p.getPlayerObjects()){
                bounced = checkObjectLocation(obj);
                if (bounced) // Reset the line if it hit a wall
                    p.resetLine();
            }
            gameObjects.addAll(p.getPlayerObjects());
        }

        return gameObjects;
    }

    /**
     * For all players, go through and see if the player has lost. A player "loses" if they get within 1/2 of a step
     * size of another player's turtle or line.
     * @return Losing Player if there has been a loss, null otherwise.
     */
    public Player checkLose(){
        Player loser = null;
        for (Player p : players.getPlayerList()){
            Location turtleLoc = p.getPlayerTurtle().getCurrentLocation();
            for (Player checkPlayer: players.getPlayerList()){
                if (checkPlayer != p) {
                    boolean lose = checkPlayer.checkLocation(turtleLoc);
                    if (lose) {
                        loser = p;
                    }
                }
            }
        }
        return loser;
    }

    /**
     * Reset all players back to initial positions, and remove all lines.
     */
    public void resetGame(){
        players.resetAllPlayers();
    }

    /**
     * Given a GameObject, check to see if the object is past the bounds of the gameboard. If it is, set the location
     * back to the bounds of the object, and reverse the orientation, creating a "bouncing" effect
     * @param obj GameObject which will be tested for location errors
     * @return True if the object was past the bounds, and false otherwise.
     */
    public boolean checkObjectLocation(GameObject obj){ // Return a boolean if a bounce happens so player can update line
        Location currentLocation = obj.getCurrentLocation();
        boolean wasChanged = false;
        if (obj instanceof Turtle) {
            if (currentLocation.getXLocation() < 0) {
                obj.setCurrentLocation(new Location(0, currentLocation.getYLocation(), -currentLocation.getOrientation()));
                wasChanged = true;
            }
            if (currentLocation.getXLocation() >= board.getWidth() - ViewApplication.IMAGE_SIZE) {
                obj.setCurrentLocation(new Location(board.getWidth() - ViewApplication.IMAGE_SIZE,
                        currentLocation.getYLocation(), -currentLocation.getOrientation()));
                wasChanged = true;
            }
            if (currentLocation.getYLocation() < 0) {
                obj.setCurrentLocation(new Location(currentLocation.getXLocation(), 0, -currentLocation.getOrientation()));
                wasChanged = true;
            }
            if (currentLocation.getYLocation() >= board.getHeight() - ViewApplication.IMAGE_SIZE) {
                obj.setCurrentLocation(new Location(currentLocation.getXLocation(), board.getHeight() -
                        ViewApplication.IMAGE_SIZE, -currentLocation.getOrientation()));
                wasChanged = true;
            }
        }
        return wasChanged;
    }
}
