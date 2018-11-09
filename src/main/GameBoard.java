package main;

import java.util.ArrayList;

public class GameBoard {
    private int numPlayers=0;
    private ArrayList<Player> playerList;

    public int getNumPlayers() {
        return numPlayers;
    }

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
        System.out.println("Numplayers updated to "+numPlayers);
    }

//    public void addPlayer(Player player){
//        playerList.add(player);
//    }
    public void addPlayer(String playername, Command playerCommands){
        // create a new player, add name, color, and commands
        Player player = new Player(playername, "red", playerCommands);
        System.out.println("Adding Player to list: "+player.toString());

        this.playerList.add(player);
    }
}
