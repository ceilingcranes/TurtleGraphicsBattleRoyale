package main;

import java.util.ArrayList;

public class GameBoard {
    private int numPlayers;
    private ArrayList<Player> playerList;

    public GameBoard(){
        numPlayers = 0;
        playerList = new ArrayList<>();
    }
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
    public void addPlayer(String playerName, Command playerCommands){
        // create a new player, add name, color, and commands
        Player player = new Player(playerName, "red", playerCommands);
        System.out.println("Adding Player to list: "+player.toString());

        this.playerList.add(player);
    }
    public Player getPlayer(int index){
        return playerList.get(index);
    }

    public void removePlayer(int index){
        playerList.remove(index);
    }
}
