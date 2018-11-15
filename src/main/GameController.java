package main;

import java.util.ArrayList;

public class GameController {
    private PlayerList players;
    private GameBoard board = new GameBoard(100,100);

    public GameController(){
        players = new PlayerList();
    }

    public PlayerList getPlayers(){
        return this.players;
    }



}
