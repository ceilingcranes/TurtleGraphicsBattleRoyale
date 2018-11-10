package test;

import main.Command;
import main.GameBoard;
import main.Player;
import org.testng.Assert;

import java.util.ArrayList;

import static org.testng.Assert.*;

public class GameBoardTest {
    GameBoard gb = new GameBoard();
    @org.testng.annotations.Test
    public void testGetNumPlayers() {
        int numPlayers = gb.getNumPlayers();
        Assert.assertEquals(numPlayers, 0);
    }

    @org.testng.annotations.Test
    public void testSetNumPlayers() {
        gb.setNumPlayers(4);
        int numPlayers = gb.getNumPlayers();
        Assert.assertEquals(numPlayers, 4);
    }


    @org.testng.annotations.Test
    public void testAddPlayer() {
        String playerName = "test1";
        Command commands = new Command("move 1");
        gb.addPlayer(playerName, commands);

        Player expectedPlayer = new Player(playerName, "red", commands); // Will have to change this if color picker changes
        Player gotPlayer = gb.getPlayer(0);
        Assert.assertEquals(expectedPlayer.getPlayerName(), gotPlayer.getPlayerName());
        gb.removePlayer(0);
    }

    @org.testng.annotations.Test
    public void testGetPlayer(){
        String playerName = "test1";
        Command commands = new Command("move 1");
        String playerName2 = "test2";
        Command commands2 = new Command("move 2");

        gb.addPlayer(playerName, commands);
        gb.addPlayer(playerName2, commands2);

        Player expectedPlayer = new Player(playerName, "red", commands); // Will have to change this if color picker changes
        Player gotPlayer = gb.getPlayer(0);
        Assert.assertEquals(expectedPlayer.getPlayerName(), gotPlayer.getPlayerName());

        Player expectedPlayer2 = new Player(playerName2, "blue", commands);
        Player gotPlayer2 = gb.getPlayer(1);
        Assert.assertEquals(expectedPlayer2.getPlayerName(), gotPlayer2.getPlayerName());

    }



}