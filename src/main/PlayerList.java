package main;

import java.util.ArrayList;

/**
 * Tracks methods for adding, removing, and accessing Player objects.
 * @Author Maxine Hartnett
 */
public class PlayerList {

    private int numPlayers;
    private ArrayList<Player> playerList;
    // Red, Green, Blue, Yellow
    private final String[] PLAYER_COLORS = new String[]{"0xff8f80", "0xa3d977", "0x5abaa7", "0xffdf71"};

    public PlayerList(){
        numPlayers = 0;
        playerList = new ArrayList<>();
        System.out.println("Initializing list");
    }

    /**
     * Get the number of players.
     * @return number of players
     */
    public int getNumPlayers() {
        return numPlayers;
    }

    /**
     * Create and add a new Player, automatically setting the color and location.
     * @param playerName The name of the player
     * @param playerCommands The Command object containing the entered commands for a player.
     */
    public void addPlayer(String playerName, Command playerCommands){
        // create a new player, add name, color, and commands
        String color = PLAYER_COLORS[playerList.size()];

        Player player = new Player(playerName, color, playerCommands, new Location(0,0));
        System.out.println("Adding Player to list: "+player.toString());

        this.playerList.add(player);
        this.numPlayers++;
    }

    /**
     * Create and add multiple players, automatically setting color and location.
     * @param playerNames List of player names
     * @param playerCommands List of Command objects representing player commands
     */
    public void addPlayers(String[] playerNames, Command[] playerCommands){
        for(int i = 0; i< playerNames.length; i++){
            Location startLoc = new Location((int)(ViewApplication.BOARDSIZE/2),
                    (int)(ViewApplication.BOARDSIZE/2));
            Player newPlayer = new Player(playerNames[i], PLAYER_COLORS[i], playerCommands[i], startLoc);
            System.out.println("Adding new player "+newPlayer.toString());
            this.playerList.add(newPlayer);
        }
        this.numPlayers+= playerNames.length;
    }

    /**
     * Get a player object
     * @param index The index of the player
     * @return Given Player object
     */
    public Player getPlayer(int index){
        return playerList.get(index);
    }


    /**
     * Delete a player from the player list.
     * @param index Index of the Player to remove
     */
    public void removePlayer(int index){
        playerList.remove(index);
    }

    public ArrayList<Player> getPlayerList(){
        return playerList;
    }

    public String[] getPlayerColors(){
        return this.PLAYER_COLORS;
    }
}
