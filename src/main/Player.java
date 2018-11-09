package main;

import main.Command;

public class Player {
    String playerName;
    String playerColor; // should this be a string?
    Command commands;
    public Player(String name, String color, Command commands){
        this.playerName = name;
        this.commands =commands;
        this.playerColor = color;
    }

    public String toString(){
        String retString = playerName + "("+playerColor+"): "+commands.toString();
        return retString;
    }

}