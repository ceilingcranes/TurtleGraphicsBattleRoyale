package main;

import java.util.ArrayList;

/**
 * A GameObject which represents a turtle, which will move around using commands stored in a Command object and
 * change location accordingly.
 */
public class Turtle extends GameObject {
    private Command turtleCommands;
    private int stepsToTake = 0;

    /**
     * Create a new turtle object at default location (0,0)
     * @param commands Command object to control the turtle
     * @param color The color of the turtle as a hex string (e.g. 0xFFFFFF)
     */
    public Turtle(Command commands, String color){
        objectColor = color;
        turtleCommands = commands;
        currentLocation = new Location(0,0);
    }

    /**
     * Create a new turtle object.
     * @param commands Command object to control turtle's movement
     * @param startLocation A Location object containing the starting location of the turtle
     * @param color The color of the turtle as a hex string (e.g. 0xFFFFFF)
     */
    public Turtle(Command commands, Location startLocation, String color){
        objectColor = color;
        turtleCommands = commands;
        currentLocation = startLocation;
    }

    /**
     * Update the turtle's location by either taking a step within the current MOVE command, or by going to the next
     * command and updating the orientation/steps left to take.
     * @return true if the turtle moved in space, or false if the turtle didn't move (e.g. turned)
     */
    public boolean update(){
        if (stepsToTake == 0) {
            LocationChange nextLoc = turtleCommands.getNextLocation();
            stepsToTake = nextLoc.getMove();
            currentLocation.updateOrientation(nextLoc.getTurn());
        }
        if (stepsToTake>0){
            currentLocation.takeStep(GameController.STEPSIZE);
            stepsToTake--;
            return true;
        }
        return false;
    }

    /**
     * Get the Command object associated with the turtle
     * @return Command object containing commands controlling that turtle
     */
    public Command getTurtleCommands(){
        return turtleCommands;
    }

    /**
     * Set the commands for a Turtle
     * @param c A Command object which will be used to control the turtle.
     */
    public void setTurtleCommands(Command c){
        turtleCommands = c;
        stepsToTake = 0;
    }
}
