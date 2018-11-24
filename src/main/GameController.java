package main;

import javax.swing.text.View;
import java.util.ArrayList;

/**
 * Provides access to the gameboard and the player list.
 * @Author Maxine Hartnett
 */
public class GameController {
    // TODO: Implement observer pattern that goes through and updates each Player to get the new location of all
    // GameObjects.
    private PlayerList players;
    private GameBoard board;
    public static final int STEPSIZE = 10;

    public GameController(){
        players = new PlayerList();
        board = new GameBoard(100,100);
    }

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
     * @return
     */
    public ArrayList<GameObject> getObjects(){
        // call player.update, get objects from each player, then return the full list. The
        // view controller then gets the location and color of each gameobject and uses this to
        // create a new "step" of the board.
        updatePlayers();
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        for (Player p: players.getPlayerList()){
            for (GameObject obj: p.getPlayerObjects()){
                checkObjectLocation(obj);
            }
            gameObjects.addAll(p.getPlayerObjects());
        }

        return gameObjects;
    }

    public Player checkWin(){
        // go through each player's turtle, and see if its current location is controlled by another player.
        // return null if no win.
        Player winner = null;
        for (Player p : players.getPlayerList()){
            Location turtleLoc = p.getPlayerTurtle().getCurrentLocation();
            for (Player checkPlayer: players.getPlayerList()){
                boolean win = checkPlayer.checkLocation(turtleLoc);
                if (win){
                    winner = p;
                }
            }
        }
        return winner;
    }

    /**
     * Go through and update all the players, thus updating the location of the turtles.
     */
    public void updatePlayers(){
        for(Player p: players.getPlayerList()){
            p.update();
        }
    }

    // TODO: Add capacity for turtle to "bounce off" wall at same angle
    public void checkObjectLocation(GameObject obj){
        Location currentLocation = obj.getCurrentLocation();
        if (currentLocation.getXLocation() < 0) {
            obj.setCurrentLocation(new Location(0,currentLocation.getYLocation()));
        }
        if (currentLocation.getXLocation() >= board.getWidth()- ViewApplication.IMAGE_SIZE){
            obj.setCurrentLocation(new Location(board.getWidth()- ViewApplication.IMAGE_SIZE, currentLocation.getYLocation()));
        }
        if (currentLocation.getYLocation() < 0){
            obj.setCurrentLocation(new Location(currentLocation.getXLocation(), 0));
        }
        if (currentLocation.getYLocation() >= board.getHeight()- ViewApplication.IMAGE_SIZE){
            obj.setCurrentLocation(new Location(currentLocation.getXLocation(), board.getHeight()- ViewApplication.IMAGE_SIZE));
        }
    }
}
