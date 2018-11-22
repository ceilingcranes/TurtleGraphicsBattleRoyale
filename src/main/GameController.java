package main;

import java.util.ArrayList;

/**
 * Provides access to the gameboard and the player list.
 * @Author Maxine Hartnett
 */
public class GameController {
    // TODO: Implement observer pattern that goes through and updates each Player to get the new location of all
    // GameObjects.
    private PlayerList players;
    private GameBoard board = new GameBoard(100,100);

    public GameController(){
        players = new PlayerList();
    }

    /**
     * @return PlayerList for game
     */
    public PlayerList getPlayers(){
        return this.players;
    }

    public ArrayList<GameObject> getObjects(){
        // call player.update, get objects from each player, then return the full list. The
        // view controller then gets the location and color of each gameobject and uses this to
        // create a new "step" of the board.
        return null;
    }

}
