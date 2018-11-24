package main;

import main.Command;

import java.util.ArrayList;

public class Player {
    private String playerName;
    private String playerColor;
    private Turtle playerTurtle;
    private ArrayList<Line> playerLines = new ArrayList<>();
    private Line currentLine = null;

    public Player(String name, String color, Command commands, Location startLoc){
        this.playerName = name;
        this.playerColor = color;
        playerTurtle = new Turtle(commands, startLoc, playerColor);
    }

    public Player(String name, String color, Command commands){
        this.playerName = name;
        this.playerColor = color;
        this.playerTurtle = new Turtle(commands, playerColor);
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

    public ArrayList<Line> getPlayerLines(){ return playerLines;}

    public ArrayList<GameObject> getPlayerObjects(){
        ArrayList<GameObject> objList = new ArrayList<>();
        objList.add(playerTurtle);
        objList.addAll(playerLines);
        return objList;
    }

    /**
     * Check to see if a given location is controlled by a player, within STEPSIZE units.
     * @param checkLocation Location to check
     * @return true if Location checklocation falls on an area controlled by player, within 0.1 units.
     */
    public boolean checkLocation(Location checkLocation){
        int tolerance = GameController.STEPSIZE;
        if (playerTurtle.getCurrentLocation().distance(checkLocation) < tolerance){
            return true;
        }
        for(Line line:playerLines){
            if (line.checkLocation(checkLocation)){
                return true;
            }
        }
        return false;
    }

    /**
     * Call update on the player's turtle, and update list of lines controlled by that player if the turtle moved.
     */
    public void update(){
        Location turtStartLoc = new Location(playerTurtle.getCurrentLocation().getXLocation(), playerTurtle.getCurrentLocation().getYLocation());
        boolean moved = playerTurtle.update();
//        if (turtStartLoc.compareTo(playerTurtle.getCurrentLocation())==0){
        if (!moved){
            currentLine = null;
        }
        else{
            // Start a new line, since the turtle turned
            if (currentLine == null){
                currentLine = new Line(turtStartLoc, playerTurtle.getCurrentLocation(), playerColor);
                playerLines.add(currentLine);
            }
            currentLine.setEndLocation(new Location(playerTurtle.getCurrentLocation()));
            System.out.println("Updating end location of "+currentLine);
            playerLines.set(playerLines.size()-1, currentLine);
            System.out.println("Updated playerLines: "+playerLines);
        }
    }

    public String toString(){
        String retString = playerName + "("+playerColor+"): "+playerTurtle.getTurtleCommands().toString();
        return retString;
    }

}
