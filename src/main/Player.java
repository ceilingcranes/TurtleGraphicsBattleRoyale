package main;

import main.Command;

public class Player {
    String playerName;
    String playerColor;
    Turtle playerTurtle;

    public Player(String name, String color, Command commands, Location startLoc){
        this.playerName = name;
        this.playerColor = color;
        playerTurtle = new Turtle(commands, startLoc);
    }

    public Player(String name, String color, Command commands){
        this.playerName = name;
        this.playerColor = color;
        this.playerTurtle = new Turtle(commands);
    }

    public String getPlayerName(){
        return playerName;
    }

    public String getPlayerColor(){
        return playerColor;
    }

    public void setPlayerTurtle(Turtle turt){
        playerTurtle = turt;
    }

    public Turtle getPlayerTurtle(){
        return playerTurtle;
    }

    public String toString(){
        String retString = playerName + "("+playerColor+"): "+playerTurtle.getTurtleCommands().toString();
        return retString;
    }

}
