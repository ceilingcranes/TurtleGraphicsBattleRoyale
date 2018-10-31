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

    public void addPlayer(Player player){
        playerList.add(player);
    }

}
