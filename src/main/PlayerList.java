package main;

import java.util.ArrayList;

public class PlayerList {

    private int numPlayers;
    private ArrayList<Player> playerList;
    private final String[] PLAYER_COLORS = new String[]{"red", "green", "blue", "yellow"};

    public PlayerList(){
        numPlayers = 0;
        playerList = new ArrayList<>();
        System.out.println("Initializing list");
    }
    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        System.out.println("Numplayers updated to "+numPlayers);
    }

    public void addPlayer(String playerName, Command playerCommands){
        // create a new player, add name, color, and commands
        String color = PLAYER_COLORS[playerList.size()];

        Player player = new Player(playerName, color, playerCommands, new Location(0,0));
        System.out.println("Adding Player to list: "+player.toString());

        this.playerList.add(player);
    }

    public void addPlayers(String[] playerNames, Command[] playerCommands){
        for(int i = 0; i< playerNames.length; i++){
            Player newPlayer = new Player(playerNames[i], PLAYER_COLORS[i], playerCommands[i]);
            System.out.println("Adding new player "+newPlayer.toString());
            this.playerList.add(newPlayer);
        }
    }

    public Player getPlayer(int index){
        return playerList.get(index);
    }

    public void removePlayer(int index){
        playerList.remove(index);
    }
}
