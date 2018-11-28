package main;

import main.Command;

import java.util.ArrayList;

/**
 * Class for Player data and associated objects. Each Player includes data about the GameObjects controlled by player,
 * as well as Player-specific information like the Name and Color. Also acts as an Observer, which implements an update
 * function to be called on each step of the program.
 */
public class Player implements Observer{
    /** The name of the player */
    private String playerName;
    /** The color associated with the player, in the form of a hex string, eg 0xFFFFFF */
    private String playerColor;
    /** The Turtle object of the player */
    private Turtle playerTurtle;
    /** The List of Line objects controlled by the player */
    private ArrayList<Line> playerLines = new ArrayList<>();
    /** Tracking the currentLine, so a new Line object isn't created with every step */
    private Line currentLine = null;

    /**
     * Create a new Player with the given name, and color, and create a new Turtle using the given Command object
     * and starting Location object.
     * @param name String of the name of the player
     * @param color Color, in the form of a hex string, eg 0xFFFFFF
     * @param commands The Command object to control the Player's Turtle
     * @param startLoc The starting Location object for the Player's Turtle
     */
    public Player(String name, String color, Command commands, Location startLoc){
        this.playerName = name;
        this.playerColor = color;
        playerTurtle = new Turtle(commands, startLoc, playerColor);
    }

    /**
     * Create a new Player with the given name, and color, and create a new Turtle using the given Command object
     * @param name String of the name of the player
     * @param color Color, in the form of a hex string, eg 0xFFFFFF
     * @param commands The Command object to control the Player's Turtle
     */
    public Player(String name, String color, Command commands){
        this.playerName = name;
        this.playerColor = color;
        this.playerTurtle = new Turtle(commands, playerColor);
    }

    /**
     * Return the Player name
     * @return the Player name
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Get the Player color
     * @return the player color
     */
    public String getPlayerColor(){
        return playerColor;
    }

    /**
     * Get the Player Turtle
     * @return the Turtle controlled by the Player
     */
    public Turtle getPlayerTurtle(){
        return playerTurtle;
    }

    /**
     * Get the arraylist of Line objects controlled by the players
     * @return ArrayList of Line objects
     */
    public ArrayList<Line> getPlayerLines(){ return playerLines;}

    /**
     * Get all the GameObjects controlled by the Player, including the Turtle and the Line objects.
     * @return ArrayList containing GameObject objects owned by the players
     */
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
        if (playerTurtle.getCurrentLocation().distance(checkLocation) < tolerance/2){
            System.out.println("Turtle at location "+checkLocation+"is inside turtle "+playerTurtle.getCurrentLocation());
            return true;
        }
        for(Line line:playerLines){
            if (line.checkLocation(checkLocation)){
                System.out.println("Turtle at location "+checkLocation+"is inside line "+line.getCurrentLocation());

                return true;
            }
        }
        return false;
    }

    /**
     * Call update on the player's turtle, and update list of lines controlled by that player if the turtle moved.
     */
    public void update(){
        Location turtStartLoc = new Location(playerTurtle.getCurrentLocation());
        boolean moved = playerTurtle.update();
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
            playerLines.set(playerLines.size()-1, currentLine);
        }
    }

    /**
     * Reset the current Line and start a new Line object.
     */
    public void resetLine(){
        currentLine = null;
    }

    /**
     * Return the player to the starting state, by removing all Lines and setting the Turtle location to startLoc.
     * @param startLoc The Location to set the Turtle to.
     */
    public void resetPlayer(Location startLoc){
        playerLines.clear();
        playerTurtle.setCurrentLocation(startLoc);
        currentLine = null;
    }

    /**
     * Make a string containing the Player information.
     * @return String with Player information
     */
    public String toString(){
        String retString = playerName + "("+playerColor+"): "+playerTurtle.getTurtleCommands().toString();
        return retString;
    }

}
